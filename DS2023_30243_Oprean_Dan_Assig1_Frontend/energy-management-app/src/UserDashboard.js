import React, { useState, useEffect } from "react";
import axios from "axios"; // Importă axios
import { useParams } from "react-router-dom";

function UserDashboard() {
  const { user_id } = useParams();
  const [devices, setDevices] = useState([]);

  useEffect(() => {
    const fetchDevices = async () => {
      try {
        const response = await axios.get(
          `${process.env.REACT_APP_DEVICEHOST}/device/user/${user_id}`
        );
        setDevices(response.data);
      } catch (error) {
        console.error("Error fetching device list:", error);
      }
    };

    fetchDevices();
  }, [user_id]);

  return (
    <div className="user-dashboard">
      <h2>Your Devices</h2>
      <ul>
        {devices.map((device) => (
          <li key={device.id}>
            <strong>Description:</strong> {device.description}
            <br />
            <strong>Address:</strong> {device.address}
            <br />
            <strong>Consumption:</strong> {device.consumption}
            <hr />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default UserDashboard;
