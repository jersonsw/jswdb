import React from 'react';

import Header from './Header'
import Sidebar from './Sidebar'
import Offsidebar from './Offsidebar'
import Footer from './Footer'

const Base = props => (
    <div className="wrapper">
        <Header />

        <Sidebar />

        <Offsidebar />

        <section className="section-container">
            { props.children }
        </section>

        <Footer />
    </div>
)

export default Base;
