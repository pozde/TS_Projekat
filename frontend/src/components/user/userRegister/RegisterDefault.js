import React, { useState } from "react";
import { Container, TextField, Button, Typography, Box } from "@mui/material";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";

function RegisterDefault() {
  const [username, setUsername] = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [dateBirth, setDateBirth] = useState("");
  const [input, setInput] = useState({});
  const [errors, setErrors] = useState({});

  function handleChange(event) {
    input[event.target.name] = event.target.value;
    setInput(input);
  }
  function handleSubmit(event) {
    event.preventDefault();
    if (validate()) {
      let input = {};
      input["password"] = "";
      input["confirm_password"] = "";
      setInput(input);
      alert("Form is submitted");
    }
    
  }
  function validate() {
    let errors = {};
    let isValid = true;
    if (!input["password"]) {
      isValid = false;
      errors["password"] = "Please enter your password.";
    }
    if (!input["confirm_password"]) {
      isValid = false;
      errors["confirm_password"] = "Please enter your confirm password.";
    }
    if (typeof input["password"] !== "undefined") {
      if (input["password"].length < 6) {
        isValid = false;
        errors["password"] = "Please add at least 6 charachter.";
      }
    }
    if (typeof input["password"] !== "undefined" && typeof input["confirm_password"] !== "undefined") {
      if (input["password"] !== input["confirm_password"]) {
        isValid = false;
        errors["confirm_password"] = "Passwords don't match.";
      }
    }
    setErrors(errors);
    return isValid;
  }

  return (
    <Container maxWidth="xs" sx={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", marginTop: "100px", bgcolor: "#ffffff" }}>
      <Typography variant="h4" sx={{ marginBottom: 3 }}>
        Registracija
      </Typography>
      <Box component="form" sx={{ width: "100%" }}>
        <TextField id="outlined-basic" label="Username" variant="outlined" value={username} onChange={(e) => setUsername(e.target.value)} fullWidth sx={{ marginBottom: 2 }} />
        <TextField label="Password" variant="outlined" type="password" name="password" value={input.password} onChange={(e) => handleChange(e)} fullWidth sx={{ marginBottom: 2 }} />
        <div className="text-danger">{errors.password}</div>
        <TextField label="Potvrdi password" variant="outlined" type="password" name="confirm_password" value={input.confirm_password} onChange={(e) => handleChange(e)} fullWidth sx={{ marginBottom: 2 }} />
        <div className="text-danger">{errors.confirm_password}</div>
        <TextField id="outlined-basic" label="Ime" variant="outlined" value={name} onChange={(e) => setName(e.target.value)} fullWidth sx={{ marginBottom: 2 }} />
        <TextField id="outlined-basic" label="Prezime" variant="outlined" value={surname} onChange={(e) => setSurname(e.target.value)} fullWidth sx={{ marginBottom: 2 }} />
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DemoContainer components={["DatePicker"]}>
            <DatePicker label="Datum rođenja" value={dateBirth} onChange={(newValue) => setDateBirth(newValue)} sx={{ marginBottom: 2 }} />
          </DemoContainer>
        </LocalizationProvider>
        <Button variant="contained" color="primary" fullWidth sx={{ marginBottom: 2 }} onClick={handleSubmit}>
          Registracija
        </Button>
      </Box>
    </Container>
  );
}

export default RegisterDefault;
