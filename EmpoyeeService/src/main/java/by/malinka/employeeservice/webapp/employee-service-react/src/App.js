import React from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Message from './components/Message';
import MessagesList from './components/MessagesList';
import Footer from './components/Footer';
import Sent from './components/Sent';
import Groups from './components/Groups';
import Register from './components/Register';
import Login from './components/Login';

import {Provider} from 'react-redux';
import store from './services/store';

function App() {
    const marginTop = {
        marginTop:"20px"
    };

    return (
        <Router>
            <NavigationBar/>
            <Container>
                <Row>
                    <Col lg={12} style={marginTop}>
                        <Routes>
                            <Route path="/" element={<Welcome />}/>
                            <Route path="/add" element={<Message />}/>
                            <Route path="/sent" element={<Sent />}/>
                            <Route path="/groups" element={<Groups />}/>
                            <Route path="/list" element={<MessagesList />}/>

                            <Route path="/login" element={<Provider store={store}>  <Login /> </Provider>}/>
                            <Route path="/register" element={<Register />}/>
                        </Routes>
                    </Col>
                </Row>
            </Container>
            {/*<Footer/>*/}
        </Router>
    );
}

export default App