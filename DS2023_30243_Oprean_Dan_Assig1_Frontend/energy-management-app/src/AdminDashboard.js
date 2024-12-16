// AdminDashboard.js
import React from "react";
import { useNavigate } from "react-router-dom"; // Folosim useNavigate în loc de useHistory
import "./styles/AdminDashboard.css";

function AdminDashboard() {
  const navigate = useNavigate(); // Folosim useNavigate în loc de useHistory

  const redirectToUsers = () => {
    navigate("/admin/crud-users"); // Utilizăm metoda navigate pentru a redirecționa către pagina dorită
  };

  const redirectToDevices = () => {
    navigate("/admin/crud-devices");
  };

  return (
    <nav>
      <a onClick={redirectToUsers}>CRUD Users</a>
      <a onClick={redirectToDevices}>CRUD Devices</a>
      <div id="indicator"></div>
    </nav>
  );
}

export default AdminDashboard;
