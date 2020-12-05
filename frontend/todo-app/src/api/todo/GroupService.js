import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class GroupService {

    // get all groups from backend
    retrieveAllGroups(name) {
        return axios.get(`${JPA_API_URL}/users/${name}/groups`);
    }

    //get a group from backend
    retrieveGroup(name, id) {
        return axios.get(`${JPA_API_URL}/users/${name}/groups/${id}`);
    }

    //delete a group for backend
    deleteGroup(name, id) {
        return axios.delete(`${JPA_API_URL}/users/${name}/groups/${id}`);
    }

    //update a group trom backend
    updateGroup(name, id, group) {
        return axios.put(`${JPA_API_URL}/users/${name}/groups/${id}`, group);
    }

    //create a new group for backend
    createGroup(name, group) {
        return axios.post(`${JPA_API_URL}/users/${name}/groups`, group);
    }

    //check if group is joined yet and get boolean 
    isJoined(name, id) {
	    return axios.get(`${JPA_API_URL}/users/${name}/groups/${id}/isJoined`);
	}
    
    //get groups based on course id from backend
	getActiveGroups(courseId){
        return axios.get(`${JPA_API_URL}/users/groups/${courseId}/getActiveGroups`);
    }

    //remove user from selected groups 
	leaveGroup(name, id){
        return axios.get(`${JPA_API_URL}/users/${name}/groups/${id}/leave`);
    }
}

export default new GroupService()