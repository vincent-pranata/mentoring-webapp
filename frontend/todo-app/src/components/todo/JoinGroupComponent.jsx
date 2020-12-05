import React, { Component } from 'react'
import { Formik, Form, ErrorMessage } from 'formik';
import GroupService from '../../api/todo/GroupService.js'
import CourseDataService from '../../api/todo/CourseDataService.js'
import AuthenticationService from './AuthenticationService.js'

class JoinGroupComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            courses: [],
            groups: [],
            id: this.props.match.params.id,
            isJoin: false,

            groupId:'',
            groupName:'',
            courseId: '',
            courseName: '',
            members:'',
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

        if(this.state.courseId!=="")
        {
            GroupService.getActiveGroups(this.state.courseId)
            .then(response => this.setState({groups:response.data})
                )   
        }
    }

    validate(values) {
        let errors = {} 
        if (!values.groupName) {
            errors.groupName = 'Select a Group'
        }
        if (!values.courseId) {
            errors.courseId = 'Select a Course'
        }

        if (this.state.isJoin===true) {
            errors.courseId = 'You have joined this group'
        }

        return errors

    }
    
    onBlurCourse() {
        this.componentDidMount();
    }

    onBlurGroup() {
        let username = AuthenticationService.getLoggedInUserName()

        GroupService.isJoined(username, this.state.groupId)
        .then(response => this.setState({isJoin: response.data})
            )

        GroupService.retrieveGroup(username, this.state.groupId)
        .then(response => this.setState({
            numberOfMember: response.data.numberOfMember,
            members: response.data.members})
        )
    }

    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()

        let group = {
            id: this.state.groupId,
            courseId: values.courseId,
            courseName: values.courseName,
            groupName: values.groupName,
            members: this.state.members+username+',',
            numberOfMember: parseInt(this.state.numberOfMember, 10)+1
        }

        GroupService.updateGroup(" ", this.state.groupId, group)
            .then(() => this.props.history.push('/groups'))
    }

    handleCourseChange = (e) => {
        let index = e.nativeEvent.target.selectedIndex;
        let label = e.nativeEvent.target[index].text;
        let value = e.target.value;

        this.setState({
            courseId:value, courseName:label});
    };

    handleGroupChange = (e) => {
        let index = e.nativeEvent.target.selectedIndex;
        let label = e.nativeEvent.target[index].text;
        let value = e.target.value;

        this.setState({groupId:value, groupName:label});
    };
    
    render() {

        let {  groupName,courseId,courseName} = this.state
        
        return (
            <div>
                <h1>Study Group Application</h1>
                <div className="container">
                    <Formik
                        initialValues={{ groupName,courseId,courseName}}
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
                                    <ErrorMessage name="courseId" component="div"
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
                                        <label>Group</label>
                                        <select
                                            className="form-control"
                                            onChange={this.handleGroupChange.bind(this)}
                                            onBlur={this.onBlurGroup.bind(this)}
                                            value={this.state.group}
                                            name="mentor"
                                        >
                                            <option value=''>Select a group</option>
                                            {
                                                this.state.groups.map(
                                                    group =>
                                                        <option key={group.id} value={group.id}>{group.groupName}</option>
                                                )
                                            }
                                        </select>
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Join</button>
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default JoinGroupComponent