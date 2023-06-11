import * as React from "react";
import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { unavailable } from "../movies/config";
import { useEffect, useState } from "react";
import "./ContentModal.css";
import { img_300 } from "../movies/config";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormHelperText from "@mui/material/FormHelperText";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import Ticket from "../../logged/ticketReservation/Ticket";
import { useNavigate } from "react-router-dom";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "#2d2d2d",
  color: "#fff",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

export default function ContentModal({ children, id }) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [filmovi, setFilmovi] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/rezervacija-karata/films")
      .then((res) => res.json())
      .then((result) => {
        setFilmovi(result);
      });
  }, []);
  /*
  useEffect(() => {
    fetch("http://localhost:8081/filmovi")
      .then((res) => res.json())
      .then((result) => {
        console.log("RES: ", result);
        setFilmovi(result);
      });
  }, []);*/

  const currentFilm = filmovi.find((film) => film.id === id);

  return (
    <div>
      <div className="media" onClick={handleOpen}>
        {children}
      </div>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
        slots={{ backdrop: Backdrop }}
        slotProps={{
          backdrop: {
            timeout: 500,
          },
        }}
      >
        <Fade in={open}>
          <Box sx={style}>
            <Button style={{ position: "absolute", top: 0, right: 0, color: "#fff" }} onClick={handleClose}>
              X
            </Button>
            <Typography style={{ marginBottom: "10px" }} variant="h5" component="div" fontWeight="bold">
              {currentFilm?.nazivFilma || ""}
            </Typography>
            <Typography variant="body1" component="div" mb={2}>
              {currentFilm?.opis || ""}
            </Typography>
            <img style={{ width: "200px", height: "300px" }} className="poster" src={currentFilm?.posterPath ? `${currentFilm?.posterPath}` : unavailable} alt={currentFilm?.naziv} />
            <Typography variant="body2" component="div">
              Trajanje filma: {currentFilm?.trajanje || ""} minuta
            </Typography>
            <Typography variant="h5" component="div" style={{ marginTop: "10px" }}>
              Želite rezervisati kartu? Prijavite se!
            </Typography>

            <Button
              style={{
                marginTop: "10px",
                backgroundColor: "white",
                color: "black",
                fontWeight: "bold",
              }}
              onClick={() => (window.location.href = "/login")}
            >
              PRIJAVI SE
            </Button>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}
