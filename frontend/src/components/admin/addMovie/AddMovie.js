import React, { useEffect, useState } from "react";
import { Container, Paper, Button, Box, TextField, Dialog, DialogTitle, DialogContent, DialogActions } from "@mui/material";

export default function AddMovie() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [ime, setIme] = useState("");
  const [prezime, setPrezime] = useState("");
  const [email, setEmail] = useState("");
  const [adresa, setAdresa] = useState("");
  const [brojTelefona, setBrojTelefona] = useState("");
  const [datumRodjenja, setDatumRodjenja] = useState("");
  const [spol, setSpol] = useState("");
  const [korisnici, setKorisnici] = useState([]);
  const [reservationSuccess, setReservationSuccess] = useState(false);
  const [reservationFail, setReservationFail] = useState(false);

  const handleClick = (e) => {
    e.preventDefault();
    const korisnik = {
      ime,
      prezime,
      datumRodjenja,
      email,
      brojTelefona,
      spol,
    };

    const hasEmptyProperties = Object.values(korisnik).some((value) => value === "");

    if (hasEmptyProperties) {
      setReservationFail(true);
    }

    /*fetch("http://localhost:8081/dodajKorisnika", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(korisnik),
    })
      .then((response) => {
        if (response.ok) {
          setReservationSuccess(true);
        } else {
          setReservationFail(true);
          // You can handle different HTTP status codes here
        }
      })
      .catch((error) => {
        console.log("Došlo je do greške prilikom slanja zahtjeva:", error);
        setReservationFail(true);
      });
    console.log("Fail", reservationFail);
    console.log("Succ", reservationSuccess);*/

    //console.log(korisnik);
    const hasSameEmail = korisnici.some((korisnik) => korisnik.email === email);
    if (hasSameEmail) {
      setReservationFail(true);
    } else if (!reservationFail) {
      fetch("http://localhost:8081/dodajKorisnika", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(korisnik),
      }).then(() => {
        console.log("Novi korisnik je uspješno dodan!");
      });
      setReservationSuccess(true);
    }

    console.log("TRENUTNO 1 2", reservationFail, reservationSuccess);
  };

  const handleClose = () => {
    //PROVJERITI!
    // Close the reservation success message

    setReservationSuccess(false);
    setReservationFail(false);
  };

  useEffect(() => {
    fetch("http://localhost:8081/korisnici")
      .then((res) => res.json())
      .then((result) => {
        setKorisnici(result);
      });
  }, []);

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
            label="Datum rođenja"
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
            label="E-mail"
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
          <Button variant="contained" onClick={handleClick} style={{ backgroundColor: "#2d2d2d", width: "50vh", marginTop: "30px" }}>
            Dodaj
          </Button>
          <Dialog open={reservationSuccess && !reservationFail} onClose={handleClose}>
            <DialogTitle variant="h5" fontWeight="bold">
              Uspješno ste dodali korisnika
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
