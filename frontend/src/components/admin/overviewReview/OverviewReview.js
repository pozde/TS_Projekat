import React, { useEffect, useState } from "react";
import { Container, Paper, Button, Box, TextField, Autocomplete } from "@mui/material";

export default function OverviewReview() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [recenzije, setRecenzije] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/recenzije")
      .then((res) => res.json())
      .then((result) => {
        setRecenzije(result);
      });
  }, []);

  return (
    <Container>
      <h1 style={{ color: "#1976d2" }}>Recenzije</h1>
      <Paper elevation={3} style={paperStyle}>
        {recenzije.map((recenzija) => (
          <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left" }} key={recenzija.id}>
            Id:{recenzija.id} <br />
            Ocjena: {recenzija.ocjena} <br />
            Komentar: {recenzija.opis} <br />
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}
