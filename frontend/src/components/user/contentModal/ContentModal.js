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
import axios from "axios";

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

  const [kolicinaKarata, setKolicinaKarata] = React.useState(0);
  const navigate = useNavigate();

  const navigateToTicketPage = () => {
    navigate(`/ticket/${kolicinaKarata}/${id}`);
  };

  const handleChange = (event) => {
    setKolicinaKarata(event.target.value);
  };

  const [filmovi, setFilmovi] = useState([]);

  useEffect(() => {
    const fetchFilmovi = async () => {
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
  }, []);

  /*useEffect(() => {
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

            <div style={{ display: "flex", alignItems: "center", color: "white", marginTop: "10px" }}>
              <InputLabel htmlFor="kolicinaKarata" style={{ color: "white", marginRight: "10px" }}>
                Odaberite broj karata:
              </InputLabel>
              <FormControl sx={{ m: 1, flex: "1" }}>
                <Select
                  style={{
                    color: "white",
                    width: "100%",
                    border: "1px solid white",
                    borderRadius: "4px",
                    backgroundColor: "transparent",
                  }}
                  value={kolicinaKarata}
                  onChange={handleChange}
                  displayEmpty
                  inputProps={{ "aria-label": "1" }}
                  id="kolicinaKarata"
                >
                  <MenuItem value="">
                    <em></em>
                  </MenuItem>
                  <MenuItem value={1}>1</MenuItem>
                  <MenuItem value={2}>2</MenuItem>
                  <MenuItem value={3}>3</MenuItem>
                  <MenuItem value={4}>4</MenuItem>
                  <MenuItem value={5}>5</MenuItem>
                </Select>
              </FormControl>
            </div>
            <Button style={{ marginTop: "10px", backgroundColor: "white", color: "black", fontWeight: "bold" }} onClick={navigateToTicketPage}>
              REZERVISI KARTE
            </Button>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}
