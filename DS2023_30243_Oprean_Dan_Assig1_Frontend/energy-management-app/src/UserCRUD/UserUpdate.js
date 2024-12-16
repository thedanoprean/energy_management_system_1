import React, { useState, useEffect } from "react";
import axios from "axios"; // Importă axios
import "../styles/users/UpdateUser.css";

const UserUpdate = () => {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [updateSuccess, setUpdateSuccess] = useState(false);

  useEffect(() => {
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

    fetchUsers();
  }, []);

  const handleUpdateClick = (user) => {
    setSelectedUser(user);
    setName(user.name);
    setUsername(user.username);
    setRole(user.role);
  };

  const handleUpdate = async () => {
    try {
      const response = await axios.put(
        `${process.env.REACT_APP_USERHOST}/user/update/${selectedUser.id}`,
        {
          name: name || selectedUser.name,
          username: username || selectedUser.username,
          password: password || selectedUser.password,
          role: role || selectedUser.role,
        }
      );
      if (response.status === 200) {
        setUpdateSuccess(true);
        setTimeout(() => {
          setUpdateSuccess(false);
        }, 1000);
      }
    } catch (error) {
      console.error("Error updating user:", error);
    }
  };

  const handleBack = () => {
    setSelectedUser(null);
    setName("");
    setUsername("");
    setPassword("");
    setRole("");
    setUpdateSuccess(false);
  };

  return (
    <div className="user-update-container">
      <h2>Update Users</h2>
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
                  <button onClick={() => handleUpdateClick(user)}>
                    Update
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {selectedUser && (
        <div className="update-form">
          <h2>Update User {selectedUser.name}</h2>
          <form>
            <div className="form-group">
              <label>Name:</label>
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </div>
            <div className="form-group">
              <label>Username:</label>
              <input
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div className="form-group">
              <label>Password:</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <div className="form-group">
              <label>Role:</label>
              <input
                type="text"
                value={role}
                onChange={(e) => setRole(e.target.value)}
              />
            </div>
          </form>
          <div className="buttons-container">
            <button onClick={handleUpdate}>Update Infos</button>
            <button onClick={handleBack}>Back</button>
          </div>
          {updateSuccess && (
            <p className="success-message">User successfully updated!</p>
          )}
        </div>
      )}
    </div>
  );
};

export default UserUpdate;
