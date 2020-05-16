import React from 'react';
import ContentWrapper from '../Layout/ContentWrapper';
import {Button, Col, Form, FormGroup, InputGroup, InputGroupAddon, InputGroupText, Row, Table} from 'reactstrap';
import Stomp from 'stompjs/';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import * as SockJS from 'sockjs-client/dist/sockjs.min';
import * as moment from 'moment';

let stompClient = null;

class Movies extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            dateRange: {
                start: moment().startOf('year').toDate(),
                end: moment().endOf('year').toDate()
            },
            movies: [],
            event: {
                status: null,
                message: ''
            },

            connected: false,
            connecting: false,
            job: {
                status: null
            },
            progress: {
                recordsCount: null,
                recordsWritten: null
            }
        };

        this.onConnected = this.onConnected.bind(this);
        this.onError = this.onError.bind(this);
        this.updateMovies = this.updateMovies.bind(this);
        this.updateEvents = this.updateEvents.bind(this);
        this.runCrawler = this.runCrawler.bind(this);
        this.reconnect = this.reconnect.bind(this);
        this.disconnect = this.disconnect.bind(this);
        this.reconnectOrDisconnect = this.reconnectOrDisconnect.bind(this);
        this.updateProgress = this.updateProgress.bind(this);
        this.resetValues = this.resetValues.bind(this);
    }

    runCrawler(event) {
        event.preventDefault();
        this.resetValues();
        const {movies: [], dateRange: {start: startDate, end: endDate}} = this.state;
        stompClient.send('/app/jobs/run', {}, JSON.stringify({
            startDate: moment(startDate).format('YYYY-MM-DD'),
            endDate: moment(endDate).format('YYYY-MM-DD')
        }));
    }

    componentDidMount() {
        if (stompClient) {
            stompClient.disconnect();
            stompClient = null;
        }
        this.connect();
    }

    resetValues(){
        this.setState({
            movies: [],
            progress: {
                recordsCount: null,
                recordsWritten: null
            },
            event: {
                status: null,
                message: ''
            }
        });
    }

    reconnect() {
        const {connected, connecting} = this.state;
        if (!connected && !connecting) {
            stompClient = null;
            this.connect();
        }
    }

    connect() {
        if (!stompClient) {
            this.setState({connecting: true});
            const socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.debug = null;
            stompClient.connect({}, this.onConnected, this.onError);
        }
    }

    disconnect() {
        if (stompClient) {
            stompClient.disconnect();
            stompClient = null;
        }
        this.setState({
            event: {status: null, message: 'The connection with the server has been loosed'},
            connected: false,
            connecting: false
        });
    }

    reconnectOrDisconnect(connect) {
        if (connect) {
            this.reconnect()
        } else {
            stompClient.send('/app/jobs/stop', {});
            this.disconnect();
        }
    }

    onConnected(resp) {
        if (stompClient.ws._transport.url) {
            console.log('Transport URL:', stompClient.ws._transport.url);
        }
        this.setState({
            connecting: false,
            connected: true,
            event: {status: null, message: 'Connection established with the server'}
        });
        stompClient.subscribe('/topic/events', this.updateEvents);
        stompClient.subscribe('/topic/progress', this.updateProgress);
        stompClient.subscribe('/topic/savedMovies', this.updateMovies);
        stompClient.subscribe('/topic/failedMovies', (resp) => {
            console.log('Failed movies:', resp);
        });
    }

    updateProgress(response) {
        if (response.body) {
            this.setState({
                progress: JSON.parse(response.body)
            });
        }
    }

    updateEvents(response) {
        const event = JSON.parse(response.body);
        this.setState({event: event});
    }

    updateMovies(response) {
        const newMovies = JSON.parse(response.body);
        if (newMovies && newMovies.length > 0) {
            const {movies} = this.state;
            if (newMovies.length > 0) {
                newMovies.forEach(m => movies.unshift(m));
            }
            if (movies.length === 20) {
                movies.pop();
            }
            this.setState({movies: movies});
        }
    }

    onError(err) {
        console.log(err);
        this.setState({
                event: {status: null, message: 'The connection with the server has been loosed'},
                connected: false,
                connecting: false
            }
        );
    }

    render() {
        const {movies, connected, connecting, dateRange, job, event, progress} = this.state;
        const recordsLeft = progress.recordsCount - progress.recordsWritten;
        const startTime = moment(progress.startTime);
        const endTime = moment(progress.endTime);
        const duration = moment.duration(endTime.diff(startTime));
        const elapsedTime = duration.asSeconds().toFixed(0);
        const elapsedStr = parseInt(elapsedTime / 3600) + 'h ' + parseInt((elapsedTime % 3600) / 60) + 'm ' + (parseInt((elapsedTime % 3600) % 60)) + 's';
        const secondsPerRecord = parseInt(elapsedTime / progress.recordsWritten);
        const timeLeftSeconds = secondsPerRecord * recordsLeft;
        const timeLeftStr = parseInt(timeLeftSeconds / 3600) + 'h ' + parseInt((timeLeftSeconds % 3600) / 60) + 'm ' + (parseInt((timeLeftSeconds % 3600) % 60)) + 's';

        return (
            <ContentWrapper>
                <div className="content-heading">
                    Movies
                    <div className={'ml-auto'}>
                        <span
                            className={'badge badge-' + (connecting ? 'warning' : (connected ? 'success' : 'danger'))}>
                            {connecting ? 'Connecting...' : connected ? 'Connected' : 'Disconnected'}
                        </span>
                        {!connecting &&
                        <Button onClick={() => this.reconnectOrDisconnect(!connected)} className={'ml-5'}
                                size={'xs'} color={'secondary'}>
                            {connected && <i className="fa fa-sign-out-alt text-danger"></i>}
                            {!connected && <i className="fa fa-plug text-success"></i>}
                        </Button>}
                    </div>
                </div>
                <Row>
                    <Col>
                        <Form>
                            <Row>
                                <Col lg={2}>
                                    <FormGroup>
                                        <InputGroup>
                                            <InputGroupAddon addonType={'prepend'}>
                                                <InputGroupText>
                                                    Start Date
                                                </InputGroupText>
                                                <DatePicker
                                                    className={'form-control'}
                                                    selected={dateRange.start}
                                                    onChange={date => {
                                                        this.setState({
                                                            dateRange: {
                                                                start: date,
                                                                end: dateRange.end
                                                            }
                                                        });
                                                    }}
                                                />
                                            </InputGroupAddon>
                                        </InputGroup>
                                    </FormGroup>
                                </Col>
                                <Col lg={2}>
                                    <FormGroup>
                                        <InputGroup>
                                            <InputGroupAddon addonType={'prepend'}>
                                                <InputGroupText>
                                                    End Date
                                                </InputGroupText>
                                                <DatePicker
                                                    className={'form-control'}
                                                    selected={dateRange.end}
                                                    onChange={date => {
                                                        this.setState({
                                                            dateRange: {
                                                                start: dateRange.start,
                                                                end: date
                                                            }
                                                        });
                                                    }}
                                                />
                                            </InputGroupAddon>
                                        </InputGroup>
                                    </FormGroup>
                                </Col>
                                <Col lg={2}>
                                    <Button disabled={!connected || connecting || job.status === 'JOB_RUNNING'}
                                            color={'success'}
                                            onClick={this.runCrawler}>Run Crawler</Button>
                                </Col>
                                <Col>
                                    {event.message}
                                </Col>
                            </Row>
                        </Form>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <div>
                            {progress.recordsCount && progress.recordsWritten && <h4 className={'mt-2 mb-2'}>
                                {progress.recordsWritten.toLocaleString()} / {progress.recordsCount.toLocaleString()} ({parseFloat((progress.recordsWritten / progress.recordsCount) * 100).toFixed(2)}%)
                                <span className={'text-muted mr-3 ml-3'}>|</span> Elapsed: <span
                                className={'text-info'}>{elapsedStr}</span><span
                                className={'text-muted mr-3 ml-3'}>|</span>Left: <span
                                className={'text-info'}>{timeLeftStr}</span>
                                <div className="progress progress-sm mt-3">
                                    <div
                                        className={"progress-bar progress-bar-striped progress-bar-animated bg-" + (connected ? 'success' : (connecting ? 'warning' : 'danger'))}
                                        role="progressbar"
                                        style={{width: (+progress.recordsWritten / +progress.recordsCount) * 100 + '%'}}>
                                    <span
                                        className="sr-only">{((progress.recordsWritten / progress.recordsCount) * 100).toString()}</span>
                                    </div>
                                </div>
                            </h4>
                            }
                        </div>
                        <Table>
                            <thead>
                            <tr>
                                <th>Image</th>
                                <th>Title</th>
                                <th>Year</th>
                                <th>Rating AVG</th>
                                <th>Rating Count</th>
                                <th>Metascore</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                movies && movies.length > 0 && movies.map(m =>
                                    <tr key={m.id}>
                                        <td>{m.profileImg &&
                                        <img src={m.profileImg + '._V1_UX20_CR,0,20,20_AL_.jpg'} alt={m.title}/>}</td>
                                        <td>{m.title}</td>
                                        <td>{m.year}</td>
                                        <td className={'text-center'}>
                                            {m.ratingAvg.toLocaleString()}
                                            <div className="progress progress-xs">
                                                <div className={"progress-bar progress-bar bg-" +
                                                (m.ratingAvg >= 8 ? 'success' : (m.ratingAvg >= 7.5 ? 'warning' : 'danger'))}
                                                     role="progressbar" style={{width: (m.ratingAvg * 10) + '%'}}>
                                                    <span className="sr-only">{m.ratingAvg}</span>
                                                </div>
                                            </div>
                                        </td>
                                        <td>{m.ratingCount.toLocaleString()}</td>
                                        <td>{m.metaScore}</td>
                                    </tr>
                                )
                            }
                            {
                                (!movies || movies.length === 0) && <tr>
                                    <td colSpan={4} className={'text-center'}></td>
                                </tr>
                            }
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </ContentWrapper>
        );
    }
}

export default Movies;
