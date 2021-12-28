import axios from "axios";

const API_URL = "http://localhost:9000/users/";

class AuthService {
    login(email, password) {
        const user = {
            name: "",
            surname: "",
            password: password,
            email: email,
        };
        return axios
            .post(API_URL + "login", {
                user
            })
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(name, email, password) {
        return axios.post(API_URL + "register", {
            name,
            email,
            password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem("user"))
    }
}

export default new AuthService();