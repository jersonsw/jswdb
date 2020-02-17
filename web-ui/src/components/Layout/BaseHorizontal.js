import React, { Component } from 'react';
import $ from '../Common/wrapper.js';

import HeaderHorizontal from './HeaderHorizontal'
import Offsidebar from './Offsidebar'
import Footer from './Footer'

class Base extends Component {

    componentWillMount() {
        $('body').addClass('layout-h');
    }

    componentWillUnmount() {
        // Only necessary for demo to restore classic layout
        $('body').removeClass('layout-h');
    }

    render() {

        return (
            <div className="wrapper">
                <HeaderHorizontal />

                <Offsidebar />

                <section className="section-container">
                    { this.props.children }
                </section>

                <Footer />
            </div>
        );
    }

}

export default Base;
