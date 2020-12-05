import React, {Component} from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import LoginComponent from './LoginComponent.jsx'
import ListCoursesComponent from './ListCoursesComponent.jsx'
import ListMentorsComponent from './ListMentorsComponent.jsx'
import ListMenteesComponent from './ListMenteesComponent.jsx'
import ListGroupsComponent from './ListGroupsComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import LogoutComponent from './LogoutComponent.jsx'
import WelcomeComponent from './WelcomeComponent.jsx'
import CourseComponent from './CourseComponent.jsx'
import MentorComponent from './MentorComponent.jsx'
import MenteeComponent from './MenteeComponent.jsx'
import JoinGroupComponent from './JoinGroupComponent.jsx'
import CreateGroupComponent from './CreateGroupComponent.jsx'
import CourseUpdateComponent from './CourseUpdateComponent.jsx'
import MenteeUpdateComponent from './MenteeUpdateComponent.jsx'
import SearchMentorComponent from './SearchMentorComponent.jsx'

class TodoApp extends Component {
    render() {
        return (
            <div className="TodoApp">
                <Router>
                    <>
                        <HeaderComponent/>
                        <Switch>
                            <Route path="/" exact component={LoginComponent}/>
                            <Route path="/login" component={LoginComponent}/>
                            <AuthenticatedRoute path="/welcome/:name" component={WelcomeComponent}/>
                            <AuthenticatedRoute path="/courses/:id" component={CourseComponent}/>
                            <AuthenticatedRoute path="/courses" component={ListCoursesComponent}/>
                            <AuthenticatedRoute path="/update/courses/:id" component={CourseUpdateComponent}/>
                            <AuthenticatedRoute path="/mentors/:id" component={MentorComponent}/>
                            <AuthenticatedRoute path="/mentors" component={ListMentorsComponent}/>
                            <AuthenticatedRoute path="/mentees/:id" component={MenteeComponent}/>
                            <AuthenticatedRoute path="/mentorname/mentees/:input" component={SearchMentorComponent}/>
                            <AuthenticatedRoute path="/mentees" component={ListMenteesComponent}/>
                            <AuthenticatedRoute path="/update/mentees/:id" component={MenteeUpdateComponent}/>
                            <AuthenticatedRoute path="/joingroups/:id" component={JoinGroupComponent}/>
                            <AuthenticatedRoute path="/groups" component={ListGroupsComponent}/>
                            <AuthenticatedRoute path="/creategroups/-1" component={CreateGroupComponent}/>
                            <AuthenticatedRoute path="/logout" component={LogoutComponent}/>
                            <Route component={ErrorComponent}/>
                        </Switch>
                        <FooterComponent/>
                    </>
                </Router>
                {/*<LoginComponent/>
                <WelcomeComponent/>*/}
            </div>
        )
    }
}

export default TodoApp