import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/device/DeleteDevice.css";

const DeleteDevice = () => {
  const [devices, setDevices] = useState([]);

  const loadData = async () => {
    try {
      const response = await axios.get(
        `${process.env.REACT_APP_DEVICEHOST}/device`
      );
      setDevices(response.data);
    } catch (error) {
      console.error("Error getting device data: ", error);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleDeleteDevice = async (deviceId) => {
    try {
      await axios.delete(
        `${process.env.REACT_APP_DEVICEHOST}/device/delete/${deviceId}`
      );
      await loadData();
    } catch (error) {
      console.error("Error deleting device: ", error);
    }
  };

  return (
    <div className="delete-device-container">
      <h2>Delete Devices</h2>
      <table className="devices-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Address</th>
            <th>Consumption</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {devices.map((device) => (
            <tr key={device.id}>
              <td>{device.id}</td>
              <td>{device.description}</td>
              <td>{device.address}</td>
              <td>{device.consumption}</td>
              <td>
                <button
                  className="delete-button"
                  onClick={() => handleDeleteDevice(device.id)}
                >
                  Delete Device
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DeleteDevice;
