import React from "react";
import { Modal, Box, Typography, Button } from "@mui/material";

const PasswordChangeResultModal = ({ open, onClose, isSuccess }) => {
  return (
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
          {isSuccess ? "Uspješno ste promijenili šifru!" : "Unijeli ste pogrešan trenutni password!"}
        </Typography>

        <Button variant="contained" onClick={onClose} color="primary" fullWidth>
          Zatvori
        </Button>
      </Box>
    </Modal>
  );
};

export default PasswordChangeResultModal;
