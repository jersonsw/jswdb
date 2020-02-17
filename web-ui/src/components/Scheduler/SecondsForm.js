import {withNamespaces} from "react-i18next";
import React from "react";
import {Col, FormGroup, Input, Label, Row} from "reactstrap";
import CheckboxNumberSeries from "./CheckboxNumberSeries";

class SecondsForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedFreq: 'everySecond',
            checkedValues: []
        };
        this.specificSecondsRef = React.createRef();
        this.secondsArray = Array.from(Array(60).keys());
    }

    lpad(value, pad, times) {
        return pad.repeat(times - (value + '').length) + value;
    }

    buildSecondsSelect(name, start = 1) {
        return (
            <select name={name} id={name}>
                {this.secondsArray.map(e =>
                    <option key={e + start} value={e + start}>
                        {this.lpad(e + start, '0', 2)}
                    </option>
                )}
            </select>
        );
    }

    setSelectedFreq(freq) {
        this.setState({
            selectedFreq: freq
        });
    }

    handleCheck(e) {
        console.log('Info: ', e);
        let checkedValues = [...this.state.checkedValues];
        if (checkedValues.indexOf(e) < 0) {
            checkedValues.push(e);
        }else{
            checkedValues = checkedValues.filter(v => v !== e);
        }
        if (checkedValues.length > 0) {
            this.setSelectedFreq('specificSeconds');
        }
        this.setState({
            checkedValues: checkedValues
        });
    }

    handleRadioChange(e) {
        const freq = e.currentTarget.value;
        if (freq && freq !== 'specificSeconds') {
            this.setState({
                checkedValues: []
            });
        }
        this.setSelectedFreq(freq);
    }

    render() {
        return (
            <FormGroup tag={"fieldset"} className={'mt-2'} row>
                <Col>
                    <FormGroup check>
                        <Label check>
                            <Input type="radio"
                                   name="secondsFreq"
                                   onChange={(e) => this.handleRadioChange(e)}
                                   value={'everySecond'}
                                   checked={this.state.selectedFreq === 'everySecond'}
                            />{' '}
                            Every second
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="secondsFreq" onChange={(e) => this.handleRadioChange(e)}
                                   value={'everyNSeconds'} checked={this.state.selectedFreq === 'everyNSeconds'}/>{' '}
                            Every &nbsp;
                            {this.buildSecondsSelect("everySeconds")}
                            &nbsp; second(s) starting at second &nbsp;
                            {this.buildSecondsSelect("startingAtSecond", 0)}
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="secondsFreq" onChange={(e) => this.handleRadioChange(e)}
                                   value={'everySecondsInRange'}
                                   checked={this.state.selectedFreq === 'everySecondsInRange'}/>{' '}
                            Every second between second&nbsp;
                            {this.buildSecondsSelect("startingAtSecond", 0)}
                            &nbsp; and second &nbsp;
                            {this.buildSecondsSelect("startingAtSecond", 0)}
                        </Label>
                    </FormGroup>
                    <FormGroup check className={'mt-4 mb-2'}>
                        <Label check>
                            <Input type="radio" name="secondsFreq" onChange={(e) => this.handleRadioChange(e)}
                                   value={'specificSeconds'}
                                   checked={this.state.selectedFreq === 'specificSeconds'}/>{' '}
                            Specific second (choose one or many) &nbsp;
                        </Label>
                        <Row className={'mt-2'}>
                            <Col lg={8} md={10} sm={12}>
                                <Row>
                                    <CheckboxNumberSeries
                                        size={60}
                                        onChange={(s) => this.handleCheck(s)}
                                        checkedValues={this.state.checkedValues}
                                    />
                                </Row>
                            </Col>
                        </Row>
                    </FormGroup>
                </Col>
            </FormGroup>
        );
    }
}

export default withNamespaces('translations')(SecondsForm);