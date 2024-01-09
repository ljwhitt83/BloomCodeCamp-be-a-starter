// eslint-disable-next-line no-unused-vars
import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {fetchAuthorities, register} from "../Services/UserService.js";


const RegisterComponent = () => {
    const [username, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [authority, setAuthority] = useState(null);
    const [authorities, setAuthorities] = useState([]);


    const [errors, setErrors] = useState({
        username: '',
        password: '',
        authority: ''
    });

    const navigate = useNavigate();

    useEffect(() => {
        fetchAuthorities()
            .then((data) => setAuthorities(data))
            .catch((error) => console.error('Error fetching authorities:  ',error));
    }, []);


function saveEmployee(e) {
    e.preventDefault();

    if(validateForm()) {
        const user = {username, password, authority: authority.name};
        console.log(user);

        register(user).then((response) => {
            console.log(response.data);
            navigate('/login');
        }).then((error) => {
            console.error(error);
        })
    }
}

function validateForm() {
    let valid = true;

    const errorsCopy = {... errors};    //copy of errors object

    if(username.trim()) {
        errorsCopy.username = '';
    }else {
        errorsCopy.username= 'You must enter a username';
        valid = false;
    }

    setErrors(errorsCopy);
    return valid;
}


function loginLink() {
    navigate('/login')
}

    return (
        <div className='container'>
            <br/>
            <br/>

            <div className='card col-md-6 offset-md-3 offset-md-3'>
                <h2>Register for access</h2>
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>
                                User Name:
                            </label>
                            <input
                                type='text'
                                placeholder='Enter your user name'
                                name='username'
                                value={username}
                                className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                onChange={(event) => setUserName(event.target.value)}
                            >
                            </input>
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>
                                Password:
                            </label>
                            <input
                                type='text'
                                placeholder='Enter your password'
                                name='password'
                                value={password}
                                className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                onChange={(event) => setPassword(event.target.value)}
                            >
                            </input>
                        </div>
                        {/*                        <div className='form-group mb-2'>
                            <label className='form-label'>Authority: </label>
                            <select
                            value={authority}
                            onChange={(e) => setAuthority(e.target.value)}
                            className={`form-control ${errors.authority ? 'is-invalid' : ''}`}>
                                <option value="">Select your role: </option>
                                {authorities.map((auth) => (
                                    <option key={auth}>{auth}</option>
                                ))}
                            </select>

                        </div>*/}


                        <button className='btn btn-success' onClick={saveEmployee}>Submit</button>
                        <button className='btn btn-primary' style={{margin: '5px'}} onClick={loginLink}>Login Page</button>

                    </form>

                </div>
            </div>


        </div>
    );
};

export default RegisterComponent;