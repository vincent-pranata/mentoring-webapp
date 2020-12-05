import axios from 'axios'
import {JPA_API_URL} from '../../Constants'

class MenteeDataService {

    //get all mentees from backend
    retrieveAllMentees(name) {
        return axios.get(`${JPA_API_URL}/users/${name}/mentees`);
    }

    //get a mentee from backend
    retrieveMentee(name, id) {
        return axios.get(`${JPA_API_URL}/users/${name}/mentees/${id}`);
    }

    //delete a mentee for backend
    deleteMentee(name, id) {
        return axios.delete(`${JPA_API_URL}/users/${name}/mentees/${id}`);
    }

    //update a mentee from backend
    updateMentee(name, id, mentee) {
        return axios.put(`${JPA_API_URL}/users/${name}/mentees/${id}`, mentee);
    }

    //create a new mentee for backend
    createMentee(name, mentee) {
        return axios.post(`${JPA_API_URL}/users/${name}/mentees/`, mentee);
    }

    //check if mentee is added and get boolean
    checkMentee(name, courseId) {
        return axios.get(`${JPA_API_URL}/users/${name}/mentees/${courseId}/checkMentee`)
    }
}

export default new MenteeDataService()