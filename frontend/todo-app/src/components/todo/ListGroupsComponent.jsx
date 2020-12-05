import React, { Component } from 'react'
import GroupService from '../../api/todo/GroupService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'

class ListJoinGroupsComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            groups: [],

            groupName:'',
            courseId: '',
            courseName: '',
            members:'',
            updatedMember: '',
            numberOfMember:'',
            message: null
        }
        this.deleteGroupClicked = this.deleteGroupClicked.bind(this)
        this.onMouseEnter = this.onMouseEnter.bind(this)
        this.createStudyGroupClicked = this.createStudyGroupClicked.bind(this)
        this.joinStudyGroupClicked = this.joinStudyGroupClicked.bind(this)
        this.refreshGroups = this.refreshGroups.bind(this)
    } 

    componentWillUnmount() {
    }

    shouldComponentUpdate(nextProps, nextState) {
        return true
    }

    componentDidMount() {
        this.refreshGroups();
    }

    //refresh the page when something is updated, clicked, and deleted
    refreshGroups() {
        let username = AuthenticationService.getLoggedInUserName()
        GroupService.retrieveAllGroups(username)
            .then(
                response => {
                    this.setState({ groups: response.data })
                }
            )
    }

    //what happen when mouse enter leave button
    async onMouseEnter(id){
        let username = AuthenticationService.getLoggedInUserName()
        await GroupService.retrieveGroup(username, id)
        .then(response => this.setState({
            groupName: response.data.groupName,
            courseId: response.data.courseId,
            courseName: response.data.courseName,
            numberOfMember: response.data.numberOfMember,
            members: response.data.members})
        )
    }

    //what happens when delete button is clicked
    async deleteGroupClicked(id) {
        let username = AuthenticationService.getLoggedInUserName()
        await GroupService.leaveGroup(username, id)
        .then(response => this.setState({
            members: response.data})
        )

        let group = {
            id: id,
            courseId: this.state.courseId,
            courseName: this.state.courseName,
            groupName: this.state.groupName,
            members: this.state.members,
            numberOfMember: parseInt(this.state.numberOfMember, 10)-1
        }

        GroupService.updateGroup(" ", id, group)
            .then(response => {
                this.setState({ message: `Successfully left group ${id}` })
                this.refreshGroups()}
            )
        if(group.numberOfMember===0){
            GroupService.deleteGroup(username, id)
                .then(
                    response => {
                        this.setState({ message: `Group ${id} is deleted because no member is left` })         
                        this.refreshGroups() 
                    }
                )
        }
    }

    //what happens when join button is clicked
    joinStudyGroupClicked() {
        this.props.history.push(`/joingroups/-1`)
    }

    //what happens when create button is clicked
    createStudyGroupClicked() {
        this.props.history.push(`/creategroups/-1`)
    }

    //what to print out
    render() {
        return (
            <div>
                <h1>Study Groups</h1>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Group Name</th>
                                <th>Course ID</th>
                                <th>Course Name</th>
                                <th>Number of Member</th>
                                <th>Start Date</th>
                                <th>Leave</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.groups.map(
                                    group =>
                                        <tr key={group.id}>
                                            <td>{group.groupName}</td>
                                            <td>{group.courseId}</td>
                                            <td>{group.courseName}</td>
                                            <td>{group.numberOfMember}</td>
                                            <td>{moment(group.startdate).format('YYYY-MM-DD')}</td>
                                            <td><button className="btn btn-warning" onMouseEnter={()=> this.onMouseEnter(group.id)} onClick={() => this.deleteGroupClicked(group.id)}>Leave</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.createStudyGroupClicked}>Create</button>   
                        <button className="btn btn-success" onClick={this.joinStudyGroupClicked} style={({ marginLeft: '0.8rem' })} >Join</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ListJoinGroupsComponent