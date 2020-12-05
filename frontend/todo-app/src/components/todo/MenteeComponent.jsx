import React, { Component } from 'react'
import moment from 'moment'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import CourseDataService from '../../api/todo/CourseDataService.js'
import MenteeDataService from '../../api/todo/MenteeDataService.js'
import MentorDataService from '../../api/todo/MentorDataService.js'
import AuthenticationService from './AuthenticationService.js'

class MenteeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            courses: [],
            mentors: [],
            id: this.props.match.params.id,
            courseId: '',
            coursename: '',
            mentorId:'',
            mentorName:'',
            mentorEmail:'',
            isMentee:false,
            mentorDate: moment(new Date()).format('YYYY-MM-DD')
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

        if(this.state.courseId!=="")
        {
            //get lists of mentor based on course id
            MentorDataService.getActiveMentor(this.state.courseId)
            .then(response => this.setState({mentors:response.data})
                )   
        }
    }
    
    //what happen when not focusing on select course anymore
    onBlurCourse() {
        let username = AuthenticationService.getLoggedInUserName()
        
        //check if student already become a mentee of selected course id
        MenteeDataService.checkMentee(username, this.state.courseId)
        .then(response => this.setState({isMentee: response.data})
            )

        this.componentDidMount();
    }

    
    //validate the inputs
    validate(values) {
        let errors = {}
        if (!values.courseId) {
            errors.courseId = 'Select a Course'
        }

        if (!moment(values.mentorDate).isValid()) {
            errors.mentorDate = 'Enter a valid Mentoring Date'
        }

        if(!values.mentorId)
        {
            errors.courseId = 'Select a mentor'
        }

        if(this.state.isMentee===true)
        {
            errors.courseId = 'You are have been already registered as a mentee for the selected course'
        }
        
        return errors
    }

    //what happen when submit button is clicked
    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()
        
        let mentee = {
            id: this.state.id,
            courseId: values.courseId,
            courseName: values.coursename,
            mentorID: values.mentorId,
            mentorName: values.mentorName,
            mentorEmail: values.mentorName+"@gmail.com",
            mentorDate: values.mentorDate
        }
        MenteeDataService.createMentee(username, mentee)
            .then(() => this.props.history.push('/mentees'))
    }
    
    //handle the changes of select course
    handleCourseChange = (e) => {
        let index = e.nativeEvent.target.selectedIndex;
        let label = e.nativeEvent.target[index].text;
        let value = e.target.value;

        this.setState({
            courseId:value, coursename:label});
    };

    //handle the changes of select mentor
    handleMentorChange = (e) => {
        let index = e.nativeEvent.target.selectedIndex;
        let label = e.nativeEvent.target[index].text;
        let value = e.target.value;

        this.setState({
            mentorId:value, mentorName:label});
    };

    //what to print out
    render() {
        let { courseId, coursename,mentorId, mentorName, mentorEmail, mentorDate} = this.state
        
        return (
            <div>
                <h1>Mentee Application</h1>
                <div className="container">
                    <Formik
                        initialValues={{ courseId,coursename,mentorId, mentorName, mentorEmail, mentorDate}}
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
                                    <ErrorMessage name="mentorDate" component="div"
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
                                                        <option key={course.id} value={course.courseId}>{course.coursename}</option>
                                                )
                                            }
                                        </select>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Mentor</label>
                                        <select
                                            className="form-control"
                                            onChange={this.handleMentorChange.bind(this)}
                                            value={this.state.mentor}
                                            name="mentor"
                                        >
                                            <option value=''>Select a mentor</option>
                                            {
                                                this.state.mentors.map(
                                                    mentor =>
                                                        <option key={mentor.id} value={mentor.id}>{mentor.username}</option>
                                                )
                                            }
                                        </select>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Mentoring Date</label>
                                        <Field className="form-control" type="date" name="mentorDate" />
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

export default MenteeComponent