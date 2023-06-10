import React, { useEffect, useState } from "react";
import { Container, Paper, Button, Box, TextField, Autocomplete } from "@mui/material";

export default function OverviewMachines() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [machine, setMachine] = useState("");
  const [quantity, setQuantity] = useState("");
  const [trainer, setTrainer] = useState("");
  const [trainingType, setTrainingType] = useState("");
  const [additionalComment, setAdditionalComment] = useState("");

  const [sprave, setMachines] = useState([]);

  const handleClick = (e) => {
    e.preventDefault();
    const order = {
      masina: machine,
      kolicina: quantity,
    };
    fetch("http://localhost:8080/naruciMasinu", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(order),
    }).then(() => {
      console.log("Uspjesno ste narucili spravu!");
    });
  };

  useEffect(() => {
    fetch("http://localhost:8080/sprave")
      .then((res) => res.json())
      .then((result) => {
        setMachines(result);
      });
  }, []);

  const allAppointments = [];

  const allMachines = ["Kros masina", "Benc klupa", "Razboj", "Vratilo", "Tegovi", "Traka za trcanje"];
  const allQuantity = ["1", "2", "3", "4", "5"];

  return (
    <Container>
      <h1 style={{ color: "#1976d2" }}>Sprave</h1>
      <Paper elevation={3} style={paperStyle}>
        {sprave.map((sprava) => (
          <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left" }} key={sprava.id}>
            Id: {sprava.id} <br />
            Vrsta: {sprava.vrsta} <br />
            Kolicina: {sprava.kolicina} <br />
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}
