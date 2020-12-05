import React, { Component } from 'react'
import MentorDataService from '../../api/todo/MentorDataService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'

class ListMentorsComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            mentors: [],
            message: null
        }
        this.deleteMentorClicked = this.deleteMentorClicked.bind(this)
        this.updateMentorClicked = this.updateMentorClicked.bind(this)
        this.addMentorClicked = this.addMentorClicked.bind(this)
        this.refreshMentors = this.refreshMentors.bind(this)
    } 

    componentWillUnmount() {
    }

    shouldComponentUpdate(nextProps, nextState) {
        return true
    }

    componentDidMount() {
        this.refreshMentors();
    }

    //refresh the page when something is updated, clicked, and deleted
    refreshMentors() {
        let username = AuthenticationService.getLoggedInUserName()
        MentorDataService.retrieveAllMentors(username)
            .then(
                response => {
                    this.setState({ mentors: response.data })
                }
            )
    }

    //what happens when delete button is clicked
    deleteMentorClicked(id) {
        let username = AuthenticationService.getLoggedInUserName()
        MentorDataService.deleteMentor(username, id)
            .then(
                response => {
                    this.setState({ message: `Delete of mentor ${id} Successful` })
                    this.refreshMentors()
                }
            )

    }

    //what happens when add button is clicked
    addMentorClicked() {
        this.props.history.push(`/mentors/-1`)
    }

    //what happens when update button is clicked
    updateMentorClicked(id) {
        this.props.history.push(`/mentors/${id}`)

    }

    //what to print out    
    render() {
        return (
            <div>
                <h1>Apply As Mentor</h1>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Course ID</th>
                                <th>Course Name</th>
                                <th>Mentor Since</th>
                                <th>Ratings</th>
                                <th>Quit</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.mentors.map(
                                    mentor =>
                                        <tr key={mentor.id}>
                                            <td>{mentor.courseId}</td>
                                            <td>{mentor.courseName}</td>
                                            <td>{moment(mentor.startdate).format('YYYY-MM-DD')}</td>
                                            <td>{mentor.rate}</td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteMentorClicked(mentor.id)}>Quit</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addMentorClicked}>Apply</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ListMentorsComponent