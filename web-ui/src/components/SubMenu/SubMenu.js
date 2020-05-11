import React, {Component} from 'react';
import ContentWrapper from '../Layout/ContentWrapper';
import {Col, Row} from 'reactstrap';

class SubMenu extends Component {

    render() {
        return (
            <ContentWrapper>
                <div className="content-heading">
                    <div>
                        Sub Menu
                        <small>Subtitle</small>
                    </div>
                </div>
                <Row>
                   <Col lg={12}>
                      <p>A row with content</p>
                   </Col>
                </Row>
            </ContentWrapper>
        );
    }

}

export default SubMenu;
