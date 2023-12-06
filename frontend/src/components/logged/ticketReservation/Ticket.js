/* import { Container, Button, Typography, Box, Dialog, DialogTitle, DialogContent, DialogActions } from "@mui/material";
import WeekendIcon from "@mui/icons-material/Weekend";
import Grid from "@mui/material/Grid";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const Ticket = () => {
  const { kolicinaKarata } = useParams();
  const [sjedista, setSjedista] = useState([]);
  const [odabrana, setOdabrana] = useState([]);
  const [reservationSuccess, setReservationSuccess] = useState(false);

  useEffect(() => {
    fetch("http://localhost:8081/sjedista")
      .then((res) => res.json())
      .then((result) => {
        setSjedista(result.map((el) => el.brojSjedista));
      });
  }, []);

  const handleReservation = (event, currentSeat) => {
    if (!sjedista.includes(currentSeat)) {
      if (odabrana.length < kolicinaKarata) {
        setOdabrana((prevOdabrana) => [...prevOdabrana, currentSeat]);
      }
    }
  };

  const handleSubmit = (event) => {
    if (Number(odabrana.length) !== Number(kolicinaKarata)) {
      alert(`Molimo odaberite tacno ${kolicinaKarata} sjedista.`);
      return;
    }
    for (let i = 0; i < odabrana.length; i++) {
      const post = {
        brojSjedista: odabrana[i],
        sala: {
          id: "1",
          brojSale: "1",
        },
      };

      // Make a POST request to the server
      fetch("http://localhost:8081/dodajSjediste", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(post),
      })
        .then((response) => response.json())
        .then((data) => {
          console.log("Success:", data);
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
    setReservationSuccess(true);
  };

  const handleClose = () => {
    fetch("http://localhost:8081/sjedista")
      .then((res) => res.json())
      .then((result) => {
        setSjedista(result.map((el) => el.brojSjedista));
      });
    setOdabrana([]);
    setReservationSuccess(false);
  };

  return (
    <Container sx={{ marginTop: "10px", paddingTop: "30px", display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", backgroundColor: "#2d2d2d" }}>
      <Box sx={{ padding: "10px", backgroundColor: "white", border: "10px solid #ccc", width: "auto" }}>
        <Grid container spacing={2}>
          {Array.from(Array(10)).map((_, row) => (
            <Grid key={row} container item xs={12} justifyContent="center">
              {Array.from(Array(10)).map((_, col) => {
                const seatNumber = row * 10 + col + 1;
                const isSeatSelected = odabrana.includes(seatNumber) || sjedista.includes(seatNumber);
                const seatColor = isSeatSelected ? "red" : "blue";
                const isSeatDisabled = isSeatSelected || odabrana.length >= kolicinaKarata;
                return (
                  <Grid key={col} item sx={{ marginLeft: 2 }}>
                    <WeekendIcon style={{ color: seatColor, pointerEvents: isSeatDisabled ? "none" : "auto" }} onClick={(e) => handleReservation(e, seatNumber)} />
                  </Grid>
                );
              })}
            </Grid>
          ))}
        </Grid>
      </Box>
      <Typography variant="body1" sx={{ marginTop: 2, fontWeight: "bold", marginBottom: 2 }}>
        ODABRANA SJEDISTA: {odabrana.join(", ")}
      </Typography>
      <Button variant="contained" color="primary" sx={{ width: "50%", marginBottom: 2 }} onClick={handleSubmit}>
        Potvrdi
      </Button>
      <Dialog open={reservationSuccess} onClose={handleClose}>
        <DialogTitle>Uspjesna rezervacija</DialogTitle>
        <DialogContent>
          <Typography variant="body1">Uspjesno ste rezervisali sjedista: {odabrana.join(", ")}!</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>OK</Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
};

export default Ticket;
 */