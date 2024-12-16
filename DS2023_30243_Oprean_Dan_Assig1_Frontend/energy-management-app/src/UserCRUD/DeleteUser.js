import React, { useState, useEffect } from "react";
import axios from "axios"; // Importă axios
import "../styles/users/DeleteUser.css";

const DeleteUser = () => {
  const [users, setUsers] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  const fetchUsers = async () => {
    try {
      const response = await axios.get(
        `${process.env.REACT_APP_USERHOST}/user/`
      );
      setUsers(response.data);
    } catch (error) {
      console.error("Error retrieving users:", error);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []); // Se execută doar o dată la încărcarea componentei

  const handleDelete = async (userId) => {
    try {
      await axios.delete(
        `${process.env.REACT_APP_USERHOST}/user/delete/${userId}`
      );
      fetchUsers(); // Reîncarcă lista de utilizatori după ștergere
      setErrorMessage("");
    } catch (error) {
      console.error("Error deleting user:", error);
      setErrorMessage("Error deleting user. Please try again.");
    }
  };

  return (
    <div className="delete-user-container">
      <h2>Delete Users</h2>
      <div className="users-list">
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Username</th>
              <th>Role</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => (
              <tr key={user.id}>
                <td>{user.name}</td>
                <td>{user.username}</td>
                <td>{user.role}</td>
                <td>
                  <button
                    className="delete-button"
                    onClick={() => handleDelete(user.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
};

export default DeleteUser;
