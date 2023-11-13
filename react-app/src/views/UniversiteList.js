import React, { Component } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

class UniversiteList extends Component {
  state = {
    universities: [],
    formData: {
      nomUniv: "",
      adresse: "",
    },
  };

  componentDidMount() {
    this.fetchUniversities();
  }

  fetchUniversities = () => {
    axios
      .get("http://localhost:8089/Kaddem/universite/retrieve-all-universites")
      .then((response) => {
        this.setState({ universities: response.data });
        toast.info("Welcome to the university list");
      })
      .catch((error) => {
        console.error("Error fetching universities:", error);
      });
  };

  handleFormChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      formData: {
        ...prevState.formData,
        [name]: value,
      },
    }));
  };

  handleFormSubmit = (e) => {
    e.preventDefault();
    const { nomUniv, adresse } = this.state.formData;

    // Check if at least one of the fields has data
    if (!nomUniv && !adresse) {
      toast.error("Please enter University Name or University Address");
      return; // Prevent submitting the form if both fields are empty
    }

    const newUniversity = { nomUniv, adresse };

    axios
      .post(
        "http://localhost:8089/Kaddem/universite/add-universite",
        newUniversity
      )
      .then((response) => {
        const updatedUniversities = [...this.state.universities, response.data];
        this.setState({
          universities: updatedUniversities,
          formData: { nomUniv: "", adresse: "" },
        });
        toast.success("University added successfully");
      })
      .catch((error) => {
        console.error("Error adding university:", error);
        toast.error("Failed to add university");
      });
  };

  render() {
    const { universities, formData } = this.state;
    console.log('Received universities:', universities);
    return (
      <div className="content">
        <ToastContainer />
        <h2 className="mb-3">University List</h2>
        {/* Form to add a new university */}
        <form onSubmit={this.handleFormSubmit}>
          <div className="mb-3">
            <label htmlFor="nomUniv" className="form-label">
              University Name
            </label>
            <input
              type="text"
              className="form-control"
              id="nomUniv"
              name="nomUniv"
              value={formData.nomUniv}
              onChange={this.handleFormChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="adresse" className="form-label">
              University Address
            </label>
            <input
              type="text"
              className="form-control"
              id="adresse"
              name="adresse"
              value={formData.adresse}
              onChange={this.handleFormChange}
            />
          </div>
          <button type="submit" className="btn btn-primary">
            Add University
          </button>
        </form>
        <table className="table table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>University Name</th>
              <th>University Address</th>
            </tr>
          </thead>
          <tbody>
            {universities && Array.isArray(universities) ? (
              universities.map((university) => (
                <tr key={university.idUniversite}>
                  <td>{university.nomUniv}</td>
                  <td>{university.adresse}</td>
                </tr>
              ))
            ) : (
              <tr key="no-data">
                <td colSpan="2">No universities available</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    );
  }
}

export default UniversiteList;
