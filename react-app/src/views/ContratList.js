import React, { Component } from "react";
import axios from "axios";
import { Table } from "react-bootstrap";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class ContratList extends Component {
    state = {
        contrats: [],
    };

    componentDidMount() {
        this.loadContrats();
    }

    loadContrats = () => {
        axios
            .get("http://localhost:8090/Kaddem/contrat/retrieve-all-contrats")
            .then((response) => {
                const contrats = response.data;
                this.setState({ contrats });
                toast.success("Welcome to the contract list");
            })
            .catch((error) => {
                console.error("Error fetching contracts:", error);
            });
    };

    render() {
        const { contrats } = this.state;

        return (
            <div className="content">
                <ToastContainer />
                <h1 className="mb-4">Contract List</h1>
                <Table striped bordered hover responsive>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Speciality</th>
                            <th>Amount</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            {/* Add more columns for other contract information */}
                        </tr>
                    </thead>
                    <tbody>
                        {contrats.map((contrat) => (
                            <tr key={contrat.idContrat}>
                                <td>{contrat.idContrat}</td>
                                <td>{contrat.specialite}</td>
                                <td>{contrat.montantContrat}</td>
                                <td>{contrat.dateDebutContrat}</td>
                                <td>{contrat.dateFinContrat}</td>
                                {/* Add more cells for other contract information */}
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default ContratList;
