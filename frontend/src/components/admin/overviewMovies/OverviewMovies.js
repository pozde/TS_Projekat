import React, { useEffect, useState } from "react";
import { Container, Paper, Button } from "@mui/material";
import axios from "axios";

export default function OverviewMovies() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [filmovi, setFilmovi] = useState([]);

  const handleObrisi = async (idFilma) => {
    // Optimistically update the local state
    setFilmovi((prevFilmovi) => prevFilmovi.filter((film) => film.id !== idFilma));

    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
      await axios.delete(`${BASE_URL}/deleteFilm/${idFilma}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      console.log("Uspješno obrisano");
    } catch (error) {
      console.error("Failed to delete movie:", error);
      // If the server request fails, roll back the local state to its previous state
      // or handle the error in a way that makes sense for your application
      setFilmovi((prevFilmovi) => [...prevFilmovi]);
    }
  };

  useEffect(() => {
    fetch("http://localhost:8081/films")
      .then((res) => res.json())
      .then((result) => {
        setFilmovi(result);
      });
  }, []);

  return (
    <Container>
      <h1 style={{ color: "white" }}>Filmovi</h1>
      <Paper elevation={3} style={paperStyle}>
        {filmovi.map((film) => (
          <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left" }} key={film.id}>
            Id: {film.id} <br />
            Naziv: {film.nazivFilma} <br />
            Trajanje: {film.trajanje} <br />
            Opis: {film.opis} <br />
            URL slike:{" "}
            <a href={film.posterPath} target="_blank" rel="noopener noreferrer" style={{ display: "block", overflow: "hidden", whiteSpace: "nowrap", textOverflow: "ellipsis", maxWidth: "100%" }}>
              {film.posterPath}
            </a>{" "}
            <br />
            Zanrovi: {film.zanrovi.map((zanr) => zanr.nazivZanra).join(", ")}
            <Button variant="contained" color="error" style={{ width: "100%", marginTop: "15px" }} onClick={() => handleObrisi(film.id)}>
              OBRISI
            </Button>
          </Paper>
        ))}
      </Paper>
    </Container>
  );
}
