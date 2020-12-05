import React, { Component } from 'react'
import moment from 'moment'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import MenteeDataService from '../../api/todo/MenteeDataService.js'
import AuthenticationService from './AuthenticationService.js'
import MentorDataService from '../../api/todo/MentorDataService.js'

class SearchMentorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            mentors: [],
            courseId: '',
            coursename: '',
            mentorId:'',
            mentorName:this.props.match.params.input,
            mentorEmail:'',
            isMentee:false,
            mentorDate: moment(new Date()).format('YYYY-MM-DD')
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
    }

    componentDidMount() {
        //get list of courses of a mentor that is registered
        MentorDataService.retrieveAllMentors(this.state.mentorName) 
        .then(response => this.setState({mentors: response.data})
        )
    }
    
    //what happen when not focusing on select course anymore
    onBlurCourse() {
        let username = AuthenticationService.getLoggedInUserName()
        
        //check if student already become a mentee of selected course id
        MenteeDataService.checkMentee(username, this.state.courseId)
        .then(response => this.setState({isMentee: response.data})
            )
    }

    //validate the inputs
    validate(values) {
        let errors = {}
        if (!values.courseId) {
            errors.courseId = 'Select a Course ID'
        }

        if (!moment(values.mentorDate).isValid()) {
            errors.mentorDate = 'Enter a valid Mentoring Date'
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
        let key = e.nativeEvent.target[index].id;
        this.setState({
            mentorId:key, courseId:value, coursename:label});
    };

    //what to print out
    render() {
        let { courseId, coursename, mentorId, mentorName, mentorEmail, mentorDate} = this.state
        
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
                                                this.state.mentors.map(
                                                    mentor =>
                                                    <option key={mentor.id} id={mentor.id} value={mentor.courseId}>{mentor.courseName}</option>
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

export default SearchMentorComponent