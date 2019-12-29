import React, {Suspense, lazy} from 'react';
import {withRouter, Switch, Route} from 'react-router-dom';
import {TransitionGroup, CSSTransition} from 'react-transition-group';
/* loader component for Suspense*/
import PageLoader from './components/Common/PageLoader';
import Base from './components/Layout/Base';
import BasePage from './components/Layout/BasePage';
// import BaseHorizontal from './components/Layout/BaseHorizontal';
/* Used to render a lazy component with react-router */
const waitFor = Tag => props => <Tag {...props}/>;
const Scheduler = lazy(() => import('./components/Scheduler/Scheduler'));
const SubMenu = lazy(() => import('./components/SubMenu/SubMenu'));
const Movies = lazy(() => import('./components/MoviesView/Movies'));
// List of routes that uses the page layout
// listed here to Switch between layouts
// depending on the current pathname
const listofPages = [
    /* See full project for reference */
];

const Routes = ({location}) => {
    const currentKey = location.pathname.split('/')[1] || '/';
    const timeout = {enter: 500, exit: 500};

    // Animations supported
    //      'rag-fadeIn'
    //      'rag-fadeInRight'
    //      'rag-fadeInLeft'

    const animationName = 'rag-fadeIn';

    if (listofPages.indexOf(location.pathname) > -1) {
        return (
            // Page Layout component wrapper
            <BasePage>
                <Suspense fallback={<PageLoader/>}>
                    <Switch location={location}>
                        {/* See full project for reference */}
                    </Switch>
                </Suspense>
            </BasePage>
        )
    } else {
        return (
            // Layout component wrapper
            // Use <BaseHorizontal> to change layout
            <Base>
                <TransitionGroup>
                    <CSSTransition key={currentKey} timeout={timeout} classNames={animationName} exit={false}>
                        <div>
                            <Suspense fallback={<PageLoader/>}>
                                <Switch location={location}>
                                    <Route path="/movies" component={waitFor(Movies)}/>
                                    <Route path="/scheduler" component={waitFor(Scheduler)}/>
                                    <Route path="/submenu" component={waitFor(SubMenu)}/>

                                </Switch>
                            </Suspense>
                        </div>
                    </CSSTransition>
                </TransitionGroup>
            </Base>
        )
    }
}

export default withRouter(Routes);
