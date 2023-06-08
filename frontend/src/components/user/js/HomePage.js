import React from "react";
import { useHistory } from "react-router-dom";
import "../css/HomePage.css";

const HomePage = () => {
  const history = useHistory();

  return (
    <div className="photo-container-Homepage">
      <div className="container-Homepage">
        <h1 className="fontHome">Dobrodošli u naše kino!</h1>
        <div className="buttonHomePage">
          <button className="buttHome" onClick={() => history.push("/login")}>
            {" "}
            Prijava{" "}
          </button>
          <button
            className="buttHome"
            onClick={() => history.push("/register")}
          >
            Registracija
          </button>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
