import React from "react";
import {Col, FormGroup, Input, Label} from "reactstrap";
import {withNamespaces} from "react-i18next";

class CheckboxNumberSeries extends React.Component {

    lpad(value, pad, times) {
        return pad.repeat(times - (value + '').length) + value;
    }

    handleCheck(e) {
        this.props.onChange(e.currentTarget.value);
    }

    render() {
        return (
            <>
                {
                    Array.from(Array(this.props.size).keys()).map(s =>
                        <Col lg={1} md={2} sm={2} xs={2} key={s}>
                            <FormGroup check>
                                <Label check>
                                    <Input key={s} type="checkbox" value={s}
                                           onChange={(s) => this.handleCheck(s)} />
                                    {this.lpad(s, '0', 2)}
                                </Label>
                            </FormGroup>
                        </Col>
                    )
                }
            </>
        )
    }
}


export default withNamespaces('translations')(CheckboxNumberSeries);