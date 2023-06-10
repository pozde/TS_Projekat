import React, { useEffect, useState } from "react";
import { Container, Grid, Typography, TextField } from "@mui/material";
import DoneOutlineIcon from "@mui/icons-material/DoneOutline";
import FastfoodIcon from "@mui/icons-material/Fastfood";
import HourglassEmptyIcon from "@mui/icons-material/HourglassEmpty";
import { Paper } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";

const Profile = ({ user }) => {
  const [korisnici, setKorisnici] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8081/korisnici")
      .then((res) => res.json())
      .then((result) => {
        setKorisnici(result);
      });
  }, []);

  return (
    <Grid style={{ marginTop: "10px" }} container justifyContent="center">
      <Grid item xs={12} sm={6} md={4}>
        {korisnici.map((korisnik) => (
          <Paper
            elevation={6}
            style={{
              margin: "10px",
              padding: "15px",
              textAlign: "left",
            }}
            key={korisnik.id}
          >
            <Typography
              variant="h4"
              align="center"
              gutterBottom
              sx={{
                fontWeight: "bold",
                fontFamily: "Arial",
                marginBottom: "50px",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              <span style={{ marginRight: "10px" }}>Profil</span>
              <PersonIcon fontSize="large" />
            </Typography>
            <div style={{ display: "flex", alignItems: "center", marginBottom: "10px" }}>
              <Typography
                variant="body1"
                sx={{
                  fontWeight: "bold",
                  fontSize: "1.2em",
                  marginRight: "10px",
                  alignSelf: "center",
                  whiteSpace: "nowrap",
                }}
              >
                Ime:
              </Typography>
              <TextField
                variant="filled"
                disabled
                fullWidth
                value={korisnik.ime}
                InputProps={{
                  style: {
                    fontWeight: "bold",
                  },
                }}
              />
            </div>
            <div style={{ display: "flex", alignItems: "center", marginBottom: "10px" }}>
              <Typography
                variant="body1"
                sx={{
                  fontWeight: "bold",
                  fontSize: "1.2em",
                  marginRight: "10px",
                  alignSelf: "center",
                  whiteSpace: "nowrap",
                }}
              >
                Prezime:
              </Typography>
              <TextField
                variant="filled"
                disabled
                fullWidth
                value={korisnik.prezime}
                InputProps={{
                  style: {
                    fontWeight: "bold",
                  },
                }}
              />
            </div>
            <div style={{ display: "flex", alignItems: "center", marginBottom: "10px" }}>
              <Typography
                variant="body1"
                sx={{
                  fontWeight: "bold",
                  fontSize: "1.2em",
                  marginRight: "10px",
                  alignSelf: "center",
                  whiteSpace: "nowrap",
                }}
              >
                Datum rodjenja:
              </Typography>
              <TextField
                variant="filled"
                disabled
                fullWidth
                value={korisnik.datumRodjenja}
                InputProps={{
                  style: {
                    fontWeight: "bold",
                  },
                }}
              />
            </div>
            <div style={{ display: "flex", alignItems: "center", marginBottom: "10px" }}>
              <Typography
                variant="body1"
                sx={{
                  fontWeight: "bold",
                  fontSize: "1.2em",
                  marginRight: "10px",
                  alignSelf: "center",
                  whiteSpace: "nowrap",
                }}
              >
                Email:
              </Typography>
              <TextField
                variant="filled"
                disabled
                fullWidth
                value={korisnik.email}
                InputProps={{
                  style: {
                    fontWeight: "bold",
                  },
                }}
              />
            </div>
            <div style={{ display: "flex", alignItems: "center", marginBottom: "10px" }}>
              <Typography
                variant="body1"
                sx={{
                  fontWeight: "bold",
                  fontSize: "1.2em",
                  marginRight: "10px",
                  alignSelf: "center",
                  whiteSpace: "nowrap",
                }}
              >
                Broj telefona:
              </Typography>
              <TextField
                variant="filled"
                disabled
                fullWidth
                value={korisnik.brojTelefona}
                InputProps={{
                  style: {
                    fontWeight: "bold",
                  },
                }}
              />
            </div>
            <div style={{ display: "flex", alignItems: "center", marginBottom: "10px" }}>
              <Typography
                variant="body1"
                sx={{
                  fontWeight: "bold",
                  fontSize: "1.2em",
                  marginRight: "10px",
                  alignSelf: "center",
                  whiteSpace: "nowrap",
                }}
              >
                Spol:
              </Typography>
              <TextField
                variant="filled"
                disabled
                fullWidth
                value={korisnik.spol}
                InputProps={{
                  style: {
                    fontWeight: "bold",
                  },
                }}
              />
            </div>
            <Typography variant="h5" sx={{ fontWeight: "bold", fontSize: "1.2em", marginTop: "20px" }}>
              <strong>Karte:</strong>
            </Typography>
            {korisnik.karte.length > 0 ? (
              korisnik.karte.map((karta) => (
                <Typography variant="body1" key={karta.ID} sx={{ fontWeight: "bold", fontSize: "1.2em" }}>
                  Broj karte: {karta.brojKarte}
                </Typography>
              ))
            ) : (
              <Typography variant="body1" sx={{ fontStyle: "italic", fontWeight: "bold", fontSize: "1.2em" }}>
                Nemate karata na profilu.
              </Typography>
            )}
          </Paper>
        ))}
      </Grid>
    </Grid>
  );
};

export default Profile;
