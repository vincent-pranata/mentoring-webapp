import React, { Component } from 'react'
import MenteeDataService from '../../api/todo/MenteeDataService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'
import Popup from "reactjs-popup"
import { Formik, Form, Field} from 'formik';
import MentorDataService from '../../api/todo/MentorDataService.js'

class ListMenteesComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            mentees: [],
            message: null,
            rating: '',
            input:'',
            empty:true,
            open:false,

            courseId:'',
            courseName:'',
            startDate:'',
            totalRate:0.0,
            numOfMentee:0.0
        }
        this.deleteMenteeClicked = this.deleteMenteeClicked.bind(this)
        this.updateMenteeClicked = this.updateMenteeClicked.bind(this)
        this.addMenteeClicked = this.addMenteeClicked.bind(this)
        this.refreshMentees = this.refreshMentees.bind(this)
        this.updateMentor = this.updateMentor.bind(this)
        this.onClickUpdate = this.onClickUpdate.bind(this)
        this.closePopup = this.closePopup.bind(this)
        this.searchMentorClicked = this.searchMentorClicked.bind(this)
    }

    componentWillUnmount() {
    }

    shouldComponentUpdate(nextProps, nextState) {
        return true
    }

    componentDidMount() {
        this.refreshMentees();
    }

    //refresh the page when something is updated, clicked, and deleted
    refreshMentees() {
        let username = AuthenticationService.getLoggedInUserName()
        MenteeDataService.retrieveAllMentees(username)
            .then(
                response => {
                    this.setState({ mentees: response.data })
                }
            )
    }

    //what happens when delete button is clicked
    deleteMenteeClicked(id) {
        let username = AuthenticationService.getLoggedInUserName()

        MenteeDataService.deleteMentee(username, id)
            .then(
                response => {
                    this.setState({ message: `Delete of mentee ${id} Successful` })
                    this.refreshMentees()
                }
            )
    }

    //what happens when update button is clicked
    updateMenteeClicked(id) {
        this.props.history.push(`update/mentees/${id}`)
    }

    //what happens when add button is clicked
    addMenteeClicked() {
        this.props.history.push(`/mentees/-1`)
    }

    searchMentorClicked(){
        if(this.state.empty===false) {
            this.props.history.push(`/mentorname/mentees/${this.state.input}`)
        }
        else{
            this.setState({message: "Please input a mentor name to use the search engine"});
        }
    }

    //what happend when submit button is clicked
    updateMentor(id){
        if(this.state.rating!=="") {
            if(this.state.rating>=0 && this.state.rating<=5){
                let mentor = {
                    id:this.state.mentorId,
                    courseId:this.state.courseId,
                    courseName:this.state.courseName,
                    startdate:this.state.startDate,
                    totalRate:parseInt(this.state.totalRate,10)+parseInt(this.state.rating,10),
                    totalMentee:parseInt(this.state.numOfMentee,10)+1,
                }

                MentorDataService.updateMentor(this.state.mentorName, this.state.mentorId, mentor) 
                    .then(() => this.setState({message: "Thank you for leaving a rating :)"}))
                this.deleteMenteeClicked(id)
                this.setState({open:false})
            }
        }
        else{
            this.setState({ message: "Please input a value for rating"})
        }
    }

    //handle the change input for rating 
    handleChange = (e) => {
        this.setState({
            rating:e.target.value});
    };

    //handle the change input for search engine 
    handleSearchChange = (e) => {
        this.setState({
            input:e.target.value, empty:false});
    };

    closePopup(){
        this.setState({open:false});
    }

    onClickUpdate(mentorName, mentorId) {
        this.setState({mentorName:mentorName, mentorId:mentorId, open:true});

        MentorDataService.retrieveMentor(mentorName, mentorId)
            .then(response=> this.setState({
                courseId:response.data.courseId, 
                courseName:response.data.courseName,
                startDate:moment(response.data.startdate).format('YYYY-MM-DD'),
                totalRate:response.data.totalRate, 
                numOfMentee:response.data.totalMentee
            }))
    }

    //what to print out
    render() {       
        return (
            <div>
                <h1>Apply As Mentee</h1>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Course ID</th>
                                <th>Course Name</th>
                                <th>Date</th>
                                <th>Mentor Name</th>
                                <th>IsCompleted?</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.mentees.map(
                                    mentee =>
                                    <tr key={mentee.id}>
                                        <td>{mentee.courseId}</td>
                                        <td>{mentee.courseName}</td>
                                        <td>{moment(mentee.mentorDate).format('YYYY-MM-DD')}</td>
                                        <td>
                                            <Popup trigger={<p>{mentee.mentorName}</p>}>
                                                <p>Mentor ID</p>
                                                <p>{mentee.mentorID}</p> 
                                                <p>Mentor Email</p>
                                                <p>{mentee.mentorEmail}</p> 
                                            </Popup>
                                        </td>
                                        <td>{mentee.isCompleted.toString()}</td>
                                        <td><button className="btn btn-success" onClick={() => this.updateMenteeClicked(mentee.id)}>Update</button></td>
                                        <td>
                                            {!mentee.isCompleted && <button className="btn btn-warning" onClick={() => this.deleteMenteeClicked(mentee.id)}>Cancel</button>}
                                            {mentee.isCompleted && <button className="btn btn-warning" onClick={() => this.onClickUpdate(mentee.mentorName, mentee.mentorID)}>Delete</button>}
                                                <Popup open={this.state.open} onClose={this.closePopup}>
                                                    <Formik>
                                                        <Form>
                                                            <fieldset className="form-group">
                                                                <label>Rating</label>
                                                                <Field className="form-control" onChange={this.handleChange.bind(this)} type="number" name="rating" min="0" max="5"/>
                                                            </fieldset>
                                                            <button className="btn btn-success" onClick={() => this.updateMentor(mentee.id)}>Submit</button>
                                                        </Form>
                                                    </Formik>
                                                </Popup>
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <fieldset className="form-group">
                            <button className="btn btn-success" onClick={this.addMenteeClicked}>Apply</button>
                        </fieldset>
                    </div>
                    <Formik>
                        <Form>
                            <div className="row">
                                <fieldset className="form-group">
                                    <Field name="input" placeholder="Input mentor's name" onChange={this.handleSearchChange.bind(this)} className="form-control" type="text"/>
                                </fieldset>
                            </div>
                            <div className="row">
                                <button className="btn btn-success" onClick={this.searchMentorClicked}>Search</button>
                            </div>
                        </Form>
                    </Formik>
                </div>
            </div>
        )
    }
}

export default ListMenteesComponent