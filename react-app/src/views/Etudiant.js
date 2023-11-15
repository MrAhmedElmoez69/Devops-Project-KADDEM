import React, { Component } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

class EtudiantList extends Component {
  state = {
    students: [],
    formData: {
      prenomE: "",
      nomE: "",
      op: "",
    },
    programOptions: ["GAMIX", "SE", "SAE", "INFINI"],
    formValid: false,
  };

  componentDidMount() {
    this.fetchStudents();
  }

  fetchStudents = () => {
    axios
      .get("http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants")
      .then((response) => {
        this.setState({ students: response.data });
        toast.info("Welcome to the student list");
      })
      .catch((error) => {
        console.error("Error fetching students:", error);
      });
  };

  handleFormChange = (e) => {
    const { name, value } = e.target;
    this.setState(
      (prevState) => ({
        formData: {
          ...prevState.formData,
          [name]: value,
        },
      }),
      () => this.validateForm()
    );
  };

  validateForm = () => {
    const { prenomE, nomE, op } = this.state.formData;
    const formValid =
      prenomE.trim() !== "" && nomE.trim() !== "" && this.state.programOptions.includes(op);
    this.setState({ formValid });
  };

  handleFormSubmit = (e) => {
    e.preventDefault();
    const { formValid, formData } = this.state;

    if (formValid) {
      const newStudent = { ...formData };

      axios
        .post("http://localhost:8089/Kaddem/etudiant/add-etudiant", newStudent)
        .then((response) => {
          const updatedStudents = [...this.state.students, response.data];
          this.setState({
            students: updatedStudents,
            formData: { prenomE: "", nomE: "", op: "" },
          });
          toast.success("Student added successfully");
        })
        .catch((error) => {
          console.error("Error adding student:", error);
          toast.error("Failed to add student");
        });
    } else {
      toast.error("Please fill in all fields with valid values.");
    }
  };

  render() {
    const { students, formData, programOptions } = this.state;
    console.log('Received students:', students);
    return (
      <div className="content">
        <ToastContainer />
        <h2 className="mb-3">Student Management</h2>
        {/* Form to add a new student */}
        <form onSubmit={this.handleFormSubmit}>
          <div className="mb-3">
            <label htmlFor="prenomE" className="form-label">
              First Name
            </label>
            <input
              type="text"
              className="form-control"
              id="prenomE"
              name="prenomE"
              value={formData.prenomE}
              onChange={this.handleFormChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="nomE" className="form-label">
              Last Name
            </label>
            <input
              type="text"
              className="form-control"
              id="nomE"
              name="nomE"
              value={formData.nomE}
              onChange={this.handleFormChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="op" className="form-label">
              Program
            </label>
            <select
              className="form-control"
              id="op"
              name="op"
              value={formData.op}
              onChange={this.handleFormChange}
            >
              <option value="" disabled>
                Select Program
              </option>
              {programOptions.map((option) => (
                <option key={option} value={option}>
                  {option}
                </option>
              ))}
            </select>
          </div>
          <button type="submit" className="btn btn-primary" disabled={!this.state.formValid}>
            Add Student
          </button>
        </form>
        <table className="table table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Program</th>
            </tr>
          </thead>
          <tbody>
            {students && Array.isArray(students) ? (
              students.map((student) => (
                <tr key={student.idEtudiant}>
                  <td>{student.prenomE}</td>
                  <td>{student.nomE}</td>
                  <td>{student.op}</td>
                </tr>
              ))
            ) : (
              <tr key="no-data">
                <td colSpan="3">No students available</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    );
  }
}

export default EtudiantList;
