import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService.js'
import GoogleLogin from 'react-google-login'
class LoginComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            username: 's3665858',
            password: 'dummy',
            hasLoginFailed: false,
            showSuccessMessage: false
        }

        this.handleChange = this.handleChange.bind(this)
        this.onClick = this.onClick.bind(this)
        this.responseGoogle = this.responseGoogle.bind(this)
    }

    handleChange(event) {
        //console.log(this.state);
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    responseGoogle(response) {
        var profile = response.getBasicProfile();
        // console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        // console.log('Name: ' + profile.getName());
        // console.log('Image URL: ' + profile.getImageUrl());
        // console.log('Email: ' + profile.getEmail()); 
        // console.log("RESPONSE", response);

        AuthenticationService
            .executeJwtAuthenticationService(response.profileObj.givenName, profile.getId())
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt(response.profileObj.givenName, response.accessToken)
                this.props.history.push(`/welcome/${response.profileObj.givenName}`)
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasLoginFailed: true })
                // AuthenticationService.createUser(response.profileObj.givenName, profile.getId())
            })
    }
    
    onClick(){
        AuthenticationService
            .executeJwtAuthenticationService(this.state.username, this.state.password)
            .then((response) => {
                AuthenticationService.registerSuccessfulLoginForJwt(this.state.username, response.data.token)
                this.props.history.push(`/welcome/${this.state.username}`)
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasLoginFailed: true })
            })
    }

    render() {
        return (
            <div>
                <h1>Login</h1>
                {/* {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Google Account</div>}
                {this.state.showSuccessMessage && <div>Login Sucessful</div>}
                <p><GoogleLogin
                    clientId="523292681676-qqq33easshjp33p16ou9hk4buhh17enf.apps.googleusercontent.com"
                    buttonText="Login"
                    onSuccess={this.responseGoogle}
                    data-onsuccess={this.responseGoogle}
                    onFailure={this.responseGoogle}
                    cookiePolicy={'single_host_origin'}
                 /></p> */}
                 <div className="container">
                    {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials or something is wrong</div>}
                    {this.state.showSuccessMessage && <div>Login Sucessful</div>}
                    User Name: <input type="text" name="username" value={this.state.username} onChange={this.handleChange} />
                    Password: <input type="password" name="password" value={this.state.password} onChange={this.handleChange} />
                    <p><button className="btn btn-success" onClick={this.onClick}>Login</button></p>
                </div>
            </div>
        )
    }
}

export default LoginComponent