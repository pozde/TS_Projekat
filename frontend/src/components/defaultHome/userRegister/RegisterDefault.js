import React, { useState, useRef } from "react";

import "./RegisterDefault.css";

import axios from "axios";
import { Link } from "react-router-dom";
import {
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
} from "@mui/material";

function RegisterDefault() {
  const [selectedOption, setSelectedOption] = useState("");
  const inputNumber = useRef(null);
  const inputEmail = useRef(null);

  const [anchorElNav, setAnchorElNav] = React.useState(null);

  const [reservationSuccess, setReservationSuccess] = useState(false);
  const [reservationFail, setReservationFail] = useState(false);

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleClose = () => {
    //PROVJERITI!
    // Close the reservation success message

    setReservationSuccess(false);
    setReservationFail(false);
  };

  const handleCloseSuccess = () => {
    //PROVJERITI!
    // Close the reservation success message

    setReservationSuccess(false);
    setReservationFail(false);

    window.location.href = '/';
  };

  function validateInput(input) {
    const id = input.id;
    const value = input.value;
    const errorText = document.getElementById(`errorText-${id}`);

    if (id === "email") {
      const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

      if (!value.match(emailRegex)) {
        errorText.style.display = "block";
        inputEmail.current.classList.add("error-field");
        return false;
      } else {
        errorText.style.display = "none";
        inputEmail.current.classList.remove("error-field");
        return true;
      }
    }
  }

  const handleRegister = async () => {
    const isEmailValid = validateInput(inputEmail.current);

    if (isEmailValid) {
      const ime = document.getElementById("ime").value;
      const prezime = document.getElementById("prezime").value;
      const email = inputEmail.current.value;
      const password = document.getElementById("password").value;
      const datumRodjenja = document.getElementById("datumRodjenja").value;
      const brojTelefona = document.getElementById("brojTelefona").value;
      const spol = document.getElementById("spol").value;
      const role = "USER"; // since role is always "USER"

      const BASE_URL =
        process.env.REACT_APP_BASE_URL || "http://localhost:8081";
      const response = await fetch(`${BASE_URL}/auth/register`, {
        // replace with your API url
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ime: ime,
          prezime: prezime,
          email: email,
          datumRodjenja: datumRodjenja,
          brojTelefona: brojTelefona,
          password: password,
          spol: spol,
          role: role,
        }),
      });

      if (response.ok) {
        // if HTTP status is 200-299
        // get the response data
        setReservationSuccess(true);
        const data = await response.json();

        localStorage.setItem("access_token", data.access_token);
        localStorage.setItem("refresh_token", data.refresh_token);

       

        // Rest of the registration processing code
        // history.push('/choose-pollingstation');
      } else if (response.status === 409) {
        setReservationFail(true);
        // if status is 409 Conflict
        // handle email already existing...
        const data = await response.json();
        console.error("Error:", data.message);
        alert(data.message);
      } else {
        setReservationFail(true);
        console.error("Error:", response.status, response.statusText);
        // handle other errors...
      }
    }
  };

  return (
    <div className="register-page">
      <div className="frameRegister">
        <span style={{ marginTop: "5px", marginBottom: "30px" }}>
          <h2 className="font-register" style={{ marginBottom: "30px" }}>
            Registracija korisnika
          </h2>
        </span>
        <div className="input-container-register">
          <input
            className={`input-field-register ${
              selectedOption === "ime" && "error-field"
            }`}
            type="text"
            id="ime"
            placeholder="Ime"
          />
        </div>
        <div className="input-container-register">
          <input
            className={`input-field-register ${
              selectedOption === "prezime" && "error-field"
            }`}
            type="text"
            id="prezime"
            placeholder="Prezime"
          />
        </div>
        <div className="input-container-register">
          <input
            className={`input-field-register ${
              selectedOption === "datumRodjenja" && "error-field"
            }`}
            type="text"
            id="datumRodjenja"
            placeholder="Datum rođenja"
          />
        </div>
        <div className="input-container-register">
          <input
            className={`input-field-register ${
              selectedOption === "brojTelefona" && "error-field"
            }`}
            type="text"
            id="brojTelefona"
            placeholder="Broj telefona"
          />
        </div>
        <div className="input-container-register">
          <input
            className={`input-field-register ${
              selectedOption === "spol" && "error-field"
            }`}
            type="text"
            id="spol"
            placeholder="Spol"
          />
        </div>
        <div className="input-container-register">
          <input
            className={`input-field-register ${
              selectedOption === "email" && "error-field"
            }`}
            type="email"
            id="email"
            placeholder="E-mail adresa"
            ref={inputEmail}
          />
          <p id="errorText-email" style={{ color: "red", display: "none" }}>
            Unesite validnu email adresu.
          </p>
        </div>
        <div className="input-container-register">
          <input
            className={`input-field-register ${
              selectedOption === "password" && "error-field"
            }`}
            type="password"
            id="password"
            placeholder="Lozinka"
          />
        </div>
        <button className="register-button" onClick={handleRegister}>
          Registracija
        </button>
        <Dialog
          open={reservationSuccess && !reservationFail}
          onClose={handleClose}
        >
          <DialogTitle variant="h5" fontWeight="bold">
            Uspješno ste se registrovali
          </DialogTitle>

          <DialogActions style={{ justifyContent: "center" }}>
            <Button
              variant="contained"
              size="large"
              style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }}
              onClick={handleCloseSuccess}
            >
              OK
            </Button>
          </DialogActions>
        </Dialog>
        <Dialog open={reservationFail} onClose={handleClose}>
          <DialogTitle variant="h5" fontWeight="bold">
            Registracija korisnika nije uspjela! Unijeli ste neispravne
            parametre!
          </DialogTitle>

          <DialogActions style={{ justifyContent: "center" }}>
            <Button
              variant="contained"
              size="large"
              style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }}
              onClick={handleClose}
            >
              OK
            </Button>
          </DialogActions>
        </Dialog>
        <div className="links">
          <button
            className="register-button"
            onClick={handleCloseNavMenu}
            component={Link}
            to="/login"
          >
            Imaš nalog? <b>Prijavi se.</b>
          </button>
        </div>
      </div>
    </div>
  );
}

export default RegisterDefault;
