import {withNamespaces} from "react-i18next";
import React from "react";
import {Col, Row} from "reactstrap";
import ContentWrapper from "../Layout/ContentWrapper";
import CronBuilder from "./CronBuilder";

class Scheduler extends React.Component {


    constructor(props) {
        super(props)
        this.state = {
            activeTab: 'seconds'
        }

    }

    setActiveTab(tabName){
        this.setState({activeTab: tabName});
    }

    render() {
        return (
            <ContentWrapper>
                <div className="content-heading">
                    Scheduler
                </div>
                <Row>
                    <Col>
                        <CronBuilder/>
                    </Col>
                </Row>
            </ContentWrapper>
        );
    }

}

export default withNamespaces('translations')(Scheduler);