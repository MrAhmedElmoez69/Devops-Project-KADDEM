import React, { Component } from "react";
import axios from "axios";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

class AddDepartement extends Component {
  state = {
    nomDepart: "",
    universiteId: "",
  };

  handleInputChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  handleAddDepartement = (e) => {
    e.preventDefault();

    const { nomDepart, universiteId } = this.state;

    // Check if at least one of the fields has data
    if (!nomDepart && !universiteId) {
      toast.error("Please enter Department Name or Universite ID");
      return; // Prevent submitting the form if both fields are empty
    }

    const data = {
      nomDepart: nomDepart,
      universite: universiteId ? [parseInt(universiteId, 10)] : null,
    };

    axios
      .post("http://localhost:8090/Kaddem/departement/add-departement", data)
      .then((response) => {
        toast.success("Department added successfully!");
        console.log("Department added:", response.data);
      })
      .catch((error) => {
        toast.error("Error adding department");
        console.error("Error adding department:", error);
      });
  };

  render() {
    const { nomDepart, universiteId } = this.state;

    return (
      <div className="content">
        <ToastContainer />
        <h1 className="mb-4">Add Department</h1>
        <Form onSubmit={this.handleAddDepartement}>
          <FormGroup>
            <Label for="nomDepart">Department Name</Label>
            <Input
              type="text"
              name="nomDepart"
              id="nomDepart"
              placeholder="Enter Department Name"
              value={nomDepart}
              onChange={this.handleInputChange}
            />
          </FormGroup>
          <FormGroup>
            <Label for="universiteId">Universite ID</Label>
            <Input
              type="text"
              name="universiteId"
              id="universiteId"
              placeholder="Enter Universite ID (optional)"
              value={universiteId}
              onChange={this.handleInputChange}
            />
          </FormGroup>
          <Button color="primary" type="submit">
            Add Department
          </Button>
        </Form>
      </div>
    );
  }
}

export default AddDepartement;