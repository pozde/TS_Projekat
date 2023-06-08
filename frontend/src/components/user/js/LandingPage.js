import React from "react";
import "../css/LandingPage.css";
import { useHistory } from "react-router-dom";

const LandingPage = (props) => {
  const history = useHistory();

  const handleSwitchToLanding = () => {
    history.push("/landing");
  };

  const handleSwitchToAbout = () => {
    history.push("/aboutUs");
  };

  const handleLogout = () => {
    localStorage.removeItem("access_token");
    localStorage.removeItem("refresh_token");
    history.push("/");
  };

  return (
    <div className="landing-page">
      <div className="header">
        <h1>KINO</h1>
        <div className="nav-buttons">
          <button onClick={handleSwitchToLanding}>
            Početna
            <br />
            <span className="small-text">Početna stranica aplikacije</span>
          </button>
          <button onClick={handleSwitchToAbout}>
            O nama
            <br />
            <span className="small-text">Više informacija o našem kinu</span>
          </button>
          <button onClick={handleLogout}>Odjava</button>
        </div>
      </div>
      <div className="content">
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <h2>Dobrodošli korisniče!</h2>
        <p>Ambassing.</p>
      </div>
    </div>
  );
};

export default LandingPage;
