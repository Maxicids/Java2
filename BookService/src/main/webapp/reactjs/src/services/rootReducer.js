import { combineReducers } from "redux";
import userReducer from "./user/userReducer";
import authReducer from "./user/auth/authReducer";
import bookReducer from "./book/bookReducer";
import cartReducer from "./cart/cartReducer";

const rootReducer = combineReducers({
  user: userReducer,
  book: bookReducer,
  auth: authReducer,
  cart: cartReducer
});

export default rootReducer;
