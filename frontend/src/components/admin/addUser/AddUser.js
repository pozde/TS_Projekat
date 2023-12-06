import React, { useState } from "react";
import { Container, Paper, Button, Box, TextField, Dialog, DialogTitle, DialogActions } from "@mui/material";
import axios from "axios";

export default function AddMovie() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };

  //deklaracina
  const [ime, setIme] = useState("");
  const [prezime, setPrezime] = useState("");
  const [email, setEmail] = useState("");
  const [datumRodjenja, setDatumRodjenja] = useState("");
  const [brojTelefona, setBrojTelefona] = useState("");
  const [password, setPassword] = useState("123123");
  const [spol, setSpol] = useState("");
  const [role, setRole] = useState("USER");

  const [reservationSuccess, setReservationSuccess] = useState(false);
  const [reservationFail, setReservationFail] = useState(false);

  const handleClick = async (e) => {
    const user = {
      ime,
      prezime,
      datumRodjenja,
      email,
      brojTelefona,
      spol,
      password,
      role
    };

    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
      const response = await axios.post(`${BASE_URL}/auth/register`, user, {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (response.ok || response.status === 201) {
        setReservationSuccess(true);
      } else {
        setReservationFail(true);
      }
    } catch (error) {
      console.error("Failed to add film:", error);
    }
  };

  const handleClose = () => {
    setReservationSuccess(false);
    setReservationFail(false);
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
            id="outlined-basic"
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
            id="outlined-basic"
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
            id="outlined-basic"
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
            id="outlined-basic"
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
            id="outlined-basic"
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
            id="outlined-basic"
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

          <Button onClick={handleClick} style={{ display: "block", backgroundColor: "#2d2d2d", color: "white", marginTop: "15px", width: "100%" }}>
            Dodaj
          </Button>
          <Dialog open={reservationSuccess && !reservationFail} onClose={handleClose}>
            <DialogTitle variant="h5" fontWeight="bold">
              Uspješno ste dodali korisnika !
            </DialogTitle>

            <DialogActions style={{ justifyContent: "center" }}>
              <Button variant="contained" size="large" style={{ fontWeight: "bold", backgroundColor: "#2d2d2d" }} onClick={handleClose}>
                OK
              </Button>
            </DialogActions>
          </Dialog>
          <Dialog open={reservationFail} onClose={handleClose}>
            <DialogTitle variant="h5" fontWeight="bold">
              Dodavanje korisnika nije uspjelo! Unijeli ste neispravne parametre!
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
