import * as BT from "./cartTypes";
import axios from "axios";

export const saveCart = (cart) => {
    return (dispatch) => {
        dispatch({
            type: BT.SAVE_CART_REQUEST,
        });
        axios
            .post("http://localhost:8081/rest/carts/add", cart)
            .then((response) => {
                dispatch(cartSuccess(response.data));
            })
            .catch((error) => {
                dispatch(cartFailure(error));
            });
    };
};

export const fetchCart = (cartId) => {
    return (dispatch) => {
        dispatch({
            type: BT.FETCH_CART_REQUEST,
        });
        axios
            .get("http://localhost:8081/rest/carts/" + cartId)
            .then((response) => {
                dispatch(cartSuccess(response.data));
            })
            .catch((error) => {
                dispatch(cartFailure(error));
            });
    };
};

export const updateCart = (cart) => {
    return (dispatch) => {
        dispatch({
            type: BT.UPDATE_CART_REQUEST,
        });
        axios
            .put("http://localhost:8081/rest/carts/", cart)
            .then((response) => {
                dispatch(cartSuccess(response.data));
            })
            .catch((error) => {
                dispatch(cartFailure(error));
            });
    };
};

export const deleteCart = (cartId) => {
    return (dispatch) => {
        dispatch({
            type: BT.DELETE_CART_REQUEST,
        });
        axios
            .delete("http://localhost:8081/rest/carts/" + cartId)
            .then((response) => {
                dispatch(cartSuccess(response.data));
            })
            .catch((error) => {
                dispatch(cartFailure(error));
            });
    };
};

const cartSuccess = (cart) => {
    return {
        type: BT.CART_SUCCESS,
        payload: cart,
    };
};

const cartFailure = (error) => {
    return {
        type: BT.CART_FAILURE,
        payload: error,
    };
};
