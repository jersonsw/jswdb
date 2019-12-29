import React from 'react';
import {Nav, NavItem, NavLink, TabContent, TabPane} from "reactstrap";
import * as classnames from "classnames";
import {withNamespaces} from "react-i18next";
import SecondsForm from "./SecondsForm";
import MinutesForm from "./MinutesForm";
import HoursForm from "./HoursForm";

class CronBuilder extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            activeTab: 'seconds'
        }
    }

    setActiveTab(tabName) {
        this.setState({activeTab: tabName});
    }

    render() {
        return (
            <>
                <Nav tabs>
                    <NavItem className={'cursor-pointer'}>
                        <NavLink onClick={() => this.setActiveTab('seconds')}
                                 className={classnames({active: this.state.activeTab === 'seconds'})}>
                            Seconds
                        </NavLink>
                    </NavItem>
                    <NavItem className={'cursor-pointer'}>
                        <NavLink onClick={() => this.setActiveTab('minutes')}
                                 className={classnames({active: this.state.activeTab === 'minutes'})}>
                            Minutes
                        </NavLink>
                    </NavItem>
                    <NavItem className={'cursor-pointer'}>
                        <NavLink onClick={() => this.setActiveTab('hours')}
                                 className={classnames({active: this.state.activeTab === 'hours'})}>
                            Hours
                        </NavLink>
                    </NavItem>
                    <NavItem className={'cursor-pointer'}>
                        <NavLink onClick={() => this.setActiveTab('day')}
                                 className={classnames({active: this.state.activeTab === 'day'})}>
                            Day
                        </NavLink>
                    </NavItem>
                    <NavItem className={'cursor-pointer'}>
                        <NavLink onClick={() => this.setActiveTab('month')}
                                 className={classnames({active: this.state.activeTab === 'month'})}>
                            Month
                        </NavLink>
                    </NavItem>
                    <NavItem className={'cursor-pointer'}>
                        <NavLink onClick={() => this.setActiveTab('year')}
                                 className={classnames({active: this.state.activeTab === 'year'})}>
                            Year
                        </NavLink>
                    </NavItem>
                </Nav>
                <TabContent activeTab={this.state.activeTab}>
                    <TabPane tabId={"seconds"}><SecondsForm/></TabPane>
                    <TabPane tabId={"minutes"}><MinutesForm/></TabPane>
                    <TabPane tabId={"hours"}><HoursForm/></TabPane>
                    <TabPane tabId={"day"}></TabPane>
                    <TabPane tabId={"month"}></TabPane>
                    <TabPane tabId={"year"}></TabPane>
                </TabContent>
            </>
        );
    }

}

export default withNamespaces('translations')(CronBuilder);