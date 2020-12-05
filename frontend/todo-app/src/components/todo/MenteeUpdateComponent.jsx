import React, { Component } from 'react'
import moment from 'moment'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import MenteeDataService from '../../api/todo/MenteeDataService.js'
import CourseDataService from '../../api/todo/CourseDataService.js'
import AuthenticationService from './AuthenticationService.js'

class MenteeUpdateComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            courses: [],
            id: this.props.match.params.id,
            courseId: '',
            courseName: '',
            mentorDate: moment(new Date()).format('YYYY-MM-DD'),
            isCompleted: '',
            mentorID:'',
            mentorEmail:'',
            mentorName:''
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

        //get and set mentee's detail
        MenteeDataService.retrieveMentee(username, this.state.id)
            .then(response => this.setState({
                courseId: response.data.courseId,
                courseName: response.data.courseName,
                mentorDate: moment(response.data.mentorDate).format('YYYY-MM-DD'),
                mentorID: response.data.mentorID,
                mentorEmail: response.data.mentorEmail,
                mentorName: response.data.mentorName
            }))
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

        if(values.isCompleted!=='false' && values.isCompleted!=='true'){
            errors.isCompleted = 'Please select a status for is completed'
        }

        return errors
    }

    //get value of radio button
    getRadioVal(form, name) {
        var val;
        // get list of radio buttons with specified name
        var radios = form.elements[name];
        
        // loop through list of radio buttons
        for (var i=0, len=radios.length; i<len; i++) {
            if ( radios[i].checked ) { // radio checked?
                val = radios[i].value; // if it is, hold its value in val
                break; // and break out of for loop
            }
        }
        return val; // return value of checked radio or undefined if none checked
    }

    //handle changes for select course
    handleChange = (e) => {
        let index = e.nativeEvent.target.selectedIndex;
        let label = e.nativeEvent.target[index].text;
        let value = e.target.value;

        this.setState({
            courseId:value, courseName:label});
    };

    //what happen when submitted
    onSubmit(values) {
        var val = this.getRadioVal(document.getElementById('updatementee'), 'isCompleted')
        let username = AuthenticationService.getLoggedInUserName()
        let mentee = {
            id: this.state.id,
            courseId: values.courseId,
            courseName: values.courseName,
            mentorDate: values.mentorDate,
            isCompleted: val,
            mentorID:this.state.mentorID,
            mentorEmail: this.state.mentorEmail,
            mentorName: this.state.mentorName
        }

        MenteeDataService.updateMentee(username, this.state.id, mentee)
            .then(() => this.props.history.push('/mentees'))
    }

    render() {
        let { courseId, courseName,mentorDate, isCompleted} = this.state
        
        return (
            <div>
                <h1>Mentee Application</h1>
                <div className="container">
                    <Formik
                        initialValues={{ courseId,courseName,mentorDate, isCompleted}}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form class='updatementee' id='updatementee'>
                                    <ErrorMessage name="courseId" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="courseName" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="mentorDate" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="isCompleted" component="div"
                                        className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Course</label>
                                        <select
                                            className="form-control"
                                            onChange={this.handleChange.bind(this)}
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
                                        <label>Mentoring Date</label>
                                        <Field className="form-control" type="date" name="mentorDate" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Is Completed</label><br/>
                                        <Field type="radio" name="isCompleted" value="true"/> True <br/> 
                                        <Field type="radio" name="isCompleted" value="false"/> False
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

export default MenteeUpdateComponent