import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import GroupService from '../../api/todo/GroupService.js'
import CourseDataService from '../../api/todo/CourseDataService.js'
import AuthenticationService from './AuthenticationService.js'

class CreateGroupComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            courses: [],
            id: this.props.match.params.id,
            groupName:'',
            courseId: '',
            courseName: '',
            numberOfMember:''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    componentDidMount() {
        let username = AuthenticationService.getLoggedInUserName()
        CourseDataService.retrieveAllCourses(username) 
        .then(response => this.setState({courses: response.data})
        )
    }

    validate(values) {
        let errors = {}
        if (!values.groupName) {
            errors.groupName = 'Input a Group Name'
        }
        if (!values.courseId) {
            errors.courseId = 'Select a Course '
        }

        return errors
    }

    handleCourseChange = (e) => {
        let index = e.nativeEvent.target.selectedIndex;
        let label = e.nativeEvent.target[index].text;
        let value = e.target.value;

        this.setState({
            courseId:value, courseName:label});
    };

    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()

        let group = {
            id: this.state.id,
            courseId: values.courseId,
            courseName: values.courseName,
            groupName: values.groupName,
            members: username+',',
            numberOfMember: 1
        }

        GroupService.createGroup(" ", group)
            .then(() => this.props.history.push('/groups'))
    }

    render() {

        let { groupName, courseId, courseName } = this.state
        //let targetDate = this.state.targetDate

        return (
            <div>
                <h1>Create Study Group</h1>
                <div className="container">
                    <Formik
                        initialValues={{ groupName, courseId, courseName }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="groupName" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="courseName" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="courseId" component="div"
                                        className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Course</label>
                                        <select
                                            className="form-control"
                                            onChange={this.handleCourseChange.bind(this)}
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
                                        <label>Group Name</label>
                                        <Field className="form-control" type="text" name="groupName" />
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Create</button>
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default CreateGroupComponent