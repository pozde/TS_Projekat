import React from "react";
import { Container, Grid, Typography } from "@mui/material";
import DoneOutlineIcon from "@mui/icons-material/DoneOutline";
import FastfoodIcon from "@mui/icons-material/Fastfood";
import HourglassEmptyIcon from "@mui/icons-material/HourglassEmpty";
import ourLocation from "../../../images/ourLocation.png";

const AboutUsAdmin = () => {
  return (
    <Container maxWidth="lg" style={{ paddingTop: "40px" }}>
      <Grid container spacing={3}>
        {/* Left Column - About Us */}
        <Grid item xs={12} md={6} style={{ paddingRight: "40px" }}>
          <Typography fontWeight="bold" variant="h4" align="center" gutterBottom>
            O nama
          </Typography>
          <Typography variant="subtitle1" align="justify" style={{ marginTop: "50px", fontSize: "18px" }}>
            Mi smo kino koje pruža najbolje filmsko iskustvo u gradu. Naša kino dvorana opremljena je najmodernijom tehnologijom, a veliki zaslon, izvanredan zvuk i udobna sjedista čine vaše kino iskustvo nezaboravnim. Naša filmska selekcija obuhvaća širok spektar žanrova - od akcijskih blokbastera
            do drama i horora. Redovito ažuriramo našu ponudu kako bismo vam donijeli najnovije filmske hitove iz cijelog svijeta. Pored projekcija, organizujemo posebne događaje kao što su premijere filmova. Naša strastvena ekipa stoji vam na raspolaganju kako bismo vam osigurali nezaboravno
            iskustvo. Vidimo se u kinu!
          </Typography>
        </Grid>

        {/* Right Column - Our Location */}
        <Grid item xs={12} md={6}>
          <Typography fontWeight="bold" variant="h4" align="center" gutterBottom>
            Naša lokacija
          </Typography>
          <img src={ourLocation} alt="Nasa lokacija" style={{ width: "100%" }} />
        </Grid>
      </Grid>

      {/* Padding between the two-column and three-column parts */}
      <div style={{ padding: "40px 0" }}></div>

      {/* Additional Part - Three Columns */}
      <Grid container spacing={3} justifyContent="center">
        {/* First Column */}
        <Grid item xs={12} sm={4}>
          <div style={{ display: "flex", flexDirection: "column", alignItems: "center", textAlign: "center" }}>
            <DoneOutlineIcon style={{ fontSize: 120, marginBottom: 10 }} />
            <Typography fontWeight="bold" variant="body1">
              Najbolje ocijenjeno kino u državi
            </Typography>
          </div>
        </Grid>

        {/* Second Column */}
        <Grid item xs={12} sm={4}>
          <div style={{ display: "flex", flexDirection: "column", alignItems: "center", textAlign: "center" }}>
            <FastfoodIcon style={{ fontSize: 120, marginBottom: 10 }} />
            <Typography fontWeight="bold" variant="body1">
              Širok izbor grickalica i pića
            </Typography>
          </div>
        </Grid>

        {/* Third Column */}
        <Grid item xs={12} sm={4}>
          <div style={{ display: "flex", flexDirection: "column", alignItems: "center", textAlign: "center" }}>
            <HourglassEmptyIcon style={{ fontSize: 120, marginBottom: 10 }} />
            <Typography fontWeight="bold" variant="body1">
              Radno vrijeme 11:00-23:00
            </Typography>
          </div>
        </Grid>
      </Grid>
    </Container>
  );
};

export default AboutUsAdmin;
