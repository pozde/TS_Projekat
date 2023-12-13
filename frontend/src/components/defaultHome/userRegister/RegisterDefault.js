import React, { useState, useRef } from "react";

import "./RegisterDefault.css";

import { useNavigate } from "react-router-dom";

import { Link } from "react-router-dom";
import { Button, Dialog, DialogTitle, DialogActions } from "@mui/material";

function RegisterDefault() {
  const [selectedOption, setSelectedOption] = useState("");
  const inputEmail = useRef(null);

  const navigate = useNavigate();
  const [reservationSuccess, setReservationSuccess] = useState(false);
  const [reservationFail, setReservationFail] = useState(false);

  const handleClose = () => {
    setReservationSuccess(false);
    setReservationFail(false);
  };

  const handleCloseSuccess = () => {
    setReservationSuccess(false);
    setReservationFail(false);

    window.location.href = "/";
  };

  const handleRedirect = () => {
    window.location.href = "/login";
  };

  function validateInput(input) {
    const id = input.id;
    const value = input.value;
    const errorText = document.getElementById(`errorText-${id}`);

    if (id === "email") {
      //email format
      const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

      if (!value.match(emailRegex)) {
        if (errorText) {
          errorText.style.display = "block";
        }
        inputEmail.current.classList.add("error-field");
        return false;
      } else {
        if (errorText) {
          errorText.style.display = "none";
        }
        inputEmail.current.classList.remove("error-field");
        return true;
      }
    } else if (id === "brojTelefona") {
      // broj sa minimalno 6 cifri
      const phoneNumberRegex = /^\d{6,}$/;

      if (!value.match(phoneNumberRegex)) {
        if (errorText) {
          errorText.style.display = "block";
        }
        input.classList.add("error-field");
        return false;
      } else {
        if (errorText) {
          errorText.style.display = "none";
        }
        input.classList.remove("error-field");
        return true;
      }
    } else if (id === "spol") {
      // iskljucivo "M" ili "Z"
      const validGenders = ["M", "Z"];

      if (!validGenders.includes(value.toUpperCase())) {
        if (errorText) {
          errorText.style.display = "block";
        }
        input.classList.add("error-field");
        return false;
      } else {
        if (errorText) {
          errorText.style.display = "none";
        }
        input.classList.remove("error-field");
        return true;
      }
    } else if (id === "password") {
      // minimalno 8 simbola od cega je obavezno jedno veliko slovo i jedan broj
      const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;

      if (!value.match(passwordRegex)) {
        if (errorText) {
          errorText.style.display = "block";
        }
        input.classList.add("error-field");
        return false;
      } else {
        if (errorText) {
          errorText.style.display = "none";
        }
        input.classList.remove("error-field");
        return true;
      }
    } else if (id === "datumRodjenja") {
      // format datuma dd.mm.yyyy.
      const dateRegex = /^\d{2}\.\d{2}\.\d{4}\.$/;

      if (!value.match(dateRegex)) {
        if (errorText) {
          errorText.style.display = "block";
        }
        input.classList.add("error-field");
        return false;
      } else {
        if (errorText) {
          errorText.style.display = "none";
        }
        input.classList.remove("error-field");
        return true;
      }
    }

    return true;
  }

  const handleRegister = async () => {
    const isEmailValid = validateInput(inputEmail.current);
    const isBrojTelefonaValid = validateInput(document.getElementById("brojTelefona"));
    const isSpolValid = validateInput(document.getElementById("spol"));
    const isPasswordValid = validateInput(document.getElementById("password"));
    const isDatumRodjenjaValid = validateInput(document.getElementById("datumRodjenja"));

    if (isEmailValid && isBrojTelefonaValid && isSpolValid && isPasswordValid && isDatumRodjenjaValid) {
      const ime = document.getElementById("ime").value;
      const prezime = document.getElementById("prezime").value;
      const email = inputEmail.current.value;
      const password = document.getElementById("password").value;
      const datumRodjenja = document.getElementById("datumRodjenja").value;
      const brojTelefona = document.getElementById("brojTelefona").value;
      const spol = document.getElementById("spol").value;
      const role = "USER"; // since role is always "USER"

      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
      const response = await fetch(`${BASE_URL}/auth/register`, {
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

      //provjeriti radi li za 201
      if (response.status === "201" || response.ok) {
        // if HTTP status is 200-299
        // get the response data
        setReservationSuccess(true);
        const data = await response.json();

        //localStorage.setItem("access_token", data.access_token);
        //localStorage.setItem("refresh_token", data.refresh_token);

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
    } else {
      // Handle other invalid inputs
      let errorMessage = "Provjerite unesene podatke za polja:\n";

      if (!isEmailValid) {
        errorMessage += "- Email mora biti u formatu ime@primjer.com.\n";
      }

      if (!isBrojTelefonaValid) {
        errorMessage += "- Broj telefona mora biti broj od minimalno 6 cifara.\n";
      }

      if (!isSpolValid) {
        errorMessage += "- Spol isključivo može biti 'M' ili 'Z'.\n";
      }

      if (!isPasswordValid) {
        errorMessage += "- Password se mora sastojati od minimalno 8 karaktera te bar jednog velikog slova i bar jednog broja.\n";
      }

      if (!isDatumRodjenjaValid) {
        errorMessage += "- Datum rođenja mora biti oblika 'dd.mm.yyyy'.\n";
      }

      alert(errorMessage);
      return;
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
          <input className={`input-field-register ${selectedOption === "ime" && "error-field"}`} type="text" id="ime" placeholder="Ime" />
        </div>
        <div className="input-container-register">
          <input className={`input-field-register ${selectedOption === "prezime" && "error-field"}`} type="text" id="prezime" placeholder="Prezime" />
        </div>
        <div className="input-container-register">
          <input className={`input-field-register ${selectedOption === "datumRodjenja" && "error-field"}`} type="text" id="datumRodjenja" placeholder="Datum rođenja" />
        </div>
        <div className="input-container-register">
          <input className={`input-field-register ${selectedOption === "brojTelefona" && "error-field"}`} type="text" id="brojTelefona" placeholder="Broj telefona" />
        </div>
        <div className="input-container-register">
          <input className={`input-field-register ${selectedOption === "spol" && "error-field"}`} type="text" id="spol" placeholder="Spol" />
        </div>
        <div className="input-container-register">
          <input className={`input-field-register ${selectedOption === "email" && "error-field"}`} type="email" id="email" placeholder="E-mail adresa" ref={inputEmail} />
        </div>
        <div className="input-container-register">
          <input className={`input-field-register ${selectedOption === "password" && "error-field"}`} type="password" id="password" placeholder="Password" />
        </div>
        <button className="register-button" onClick={handleRegister}>
          Registracija
        </button>
        <div>
          <Button style={{ color: "white" }} onClick={handleRedirect}>
            <b> Imate nalog? Prijavite se!</b>
          </Button>
        </div>

        <Dialog open={reservationSuccess && !reservationFail} onClose={handleClose}>
          <DialogTitle variant="h5" fontWeight="bold">
            Uspješno ste se registrovali
          </DialogTitle>

          <DialogActions style={{ justifyContent: "center" }}>
            <Button variant="contained" size="large" style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }} onClick={handleCloseSuccess}>
              OK
            </Button>
          </DialogActions>
        </Dialog>
        <Dialog open={reservationFail} onClose={handleClose}>
          <DialogTitle variant="h5" fontWeight="bold">
            Registracija korisnika nije uspjela! Unijeli ste neispravne parametre ili email koji je već u upotrebi!
          </DialogTitle>

          <DialogActions style={{ justifyContent: "center" }}>
            <Button variant="contained" size="large" style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }} onClick={handleClose}>
              OK
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    </div>
  );
}

export default RegisterDefault;
