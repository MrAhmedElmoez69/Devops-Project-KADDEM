import React, { Component } from "react";
import axios from "axios";
import { Table, Button, Input } from "reactstrap";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link } from "react-router-dom";

class EquipeList extends Component {
  state = {
    equipes: [],
    searchTerm: "",
  };

  componentDidMount() {
    axios
      .get("http://localhost:8090/equipes/retrieve-all-equipes")
      .then((response) => {
        const equipes = response.data;
        this.setState({ equipes });
        toast.success("Welcome to Equipe list");
      })
      .catch((error) => {
        console.error("Error fetching Equipes:", error);
      });
  }

  handleDelete = (equipeId) => {
    axios
      .delete(`http://localhost:8090/equipes/${equipeId}`)
      .then((response) => {
        this.setState((prevState) => ({
          equipes: prevState.equipes.filter(
            (equipe) => equipe.idEquipe !== equipeId
          ),
        }));
        toast.success("Equipe deleted successfully");
        console.log("Equipe deleted:", response.data);
      })
      .catch((error) => {
        console.error("Error deleting Equipe:", error);
      });
  };

  handleSearchChange = (e) => {
    this.setState({ searchTerm: e.target.value });
  };

  render() {
    const { equipes, searchTerm } = this.state;
    const filteredEquipes = equipes.filter((equipe) =>
      equipe.nomEquipe.toLowerCase().includes(searchTerm.toLowerCase())
    );
    return (
      <div className="content">
        <ToastContainer />
        <h1 className="mb-4">Equipe List</h1>
        <Input
          type="text"
          placeholder="Search for an equipe"
          value={searchTerm}
          onChange={this.handleSearchChange}
          style={{ width: "100%", marginBottom: "10px" }}
        />
        <Table striped bordered hover responsive>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nom d'équipe</th>
              <th>Niveau</th>
              <th style={{ width: "250px" }}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredEquipes.map((equipe) => (
              <tr key={equipe.idEquipe}>
                <td>{equipe.idEquipe}</td>
                <td>{equipe.nomEquipe}</td>
                <td>{equipe.niveau}</td>
                <td>
                  <Button
                    color="danger"
                    onClick={() => this.handleDelete(equipe.idEquipe)}
                  >
                    Delete
                  </Button>
                  {/* Ajoutez le lien vers la page d'édition si nécessaire */}
                  <Link
                    to={`/edit-equipe/${equipe.idEquipe}`}
                    className="btn btn-primary ml-2"
                  >
                    Edit
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default EquipeList;
