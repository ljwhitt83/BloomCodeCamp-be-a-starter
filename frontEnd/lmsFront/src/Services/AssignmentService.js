import axios from "axios";

const baseUrl = "http://localhost:8080/api/assignments";

export const createAssignment = (assignment) => axios.post(baseUrl, assignment);

export const getUserAssignments = async (assignment) => {

    try {
        const response = await axios.get(baseUrl, assignment);
        return response.data;
    } catch (error) {
        console.error('Error fetching assignments', error);
        throw new Error(error.response.data || 'Error fetching assignments');
    }
}