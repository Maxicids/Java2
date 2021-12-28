import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {connect} from 'react-redux';
import {Card, Row, Col, Form, Button, InputGroup, FormControl, Alert} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSignInAlt, faUndo, faEnvelope, faLock, faArrowRight} from '@fortawesome/free-solid-svg-icons';

import axios from 'axios';
import { useNavigate } from "react-router-dom";
import AuthService from "../services/authService";

import {authenticateUser} from '../services/index';



class Login extends React.Component {

    historyD = () => {
        return useNavigate();
    }
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.setEmail = this.setEmail.bind(this);
        this.setPassword = this.setPassword.bind(this);
        this.setError = this.setError.bind(this);
    }

    initialState = {
        email:'', password:'', error:''
    };

    setEmail(event) {
        this.state.email = event.target.value;
    }

    setPassword(event) {
        this.state.password = event.target.value;
    }

    setError(event) {
        this.state.error = event.target.value;
    }

    validateUser = () => {
        this.props.authenticateUser(this.state.email, this.state.password);
        //     .then(
        //     () => {
        //         this.props.history.push("/");
        //         window.location.reload();
        //     }
        // );
        setTimeout(() => {
            if(this.props.auth.isLoggedIn) {
                //const { history } = this.context;
                console.log(this)
                console.log(this.historyD())
                //return this.historyD().navigate("/thePath")
                //return this.props.history.push("/");
            } else {
                this.setState({"error":"Invalid email and password"});
            }
        }, 500);
    };

    render() {
        return (
            <Row className="justify-content-md-center">
                <Col xs={5}>
                    {this.state.error && <Alert variant="danger">{this.state.error}</Alert>}
                    <Card className={"border border-dark bg-dark text-white"}>
                        <Card.Header>
                            <FontAwesomeIcon icon={faSignInAlt}/> Login
                        </Card.Header>
                        <Card.Body>
                            <Form.Group className="mb-3" controlId="formBasicEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control className={"bg-dark text-white"}
                                              required
                                              type="email"
                                              placeholder="Enter email"
                                              defaultValue={this.state.email}
                                              onChange={this.setEmail}
                                />
                                <Form.Text className="text-muted">
                                    Please input your email
                                </Form.Text>
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Password</Form.Label>
                                <Form.Control
                                    required
                                    className={"bg-dark text-white"}
                                    defaultValue={this.state.password}
                                    onChange={this.setPassword}
                                    type="password" placeholder="Name"
                                />
                                <Form.Text className="text-muted">
                                    Please input your password
                                </Form.Text>
                            </Form.Group>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"right"}}>
                            <Button variant="primary" type="submit" onClick={this.validateUser}>
                                Login <FontAwesomeIcon icon={faSignInAlt} />
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            </Row>
        );
    }
}

const mapStateToProps = state => {
    return {
        auth:state.auth
    }
};

const mapDispatchToProps = dispatch => {
    return {
        authenticateUser: (email, password) => dispatch(authenticateUser(email, password))
    };
};


export default connect(mapStateToProps, mapDispatchToProps)(Login);