import React from 'react';

import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faUserPlus, faSignInAlt} from '@fortawesome/free-solid-svg-icons';

class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/b/ba/Book_icon_1.png" width="25" height="25" alt="brand"/> Employee Service
                </Link>
                <Nav className="mr-auto">
                    <Link to={"add"} className="nav-link">Compose</Link>
                    <Link to={"list"} className="nav-link">Inbox</Link>
                </Nav>
                <Nav className="ms-auto">
                    <Link to={"register"} className="nav-link"><FontAwesomeIcon icon={faUserPlus} /> Register</Link>
                    <Link to={"login"} className="nav-link"><FontAwesomeIcon icon={faSignInAlt} /> Login</Link>
                </Nav>
            </Navbar>
        );
    }
}

export default NavigationBar;