import React, { Component } from 'react';
import { Button, Form, FormGroup, Label, Input, Col, Card, CardHeader, CardBody, CardFooter, CardTitle } from 'reactstrap';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class CreateDetailEquipe extends Component {
  state = {
    salle: '',
    thematique: '',
    errors: {},
    showNotification: false,
    
  };

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  }

  validateForm = () => {
    const errors = {};
    const { salle, thematique } = this.state;

    if (!salle) {
      errors.salle = 'Salle is required';
    }

    if (!thematique) {
      errors.thematique = 'Thematique is required';
    }

    this.setState({ errors });
    return Object.keys(errors).length === 0;
  }

  handleSubmit = (e) => {
    e.preventDefault();

    if (this.validateForm()) {
      const newDetailEquipe = {
        salle: this.state.salle,
        thematique: this.state.thematique,
      };

      axios.post('http://localhost:8089/Kaddem/detailequipe/add-detail-equipe', newDetailEquipe)
        .then(response => {
          console.log('DetailEquipe created:', response.data);
          this.setState({ showNotification: true }, () => {
            setTimeout(() => {
              this.setState({ showNotification: false });
            }, 60000);
          });
        })
        .catch(error => {
          console.error('Error creating DetailEquipe:', error);
          toast.error('An error occurred while creating the DetailEquipe');
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
              <CardTitle tag="h4">Create DetailEquipe</CardTitle>
            </CardHeader>
            <CardBody>
              <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                  <Label for="salle">Salle</Label>
                  <Input
                    type="text"
                    name="salle"
                    id="salle"
                    value={this.state.salle}
                    onChange={this.handleChange}
                    className={errors.salle ? 'is-invalid' : ''}
                  />
                  {errors.salle && <div className="invalid-feedback">{errors.salle}</div>}
                </FormGroup>
                <FormGroup>
                  <Label for="thematique">Thematique</Label>
                  <Input
                    type="text"
                    name="thematique"
                    id="thematique"
                    value={this.state.thematique}
                    onChange={this.handleChange}
                    className={errors.thematique ? 'is-invalid' : ''}
                  />
                  {errors.thematique && <div className="invalid-feedback">{errors.thematique}</div>}
                </FormGroup>
                <Button color="primary" type="submit">Create DetailEquipe</Button>
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

export default CreateDetailEquipe;
