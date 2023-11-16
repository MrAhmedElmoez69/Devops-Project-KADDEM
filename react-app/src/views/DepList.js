import React, { Component } from "react";
import axios from "axios";
import { Table } from "reactstrap";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

class DepList extends Component {
  state = {
    departments: [],
  };

  componentDidMount() {
    axios
      .get("http://localhost:8090/Kaddem/departement/retrieve-all-departements")
      .then((response) => {
        const departments = response.data;
        this.setState({ departments });
        toast.success("Departments loaded successfully");
      })
      .catch((error) => {
        console.error("Error fetching departments:", error);
      });
  }

  render() {
    const { departments } = this.state;
    return (
      <div className="content">
        <ToastContainer />
        <h1 className="mb-4">Department List</h1>
        
        {/* Department table */}
        <Table striped bordered hover responsive>
          <thead>
            <tr>
              <th>ID</th>
              <th>Department Name</th>
              {/* Add other department-related columns as needed */}
            </tr>
          </thead>
          <tbody>
            {departments.map((department) => (
              <tr key={department.idDepartement}>
                <td>{department.idDepartement}</td>
                <td>{department.nomDepart}</td>
                {/* Add other department-related columns as needed */}
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default DepList;
