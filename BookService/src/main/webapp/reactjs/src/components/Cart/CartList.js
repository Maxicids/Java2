import React, { Component } from "react";

import { connect } from "react-redux";

import "./../../assets/css/Style.css";
import {Card, Table, ButtonGroup, Button, InputGroup, FormControl,} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faList, faTrash, faStepBackward, faFastBackward, faStepForward, faFastForward, faSearch, faTimes,} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";
import axios from "axios";
import {deleteCart} from "../../services";

class CartList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            carts: [],
            search: "",
            currentPage: 1,
            cartsPerPage: 5,
            sortDir: "asc",
        };
    }

    sortData = () => {
        setTimeout(() => {
            this.state.sortDir === "asc"
                ? this.setState({ sortDir: "desc" })
                : this.setState({ sortDir: "asc" });
            this.findAllCarts(this.state.currentPage);
        }, 500);
    };

    componentDidMount() {
        this.findAllCarts(this.state.currentPage);
    }

    findAllCarts(currentPage) {
        currentPage -= 1;
        axios
            .get(
                "http://localhost:8081/rest/carts/with-email?pageNumber=" +
                currentPage +
                "&pageSize=" +
                this.state.cartsPerPage +
                "&sortBy=id&sortDir=" +
                this.state.sortDir +
                "&email=" +
                localStorage.getItem("email")
            )
            .then((response) => response.data)
            .then((data) => {
                this.setState({
                    carts: data.content,
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    currentPage: data.number + 1,
                });
            })
            .catch((error) => {
                console.log(error);
                localStorage.removeItem("jwtToken");
                localStorage.removeItem("role");
                this.props.history.push("/");
            });
    }

    deleteCart = (cartId) => {
        this.props.deleteCart(cartId);
        setTimeout(() => {
            if (this.props.cartObject != null) {
                this.setState({ show: true });
                setTimeout(() => this.setState({ show: false }), 3000);
                this.findAllCarts(this.state.currentPage);
            } else {
                this.setState({ show: false });
            }
        }, 1000);
    };

    changePage = (event) => {
        let targetPage = parseInt(event.target.value);
        if (this.state.search) {
            this.searchData(targetPage);
        } else {
            this.findAllCarts(targetPage);
        }
        this.setState({
            [event.target.name]: targetPage,
        });
    };

    firstPage = () => {
        let firstPage = 1;
        if (this.state.currentPage > firstPage) {
            if (this.state.search) {
                this.searchData(firstPage);
            } else {
                this.findAllCarts(firstPage);
            }
        }
    };

    prevPage = () => {
        let prevPage = 1;
        if (this.state.currentPage > prevPage) {
            if (this.state.search) {
                this.searchData(this.state.currentPage - prevPage);
            } else {
                this.findAllCarts(this.state.currentPage - prevPage);
            }
        }
    };

    lastPage = () => {
        let condition = Math.ceil(
            this.state.totalElements / this.state.cartsPerPage
        );
        if (this.state.currentPage < condition) {
            if (this.state.search) {
                this.searchData(condition);
            } else {
                this.findAllCarts(condition);
            }
        }
    };

    nextPage = () => {
        if (
            this.state.currentPage <
            Math.ceil(this.state.totalElements / this.state.cartsPerPage)
        ) {
            if (this.state.search) {
                this.searchData(this.state.currentPage + 1);
            } else {
                this.findAllCarts(this.state.currentPage + 1);
            }
        }
    };

    searchChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };

    cancelSearch = () => {
        this.setState({ search: "" });
        this.findAllCarts(this.state.currentPage);
    };

    searchData = (currentPage) => {
        currentPage -= 1;
        axios
            .get(
                "http://localhost:8081/rest/carts/search/" +
                this.state.search +
                "?page=" +
                currentPage +
                "&size=" +
                this.state.booksPerPage
            )
            .then((response) => response.data)
            .then((data) => {
                this.setState({
                    carts: data.content,
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    currentPage: data.number + 1,
                });
            });
    };

    render() {
        const { carts, currentPage, totalPages, search } = this.state;

        return (
            <div>
                <div style={{ display: this.state.show ? "block" : "none" }}>
                    <MyToast
                        show={this.state.show}
                        message={"Cart item deleted Successfully."}
                        type={"danger"}
                    />
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>
                        <div style={{ float: "left" }}>
                            <FontAwesomeIcon icon={faList} /> Cart List
                        </div>
                        <div style={{ float: "right" }}>
                            <InputGroup size="sm">
                                <FormControl
                                    placeholder="Search"
                                    name="search"
                                    value={search}
                                    className={"info-border bg-dark text-white"}
                                    onChange={this.searchChange}
                                />
                                <InputGroup.Append>
                                    <Button
                                        size="sm"
                                        variant="outline-info"
                                        type="button"
                                        onClick={this.searchData}
                                    >
                                        <FontAwesomeIcon icon={faSearch} />
                                    </Button>
                                    <Button
                                        size="sm"
                                        variant="outline-danger"
                                        type="button"
                                        onClick={this.cancelSearch}
                                    >
                                        <FontAwesomeIcon icon={faTimes} />
                                    </Button>
                                </InputGroup.Append>
                            </InputGroup>
                        </div>
                    </Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                            <tr>
                                <th>Unique number</th>
                                <th>Customer</th>
                                <th>Book</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {carts.length === 0 ? (
                                <tr align="center">
                                    <td colSpan="4">Cart is empty.</td>
                                </tr>
                            ) : (
                                carts.map((cart) => (
                                    <tr key={cart.id}>
                                        <td>{cart.id}</td>
                                        <td>{cart.customer.email}</td>
                                        <td>{cart.book.title}</td>

                                        <td>
                                            <ButtonGroup>
                                                <Button
                                                    size="sm"
                                                    variant="outline-danger"
                                                    onClick={() => this.deleteCart(cart.id)}
                                                >
                                                    <FontAwesomeIcon icon={faTrash} />
                                                </Button>
                                            </ButtonGroup>
                                        </td>
                                    </tr>
                                ))
                            )}
                            </tbody>
                        </Table>
                    </Card.Body>
                    {carts.length > 0 ? (
                        <Card.Footer>
                            <div style={{ float: "left" }}>
                                Showing Page {currentPage} of {totalPages}
                            </div>
                            <div style={{ float: "right" }}>
                                <InputGroup size="sm">
                                    <InputGroup.Prepend>
                                        <Button
                                            type="button"
                                            variant="outline-info"
                                            disabled={currentPage === 1}
                                            onClick={this.firstPage}
                                        >
                                            <FontAwesomeIcon icon={faFastBackward} /> First
                                        </Button>
                                        <Button
                                            type="button"
                                            variant="outline-info"
                                            disabled={currentPage === 1}
                                            onClick={this.prevPage}
                                        >
                                            <FontAwesomeIcon icon={faStepBackward} /> Prev
                                        </Button>
                                    </InputGroup.Prepend>
                                    <FormControl
                                        className={"page-num bg-dark"}
                                        name="currentPage"
                                        value={currentPage}
                                        onChange={this.changePage}
                                    />
                                    <InputGroup.Append>
                                        <Button
                                            type="button"
                                            variant="outline-info"
                                            disabled={currentPage === totalPages}
                                            onClick={this.nextPage}
                                        >
                                            <FontAwesomeIcon icon={faStepForward} /> Next
                                        </Button>
                                        <Button
                                            type="button"
                                            variant="outline-info"
                                            disabled={currentPage === totalPages}
                                            onClick={this.lastPage}
                                        >
                                            <FontAwesomeIcon icon={faFastForward} /> Last
                                        </Button>
                                    </InputGroup.Append>
                                </InputGroup>
                            </div>
                        </Card.Footer>
                    ) : null}
                </Card>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        cartObject: state.cart,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        deleteCart: (cartId) => dispatch(deleteCart(cartId)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(CartList);
