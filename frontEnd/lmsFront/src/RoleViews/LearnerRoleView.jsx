import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {getUserAssignments} from "../Services/AssignmentService.js";



const LearnerRoleView = () => {

    const [assignmentNumber, setAssignmentNumber] = useState('');
    const [gitHubUrl, setGitHubUrl] = useState('');
    const [branchUrl, setBranchUrl]= useState('');
    const [expandedIndex, setExpandedIndex] = useState([]);
    const navigate = useNavigate();
    const [currentAssignment, setCurrentAssignment] = useState('');

    const handleExpandClick = (index) => {
        setExpandedIndex((prevExpandedIndex) => {
            return prevExpandedIndex.includes(index)
                ? prevExpandedIndex.filter((i) => i !== index) : [...prevExpandedIndex, index];
        });
    };

    function submitAssignment() {
        navigate('/submitAssignment')

    }

    const viewAssignment = () => {
        getUserAssignments([
            assignmentNumber,
            gitHubUrl,
            branchUrl,
        ]).then((response) => {
            console.log(response.data);
            setCurrentAssignment(response.data);
        }).catch((error) => {
            console.error(error);
        });
    }

    return (
        <div className='container-fluid'>
            <div className={'card'} style={{display: 'flex', flexDirection: 'row'}}>
                <button className='btn btn-primary' style={{flex: 1}}
                        onClick={() => submitAssignment()}>
                    <h5>Submit Assignment</h5>
                </button>
                <button className='btn btn-danger' style={{flex: 1}}>
                    <h5>Logout</h5>
                </button>
            </div>

            <div className='row'>
                {['Needs Worked on', 'Completed Assignmensts', 'Assignments under review'].map((title, index) => (
                    <div className={'card'} key={index}>
                        <div className={'card-header'}>
                            <h5>{title}</h5>
                            <button onClick={() => handleExpandClick(index)}>
                                {expandedIndex.includes(index) ? '-' : '+'}
                            </button>
                            {index === 0 &&
                                (<button onClick={viewAssignment}>View Assignments</button>)}

                        </div>
                        <div className={'card-body'} style={{display: expandedIndex.includes(index) ? 'block' : 'none'}}>
                            {currentAssignment && index === 0 && (
                                <div>
                                    <h4>Assignment Number {currentAssignment.assignmentNumber}</h4>
                                    <p>GitHub URL: {currentAssignment.gitHubUrl}</p>
                                    <p>Branch URL: {currentAssignment.branchUrl}</p>
                                </div>
                            )}
                            <p> You will find your {title} here</p>
                        </div>

                    </div>

                    )
                )}
            </div>
        </div>
    );
};

export default LearnerRoleView;