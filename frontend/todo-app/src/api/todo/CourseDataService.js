 import axios from 'axios'
import {JPA_API_URL } from '../../Constants'

class CourseDataService {

    //get a list of courses from backend
    retrieveAllCourses(name) {
        return axios.get(`${JPA_API_URL}/users/${name}/courses`);
    }

    //get a course from backend
    retrieveCourse(name, id) {
        return axios.get(`${JPA_API_URL}/users/${name}/courses/${id}`);
    }

    //delete a course for backend
    deleteCourse(name, id) {
        return axios.delete(`${JPA_API_URL}/users/${name}/courses/${id}`);
    }

    //update a course from backend
    updateCourse(name, id, course) {
        return axios.put(`${JPA_API_URL}/users/${name}/courses/${id}`, course);
    }

    //creates a new course for backend
    createCourse(name, course) {
        return axios.post(`${JPA_API_URL}/users/${name}/courses/`, course);
    }
    
    //check grade in backend and get boolean
    checkGrade(name, id) {
        return axios.get(`${JPA_API_URL}/users/${name}/courses/${id}/grade`);
    }

    //check if courses added in backend and get boolean
    checkCourse(name, courseId) {
        return axios.get(`${JPA_API_URL}/users/${name}/courses/${courseId}/checkExistingCourse`);
    }
}

export default new CourseDataService()