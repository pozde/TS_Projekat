import React, { useEffect, useState } from "react";
import { Container, Paper, Button, Box, TextField, Autocomplete } from "@mui/material";

export default function MachineOrder() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [machine, setMachine] = useState("");
  const [quantity, setQuantity] = useState("");
  const [trainer, setTrainer] = useState("");
  const [trainingType, setTrainingType] = useState("");
  const [additionalComment, setAdditionalComment] = useState("");

  const handleClick = (e) => {
    e.preventDefault();
    const order = {
      vrsta: machine,
      kolicina: quantity,
    };
    fetch("http://localhost:8080/dodajSpravu", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(order),
    }).then(() => {
      console.log("Uspjesno ste narucili spravu!");
    });
  };

  const allAppointments = [];

  const allMachines = ["Kros masina", "Benc klupa", "Razboj", "Vratilo", "Tegovi", "Traka za trcanje"];
  const allQuantity = ["1", "2", "3", "4", "5"];

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1 style={{ color: "#1976d2" }}>Narucivanje sprave</h1>
        <Box
          component="form"
          sx={{
            "& > :not(style)": { m: 1 },
          }}
          noValidate
          autoComplete="off"
        >
          <Autocomplete disablePortal id="combo-box-demo" options={allMachines} renderInput={(params) => <TextField {...params} label="Vrsta sprave" />} onChange={(e, newValue) => setMachine(newValue)} />
          <Autocomplete disablePortal id="combo-box-demo" options={allQuantity} renderInput={(params) => <TextField {...params} label="Kolicina" />} onChange={(e, newValue) => setQuantity(newValue)} />

          <Button variant="contained" onClick={handleClick}>
            Zavrsi narudzbu
          </Button>
        </Box>
      </Paper>
    </Container>
  );
}
