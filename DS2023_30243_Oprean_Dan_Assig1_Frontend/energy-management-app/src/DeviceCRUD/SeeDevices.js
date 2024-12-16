import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/device/SeeDevice.css";

const SeeDevices = () => {
  const [devices, setDevices] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `${process.env.REACT_APP_DEVICEHOST}/device`
        );
        setDevices(response.data);
      } catch (error) {
        console.error("Error getting device data: ", error);
        setErrorMessage("Error getting device data. Please try again.");
      }
    };

    fetchData();
  }, []);

  return (
    <div className="see-devices-container">
      <h2>Devices</h2>
      <table className="devices-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Address</th>
            <th>Consumption</th>
            <th>User ID</th>
          </tr>
        </thead>
        <tbody>
          {devices.map((device) => (
            <tr key={device.id}>
              <td>{device.id}</td>
              <td>{device.description}</td>
              <td>{device.address}</td>
              <td>{device.consumption}</td>
              <td>{device.userId}</td>
            </tr>
          ))}
        </tbody>
      </table>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
};

export default SeeDevices;
