import React, { useEffect, useState } from "react";
import { Container, Paper, Button, Box, TextField, Autocomplete } from "@mui/material";
import axios from "axios";

export default function OverviewUser() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [korisnici, setKorisnici] = useState([]);

  useEffect(() => {
    const fetchKorisnici = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
        const response = await axios.get(`${BASE_URL}/preporucivanje-sadrzaja-pogodnosti/korisnici/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setKorisnici(response.data);
      } catch (error) {
        console.error("Failed to fetch users:", error);
      }
    };

    fetchKorisnici();
    //filterMovies();
  }, []);

  /*useEffect(() => {
    fetch("http://localhost:8080/preporucivanje-sadrzaja-pogodnosti/korisnici/")
      .then((res) => res.json())
      .then((result) => {
        setKorisnici(result);
      });
  }, []);
  console.log("TOKEN", localStorage.getItem("access_token"));*/

  return (
    <Container>
      <h1 style={{ color: "white" }}>Korisnici</h1>
      <Paper elevation={3} style={paperStyle}>
        {korisnici.map((korisnik) => (
          <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left", color: "black" }} key={korisnik.id}>
            Id: {korisnik.id} <br />
            Ime: {korisnik.ime} <br />
            Prezime: {korisnik.prezime} <br />
            Datum rođenja: {korisnik.datumRodjenja} <br />
            Email: {korisnik.email} <br />
            Broj telefona: {korisnik.brojTelefona} <br />
            Spol: {korisnik.spol}
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}
