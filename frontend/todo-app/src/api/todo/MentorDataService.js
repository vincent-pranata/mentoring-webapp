import axios from 'axios'
import {JPA_API_URL } from '../../Constants'

class MentorDataService {

    //get all mentors by username from backend
    retrieveAllMentors(name) {
        return axios.get(`${JPA_API_URL}/users/${name}/mentors`);
    }

    //get a mentor from backend
    retrieveMentor(name, id) {
        return axios.get(`${JPA_API_URL}/users/${name}/mentors/${id}`);
    }

    //delete a mentor for backend
    deleteMentor(name, id) {
        return axios.delete(`${JPA_API_URL}/users/${name}/mentors/${id}`);
    }

    //update a mentor from backend
    updateMentor(name, id, mentor) {
        return axios.put(`${JPA_API_URL}/users/${name}/mentors/${id}`, mentor);
    }

    //create a new mentor for backend
    createMentor(name, mentor) {
        return axios.post(`${JPA_API_URL}/users/${name}/mentors/`, mentor);
    }

    //check if mentor of a course is added and get boolean 
    isMentor(name, courseId){
        return axios.get(`${JPA_API_URL}/users/${name}/mentors/${courseId}/isMentor`);
    }

    //get all mentors by course id  
    getActiveMentor(courseId){
        return axios.get(`${JPA_API_URL}/users/mentors/${courseId}/getActiveMentor`);
    }
}
export default new MentorDataService()