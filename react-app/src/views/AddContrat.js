import React, { Component } from 'react';
import { Button, Form, FormGroup, Label, Input, Col, Card, CardHeader, CardBody, CardFooter, CardTitle } from 'reactstrap';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'react-datepicker/dist/react-datepicker.css'; 
import { Link } from 'react-router-dom';

const Specialite = {
    IA: 'IA',
    RESEAU: 'RESEAU',
    CLOUD: 'CLOUD',
    SECURITE: 'SECURITE',
  };

class AddContrat extends Component {
    state = {
      specialite: '',
      montantContrat: '',
      dateDebutContrat: '',
      dateFinContrat: '',
      errors: {},
    };
  
    handleChange = (e) => {
      this.setState({ [e.target.name]: e.target.value });
    }
  
    validateForm = () => {
      const errors = {};
      const { specialite, montantContrat, dateDebutContrat, dateFinContrat } = this.state;
  
      // Logique de validation
  
      this.setState({ errors });
      return Object.keys(errors).length === 0;
    }
  
    handleSubmit = (e) => {
      e.preventDefault();
    
      if (this.validateForm()) {
        const newContrat = {
          specialite: this.state.specialite,
          montantContrat: this.state.montantContrat,
          dateDebutContrat: this.state.dateDebutContrat,
          dateFinContrat: this.state.dateFinContrat,
        };
    
        axios.post('http://localhost:8090/Kaddem/contrat/add-contrat', newContrat)
          .then(response => {
            console.log('Contrat created:', response.data);
    
            // Display a success notification
            toast.success('Contrat created successfully');
          })
          .catch(error => {
            console.error('Error creating contrat:', error);
    
            // Display an error notification
            toast.error('An error occurred while creating the contrat');
          });
      }
    }
  
    render() {
      const { errors } = this.state;
  
      return (
        <div className="content">
          <Col sm="12" md={{ size: 6, offset: 3 }}>
            <Card>
              <CardHeader>
                <CardTitle tag="h4">Create Contrat</CardTitle>
              </CardHeader>
              <CardBody>
                <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                  <Label for="specialite">Contrat Specialite</Label>
                  <Input
                    type="select"
                    name="specialite"
                    id="specialite"
                    value={this.state.specialite}
                    onChange={this.handleChange}
                    className={errors.specialite ? 'is-invalid' : ''}
                    style={{
                      padding: "10px",
                      border: "2px solid #ccc",
                      borderRadius: "20px",
                      fontSize: "16px",
                      outline: "none",
                  }}
                  >
                    <option value="">Sélectionnez une spécialité</option>
                    {Object.values(Specialite).map((value) => (
                      <option key={value} value={value}>
                        {value}
                      </option>
                    ))}
                  </Input>
                  {errors.specialite && <div className="invalid-feedback">{errors.specialite}</div>}
                </FormGroup>
                  <FormGroup>
                    <Label for="montantContrat">Contrat Montant</Label>
                    <Input
                      type="number"
                      name="montantContrat"
                      id="montantContrat"
                      value={this.state.montantContrat}
                      onChange={this.handleChange}
                      className={errors.montantContrat ? 'is-invalid' : ''}
                      style={{
                        padding: "10px",
                        border: "2px solid #ccc",
                        borderRadius: "20px",
                        fontSize: "16px",
                        outline: "none",
                    }}
                    />
                    {errors.montantContrat && <div className="invalid-feedback">{errors.montantContrat}</div>}
                  </FormGroup>
                  <FormGroup>
                    <Label for="dateDebutContrat">Contrat Date Début</Label>
                    <Input
                      type="date"
                      name="dateDebutContrat"
                      id="dateDebutContrat"
                      value={this.state.dateDebutContrat}
                      onChange={this.handleChange}
                      className={errors.dateDebutContrat ? 'is-invalid' : ''}
                      style={{
                        padding: "10px",
                        border: "2px solid #ccc",
                        borderRadius: "20px",
                        fontSize: "16px",
                        outline: "none",
                    }}
                    />
                    {errors.dateDebutContrat && <div className="invalid-feedback">{errors.dateDebutContrat}</div>}
                  </FormGroup>
                  <FormGroup>
                    <Label for="dateFinContrat">Contrat Date Fin</Label>
                    <Input
                      type="date"
                      name="dateFinContrat"
                      id="dateFinContrat"
                      value={this.state.dateFinContrat}
                      onChange={this.handleChange}
                      className={errors.dateFinContrat ? 'is-invalid' : ''}
                      style={{
                        padding: "10px",
                        border: "2px solid #ccc",
                        borderRadius: "20px",
                        fontSize: "16px",
                        outline: "none",
                    }}
                    />
                    {errors.dateFinContrat && <div className="invalid-feedback">{errors.dateFinContrat}</div>}
                  </FormGroup >
                  <FormGroup className="text-center">
                    <Button color="primary" type="submit" style={{borderRadius: "20px",}}>Create</Button>
                    <Link to={`/admin/contrat`} className="btn btn-secondary ml-2" style={{borderRadius: "20px",}}>Cancel</Link>
                  </FormGroup>
                </Form>
              </CardBody>
              <CardFooter></CardFooter>
            </Card>
          </Col>
          <ToastContainer />
        </div>
      );
    }
  }
  
  export default AddContrat;