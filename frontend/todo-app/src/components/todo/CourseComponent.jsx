import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import CourseDataService from '../../api/todo/CourseDataService.js'
import AuthenticationService from './AuthenticationService.js'

class CourseComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            courseId: '',
            coursename: '',
            courseExist:false
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    componentDidMount() {
    }

    //what happen when not focusing on course name input anymore
    setValidationValues() {
        let username = AuthenticationService.getLoggedInUserName()

        //get the boolean value of wheter course is added or not
        CourseDataService.checkCourse(username, this.state.courseId)
        .then(response => this.setState({courseExist: response.data})
            )
    }

    //validate the inputs
    validate(values) {
        let errors = {}
        if (!values.courseId) {
            errors.courseId = 'Enter a course ID'
        }

        if (!values.coursename) {
            errors.coursename = 'Enter a course name'
        }

        if (this.state.courseExist===true) {
            errors.courseId = 'Course ID already existed! Please try again'
        } 

        return errors

    }

    //what happen when submit button is clicked
    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()

        let course = {
            id: this.state.id,
            courseId: values.courseId,
            coursename: values.coursename
        }

        CourseDataService.createCourse(username, course)
            .then(() => this.props.history.push('/courses'))
    }

    //what happen when not focusing on course id input
    onBlur = (event) => {
        this.setState({courseId: event.target.value});
    }

    //what to print out
    render() {

        let { courseId, coursename } = this.state
        
        return (
            <div>
                <h1>Add Course</h1>
                <div className="container">
                    <Formik
                        initialValues={{ courseId, coursename }}
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
                                    <ErrorMessage name="coursename" component="div"
                                        className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Course ID</label>
                                        <Field className="form-control" type="text" name="courseId" onBlur={this.onBlur.bind(this)}/>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Course Name</label>
                                        <Field className="form-control" type="text" name="coursename" onBlur={this.setValidationValues.bind(this)}/>
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

export default CourseComponent