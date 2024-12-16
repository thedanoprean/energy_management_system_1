// CRUDDevices.js
import React from "react";
import "./styles/CRUDDevices.css";

function CRUDDevices() {
  return (
    <nav className="crud-devices">
      <a
        onClick={() =>
          (window.location.href = "/admin/crud-devices/see-devices")
        }
      >
        See Devices
      </a>
      <a
        onClick={() =>
          (window.location.href = "/admin/crud-devices/create-device")
        }
      >
        Create Device
      </a>
      <a
        onClick={() =>
          (window.location.href = "/admin/crud-devices/update-device")
        }
      >
        Update Device
      </a>
      <a
        onClick={() =>
          (window.location.href = "/admin/crud-devices/delete-device")
        }
      >
        Delete Device
      </a>
      <div id="indicator"></div>
    </nav>
  );
}

export default CRUDDevices;
