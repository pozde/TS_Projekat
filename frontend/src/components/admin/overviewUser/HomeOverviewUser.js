import React, { useEffect, useState } from "react";
import AppbarAdmin from "../AppbarAdmin";
import OverviewUser from "./OverviewUser";
import jwt_decode from 'jwt-decode';
import "./UserOverview.css";
import notFoundImage from "../../../images/notFound.png";

function HomeOverviewUser() {
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
      setIsAdmin(decodedToken.role === 'ROLE_ADMIN');
    };

    checkAdminRole();
  }, []);

  

  return (
    <>
      {isLogged && (isAdmin && <AppbarAdmin />)}
      {isLogged ? (
        isAdmin ? (
          <OverviewUser />
        ) : (
          <div style={{ textAlign: "center", margin: "20px" }}>
            <p className="no-permission-title">Nemate pristup. Kontaktirajte administratora.</p>
            <img src={notFoundImage} alt="notFound" />
            <button  className="no-permission-button" onClick={() => window.location.href = "/homeUser"}>Return to Homepage</button>
          </div>
        )
      ) : (
        <div style={{ textAlign: "center", margin: "20px" }}>
          <p className="no-permission-title">Morate biti prijavljeni da biste pristupili ovom sadržaju.</p>
          {/* Add a link or button to navigate to the login page */}
          <img src={notFoundImage} alt="notFound" />
          <button className="no-permission-button" onClick={() => window.location.href = "/login"}>Go to Login</button>
        </div>
      )}
    </>
  );
}

export default HomeOverviewUser;
