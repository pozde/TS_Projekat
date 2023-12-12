import React, { useState, useEffect } from "react";
import { Button, Grid, Typography, TextField, Modal, Box, Paper } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import axios from "axios";
import "./Profile.css";
import PasswordChangeResultModal from "./PasswordChangeResultModal"; // Import the new modal

const PasswordChangeModal = ({ open, onClose }) => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [isSuccess, setIsSuccess] = useState(null);

  const handlePasswordChange = async () => {
    let checkPassword = document.getElementById("novi-password");
    let isPasswordValid = false;
    const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;
    if (!checkPassword.value.match(passwordRegex)) {
        checkPassword.classList.add("error-field");
        isPasswordValid = false;
      } else {
        checkPassword.classList.remove("error-field");
        isPasswordValid = true;
      }

    if(isPasswordValid){
        try {
            
        const token = localStorage.getItem("access_token");
        const email = localStorage.getItem("email");
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
        const response = await fetch(`${BASE_URL}/auth/reset-password/${email}/${currentPassword}/${newPassword}`, {
            method: "POST",
            headers: { Authorization: `Bearer ${token}` },
        });

        // Check the response status to determine success or failure
        if (response.ok) {
            setIsSuccess(response.ok);
            setSuccessMessage("Password changed successfully!");
        } else {
            setIsSuccess(false);
            setErrorMessage("Failed to change password. Please try again.");
        }
        } catch (error) {
        setIsSuccess(false);
        console.error("Error while setting password!", error);
        setErrorMessage("An error occurred. Please try again later.");
        } finally {
        onClose();
        }
    }
    else{
        let errorMessage = "Provjerite unesene podatke za polja:\n- Password se mora sastojati od minimalno 8 karaktera te bar jednog velikog slova i bar jednog broja.\n";
        alert(errorMessage);
        return;
    }
  };

  return (
    <>
      <PasswordChangeResultModal open={isSuccess !== null} onClose={() => setIsSuccess(null)} isSuccess={isSuccess} />
      <Modal open={open} onClose={onClose}>
        <Box
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 300,
            bgcolor: "white",
            borderRadius: "8px",
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
              mb: 2,
            }}
          />

          <TextField
            label="Novi password"
            type="password"
            id = "novi-password"
            fullWidth
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            margin="normal"
            sx={{
              mb: 2,
            }}
          />
          <Button variant="contained" onClick={handlePasswordChange} color="primary" fullWidth>
            Potvrdi
          </Button>
        </Box>
      </Modal>
    </>
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

          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
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
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
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
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
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
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
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
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
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
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
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
