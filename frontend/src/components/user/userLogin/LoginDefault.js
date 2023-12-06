import React, { useState } from "react";
import { Container, TextField, Button, Typography, Box } from "@mui/material";
import { Link } from "react-router-dom";

function LoginDefault() {
  const [username, setUsername] = useState("");
  const [anchorElNav, setAnchorElNav] = React.useState(null);

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  return (
    <Container maxWidth="xs" sx={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", marginTop: "100px", bgcolor: "#ffffff" }}>
      <Typography variant="h4" sx={{ marginBottom: 3 }}>
        Login
      </Typography>
      <Box component="form" sx={{ width: "100%" }}>
        <TextField id="outlined-basic" label="Username" variant="outlined" value={username} onChange={(e) => setUsername(e.target.value)} fullWidth sx={{ marginBottom: 2 }} />
        <TextField label="Password" variant="outlined" type="password" fullWidth sx={{ marginBottom: 2 }} />
        <Button variant="contained" color="primary" fullWidth sx={{ marginBottom: 2 }} onClick={handleCloseNavMenu} component={Link} to={username === "admin" ? "/homeAdmin" : "/home"}>
          Login
        </Button>
      </Box>
    </Container>
  );
}

export default LoginDefault;
