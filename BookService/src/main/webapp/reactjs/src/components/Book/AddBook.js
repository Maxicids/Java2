import React, { Component } from "react";

import {connect, useDispatch} from "react-redux";
import {saveBook, fetchBook, updateBook, fetchLanguages, fetchGenres, registerUser,} from "../../services/index";

import { Card, Form, Button, Col, InputGroup, Image } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faPlusSquare,
    faUndo,
    faList,
    faEdit,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";

class AddBook extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state = {
            genres: [],
            languages: [],
            show: false,
        };
    }

    initialState = {
        id: "",
        title: "",
        author: "",
        coverPhotoURL: "",
        isbnNumber: "",
        price: "",
        language: "",
        genre: "",
    };

    componentDidMount() {
        this.findAllLanguages();
        const bookId = +this.props.match.params.id;
        if (bookId) {
            this.findBookById(bookId);
        }
    }

    findAllLanguages = () => {
        this.props.fetchLanguages();
        setTimeout(() => {
            let bookLanguages = this.props.bookObject.languages;
            if (bookLanguages) {
                this.setState({
                    languages: [{ value: "", display: "Select Language" }].concat(
                        bookLanguages.map((language) => {
                            return { value: language, display: language };
                        })
                    ),
                });
                this.findAllGenres();
            }
        }, 2000);
    };

    findAllGenres = () => {
        this.props.fetchGenres();
        setTimeout(() => {
            let bookGenres = this.props.bookObject.genres;
            if (bookGenres) {
                this.setState({
                    genres: [{ value: "", display: "Select Genre" }].concat(
                        bookGenres.map((genre) => {
                            return { value: genre, display: genre };
                        })
                    ),
                });
            }
        }, 2000);
    };

    findBookById = (bookId) => {
        this.props.fetchBook(bookId);
        setTimeout(() => {
            let book = this.props.bookObject.book;
            if (book != null) {
                this.setState({
                    id: book.id,
                    title: book.title,
                    author: book.author,
                    coverPhotoURL: book.coverPhotoURL,
                    isbnNumber: book.isbnNumber,
                    price: book.price,
                    language: book.language,
                    genre: book.genre,
                });
            }
        }, 1000);
    };

    resetBook = () => {
        this.setState(() => this.initialState);
    };

    submitBook = (event) => {
        event.preventDefault();

        const book = {
            title: this.state.title,
            author: this.state.author,
            coverPhotoURL: this.state.coverPhotoURL,
            isbnNumber: this.state.isbnNumber,
            price: this.state.price,
            language: this.state.language,
            genre: this.state.genre,
        };
        const dispatch = useDispatch();


        dispatch(saveBook(book)).then(
            () => {
                this.setState({show: true, method: "post", message: "Book Saved Successfully."});
                setTimeout(() => this.setState({show: false}), 3000);
            }
        ).catch((error) => {
            this.setState({show: true, method: "put", message: error.response.data.message});
        });

        // setTimeout(() => {
        //     if (this.props.bookObject.book != null) {
        //         this.setState({ show: true, method: "post", message: "Book Saved Successfully." });
        //         setTimeout(() => this.setState({ show: false }), 3000);
        //     } else {
        //         this.setState({ show: true, method: "put", message: "Book Saved Successfully." });
        //     }
        // }, 2000);
        this.setState(this.initialState);
    };

    updateBook = (event) => {
        event.preventDefault();

        const book = {
            id: this.state.id,
            title: this.state.title,
            author: this.state.author,
            coverPhotoURL: this.state.coverPhotoURL,
            isbnNumber: this.state.isbnNumber,
            price: this.state.price,
            language: this.state.language,
            genre: this.state.genre,
        };
        this.props.updateBook(book);
        setTimeout(() => {
            if (this.props.bookObject.book != null) {
                this.setState({ show: true, method: "put" });
                setTimeout(() => this.setState({ show: false }), 3000);
            } else {
                this.setState({ show: false });
            }
        }, 2000);
        this.setState(this.initialState);
    };

    bookChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };

    bookList = () => {
        return this.props.history.push("/list");
    };

    render() {
        const { title, author, coverPhotoURL, isbnNumber, price, language, genre } =
            this.state;

        return (
            <div>
                <div style={{ display: this.state.show ? "block" : "none" }}>
                    <MyToast
                        show={this.state.show}
                        message={this.state.message}
                        type={this.state.method = "post" ? "success" : "danger"}
                    />
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>
                        <FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare} />{" "}
                        {this.state.id ? "Update Book" : "Add New Book"}
                    </Card.Header>
                    <Form
                        onReset={this.resetBook}
                        onSubmit={this.state.id ? this.updateBook : this.submitBook}
                        id="bookFormId"
                    >
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridTitle">
                                    <Form.Label>Title</Form.Label>
                                    <Form.Control
                                        required
                                        autoComplete="off"
                                        type="test"
                                        name="title"
                                        value={title || ''}
                                        onChange={this.bookChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Book Title"
                                    />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridAuthor">
                                    <Form.Label>Author</Form.Label>
                                    <Form.Control
                                        required
                                        autoComplete="off"
                                        type="test"
                                        name="author"
                                        value={author || ''}
                                        onChange={this.bookChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Book Author"
                                    />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridCoverPhotoURL">
                                    <Form.Label>Cover Photo URL</Form.Label>
                                    <InputGroup>
                                        <Form.Control
                                            required
                                            autoComplete="off"
                                            type="test"
                                            name="coverPhotoURL"
                                            value={coverPhotoURL || ''}
                                            onChange={this.bookChange}
                                            className={"bg-dark text-white"}
                                            placeholder="Enter Book Cover Photo URL"
                                        />
                                        <InputGroup.Append>
                                            {this.state.coverPhotoURL !== "" && (
                                                <Image
                                                    src={this.state.coverPhotoURL}
                                                    width="40"
                                                    height="38"
                                                />
                                            )}
                                        </InputGroup.Append>
                                    </InputGroup>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridISBNNumber">
                                    <Form.Label>ISBN Number</Form.Label>
                                    <Form.Control
                                        required
                                        autoComplete="off"
                                        type="test"
                                        name="isbnNumber"
                                        value={isbnNumber || ''}
                                        onChange={this.bookChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Book ISBN Number"
                                    />
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridPrice">
                                    <Form.Label>Price</Form.Label>
                                    <Form.Control
                                        required
                                        autoComplete="off"
                                        type="test"
                                        name="price"
                                        value={price || ''}
                                        onChange={this.bookChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Book Price"
                                    />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridLanguage">
                                    <Form.Label>Language</Form.Label>
                                    <Form.Control
                                        required
                                        as="select"
                                        custom
                                        onChange={this.bookChange}
                                        name="language"
                                        value={language || ''}
                                        className={"bg-dark text-white"}
                                    >
                                        {<option key={"English"} value={"English"}>English</option>}
                                        {<option key={"Russian"} value={"Russian"}>Russian</option>}
                                        {<option key={"French"} value={"French"}>French</option>}
                                        {<option key={"Portuguese"} value={"Portuguese"}>Portuguese</option>}
                                        {<option key={"Hindi"} value={"Hindi"}>Hindi</option>}
                                        {<option key={"Arabic"} value={"Arabic"}>Arabic</option>}
                                        {<option key={"Spanish"} value={"Spanish"}>Spanish</option>}
                                        {<option key={"Chinese"} value={"Chinese"}>Chinese</option>}
                                    </Form.Control>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridGenre">
                                    <Form.Label>Genre</Form.Label>
                                    <Form.Control
                                        required
                                        as="select"
                                        custom
                                        onChange={this.bookChange}
                                        name="genre"
                                        value={genre || ''}
                                        className={"bg-dark text-white"}
                                    >
                                        {<option key={"Technology"} value={"Technology"}>Technology</option>}
                                        {<option key={"Science"} value={"Science"}>Science</option>}
                                        {<option key={"History"} value={"History"}>History</option>}
                                        {<option key={"Fantasy"} value={"Fantasy"}>Fantasy</option>}
                                        {<option key={"Biography"} value={"Biography"}>Biography</option>}
                                        {<option key={"Horror"} value={"Horror"}>Horror</option>}
                                        {<option key={"Romance"} value={"Romance"}>Romance</option>}
                                    </Form.Control>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{ textAlign: "right" }}>
                            <Button size="sm" variant="success" type="submit">
                                <FontAwesomeIcon icon={faSave} />{" "}
                                {this.state.id ? "Update" : "Save"}
                            </Button>{" "}
                            <Button size="sm" variant="info" type="reset">
                                <FontAwesomeIcon icon={faUndo} /> Reset
                            </Button>{" "}
                            <Button
                                size="sm"
                                variant="info"
                                type="button"
                                onClick={() => this.bookList()}
                            >
                                <FontAwesomeIcon icon={faList} /> Book List
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        bookObject: state.book,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        saveBook: (book) => dispatch(saveBook(book)),
        fetchBook: (bookId) => dispatch(fetchBook(bookId)),
        updateBook: (book) => dispatch(updateBook(book)),
        fetchLanguages: () => dispatch(fetchLanguages()),
        fetchGenres: () => dispatch(fetchGenres()),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AddBook);