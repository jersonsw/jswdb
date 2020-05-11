import React, {Component} from 'react';
import {BrowserRouter} from 'react-router-dom';
// App Routes
import Routes from './Routes';
// Vendor dependencies
import "./Vendor";
// Application Styles
import './styles/bootstrap.scss';
import './styles/app.scss'


class App extends Component {
  render() {

    // specify base href from env variable 'PUBLIC_URL'
    // use only if application isn't served from the root
    // for development it is forced to root only
    /* global PUBLIC_URL */
    const basename = process.env.NODE_ENV === 'development' ? '/' : (PUBLIC_URL || '/');

    return (
        <BrowserRouter basename={basename}>
            <Routes />
        </BrowserRouter>
    );

  }
}

export default App;
