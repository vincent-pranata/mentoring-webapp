import React, { Component } from 'react'
import moment from 'moment'
import { Formik, Form, ErrorMessage } from 'formik';
import CourseDataService from '../../api/todo/CourseDataService.js'
import MentorDataService from '../../api/todo/MentorDataService.js'
import AuthenticationService from './AuthenticationService.js'

class MentorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            courses: [],
            id: this.props.match.params.id,
            keyId:'',
            courseId: '',
            coursename: '',
            grade:false,
            isMentor:false,
            targetDate: moment(new Date()).format('YYYY-MM-DD')
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
    }

    componentDidMount() {
        let username = AuthenticationService.getLoggedInUserName()
        //get list of courses
        CourseDataService.retrieveAllCourses(username) 
        .then(response => this.setState({courses: response.data})
        )        
    }

    //validate the inputs
    validate(values) {
        let errors = {}
        
        if (!values.courseId) {
            errors.courseId = 'Select a Course'
        }
        
        if(this.state.grade===false)
        {
            errors.courseId = 'Unnable to become a mentor. Your grade is lower than 70'
        }

        if(this.state.isMentor===true)
        {
            errors.courseId = 'You are have been already registered as a mentor for the selected course'
        }
        return errors
    }


    //what happen when not focusing on select course 
    onBlurCourse() {
        let username = AuthenticationService.getLoggedInUserName()
        
        CourseDataService.checkGrade(username, this.state.keyId) 
        .then(response => this.setState({grade: response.data})
            )

        MentorDataService.isMentor(username, this.state.courseId)
        .then(response => this.setState({isMentor: response.data})
            )
    }

    //handle the changes of select course
    handleCourseChange = (e) => {
        let index = e.nativeEvent.target.selectedIndex;
        let key= e.target.options[index].getAttribute('data-key');
        let label = e.nativeEvent.target[index].text;
        let value = e.target.value;
        
        this.setState({keyId:key ,courseId:value, coursename:label});
    };

    //what happen when submit button is clicked
    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()

        let mentor = {
            id: this.state.id,
            courseId: values.courseId,
            courseName: values.coursename,
            startdate: values.startdate
        }
        
        MentorDataService.createMentor(username, mentor)
            .then(() => this.props.history.push(`/mentors`))   
    }

    //what to print out
    render() {
        let { courseId,coursename,startdate } = this.state
        
        return (
            <div>
                <h1>Mentor Application</h1>
                <div className="container">
                    <Formik
                        initialValues={{ courseId,coursename,startdate }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="courseId" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="courseName" component="div"
                                    className="alert alert-warning" />
                                    <ErrorMessage name="startdate" component="div"
                                    className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Course</label>
                                        <select
                                            className="form-control"
                                            onChange={this.handleCourseChange.bind(this)}
                                            onBlur={this.onBlurCourse.bind(this)}
                                            value={this.state.courseId}
                                            name="courseId"
                                        >
                                            <option value=''>Select a course</option>
                                            {
                                                this.state.courses.map(
                                                    course =>
                                                        <option key={course.id} data-key={course.id} value={course.courseId}>{course.coursename}</option>
                                                )
                                            }
                                        </select>
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Apply</button>
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default MentorComponent