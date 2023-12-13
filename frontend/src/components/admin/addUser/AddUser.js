import React, { useState } from "react";
import { Container, Paper, Button, Box, TextField, Dialog, DialogTitle, DialogContent, DialogActions } from "@mui/material";
import axios from "axios";

const CustomAlert = ({ open, onClose, title, message, buttonText }) => {
  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle style={{ fontWeight: "bold", backgroundColor: "#2196F3", color: "white" }}>{title}</DialogTitle>
      <DialogContent dangerouslySetInnerHTML={{ __html: message }} />

      <DialogActions>
        <Button onClick={onClose} color="primary" style={{ fontWeight: "bold", backgroundColor: "#2196F3", color: "white" }}>
          {buttonText || "OK"}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default function AddMovie() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };

  const [showAlert, setShowAlert] = useState(false);
  const [alertTitle, setAlertTitle] = useState("");
  const [alertMessage, setAlertMessage] = useState("");

  const [ime, setIme] = useState("");
  const [prezime, setPrezime] = useState("");
  const [email, setEmail] = useState("");
  const [datumRodjenja, setDatumRodjenja] = useState("");
  const [brojTelefona, setBrojTelefona] = useState("");
  const [password, setPassword] = useState("");
  const [spol, setSpol] = useState("");
  const [role, setRole] = useState("USER");

  const [reservationSuccess, setReservationSuccess] = useState(false);
  const [reservationFail, setReservationFail] = useState(false);

  const [isButtonDisabled, setIsButtonDisabled] = useState(false);

  const generateRandomPassword = () => {
    const lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    const uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    const numbers = "0123456789";

    const getRandomChar = (pool) => {
      const randomIndex = Math.floor(Math.random() * pool.length);
      return pool.charAt(randomIndex);
    };

    let password = "";

    // Ensure at least one uppercase letter
    password += getRandomChar(uppercaseLetters);

    // Generate random characters
    for (let i = 1; i < 16; i++) {
      const pool = `${lowercaseLetters}${uppercaseLetters}${numbers}`;
      password += getRandomChar(pool);
    }

    return password;
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
        input.classList.add("error-field");
        return false;
      } else {
        if (errorText) {
          errorText.style.display = "none";
        }
        input.classList.remove("error-field");
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

  const handleClick = async (e) => {
    const isEmailValid = validateInput(document.getElementById("email"));
    const isBrojTelefonaValid = validateInput(document.getElementById("brojTelefona"));
    const isSpolValid = validateInput(document.getElementById("spol"));

    const isDatumRodjenjaValid = validateInput(document.getElementById("datumRodjenja"));

    if (isEmailValid && isBrojTelefonaValid && isSpolValid && isDatumRodjenjaValid) {
      const ime = document.getElementById("ime").value;
      const prezime = document.getElementById("prezime").value;
      const email = document.getElementById("email").value;

      const datumRodjenja = document.getElementById("datumRodjenja").value;
      const brojTelefona = document.getElementById("brojTelefona").value;
      const spol = document.getElementById("spol").value;
      const role = "USER"; // since role is always "USER"

      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
      const response = await fetch(`${BASE_URL}/auth/addUser`, {
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
          password: generateRandomPassword(),
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
        setIsButtonDisabled(false);
      } else {
        setReservationFail(true);
        setIsButtonDisabled(false);
        console.error("Error:", response.status, response.statusText);
        // handle other errors...
      }
    } else {
      // Handle other invalid inputs
      let errorMessage = "Provjerite unesene podatke za polja:<br>";

      if (!isEmailValid) {
        errorMessage += "- Email mora biti u formatu ime@primjer.com.<br>";
      }

      if (!isBrojTelefonaValid) {
        errorMessage += "- Broj telefona mora biti broj od minimalno 6 cifara.<br>";
      }

      if (!isSpolValid) {
        errorMessage += "- Spol isključivo može biti 'M' ili 'Z'.<br>";
      }

      if (!isDatumRodjenjaValid) {
        errorMessage += "- Datum rođenja mora biti oblika 'dd.mm.yyyy'.<br>";
      }

      setAlertTitle("Neispravan unos");
      setAlertMessage(errorMessage);
      setShowAlert(true);
      setIsButtonDisabled(false);
      return;
    }
  };

  const handleCloseAlert = () => {
    setIsButtonDisabled(false);
    setShowAlert(false);
  };

  const handleClose = () => {
    setReservationSuccess(false);
    setReservationFail(false);
    setIsButtonDisabled(false);
  };

  const handleCloseSuccess = () => {
    setReservationSuccess(false);
    setReservationFail(false);
    setIsButtonDisabled(false);

    window.location.href = "/overviewUser";
  };

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1 style={{ color: "#2d2d2d" }}>Dodaj korisnika</h1>
        <Box
          component="form"
          sx={{
            "& > :not(style)": { m: 1 },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField
            id="ime"
            label="Ime"
            variant="outlined"
            value={ime}
            onChange={(e) => setIme(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />
          <TextField
            id="prezime"
            label="Prezime"
            variant="outlined"
            value={prezime}
            onChange={(e) => setPrezime(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />

          <TextField
            id="email"
            label="Email"
            variant="outlined"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />

          <TextField
            id="datumRodjenja"
            label="Datum rodjenja"
            variant="outlined"
            value={datumRodjenja}
            onChange={(e) => setDatumRodjenja(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />

          <TextField
            id="brojTelefona"
            label="Broj telefona"
            variant="outlined"
            value={brojTelefona}
            onChange={(e) => setBrojTelefona(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />

          <TextField
            id="spol"
            label="Spol"
            variant="outlined"
            value={spol}
            onChange={(e) => setSpol(e.target.value)}
            fullWidth
            InputLabelProps={{
              style: { fontWeight: "bold" },
            }}
            InputProps={{
              style: { fontWeight: "bold" },
            }}
          />

          <Button
            onClick={() => {
              handleClick();
              setIsButtonDisabled(true);
            }}
            disabled={isButtonDisabled}
            style={{ display: "block", backgroundColor: "#2d2d2d", color: "white", marginTop: "15px", width: "100%" }}
          >
            Dodaj
          </Button>
          <CustomAlert open={showAlert} onClose={handleCloseAlert} title={alertTitle} message={alertMessage} />
          <Dialog open={reservationSuccess && !reservationFail} onClose={handleClose}>
            <DialogTitle variant="h5" fontWeight="bold">
              Uspješno ste dodali korisnika !
            </DialogTitle>

            <DialogActions style={{ justifyContent: "center" }}>
              <Button variant="contained" size="large" style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }} onClick={handleCloseSuccess}>
                OK
              </Button>
            </DialogActions>
          </Dialog>
          <Dialog open={reservationFail} onClose={handleClose}>
            <DialogTitle variant="h5" fontWeight="bold">
              Dodavanje korisnika nije uspjelo! Unijeli ste neispravne parametre ili email koji je već u upotrebi!
            </DialogTitle>

            <DialogActions style={{ justifyContent: "center" }}>
              <Button variant="contained" size="large" style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }} onClick={handleClose}>
                OK
              </Button>
            </DialogActions>
          </Dialog>
        </Box>
      </Paper>
    </Container>
  );
}
