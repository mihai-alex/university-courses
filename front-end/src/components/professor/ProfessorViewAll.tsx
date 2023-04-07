import React, { useEffect, useState } from "react";
import { Professor } from "../../models/Professor";
import ProfessorService from "../../services/ProfessorService";
import { Link } from "react-router-dom";

const ProfessorViewAll = () => {
  const [professors, setProfessors] = useState([]);

  useEffect(() => {
    ProfessorService.getAllProfessors()
      .then((response) => {
        setProfessors(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  if (professors.length === 0) {
    return (
      <div className="empty-list-message">
        <span>No professors to view</span>
        <Link to="add-professor" className="btn btn-primary mb-2">
          Add professor
        </Link>
      </div>
    );
  }

  return (
    <div className="container">
      <div className="py-4">
        <Link to="add-professor" className="btn btn-primary mb-2">
          Add professor
        </Link>
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Email</th>
              <th scope="col">Phone</th>
              <th scope="col">Salary</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            {professors.map((professor: Professor, index: number) => (
              <tr key={index}>
                <td>{index + 1}</td>
                <td>{professor.firstName}</td>
                <td>{professor.lastName}</td>
                <td>{professor.email}</td>
                <td>{professor.phone}</td>
                <td>{professor.salary}</td>
                <td>
                  <button className="btn btn-primary mx-2">View</button>
                  <button className="btn btn-outline-primary mx-2">Edit</button>
                  <button className="btn btn-danger mx-2">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ProfessorViewAll;
