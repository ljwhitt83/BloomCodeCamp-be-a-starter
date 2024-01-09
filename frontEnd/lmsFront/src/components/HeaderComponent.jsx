import React from 'react';
import {useNavigate} from "react-router-dom";

const  HeaderComponent = () => {

    const navigate = useNavigate();
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    function login() {
        navigate('/login');
    }

    function register() {
        navigate('/register');
    }

    function dashBoard() {
        navigate('/dashboard');
    }
    return (
        <div className='nav-header'>
                <nav className="navbar navbar-expand-lg bg-body-tertiary">
                    <div className="container-fluid">

                        <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div className="navbar-nav">

                                <a className="nav-link active" aria-current="page" href="/">Home</a>
                                {!isLoggedIn && (
                                    <>
                                        <a className="nav-link active" onClick={login}>Login</a>
                                        <a className="nav-link active" onClick={register}>Register</a>
                                    </>
                            )}
                                <a className="nav-link" onClick={dashBoard}></a>
                            </div>
                        </div>
                    </div>
                </nav>
        </div>
    )
}

export default HeaderComponent;