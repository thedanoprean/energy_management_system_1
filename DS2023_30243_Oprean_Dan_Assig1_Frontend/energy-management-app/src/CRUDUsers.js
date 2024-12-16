// CRUDUsers.js
import React from "react";
import "./styles/CRUDUsers.css";

function CRUDUsers() {
  return (
    <nav className="crud-users">
      <a onClick={() => (window.location.href = "/admin/crud-users/see-users")}>
        See Users
      </a>
      <a
        onClick={() => (window.location.href = "/admin/crud-users/create-user")}
      >
        Create User
      </a>
      <a
        onClick={() => (window.location.href = "/admin/crud-users/update-user")}
      >
        Update User
      </a>
      <a
        onClick={() => (window.location.href = "/admin/crud-users/delete-user")}
      >
        Delete User
      </a>
      <div id="indicator"></div>
    </nav>
  );
}

export default CRUDUsers;
