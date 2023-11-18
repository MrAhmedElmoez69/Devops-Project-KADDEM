import React, { Component } from "react";
import axios from "axios";
import { Table, Input } from "reactstrap";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

class DetailEquipeList extends Component {
  state = {
    detailsEquipe: [],
    searchTerm: "",
  };

  componentDidMount() {
    axios
      .get("http://localhost:8090/Kaddem/detailequipe/retrieve-all-detail-equipes")
      .then((response) => {
        const detailsEquipe = response.data;
        this.setState({ detailsEquipe });
        toast.success("Welcome to DetailEquipe list");
      })
      .catch((error) => {
        console.error("Error fetching DetailEquipes:", error);
      });
  }

 

  handleSearchChange = (e) => {
    this.setState({ searchTerm: e.target.value });
  };

  render() {
    const { detailsEquipe, searchTerm } = this.state;
    const filteredDetailsEquipe = detailsEquipe.filter((detailEquipe) =>
      detailEquipe.thematique
        .toLowerCase()
        .includes(searchTerm.toLowerCase())
    );
    return (
      <div className="content">
        <ToastContainer />
        <h1 className="mb-4">DetailEquipe List</h1>
        <Input
          type="text"
          placeholder="Rechercher un détail d'équipe"
          value={searchTerm}
          onChange={this.handleSearchChange}
          style={{ width: "100%", marginBottom: "10px" }}
        />
        <Table striped bordered hover responsive>
          <thead>
            <tr>
              <th>ID</th>
              <th>Salle</th>
              <th>Thematique</th>
              
            </tr>
          </thead>
          <tbody>
            {filteredDetailsEquipe.map((detailEquipe) => (
              <tr key={detailEquipe.idDetailEquipe}>
                <td>{detailEquipe.idDetailEquipe}</td>
                <td>{detailEquipe.salle}</td>
                <td>{detailEquipe.thematique}</td>
                <td>
                 
                  {/* Ajoutez le lien vers la page d'édition si nécessaire */}
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default DetailEquipeList;