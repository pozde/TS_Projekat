import React from "react";
import { Grid, Typography } from "@mui/material";

const Events = () => {
  return (
    <div style={{ marginTop: "50px" }}>
      <Grid container spacing={2} justifyContent="center">
        <Grid item xs={12} sm={6} md={6} lg={6} style={{ marginBottom: "20px" }}>
          <div className="square" style={{ border: "5px solid white", borderRadius: "8px", background: "#2d2d2d", padding: "16px", width: "300px", height: "300px", margin: "auto", textAlign: "left", color: "white", marginRight: "50px" }}>
            <Typography variant="h4" sx={{ textAlign: "center" }}>
              Premium članarina
            </Typography>
            <Typography variant="h5" fontWeight="bold" sx={{ textAlign: "center", marginTop: "20px", fontSize: "36px" }}>
              60 KM
            </Typography>
            <div style={{ marginTop: "16px" }} />
            <Typography variant="h6">
              Popust na sve projekcije u mjesecu u iznosu od 10%
              <br />
              Prijevremeni pristup rezervaciji karata za premijere
            </Typography>
          </div>
        </Grid>
        <Grid item xs={12} sm={6} md={6} lg={6} style={{ marginBottom: "20px" }}>
          <div className="square" style={{ border: "5px solid white", borderRadius: "8px", background: "#2d2d2d", padding: "16px", width: "300px", height: "300px", margin: "auto", textAlign: "left", color: "white", marginLeft: "50px" }}>
            <Typography variant="h4" sx={{ textAlign: "center" }}>
              Classic članarina
            </Typography>
            <Typography variant="h5" fontWeight="bold" sx={{ textAlign: "center", marginTop: "20px", fontSize: "36px" }}>
              50 KM
            </Typography>
            <div style={{ marginTop: "16px" }} />
            <Typography variant="h6">Svaka deseta projekcija filma besplatna</Typography>
          </div>
        </Grid>
        <Grid item xs={12} sm={6} md={6} lg={6} style={{ marginTop: "20px" }}>
          <div className="square" style={{ border: "5px solid white", borderRadius: "8px", background: "#2d2d2d", padding: "16px", width: "300px", height: "300px", margin: "auto", textAlign: "left", color: "white", marginRight: "50px" }}>
            <Typography variant="h4" sx={{ textAlign: "center" }}>
              Studentski popust
            </Typography>
            <div style={{ marginTop: "36px" }} />
            <Typography variant="h6">
              Popust za studente u iznosu od 20%
              <br />
              Obavezan pokaz indeksa prilikom preuzimanja karata
            </Typography>
          </div>
        </Grid>
        <Grid item xs={12} sm={6} md={6} lg={6} style={{ marginTop: "20px" }}>
          <div className="square" style={{ border: "5px solid white", borderRadius: "8px", background: "#2d2d2d", padding: "16px", width: "300px", height: "300px", margin: "auto", textAlign: "left", color: "white", marginLeft: "50px" }}>
            <Typography variant="h4" sx={{ textAlign: "center" }}>
              Porodični popust
            </Typography>
            <div style={{ marginTop: "36px" }} />
            <Typography variant="h6">Popust u iznosu od 20% za porodice prilikom kupovine 3 ili više karata</Typography>
          </div>
        </Grid>
      </Grid>
    </div>
  );
};

export default Events;
