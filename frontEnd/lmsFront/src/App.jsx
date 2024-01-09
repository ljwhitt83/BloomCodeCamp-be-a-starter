import './App.css'
import FooterComponent from "./components/FooterComponent.jsx";
import DashboardComponent from "./components/DashboardComponent.jsx";
import HeaderComponent from "./components/HeaderComponent.jsx";
import {Routes, Route, BrowserRouter} from "react-router-dom";
import HomeComponent from "./components/HomeComponent.jsx";
import LoginComponent from "./components/LoginComponent.jsx";
import RegisterComponent from "./components/RegisterComponent.jsx";
import UserViewRole from "./RoleViews/UserViewRole.jsx";
import LearnerRoleView from "./RoleViews/LearnerRoleView.jsx";
import CodeReviewRoleView from "./RoleViews/CodeReviewRoleView.jsx";
import AdminRoleView from "./RoleViews/AdminRoleView.jsx";
import LearnerAssignmentComponent from "./components/LearnerAssignmentComponent.jsx";

function App() {

  return (
    <>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomeComponent />}></Route>
                <Route path="/dashboard" element={<DashboardComponent />}></Route>
                <Route path="/login" element={<LoginComponent />}></Route>
                <Route path="/register" element={<RegisterComponent />}></Route>
                <Route path="/learner" element={<LearnerRoleView />} />
                <Route path="/code-review" element={<CodeReviewRoleView />} />
                <Route path="/admin" element={<AdminRoleView />} />
                <Route path="/" element={<UserViewRole />} />
                <Route path={"/submitAssignment"} element={<LearnerAssignmentComponent />} />
            </Routes>
            <FooterComponent />
        </BrowserRouter>
    </>
  )
}

export default App
