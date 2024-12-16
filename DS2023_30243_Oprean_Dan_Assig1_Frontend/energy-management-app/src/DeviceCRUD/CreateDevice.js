import React, { useState } from "react";
import axios from "axios";
import "../styles/device/CreateDevice.css";

const CreateDevice = () => {
  const [deviceDetails, setDeviceDetails] = useState({
    description: "",
    address: "",
    consumption: "",
    userid: "",
  });
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setDeviceDetails({ ...deviceDetails, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        `${process.env.REACT_APP_DEVICEHOST}/device`,
        deviceDetails
      );
      setSuccessMessage(
        `Device created successfully with ID: ${response.data}`
      );
      setErrorMessage("");
    } catch (error) {
      setErrorMessage("Error creating device. Please try again.");
      setSuccessMessage("");
    }
  };

  return (
    <div className="create-device-container">
      <h2>Create Device</h2>
      <form className="create-device-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Description:</label>
          <input
            type="text"
            name="description"
            value={deviceDetails.description}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Address:</label>
          <input
            type="text"
            name="address"
            value={deviceDetails.address}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Consumption:</label>
          <input
            type="text"
            name="consumption"
            value={deviceDetails.consumption}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>UserID:</label>
          <input
            type="text"
            name="userid"
            value={deviceDetails.userid}
            onChange={handleInputChange}
            required
          />
        </div>
        <button type="submit">Create Device</button>
      </form>
      {successMessage && <p className="success-message">{successMessage}</p>}
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
};

export default CreateDevice;
