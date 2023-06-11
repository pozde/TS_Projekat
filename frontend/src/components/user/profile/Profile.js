import React, { useEffect, useState } from "react";
import { Container, Grid, Typography, TextField } from "@mui/material";
import { Paper } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import axios from "axios";

const Profile = ({ user }) => {
  const [korisnik, setKorisnik] = useState({});
  const email = localStorage.getItem("email");

  //const [email, setEmail] = useEffect(localStorage.getItem("email"));

  /*useEffect(() => {
    const fetchUsers = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response = await axios.get(`${BASE_URL}/rezervacija-karata/filmovi`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setFilmovi(response.data);
      } catch (error) {
        console.error("Failed to fetch movies:", error);
      }
    };

    fetchFilmovi();
    filterMovies();
  }, [searchTerm, selectedGenres]);*/

  useEffect(() => {
    const fetchKorisnik = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response = await axios.get(`${BASE_URL}/preporucivanje-sadrzaja-pogodnosti/korisnici/mail/${email}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setKorisnik(response.data);
      } catch (error) {
        console.error("Failed to fetch users:", error);
      }
    };
    fetchKorisnik();
  }, []);

  console.log("KORISNIK", korisnik);

  return (
    <Grid style={{ marginTop: "10px" }} container justifyContent="center">
      <Grid item xs={12} sm={6} md={4}>
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
        </Paper>
      </Grid>
    </Grid>
  );
};

export default Profile;
