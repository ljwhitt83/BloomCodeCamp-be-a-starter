import React, {useState} from 'react';
import {createAssignment, getUserAssignments} from "../Services/AssignmentService.js";
import {useNavigate} from "react-router-dom";

const LearnerAssignmentComponent = () => {

    const navigate = useNavigate();
    const assignmentNumber = 1;
    const gitHubUrl = useState('');
    const branchUrl= useState('');

    function newAssignment() {
        const assignmentParts = {assignmentNumber, gitHubUrl, branchUrl};

        createAssignment(assignmentParts).then((response) => {
            console.log(response.data);
            navigate('/learner')
        }).then((error) => {
            console.error(error);
        })
    }



    function returnHome() {
        navigate('/learner')
    }


    return (
        <div className={'container-fluid'}>
            <h1>Assignment submission</h1>
            <div className={'card'}>
                <div className={'card-header'} style={{textAlign: "center"}}>
                    <h3>Assignment {assignmentNumber}</h3>
                </div>
                <div className={'card-body'}>
                    <form className={'row gy-2 gx-3 align-items-center'}>
                        <div className={'col-auto'}>
                            <label className={'visually-hidden-focusable'}>
                                GitHub URL
                            </label>
                            <input type={'text'}
                                    className={'form-control'}
                                   placeholder={'GitHub URL'} />
                        </div>
                        <div className={'col-auto'}>
                            <label className={'visually-hidden-focusable'}>
                                Branch URL
                            </label>
                            <input type={'text'}
                                    className={'form-control'}
                                    placeholder={'Branch URL'}
                            />
                        </div>
                        <div className={'col-auto'}>
                            <button type={'submit'}
                                    className={'btn btn-primary'}
                                    onClick={newAssignment}
                            >
                                Submit
                            </button>
                        </div>
                        <div className={'col-auto'}>
                            <button type={'button'}
                                    className={'btn btn-danger'}
                                    onClick={returnHome}>
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default LearnerAssignmentComponent;