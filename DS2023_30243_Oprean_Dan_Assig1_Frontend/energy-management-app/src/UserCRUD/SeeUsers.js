import React, { useState, useEffect } from "react";
import axios from "axios"; // Importă axios
import "../styles/users/SeeUsers.css";

function SeeUsers() {
  const [users, setUsers] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get(
          process.env.REACT_APP_USERHOST + "/user/"
        );
        setUsers(response.data);
      } catch (error) {
        console.error("Error fetching user list:", error);
        setErrorMessage("Error fetching user list. Please try again.");
      }
    };

    fetchUsers();
  }, []);

  return (
    <div className="see-users-container">
      <h2>User List</h2>
      <table className="users-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Username</th>
            <th>Role</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.name}</td>
              <td>{user.username}</td>
              <td>{user.role}</td>
            </tr>
          ))}
        </tbody>
      </table>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
}

export default SeeUsers;
