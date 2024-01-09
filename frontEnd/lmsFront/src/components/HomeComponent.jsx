import React, {useEffect, useState} from 'react';
import RoleView from "./RoleView.jsx";

import LearnerRoleView from "../RoleViews/LearnerRoleView.jsx";
import CodeReviewRoleView from "../RoleViews/CodeReviewRoleView.jsx";
import AdminRoleView from "../RoleViews/AdminRoleView.jsx";
import LoginComponent from "./LoginComponent.jsx";


const HomeComponent = () => {

    const [currentUserRole] = useState('CodeReview');

    useEffect(() => {
        console.log("current user role: ", currentUserRole);
    }, [currentUserRole]);




    return (
        <div className='container-fluid'>
            <h1>Welcome to the Assignment Review App!</h1>
            <RoleView
                roles={['User', 'Learner', 'CodeReview', 'Admin']}
                userRole={currentUserRole}
                User={<LoginComponent />}
                Learner={<LearnerRoleView />}
                CodeReview={<CodeReviewRoleView />}
                Admin={<AdminRoleView />}
            />
        </div>
    );
};

export default HomeComponent;