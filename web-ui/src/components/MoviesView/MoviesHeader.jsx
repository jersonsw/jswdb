import React from 'react';
import {Button} from "reactstrap";

export default function MoviesHeader(props) {

    const {connecting, connected} = props;

    return (
        <div className="content-heading">
            Movies
            <div className={'ml-auto'}>
                    <span className={'badge badge-' + (connecting ? 'warning' : (connected ? 'success' : 'danger'))}>
                          {connecting ? 'Connecting...' : connected ? 'Connected' : 'Disconnected'}
                    </span>
                {!connecting &&
                <Button onClick={() => props.toggleConnection(!connected)}
                        className={'ml-5'}
                        size={'xs'} color={'secondary'}>
                    {connected && <i className="fa fa-sign-out-alt text-danger"></i>}
                    {!connected && <i className="fa fa-plug text-success"></i>}
                </Button>}
            </div>
        </div>
    )
}
