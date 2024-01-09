import React from 'react';

import LearnerRoleView from "../RoleViews/LearnerRoleView.jsx";
import CodeReviewRoleView from "../RoleViews/CodeReviewRoleView.jsx";
import AdminRoleView from "../RoleViews/AdminRoleView.jsx";
import LoginComponent from "./LoginComponent.jsx";

const RoleView = ({ userRole }) => { // Receive userRole as a prop
    const roleViews = {
        User: LoginComponent,
        Learner: LearnerRoleView,
        CodeReview: CodeReviewRoleView,
        Admin: AdminRoleView,
    };

    const ViewComponent = roleViews[userRole]; // Look up the component to render

    if (!ViewComponent) {
        console.error('Error: No matching role or view found.');
        console.error('userRole:', userRole);
        return <div>Something went wrong.</div>;
    }

    return <ViewComponent />;
};
export default RoleView;