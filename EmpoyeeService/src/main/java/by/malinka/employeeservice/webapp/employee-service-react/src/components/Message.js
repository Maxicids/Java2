import React from 'react';

import {Card, Form, Button, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSave, faPlusSquare, faArrowRight} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';

class Message extends React.Component {
    constructor(props) {
        super(props);
        this.state = {senderEmail:'', recipientId:'', text:''};
        this.messageChange = this.messageChange.bind(this);
        this.submitMessage = this.submitMessage.bind(this);
        this.setSenderEmail = this.setSenderEmail.bind(this);
        this.setRecipientId = this.setRecipientId.bind(this);
        this.setText = this.setText.bind(this);

    }

    submitMessage(event) {
        alert('From: '+this.state.senderEmail+', To: '+this.state.recipientId+', Message: '+this.state.text);
        event.preventDefault();

        const message = {
            senderEmail: this.state.setSenderEmail,
            recipientEmail: this.state.recipientId,
            text: this.state.text
        };

        axios.post("http://localhost:9000/send/", message)
            .then(response => {
                if(response.data != null) {
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}), 3000);
                } else {
                    this.setState({"show":false});
                }
            });
    }

    setSenderEmail(event) {
        this.state.senderEmail = event.target.value;
    }

    setRecipientId(event) {
        this.state.recipientId = event.target.value;
    }

    setText(event) {
        this.state.text = event.target.value;
    }

    messageChange(event) {
        alert(event.target.name + ' ' + event.target.value);
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Form onSubmit={this.submitMessage} id="messageFormId">
                <Card.Header style={{"textAlign":"center"}}>
                    <FontAwesomeIcon icon={faPlusSquare} /> Compose New Message
                </Card.Header>
                <Card.Body>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Recipient</Form.Label>
                        <Form.Control className={"bg-dark text-white"}
                                      required
                                      type="email"
                                      placeholder="Enter email"
                                      defaultValue={this.state.senderEmail}
                                      onChange={this.setSenderEmail}/>
                        <Form.Text className="text-muted">
                            Please input recipient email or group
                        </Form.Text>
                    </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Recipient</Form.Label>
                            <Form.Control className={"bg-dark text-white"}
                                          required
                                          type="email"
                                          placeholder="Enter email"
                                          defaultValue={this.state.recipientId}
                                          onChange={this.setRecipientId}/>
                            <Form.Text className="text-muted">
                                Please input recipient email or group
                            </Form.Text>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBasicText">
                            <Form.Label>Message</Form.Label>
                            <Form.Control
                                required
                                className={"bg-dark text-white"}
                                type="text" placeholder="Message"
                                defaultValue={this.state.text}
                                onChange={this.setText}
                            />
                        </Form.Group>
                </Card.Body>
                <Card.Footer style={{"textAlign":"right"}}>
                    <Button variant="primary" type="submit">
                        Send <FontAwesomeIcon icon={faArrowRight} />
                    </Button>
                </Card.Footer>
            </Form>
            </Card>
        );
    }
}

export default Message;