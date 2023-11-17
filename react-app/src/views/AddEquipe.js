import React, { Component } from 'react';
import { Button, Form, FormGroup, Label, Input, Col, Card, CardHeader, CardBody, CardFooter, CardTitle } from 'reactstrap';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
const Niveau = {
  JUNIOR: 'JUNIOR',
  SENIOR: 'SENIOR',
  EXPERT: 'EXPERT',
};
class CreateEquipe extends Component {
  state = {
    nomEquipe: '',
    niveau: '',
    errors: {},
  };

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  }


  handleSubmit = (e) => {
    e.preventDefault();

      const newEquipe = {
        nomEquipe: this.state.nomEquipe,
        niveau: this.state.niveau,
      };

      axios.post('http://localhost:8090/Kaddem/equipe/add-equipe', newEquipe)
        .then(response => {
          console.log('Equipe created:', response.data);

          // Display a success notification
          toast.success('Equipe created successfully');
        })
        .catch(error => {
          console.error('Error creating equipe:', error);

          // Display an error notification
          toast.error('An error occurred while creating the equipe');
        });
    
  }

  render() {
    const { errors } = this.state;

    return (
      <div className="content">
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <Card>
            <CardHeader>
              <CardTitle tag="h4">Create Equipe</CardTitle>
            </CardHeader>
            <CardBody>
              <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                  <Label for="nomEquipe">Equipe Name</Label>
                  <Input
                    type="text"
                    name="nomEquipe"
                    id="nomEquipe"
                    value={this.state.nomEquipe}
                    onChange={this.handleChange}
                    className={errors.nomEquipe ? 'is-invalid' : ''}
                    style={{
                      padding: "10px",
                      border: "2px solid #ccc",
                      borderRadius: "20px",
                      fontSize: "16px",
                      outline: "none",
                    }}
                  />
                  {errors.nomEquipe && <div className="invalid-feedback">{errors.nomEquipe}</div>}
                </FormGroup>
                <FormGroup>
                  <Label for="niveau">Equipe Niveau</Label>
                  <Input
                    type="select"
                    name="niveau"
                    id="niveau"
                    value={this.state.niveau}
                    onChange={this.handleChange}
                    className={errors.niveau ? 'is-invalid' : ''}
                    style={{
                      padding: "10px",
                      border: "2px solid #ccc",
                      borderRadius: "20px",
                      fontSize: "16px",
                      outline: "none",
                    }}
                  >
                    <option value="">Select a niveau</option>
                    {Object.values(Niveau).map((value) => (
                      <option key={value} value={value}>
                        {value}
                      </option>
                    ))}
                  </Input>
                  {errors.niveau && <div className="invalid-feedback">{errors.niveau}</div>}
                </FormGroup>
                <FormGroup className="text-center">
                  <Button color="primary" type="submit" style={{ borderRadius: "20px" }}>Create</Button>
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

export default CreateEquipe;
