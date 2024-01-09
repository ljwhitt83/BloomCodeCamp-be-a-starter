import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {loginAction} from "../Services/UserService.js";


const LoginComponent = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loginError, setLoginError] = useState(null);

    const navigate = useNavigate();

    const handleLogin = async (event) => {
        event.preventDefault();
        try {
            const response = await loginAction(username, password);

            console.log('Login successful', response.data);
            navigate('/dashboard');
        } catch (error) {
            console.log(error);
            setLoginError('Invalid Credentials');
        }
    }

    function registerLink() {
        navigate('/register')
    }

    return (
        <div className='container-fluid'>
            <form>
                <div className="mb-3">
                    <label className="form-label" style={{padding: '10px'}}>UserName: </label>
                    <input type='text'
                           className='form-control'
                           placeholder=' Enter user name: '
                           name='username'
                           value={username}
                           onChange={(e) => setUsername(e.target.value)}>
                    </input>
                </div>
                <div className="mb-3 ">
                    <label className="form-label" style={{padding: '10px'}}>Password: </label>
                    <input type='text'
                           className='form-control'
                           placeholder='Enter password'
                           name='password'
                           value={password}
                           onChange={(e) => setPassword(e.target.value)}>
                    </input>
                </div>
                <button type='submit' style={{margin: '10px'}} className='btn btn-primary' onClick={handleLogin}>Login
                </button>
                {loginError && <div className='alert alert-danger'>{loginError}</div>}
            </form>

            <button type={'submit'} style={{margin: '10px'}} className={'btn btn-primary'} onClick={registerLink}>Have you registered?</button>


        </div>
    );
};

export default LoginComponent;