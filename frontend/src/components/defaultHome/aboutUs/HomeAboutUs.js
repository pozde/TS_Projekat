import React, { useEffect, useState } from "react";
import AboutUs from "./AboutUs";
import AppbarDefault from "../AppbarDefault";
import notFoundImage from "../../../images/notFound.png";
import jwt_decode from "jwt-decode";

function HomeAboutUs() {
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLogged, setIsLogged] = useState(true); // Track whether the user is logged in

  useEffect(() => {
    const checkAdminRole = () => {
      const token = localStorage.getItem("access_token");

      // Check if the token is empty
      if (!token) {
        setIsLogged(false);
        return;
      }

      const decodedToken = jwt_decode(token);
      setIsAdmin(decodedToken.role === "ROLE_ADMIN");
    };

    checkAdminRole();
  }, []);

  return (
    <>
      {isLogged ? (
        isAdmin ? (
          <>
            <div style={{ textAlign: "center", margin: "20px" }}>
              <p className="no-permission-title">Nemate pristup. Samo nelogovani korisnik ima pristup ovoj stranici.</p>
              <img src={notFoundImage} alt="notFound" />
              <button className="no-permission-button" onClick={() => (window.location.href = "/homeAdmin")}>
                Return to Homepage
              </button>
            </div>
          </>
        ) : (
          <>
            <div style={{ textAlign: "center", margin: "20px" }}>
              <p className="no-permission-title">Nemate pristup. Samo nelogovani korisnik ima pristup ovoj stranici.</p>
              <img src={notFoundImage} alt="notFound" />
              <button className="no-permission-button" onClick={() => (window.location.href = "/homeUser")}>
                Return to Homepage
              </button>
            </div>
          </>
        )
      ) : (
        <>
          <AppbarDefault />
          <AboutUs />
        </>
      )}
    </>
  );
}

export default HomeAboutUs;
