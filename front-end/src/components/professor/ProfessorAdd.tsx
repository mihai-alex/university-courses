import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom"; // useNavigate instead of useHistory in React Router 6 - redirect to another page
import ProfessorService from "../../services/ProfessorService";

const ProfessorAdd = () => {
  const [professor, setProfessor] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    salary: "",
  });

  const { firstName, lastName, email, phone, salary } = professor; // destructuring

  const onInputChange = (e: any) => {
    // spread operator to copy the existing values of professor object and then
    // update the value of the property whose name is the same as the name attribute of the input field
    setProfessor({ ...professor, [e.target.name]: e.target.value });
  };

  const navigate = useNavigate(); // used to redirect to another page after form submission

  const saveProfessor = (e: any) => {
    e.preventDefault(); // So that the page does not refresh on submit button click event (default behavior of submit button)
    const professor = {
      firstName,
      lastName,
      email,
      phone,
      salary: parseInt(salary),
    };
    ProfessorService.createProfessor(professor)
      .then((response) => {
        console.log(response);
        navigate("/professors"); // redirect to another page
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
            <h2 className="text-center m-4">Add Professor</h2>
            <div className="mb-3 text-center">
              <div className="mb-3">
                <label className="form-label">First name</label>
                <input
                  type="text"
                  placeholder="Enter first name"
                  name="firstName"
                  className="form-control"
                  value={firstName}
                  onChange={(e) => onInputChange(e)}
                ></input>
              </div>

              <div className="mb-3">
                <label className="form-label">Last name</label>
                <input
                  type="text"
                  placeholder="Enter last name"
                  name="lastName"
                  className="form-control"
                  value={lastName}
                  onChange={(e) => onInputChange(e)}
                ></input>
              </div>

              <div className="mb-3">
                <label className="form-label">Email</label>
                <input
                  type="text"
                  placeholder="Enter email"
                  name="email"
                  className="form-control"
                  value={email}
                  onChange={(e) => onInputChange(e)}
                ></input>
              </div>

              <div className="mb-3">
                <label className="form-label">Phone</label>
                <input
                  type="text"
                  placeholder="Enter phone"
                  name="phone"
                  className="form-control"
                  value={phone}
                  onChange={(e) => onInputChange(e)}
                ></input>
              </div>

              <div className="mb-3">
                <label className="form-label">Salary</label>
                <input
                  type="text"
                  placeholder="Enter salary"
                  name="salary"
                  className="form-control"
                  value={salary}
                  onChange={(e) => onInputChange(e)}
                ></input>
              </div>

              <button
                type="submit"
                className="btn btn-outline-primary mx-2"
                onClick={(e) => saveProfessor(e)}
              >
                Submit
              </button>

              <Link to="/professors" className="btn btn-outline-danger mx-2">
                Cancel
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfessorAdd;
