import React, { useState, useEffect } from "react";
import { Button, Grid, Typography, TextField, Modal, Box } from "@mui/material";
import { Paper } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import axios from "axios";

const PasswordChangeModal = ({ open, onClose, user }) => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");

  const handlePasswordChange = async () => {
    onClose();
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box
        sx={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 300,
          bgcolor: "white", // Set background color
          borderRadius: "8px", // Add border radius for a rounded appearance
          boxShadow: 24,
          p: 4,
        }}
      >
        <Typography variant="h6" component="div" gutterBottom sx={{ color: "black" }}>
          Promijeni šifru
        </Typography>
        <TextField
          label="Trenutni password"
          type="password"
          fullWidth
          value={currentPassword}
          onChange={(e) => setCurrentPassword(e.target.value)}
          margin="normal"
          sx={{
            mb: 2, // Add margin bottom
            "& .MuiInputLabel-root": { color: "rgba(0, 0, 0, 255)" }, // Adjust label color
            "& .MuiInputBase-input": { color: "rgba(0, 0, 0, 255)" }, // Adjust input text color
            "& .MuiInput-underline:before": { borderBottomColor: "rgba(0, 0, 0, 0.42)" }, // Adjust underline color
            "& .MuiInput-underline:hover:not(.Mui-disabled):before": { borderBottomColor: "rgba(0, 0, 0, 0.87)" }, // Adjust underline hover color
          }}
        />

        <TextField
          label="Novi password"
          type="password"
          fullWidth
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
          margin="normal"
          sx={{
            mb: 2, // Add margin bottom
            "& .MuiInputLabel-root": { color: "rgba(0, 0, 0, 255)" }, // Adjust label color
            "& .MuiInputBase-input": { color: "rgba(0, 0, 0, 255)" }, // Adjust input text color
            "& .MuiInput-underline:before": { borderBottomColor: "rgba(0, 0, 0, 0.42)" }, // Adjust underline color
            "& .MuiInput-underline:hover:not(.Mui-disabled):before": { borderBottomColor: "rgba(0, 0, 0, 0.87)" }, // Adjust underline hover color
          }} // Add margin bottom
        />
        <Button variant="contained" onClick={handlePasswordChange} color="primary" fullWidth>
          Potvrdi
        </Button>
      </Box>
    </Modal>
  );
};

const Profile = () => {
  const [user, setUser] = useState({});
  const [openPasswordModal, setOpenPasswordModal] = useState(false);
  const email = localStorage.getItem("email");

  useEffect(() => {
    const fetchUser = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
        const response = await axios.get(`${BASE_URL}/user/email/${email}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setUser(response.data);
      } catch (error) {
        console.error("Failed to fetch users:", error);
      }
    };
    fetchUser();
  }, []);

  const handleOpenPasswordModal = () => {
    setOpenPasswordModal(true);
  };

  const handleClosePasswordModal = () => {
    setOpenPasswordModal(false);
  };

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
          key={user.id}
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
              value={user.ime}
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
              value={user.prezime}
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
              value={user.datumRodjenja}
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
              value={user.email}
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
              value={user.brojTelefona}
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
              value={user.spol}
              InputProps={{
                style: {
                  fontWeight: "bold",
                },
              }}
            />
          </div>
          <Button fullWidth variant="contained" color="primary" onClick={handleOpenPasswordModal} sx={{ color: "white" }}>
            Promijeni šifru
          </Button>
        </Paper>
      </Grid>

      {/* PasswordChangeModal component */}
      <PasswordChangeModal open={openPasswordModal} onClose={handleClosePasswordModal} />
    </Grid>
  );
};

export default Profile;
