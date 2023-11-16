import React, { Component } from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class AddUniversity extends Component {
  state = {
    nomUniv: '',
    adresse: '',
  };

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  handleSubmit = (e) => {
    e.preventDefault();

    const { nomUniv, adresse } = this.state;

    // Perform validation if needed

    // Make the API call to add the university
    axios
      .post('http://localhost:8091/Kaddem/universite/add-universite', {
        nomUniv,
        adresse,
      })
      .then((response) => {
        console.log('University added successfully:', response.data);
        toast.success('University added successfully');
        // Optionally, you can redirect to the university list page or perform other actions
      })
      .catch((error) => {
        console.error('Error adding university:', error);
        toast.error('Failed to add university');
      });
  };

  render() {
    const { nomUniv, adresse } = this.state;

    return (
      <div className="content">
        <ToastContainer />

        <h2 className="mb-3">Add University</h2>

        <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label htmlFor="nomUniv">University Name:</label>
            <input
              type="text"
              id="nomUniv"
              name="nomUniv"
              value={nomUniv}
              onChange={this.handleChange}
              className="form-control"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="adresse">Address:</label>
            <input
              type="text"
              id="adresse"
              name="adresse"
              value={adresse}
              onChange={this.handleChange}
              className="form-control"
              required
            />
          </div>

          <button type="submit" className="btn btn-primary">
            Add University
          </button>
        </form>
      </div>
    );
  }
}

export default AddUniversity;
