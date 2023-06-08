import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect,
} from "react-router-dom";
import LoginPage from "./components/user/js/LoginPage";
import "./App.css";
import "./components/user/css/RegisterPage.css";
import RegisterPage from "./components/user/js/RegisterPage";
import React from "react";
import LandingPage from "./components/user/js/LandingPage";
import "./components/user/css/LandingPage.css";
import HomePage from "./components/user/js/HomePage";
import AboutUsPage from "./components/user/js/AboutUsPage";
import "./components/user/css/AboutUsPage.css";
import AdminLandingPage from "./components/admin/js/AdminLandingPage";
import jwtDecode from "jwt-decode";

function App() {
  const handleLogout = () => {
    // Don't forget to clear the access tokens when logging out
    localStorage.removeItem("access_token");
    localStorage.removeItem("refresh_token");
  };

  // Higher-order component for protected routes
  const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route
      {...rest}
      render={(props) =>
        localStorage.getItem("access_token") ? (
          <Component {...props} />
        ) : (
          <Redirect to="/login" />
        )
      }
    />
  );

  return (
    <Router>
      <Switch>
        <Route path="/home">
          <HomePage />
        </Route>
        <Route path="/register">
          <RegisterPage />
        </Route>
        <Route path="/login">
          <LoginPage />
        </Route>
        <PrivateRoute
          path="/landing"
          component={LandingPage}
          onLogout={handleLogout}
        />
        <PrivateRoute
          path="/admin-landing"
          component={AdminLandingPage}
          onLogout={handleLogout}
        />
        <PrivateRoute path="/aboutUs" component={AboutUsPage} />
        <Route path="/">
          {/* Default route */}
          <HomePage />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
