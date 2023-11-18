import React, { Component } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Card, CardHeader, CardBody, CardFooter, CardTitle, Form, FormGroup, Label, Input, Button, Table } from 'reactstrap';

class UniversiteList extends Component {
  state = {
    universities: [],
    formData: {
      nomUniv: "",
      adresse: "",
    },
    errors: {},
    searchTerm: "",
  };

  componentDidMount() {
    this.fetchUniversities();
  }

  fetchUniversities = () => {
    axios
      .get("http://localhost:8090/Kaddem/universite/retrieve-all-universites")
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

  validateForm = () => {
    const errors = {};
    const { nomUniv, adresse } = this.state.formData;

    if (!nomUniv) {
      errors.nomUniv = 'University Name is required';
    }

    if (!adresse) {
      errors.adresse = 'University Address is required';
    }

    this.setState({ errors });
    return Object.keys(errors).length === 0;
  };

  handleSubmit = (e) => {
    e.preventDefault();

    if (this.validateForm()) {
      const { nomUniv, adresse } = this.state.formData;
      const newUniversity = { nomUniv, adresse };

      axios
        .post(
          "http://localhost:8090/Kaddem/universite/add-universite",
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
    }
  };

  handleSearchChange = (e) => {
    this.setState({ searchTerm: e.target.value });
  };

  render() {
    const { universities, formData, errors, searchTerm } = this.state;
    const filteredUniversities = universities.filter((university) =>
      university.nomUniv.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
      <div className="content">
        <ToastContainer />
        <h2 className="mb-3">University List</h2>
        <Card>
          <CardHeader>
            <CardTitle tag="h4">Add University</CardTitle>
          </CardHeader>
          <CardBody>
            <Form onSubmit={this.handleSubmit}>
              <FormGroup>
                <Label for="nomUniv">University Name</Label>
                <Input
                  type="text"
                  id="nomUniv"
                  name="nomUniv"
                  value={formData.nomUniv}
                  onChange={this.handleFormChange}
                  className={`form-control ${errors.nomUniv ? 'is-invalid' : ''}`}
                />
                {errors.nomUniv && (
                  <div className="invalid-feedback">{errors.nomUniv}</div>
                )}
              </FormGroup>
              <FormGroup>
                <Label for="adresse">University Address</Label>
                <Input
                  type="text"
                  id="adresse"
                  name="adresse"
                  value={formData.adresse}
                  onChange={this.handleFormChange}
                  className={`form-control ${errors.adresse ? 'is-invalid' : ''}`}
                />
                {errors.adresse && (
                  <div className="invalid-feedback">{errors.adresse}</div>
                )}
              </FormGroup>
              <Button type="submit" color="primary">
                Add University
              </Button>
            </Form>
          </CardBody>
          <CardFooter></CardFooter>
        </Card>
        <Input
          type="text"
          placeholder="Search for a university By University Name"
          value={searchTerm}
          onChange={this.handleSearchChange}
          style={{ width: "100%", marginBottom: "10px" }}
        />
        <Table className="table table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>University Name</th>
              <th>University Address</th>
            </tr>
          </thead>
          <tbody>
            {filteredUniversities.length > 0 ? (
              filteredUniversities.map((university) => (
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
        </Table>
      </div>
    );
  }
}

export default UniversiteList;
