import React, { useState } from "react";

import { FiEye, FiEyeOff } from "react-icons/fi";
import "./LoginDefault.css";
import axios from "axios";
import jwtDecode from "jwt-decode";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";

import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import TextField from "@mui/material/TextField";

const ResetPasswordModal = ({ open, onClose, onConfirm, emailReset, setEmailReset, isButtonDisabled, setIsButtonDisabled }) => {
  //const [isButtonDisabled, setIsButtonDisabled] = useState(false);
  //const [emailReset, setEmailReset] = useState("");

  const handleEmailChange = (e) => {
    setEmailReset(e.target.value);
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle style={{ fontWeight: "bold" }}>Potvrdite povrat passworda</DialogTitle>
      <DialogContent>
        <TextField label={<span style={{ fontWeight: "bold" }}>Email</span>} type="email" fullWidth value={emailReset} onChange={handleEmailChange} InputProps={{ style: { fontWeight: "bold" } }} />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary" style={{ fontWeight: "bold" }}>
          Prekini
        </Button>
        <Button
          onClick={() => {
            onConfirm(emailReset);
            setIsButtonDisabled(true);
          }}
          color="primary"
          style={{ fontWeight: "bold" }}
          disabled={isButtonDisabled}
        >
          Potvrdi
        </Button>
      </DialogActions>
    </Dialog>
  );
};

const ResetPasswordSuccessModal = ({ open, onClose, isButtonDisabled, setIsButtonDisabled }) => {
  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle style={{ fontWeight: "bold", backgroundColor: "#2196F3", color: "white" }}>Povrat passworda</DialogTitle>
      <DialogContent>
        <p style={{ color: "#333" }}>Dobit ćete email ukoliko postoji korisnički račun povezan sa unešenim emailom.</p>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary" style={{ fontWeight: "bold", backgroundColor: "#2196F3", color: "white" }}>
          OK
        </Button>
      </DialogActions>
    </Dialog>
  );
};

function LoginDefault() {
  const [showPassword, setShowPassword] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [emailReset, setEmailReset] = useState(""); // Declare emailReset in the state
  const [isButtonDisabled, setIsButtonDisabled] = useState(false);
  const navigate = useNavigate();

  const [isResetSuccessModalOpen, setIsResetSuccessModalOpen] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleLogin = async () => {
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
      const response = await axios.post(`${BASE_URL}/auth/login`, {
        email,
        password,
      });
      const tokens = response.data;
      localStorage.setItem("access_token", tokens.access_token);
      localStorage.setItem("refresh_token", tokens.refresh_token);

      const decodedToken = jwtDecode(tokens.access_token);

      localStorage.setItem("email", decodedToken.sub);

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

  const handleRedirect = () => {
    window.location.href = "/register";
  };

  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleForgotPassword = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const handleResetPassword = async () => {
    const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";

    try {
      const response = await fetch(`${BASE_URL}/auth/forgot-password/${emailReset}`, {
        method: "POST",
      });

      // Check the response status to determine success or failure
      if (response.ok) {
        setIsResetSuccessModalOpen(true);
        console.log("Success");
      } else {
        console.log("Not success");
      }
    } catch (error) {
      console.error("Error during password reset:", error);
    } finally {
      handleCloseModal();
    }
  };

  /*const handleResetPassword = () => {

    handleCloseModal();
  };*/

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
        <div>
          <Button style={{ color: "white" }} onClick={handleRedirect}>
            <b>Nemate nalog? Registrujte se!</b>
          </Button>
        </div>
        <div>
          <Button
            style={{ color: "white" }}
            onClick={() => {
              handleForgotPassword();
              setIsButtonDisabled(false);
            }}
          >
            <b>Zaboravili ste password?</b>
          </Button>
          <ResetPasswordModal open={isModalOpen} onClose={handleCloseModal} onConfirm={handleResetPassword} emailReset={emailReset} setEmailReset={setEmailReset} isButtonDisabled={isButtonDisabled} setIsButtonDisabled={setIsButtonDisabled} />
          <ResetPasswordSuccessModal open={isResetSuccessModalOpen} onClose={() => setIsResetSuccessModalOpen(false)} />
        </div>
      </div>
    </div>
  );
}

export default LoginDefault;
