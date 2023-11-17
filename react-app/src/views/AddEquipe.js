import React, { Component } from 'react';
import { Button, Form, FormGroup, Label, Input, Col, Card, CardHeader, CardBody, CardFooter, CardTitle } from 'reactstrap';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class CreateEquipe extends Component {
  state = {
    nomEquipe: '',
    niveau: '', // Utilisez cette valeur pour stocker la sélection du niveau
    errors: {},
    showNotification: false,
  };

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  }

  validateForm = () => {
    const errors = {};
    const { nomEquipe, niveau } = this.state;

    if (!nomEquipe) {
      errors.nomEquipe = 'Nom d\'équipe est requis';
    }

    if (!niveau) {
      errors.niveau = 'Niveau est requis';
    }

    this.setState({ errors });
    return Object.keys(errors).length === 0;
  }

  handleSubmit = (e) => {
    e.preventDefault();

    if (this.validateForm()) {
      const newEquipe = {
        nomEquipe: this.state.nomEquipe,
        niveau: this.state.niveau,
        etudiants: [], // Ajoutez les étudiants si nécessaire
      };

      axios.post('http://localhost90/Kaddem/equipe/add-equipe', newEquipe)
        .then(response => {
          console.log('Equipe created:', response.data);
          this.setState({ showNotification: true }, () => {
            setTimeout(() => {
              this.setState({ showNotification: false });
            }, 60000);
          });
        })
        .catch(error => {
          console.error('Error creating Equipe:', error);
          toast.error('An error occurred while creating the Equipe');
        });
    }
  }

  render() {
    const { errors } = this.state;

    return (
      <div className="content d-flex align-items-center justify-content-center" style={{ height: '100vh' }}>
        <Col md="8">
          <Card>
            <CardHeader>
              <CardTitle tag="h4">Create Equipe</CardTitle>
            </CardHeader>
            <CardBody>
              <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                  <Label for="nomEquipe">Nom d'équipe</Label>
                  <Input
                    type="text"
                    name="nomEquipe"
                    id="nomEquipe"
                    value={this.state.nomEquipe}
                    onChange={this.handleChange}
                    className={errors.nomEquipe ? 'is-invalid' : ''}
                  />
                  {errors.nomEquipe && <div className="invalid-feedback">{errors.nomEquipe}</div>}
                </FormGroup>
                <FormGroup>
                  <Label for="niveau">Niveau</Label>
                  <Input
                    type="select"
                    name="niveau"
                    id="niveau"
                    value={this.state.niveau}
                    onChange={this.handleChange}
                    className={errors.niveau ? 'is-invalid' : ''}
                  >
                    <option value="">Sélectionnez un niveau</option>
                    <option value="JUNIOR">JUNIOR</option>
                    <option value="SENIOR">SENIOR</option>
                    <option value="EXPERT">EXPERT</option>
                  </Input>
                  {errors.niveau && <div className="invalid-feedback">{errors.niveau}</div>}
                </FormGroup>
                <Button color="primary" type="submit">Create Equipe</Button>
              </Form>
            </CardBody>
            <CardFooter>
            </CardFooter>
          </Card>
        </Col>

        <ToastContainer />
      </div>
    );
  }
}

export default CreateEquipe;
