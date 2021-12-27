import React from 'react';
import {Card, Table, Image, ButtonGroup, Button, Row, Col, Form} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList, faEdit, faUserPlus, faSignInAlt} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import Login from "./Login";

class Register extends React.Component {
    render() {
        return (
            <Row className="justify-content-md-center">
                <Col xs={5}>
                    {/*{error && <Alert variant="danger">{error}</Alert>}*/}
                    <Card className={"border border-dark bg-dark text-white"}>
                        <Card.Header>
                            <FontAwesomeIcon icon={faSignInAlt}/> Registration
                        </Card.Header>
                        <Card.Body>
                            <Form.Group className="mb-3" controlId="formBasicEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control className={"bg-dark text-white"}
                                              required
                                              type="email"
                                              placeholder="Enter email"/>
                                <Form.Text className="text-muted">
                                    Please input your email
                                </Form.Text>
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Password</Form.Label>
                                <Form.Control
                                    required
                                    className={"bg-dark text-white"}
                                    type="password" placeholder="Password"
                                />
                                <Form.Text className="text-muted">
                                    Please input your password
                                </Form.Text>
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Name</Form.Label>
                                <Form.Control
                                    required
                                    className={"bg-dark text-white"}
                                    type="text" placeholder="Name"
                                />
                                <Form.Text className="text-muted">
                                    Please input your name
                                </Form.Text>
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Surname</Form.Label>
                                <Form.Control
                                    required
                                    className={"bg-dark text-white"}
                                    type="text" placeholder="Name"
                                />
                                <Form.Text className="text-muted">
                                    Please input your surname
                                </Form.Text>
                            </Form.Group>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"right"}}>
                            <Button variant="primary" type="submit">
                                Register <FontAwesomeIcon icon={faUserPlus} />
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            </Row>
        );
    }
}


export default Register;