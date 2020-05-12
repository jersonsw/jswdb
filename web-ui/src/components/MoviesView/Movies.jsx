import React from 'react';
import ContentWrapper from '../Layout/ContentWrapper';
import {Button, Col, Form, FormGroup, InputGroup, InputGroupAddon, InputGroupText, Row, Table} from 'reactstrap';
import Stomp from 'stompjs/';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import * as SockJS from 'sockjs-client/dist/sockjs.min';
import * as moment from 'moment';
import MoviesHeader from "./MoviesHeader";

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
                stopping: false,
                running: false,
                stopped: true
            },
            progress: {
                recordsCount: null,
                recordsWritten: null,
                startTime: null,
                endTime: null
            }
        };

        this.onConnected = this.onConnected.bind(this);
        this.onError = this.onError.bind(this);
        this.updateMovies = this.updateMovies.bind(this);
        this.updateEvents = this.updateEvents.bind(this);
        this.runCrawler = this.runCrawler.bind(this);
        this.stopCrawler = this.stopCrawler.bind(this);
        this.reconnect = this.reconnect.bind(this);
        this.disconnect = this.disconnect.bind(this);
        this.reconnectOrDisconnect = this.reconnectOrDisconnect.bind(this);
        this.updateProgress = this.updateProgress.bind(this);
        this.resetValues = this.resetValues.bind(this);
    }

    runCrawler(event) {
        event.preventDefault();
        this.resetValues();
        const {dateRange: {start: startDate, end: endDate}} = this.state;
        stompClient.send('/app/jobs/run', {}, JSON.stringify({
            startDate: moment(startDate).format('YYYY-MM-DD'),
            endDate: moment(endDate).format('YYYY-MM-DD')
        }));
    }

    stopCrawler(event) {
        console.log('Stopping crawler.....');
        this.setState({
            job: {
                running: true,
                stopping: true,
                stopped: false
            }
        });
        event.preventDefault();
        stompClient.send('/app/jobs/stop', {})
    }

    componentDidMount() {
        if (stompClient && stompClient.connected) {
            stompClient.disconnect();
            stompClient = null;
        }
        this.connect();
    }

    resetValues() {
        this.setState({
            movies: [],
            progress: {
                recordsCount: null,
                recordsWritten: null,
                startTime: null,
                endTime: null
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
            console.log('Disconnected');
        }
        this.setState({
            job: {
                stopping: false,
                running: false,
                stopped: true
            },
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
        console.log('Update progress...')
        if (response.body) {
            const {job: {stopping, stopped}} = this.state;
            this.setState({
                job: {running: !stopping, stopping: stopping, stopped: stopped},
                progress: JSON.parse(response.body)
            });
        }
    }

    updateEvents(response) {
        console.log('Update event...')
        const event = JSON.parse(response.body);
        let {job: {running, stopping}} = this.state;
        if(!stopping){
            running = (event.status !== 'JOB_STOPPED')
        }
        const stopped =(stopping && event.status === 'JOB_STOPPED')
        this.setState({
            event: event,
            job: {
                running: running,
                stopping: stopping,
                stopped: stopped
            }
        });
    }

    updateMovies(response) {
        console.log('Update movies...')
        const {job: {stopping, stopped}} = this.state;
        this.setState({job: {running: !stopping, stopping: stopping, stopped: stopped}});
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
        this.setState({
                job: {
                    running: false,
                    stopping: false,
                    stopped: true
                },
                event: {status: null, message: 'The connection with the server has been loosed'},
                connected: false,
                connecting: false
            }
        );
    }

    render() {
        const {movies, connected, connecting, dateRange, job, event, progress: {recordsCount, recordsWritten, startTime: st, endTime: et}} = this.state;
        const startTime = moment(st);
        const endTime = moment(et);
        const recordsLeft = recordsCount - recordsWritten;
        const duration = moment.duration(endTime.diff(startTime));
        const elapsedTime = duration.asSeconds().toFixed(0);
        const elapsedStr = parseInt(elapsedTime / 3600) + 'h ' + parseInt((elapsedTime % 3600) / 60) + 'm ' + (parseInt((elapsedTime % 3600) % 60)) + 's';
        const secondsPerRecord = parseInt(elapsedTime / recordsWritten);
        const timeLeftSeconds = secondsPerRecord * recordsLeft;
        const timeLeftStr = parseInt(timeLeftSeconds / 3600) + 'h ' + parseInt((timeLeftSeconds % 3600) / 60) + 'm ' + (parseInt((timeLeftSeconds % 3600) % 60)) + 's';

        return (
            <ContentWrapper>
                <MoviesHeader toggleConnection={this.reconnectOrDisconnect} connected={connected} connecting={connecting} />
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
                                    {console.log('Running - Stopping:', job.running, job.stopping)}
                                    {(!job.running && !job.stopping || job.stopped) && <Button disabled={!connected || connecting}
                                                             color={'success'}
                                                             onClick={this.runCrawler}>Run Crawler</Button>}
                                    {(job.running || job.stopping) && !job.stopped && <Button color={'danger'}
                                                            onClick={this.stopCrawler}>{job.stopping?'Stopping...':'Stop Crawler'}</Button>}
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
                            {recordsCount && recordsWritten && <h4 className={'mt-2 mb-2'}>
                                {`${recordsWritten.toLocaleString()} of ${recordsCount.toLocaleString()} titles loaded (${parseFloat((recordsWritten / recordsCount) * 100).toFixed(2)}%)`}
                                <span className={'text-muted mr-3 ml-3'}>|</span> Elapsed: <span
                                className={'text-info'}>{elapsedStr}</span><span
                                className={'text-muted mr-3 ml-3'}>|</span>Left: <span
                                className={'text-info'}>{timeLeftStr}</span>
                                <div className="progress progress-sm mt-3">
                                    <div
                                        className={"progress-bar progress-bar-striped progress-bar-animated bg-" + (connected ? 'success' : (connecting ? 'warning' : 'danger'))}
                                        role="progressbar"
                                        style={{width: (+recordsWritten / +recordsCount) * 100 + '%'}}>
                                    <span
                                        className="sr-only">{((recordsWritten / recordsCount) * 100).toString()}</span>
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
                                        <td style={{width: '120px'}}><img
                                            src={!!m.profileImg ? (m.profileImg + '._V1_UX100_CR,0,100,100_AL_.jpg') : 'https://via.placeholder.com/100'}
                                            alt={m.title}/></td>
                                        <td style={{width: '300px'}}>
                                            <p className={'text-bold'}>{m.title}</p>
                                            <p>{m.summary}</p>
                                        </td>
                                        <td>{m.year}</td>
                                        <td className={'text-center'}>
                                            {!!m.ratingAvg ? m.ratingAvg.toLocaleString() : ''}
                                            {!!m.ratingAvg && <div className="progress progress-xs">
                                                <div className={"progress-bar progress-bar bg-" +
                                                (!!m.ratingAvg && m.ratingAvg >= 8 ? 'success' : (m.ratingAvg >= 7.5 ? 'warning' : 'danger'))}
                                                     role="progressbar" style={{width: (m.ratingAvg * 10) + '%'}}>
                                                    <span className="sr-only">{m.ratingAvg}</span>
                                                </div>
                                            </div>}
                                            {!m.ratingAvg && 'Without rating'}
                                        </td>
                                        <td>{!!m.ratingCount ? m.ratingCount.toLocaleString() : ''}</td>
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
