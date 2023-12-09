import React, { useEffect, useState } from "react";
import AppbarUser from "../AppbarUser";
import AboutUsUser from "./AboutUsUser";

import notFoundImage from "../../../images/notFound.png";
import jwt_decode from "jwt-decode";

function HomeAboutUsUser() {
  const [isLogged, setIsLogged] = useState(true); // Track whether the user is logged in
  const [isUser, setIsUser] = useState(false); // Track whether the user has admin role

  useEffect(() => {
    const checkLoginStatus = () => {
      const token = localStorage.getItem("access_token");

      // Check if the token is empty
      if (!token) {
        setIsLogged(false);
        return;
      }

      const decodedToken = jwt_decode(token);

      // Check if the user has the user role
      setIsUser(decodedToken.role === "ROLE_USER");

      setIsLogged(true);
    };

    checkLoginStatus();
  }, []);

  return (
    <>
      {isLogged && isUser && <AppbarUser />}
      {isLogged ? (
        isUser ? (
          <AboutUsUser />
        ) : (
          <div style={{ textAlign: "center", margin: "20px" }}>
            <p className="no-permission-title">Nemate pristup korisničkim stranicama.</p>
            <img src={notFoundImage} alt="notFound" />
            <button className="no-permission-button" onClick={() => (window.location.href = "/homeAdmin")}>
              Return to Homepage
            </button>
          </div>
        )
      ) : (
        <div style={{ textAlign: "center", margin: "20px" }}>
          <p className="no-permission-title">Morate biti prijavljeni da biste pristupili ovom sadržaju.</p>
          <img src={notFoundImage} alt="notFound" />
          {/* Add a link or button to navigate to the login page */}
          <button className="no-permission-button" onClick={() => (window.location.href = "/login")}>
            Go to Login
          </button>
        </div>
      )}
    </>
  );
}

export default HomeAboutUsUser;
