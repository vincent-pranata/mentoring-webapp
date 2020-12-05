import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import CourseDataService from '../../api/todo/CourseDataService.js'
import AuthenticationService from './AuthenticationService.js'

class CourseUpdateComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            courseId: '',
            coursename: '',
            completed: '',
            grade: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    componentDidMount() {
        //get the value of updated course and set it
        let username = AuthenticationService.getLoggedInUserName()
        CourseDataService.retrieveCourse(username, this.state.id)
            .then(response => this.setState({
                courseId: response.data.courseId,
                coursename: response.data.coursename,
                grade: response.data.grade
            }))
    }

    //validate the values
    validate(values) {
        let errors = {}
        if (!values.courseId) {
            errors.courseId = 'Enter a course ID'
        } 

        if (!values.coursename) {
            errors.coursename = 'Enter a course name'
        }

        if(values.completed!=='false' && values.completed!=='true'){
            errors.completed = 'Please select a status for is completed'
        }

        if(values.completed==='false' && values.grade>0){
            errors.grade = 'Please change is completed status to true'
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

    //what happen when submitted
    onSubmit(values) {
        var val = this.getRadioVal(document.getElementById('updatecourse'), 'completed')
        let username = AuthenticationService.getLoggedInUserName()
        let course = {
            id: this.state.id,
            courseId: values.courseId,
            coursename: values.coursename,
            completed: val,
            grade: values.grade
        }

        CourseDataService.updateCourse(username, this.state.id, course)
            .then(() => this.props.history.push('/courses'))
    }

    //what to print out
    render() {

        let { courseId, coursename, completed, grade} = this.state

        return (
            <div>
                <h1>Add Course</h1>
                <div className="container">
                    <Formik
                        initialValues={{ courseId, coursename, completed, grade}}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form class='updatecourse' id='updatecourse'>
                                    <ErrorMessage name="courseId" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="coursename" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="completed" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="grade" component="div"
                                        className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Course ID</label>
                                        <Field className="form-control" type="text" name="courseId" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Course Name</label>
                                        <Field className="form-control" type="text" name="coursename" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Is Completed</label><br/>
                                        <Field type="radio" name="completed" value="true"/> True <br/> 
                                        <Field type="radio" name="completed" value="false"/> False
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Grade</label>
                                        <Field className="form-control" type="number" name="grade" min="0" max="100"/>
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Save</button>
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default CourseUpdateComponent