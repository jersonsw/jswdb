import {withNamespaces} from "react-i18next";
import React from "react";
import {Col, FormGroup, Input, Label, Row} from "reactstrap";
import CheckboxNumberSeries from "./CheckboxNumberSeries";

class HoursForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
        this.hoursArray = Array.from(Array(24).keys());
    }

    lpad(value, pad, times) {
        return pad.repeat(times - (value + '').length) + value;
    }

    buildHoursSelect(name, start = 1) {
        return (
            <select name={name} id={name}>
                {this.hoursArray.map(e => <option key={e + start}
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
                            <Input type="radio" name="hoursFreq" defaultChecked/>{' '}
                            Every hour
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="hoursFreq"/>{' '}
                            Every &nbsp;
                            {this.buildHoursSelect("everyHours")}
                            &nbsp; hour(s) starting at hour &nbsp;
                            {this.buildHoursSelect("startingAtHour", 0)}
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="hoursFreq"/>{' '}
                            Every hour between hour&nbsp;
                            {this.buildHoursSelect("startingAtHour", 0)}
                            &nbsp; and hour &nbsp;
                            {this.buildHoursSelect("startingAtHour", 0)}
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="hoursFreq"/>{' '}
                            Specific hour (choose one or many) &nbsp;
                            <Row className={'mt-2'}>
                                <Col className={'m-0'} lg={8} md={10} sm={12} xs={12}>
                                    <Row>
                                        <CheckboxNumberSeries key={'hoursSeries'} size={24} onChange={this.handleCheck}/>
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

export default withNamespaces('translations')(HoursForm);