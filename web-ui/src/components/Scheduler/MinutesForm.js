import {withNamespaces} from "react-i18next";
import React from "react";
import {Col, FormGroup, Input, Label, Row} from "reactstrap";
import CheckboxNumberSeries from "./CheckboxNumberSeries";

class MinutesForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
        this.minutesArray = Array.from(Array(60).keys());
    }

    lpad(value, pad, times) {
        return pad.repeat(times - (value + '').length) + value;
    }

    buildMinutesSelect(name, start = 1) {
        return (
            <select name={name} id={name}>
                {this.minutesArray.map(e => <option key={e + start}
                                                    value={e + start}>{this.lpad(e + start, '0', 2)}</option>)}
            </select>
        );
    }

    handleCheck(e){
        console.log(e);
    }

    render() {
        return (
            <FormGroup tag={"fieldset"} className={'mt-2'} row>
                <Col>
                    <FormGroup check>
                        <Label check>
                            <Input type="radio" name="minutesFreq" defaultChecked/>{' '}
                            Every minute
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="minutesFreq"/>{' '}
                            Every &nbsp;
                            {this.buildMinutesSelect("everyMinutes")}
                            &nbsp; minute(s) starting at minute &nbsp;
                            {this.buildMinutesSelect("startingAtMinute", 0)}
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="minutesFreq"/>{' '}
                            Every minute between minute&nbsp;
                            {this.buildMinutesSelect("startingAtMinute", 0)}
                            &nbsp; and minute &nbsp;
                            {this.buildMinutesSelect("startingAtMinute", 0)}
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="minutesFreq"/>{' '}
                            Specific minute (choose one or many) &nbsp;
                            <Row className={'mt-2'}>
                                <Col className={'m-0'} lg={8} md={10} sm={12} xs={12}>
                                    <Row>
                                        <CheckboxNumberSeries key={'minutesSeries'} size={60} onChange={this.handleCheck}/>
                                    </Row>
                                </Col>
                            </Row>
                        </Label>
                    </FormGroup>
                </Col>
            </FormGroup>
        );
    }
}

export default withNamespaces('translations')(MinutesForm);