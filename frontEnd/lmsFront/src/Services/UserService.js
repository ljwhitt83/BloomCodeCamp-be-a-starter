import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/auth';

export const register = async (user) => {
    try {
        const response = await     axios.post(REST_API_BASE_URL + '/register', user);
        return response.data;
    }catch (error) {
        console.error('Registration Error', error);
        throw error;
    }
}

export const fetchAuthorities = async () => {
    try {
    const response = await axios.get(REST_API_BASE_URL + '/authorities');
    return response.data;
    }catch (error) {
        console.error('Error fetching authorities', error);
        throw error;
    }
}


export const loginAction = async (username, password) => {
    try {
        const response = await axios.post(REST_API_BASE_URL + '/login', {username, password});
        return response.data;
    } catch (error ) {
        console.error('Login Error', error);
        throw new Error(error.response.data || 'Login Failed');
    }
}