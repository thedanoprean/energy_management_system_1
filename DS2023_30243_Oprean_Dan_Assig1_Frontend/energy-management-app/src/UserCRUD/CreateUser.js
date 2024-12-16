import React, { useState } from "react";
import axios from "axios";
import "../styles/users/CreateUser.css";

function CreateUser() {
  const [formData, setFormData] = useState({
    name: "",
    username: "",
    password: "",
    role: "",
  });
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post(process.env.REACT_APP_USERHOST + "/user/", formData)
      .then((response) => {
        setSuccessMessage("User successfully created!");
        setErrorMessage("");
      })
      .catch((error) => {
        console.error("Error creating user:", error);
        setSuccessMessage("");
        setErrorMessage("Error creating user. Please try again.");
      });
  };

  return (
    <div className="create-user-container user-specific">
      <h2>Create New User</h2>
      <form
        className="create-user-form user-specific-form"
        onSubmit={handleSubmit}
      >
        <div className="form-group user-specific-group">
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group user-specific-group">
          <label>Username:</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group user-specific-group">
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group user-specific-group">
          <label>Role:</label>
          <input
            type="text"
            name="role"
            value={formData.role}
            onChange={handleInputChange}
            required
          />
        </div>
        <button type="submit" className="create-button user-specific-button">
          Create User
        </button>
      </form>
      {successMessage && <p className="success-message">{successMessage}</p>}
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
}

export default CreateUser;
