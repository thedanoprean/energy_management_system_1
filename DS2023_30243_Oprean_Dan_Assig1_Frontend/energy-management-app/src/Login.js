import React, { useState } from "react";
import axios from "axios"; // Importă axios
import "./styles/Login.css";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        `${process.env.REACT_APP_USERHOST}/user/login`,
        {
          username,
          password,
        }
      );

      const user_id = response.data.userId;
      if (username === "admin" && password === "admin123") {
        window.location.href = "/admin";
      } else {
        window.location.href = `/user/${user_id}`;
      }
    } catch (error) {
      console.error("Login error:", error);
      setError("Username or password entered incorrectly");
    }
  };

  return (
    <div className="login-container">
      <h2>Logare</h2>
      {error && <div className="error-message">{error}</div>}
      <form className="login-form" onSubmit={handleLogin}>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Username"
          required
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
          required
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
