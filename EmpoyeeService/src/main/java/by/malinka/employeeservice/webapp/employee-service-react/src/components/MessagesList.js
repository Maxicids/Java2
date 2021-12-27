import React from 'react';
import {Card, Table, Image, ButtonGroup, Button} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList, faEdit, faTrash} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';

class MessagesList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            messages : []
        };
    }

    componentDidMount() {
        this.findAllMessages();
    }

    findAllMessages() {
        axios.get("http://localhost:9000/messages/")
            .then(response => response.data)
            .then((data) => {
                this.setState({messages: data});
                console.log(data);
            });
    }
    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><FontAwesomeIcon icon={faList} /> Messages</Card.Header>
                <Card.Body>
                    <Table bordered hover striped variant="dark">
                        <thead>
                        <tr>
                            <th>Sender</th>
                            <th>Recipient</th>
                            <th>Content</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.messages.length === 0 ?
                                <tr align="center">
                                    <td colSpan="3">No Messages</td>
                                </tr> :
                                this.state.messages.content.map((message) => (
                                    <tr key={message.id}>
                                        <td>{message.senderId.email}</td>
                                        <td>{message.recipientId.email}</td>
                                        <td>{message.messageId.text}</td>
                                    </tr>
                                ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default MessagesList;