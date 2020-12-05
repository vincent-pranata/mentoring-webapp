import React, { Component } from 'react'
import CourseDataService from '../../api/todo/CourseDataService.js'
import AuthenticationService from './AuthenticationService.js'

class ListCoursesComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            courses: [],
            message: null
        }
        this.deleteCourseClicked = this.deleteCourseClicked.bind(this)
        this.updateCourseClicked = this.updateCourseClicked.bind(this)
        this.addCourseClicked = this.addCourseClicked.bind(this)
        this.refreshCourses = this.refreshCourses.bind(this)
    }

    componentWillUnmount() {
    }

    shouldComponentUpdate(nextProps, nextState) {
        return true
    }

    componentDidMount() {
        this.refreshCourses();
    }

    //reset the page wheter something is added, updated or deleted
    refreshCourses() {
        //get the list of courses and set it to an array
        let username = AuthenticationService.getLoggedInUserName()
        CourseDataService.retrieveAllCourses(username)
            .then(
                response => {
                    this.setState({ courses: response.data })
                }
            )
    }

    //what happen when delete button is clicked
    deleteCourseClicked(id) {
        let username = AuthenticationService.getLoggedInUserName()
        CourseDataService.deleteCourse(username, id)
            .then(
                response => {
                    this.setState({ message: `Delete of course ${id} Successful` })
                    this.refreshCourses()
                }
            )

    }

    //what happen when add button is clicked
    addCourseClicked() {
        this.props.history.push(`/courses/-1`)
    }

    //what happen when update button is clicked
    updateCourseClicked(id) {
        this.props.history.push(`/update/courses/${id}`)
    }

    render() {
        return (
            <div>
                <h1>Courses List</h1>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Course ID</th>
                                <th>Course Name</th>
                                <th>IsCompleted?</th>
                                <th>Grade</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.courses.map(
                                    course =>
                                        <tr key={course.id}>
                                            <td>{course.courseId}</td>
                                            <td>{course.coursename}</td>
                                            <td>{course.completed.toString()}</td>
                                            <td>{course.grade}</td>
                                            <td><button className="btn btn-success" onClick={() => this.updateCourseClicked(course.id)}>Update</button></td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteCourseClicked(course.id)}>Delete</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addCourseClicked}>Add</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ListCoursesComponent