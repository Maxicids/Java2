import * as BT from "./cartTypes";

const initialState = {
    cart: "",
    error: "",
};

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case BT.SAVE_CART_REQUEST:
        case BT.FETCH_CART_REQUEST:
        case BT.UPDATE_CART_REQUEST:
        case BT.DELETE_CART_REQUEST:
            return {
                ...state,
            };
        case BT.CART_SUCCESS:
            return {
                book: action.payload,
                error: "",
            };
        case BT.CART_FAILURE:
            return {
                book: "",
                error: action.payload,
            };
        default:
            return state;
    }
};

export default reducer;
