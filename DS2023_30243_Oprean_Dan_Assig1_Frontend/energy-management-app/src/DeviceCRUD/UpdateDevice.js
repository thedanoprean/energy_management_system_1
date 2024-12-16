import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/device/UpdateDevice.css";

const UpdateDevice = () => {
  const [devices, setDevices] = useState([]);
  const [selectedDevice, setSelectedDevice] = useState(null);
  const [updatedDescription, setUpdatedDescription] = useState("");
  const [updatedAddress, setUpdatedAddress] = useState("");
  const [updatedConsumption, setUpdatedConsumption] = useState("");
  const [updatedUserId, setUpdatedUserId] = useState("");
  const [updateSuccess, setUpdateSuccess] = useState(false);

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

  const handleUpdateClick = (device) => {
    setSelectedDevice(device);
    setUpdatedDescription(device.description);
    setUpdatedAddress(device.address);
    setUpdatedConsumption(device.consumption);
    setUpdatedUserId(device.userId);
  };

  const handleUpdateInfo = async () => {
    try {
      await axios.put(
        `${process.env.REACT_APP_DEVICEHOST}/device/update/${selectedDevice.id}`,
        {
          description: updatedDescription,
          address: updatedAddress,
          consumption: updatedConsumption,
          userId: updatedUserId,
        }
      );
      setUpdateSuccess(true);
      loadData();
      setTimeout(() => {
        setSelectedDevice(null);
        setUpdatedDescription("");
        setUpdatedAddress("");
        setUpdatedConsumption("");
        setUpdatedUserId("");
        setUpdateSuccess(false);
      }, 2000);
    } catch (error) {
      console.error("Error updating device: ", error);
    }
  };

  const handleBackClick = () => {
    setSelectedDevice(null);
    setUpdatedDescription("");
    setUpdatedAddress("");
    setUpdatedConsumption("");
    setUpdatedUserId("");
  };

  return (
    <div className="update-device-container">
      <h2>Update Devices</h2>
      <table className="devices-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Address</th>
            <th>Consumption</th>
            <th>User ID</th>
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
              <td>{device.userId}</td>
              <td>
                <button
                  className="update-button"
                  onClick={() => handleUpdateClick(device)}
                >
                  Update
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {selectedDevice && (
        <div className="update-form-container">
          <h3>Update Device Information</h3>
          <label>
            Descriere:
            <input
              type="text"
              value={updatedDescription}
              onChange={(e) => setUpdatedDescription(e.target.value)}
            />
          </label>
          <br />
          <label>
            Adresă:
            <input
              type="text"
              value={updatedAddress}
              onChange={(e) => setUpdatedAddress(e.target.value)}
            />
          </label>
          <br />
          <label>
            Consum:
            <input
              type="text"
              value={updatedConsumption}
              onChange={(e) => setUpdatedConsumption(e.target.value)}
            />
          </label>
          <br />
          <label>
            UserId:
            <input
              type="text"
              value={updatedUserId}
              onChange={(e) => setUpdatedUserId(e.target.value)}
            />
          </label>
          <br />
          <button className="update-info-button" onClick={handleUpdateInfo}>
            Actualizare Info
          </button>
          <button className="back-button" onClick={handleBackClick}>
            Înapoi
          </button>
        </div>
      )}

      {updateSuccess && (
        <div className="success-message">Device updated successfully!</div>
      )}
    </div>
  );
};

export default UpdateDevice;
