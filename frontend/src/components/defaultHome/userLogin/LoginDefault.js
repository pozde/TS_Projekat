import React, { useState } from "react";

import { FiEye, FiEyeOff } from "react-icons/fi";
import "./LoginDefault.css";
import axios from "axios";
import jwtDecode from "jwt-decode";
import { useNavigate } from "react-router-dom";

function LoginDefault() {
  const [showPassword, setShowPassword] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleLogin = async () => {
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
      const response = await axios.post(`${BASE_URL}/auth-service/auth/login`, {
        email,
        password,
      });
      const tokens = response.data;
      localStorage.setItem("access_token", tokens.access_token);
      localStorage.setItem("refresh_token", tokens.refresh_token);

      const decodedToken = jwtDecode(tokens.access_token);

      if (decodedToken.role === "ROLE_ADMIN") {
        navigate("/homeAdmin");
      } else {
        navigate("/homeUser");
      }
    } catch (error) {
      if (error.response && error.response.status === 409) {
        setError(error.response.data.message);
      } else {
        setError("Greška!");
      }
    }
  };

  return (
    <div className="login-page">
      <div className="frameLogIn">
        <h2 className="fontLogin">Prijava korisnika</h2>
        <div className="input-containerLogin"></div>
        <div className="input-containerLogin">
          <input className="input-field-emailLogin" type="email" id="email" placeholder="E-mail adresa" onChange={(e) => setEmail(e.target.value)} value={email} />
        </div>
        <div className="input-containerLogin">
          <input className="input-fieldLogin" type={showPassword ? "text" : "password"} id="password" placeholder="Lozinka" onChange={(e) => setPassword(e.target.value)} value={password} />
          <span
            style={{
              marginTop: "4px",
              marginBottom: "-15px",
              fontSize: "1.5em",
            }}
          >
            {showPassword ? <FiEyeOff onClick={togglePasswordVisibility} /> : <FiEye onClick={togglePasswordVisibility} />}
          </span>
        </div>
        <button className="login-button" onClick={handleLogin}>
          Prijava
        </button>
        <div>{error && <p className="error-message">{error}</p>}</div>
        <div className="links">
          <a className="link" href="#">
            Izgubljena lozinka?
          </a>
          <button className="buttonLink" onClick={() => navigate("/register")}>
            Nemaš nalog? <b>Registruj se.</b>
          </button>
        </div>
      </div>
    </div>
  );
}

export default LoginDefault;
