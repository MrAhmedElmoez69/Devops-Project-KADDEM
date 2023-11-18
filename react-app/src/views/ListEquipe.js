import React, { Component } from "react";
import axios from "axios";
import { Table} from "reactstrap";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";


class EquipeList extends Component {
  state = {
    equipes: [],
  };

  componentDidMount() {
    this.loadEquipes();
  }

  loadEquipes = () => {
    axios
      .get("http://localhost:8090/Kaddem/equipe/retrieve-all-equipes")
      .then((response) => {
        const equipes = response.data;
        this.setState({ equipes });
        toast.success("Welcome to the equipe list");
      })
      .catch((error) => {
        console.error("Error fetching equipes:", error);
      });
  };

  render() {
    const { equipes } = this.state;

    return (
      <div className="content">
        <ToastContainer />
        <h1 className="mb-4">Equipe List</h1>
        <Table striped bordered hover responsive>
          <thead>
            <tr>
              <th>Equipe Name</th>
              <th>Niveau</th>
              {/* Add more columns for other equipe information */}
            </tr>
          </thead>
          <tbody>
            {equipes.map((equipe) => (
              <tr key={equipe.idEquipe}>
                <td>{equipe.nomEquipe}</td>
                <td>{equipe.niveau}</td>
                {/* Add more cells for other equipe information */}
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default EquipeList;