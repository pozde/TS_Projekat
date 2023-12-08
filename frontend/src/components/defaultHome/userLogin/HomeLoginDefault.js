import React, { useEffect, useState } from "react";
import LoginDefault from "./LoginDefault";
import AppbarDefault from "../AppbarDefault";

import notFoundImage from "../../../images/notFound.png";

function HomeLoginDefault() {
  const [isLogged, setIsLogged] = useState(true); // Track whether the user is logged in

  useEffect(() => {
    const checkLoginStatus = () => {
      const token = localStorage.getItem("access_token");

      // Check if the token is empty
      if (!token) {
        setIsLogged(false);
        return;
      }
    };

    checkLoginStatus();
  }, []);

  return (
    <>
      <AppbarDefault />
      <LoginDefault />
    </>
  );
}

export default HomeLoginDefault;
