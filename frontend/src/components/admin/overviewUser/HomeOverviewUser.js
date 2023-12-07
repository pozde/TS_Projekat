import React, { useEffect, useState } from "react";
import AppbarAdmin from "../AppbarAdmin";
import OverviewUser from "./OverviewUser";
import jwt_decode from 'jwt-decode';

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
            <p>Nemate pristup. Kontaktirajte administratora.</p>
            <button  onClick={() => window.location.href = "/homeUser"}>Return to Homepage</button>
          </div>
        )
      ) : (
        <div style={{ textAlign: "center", margin: "20px" }}>
          <p>Morate biti prijavljeni da biste pristupili ovom sadržaju.</p>
          {/* Add a link or button to navigate to the login page */}
          <button onClick={() => window.location.href = "/login"}>Go to Login</button>
        </div>
      )}
    </>
  );
}

export default HomeOverviewUser;
