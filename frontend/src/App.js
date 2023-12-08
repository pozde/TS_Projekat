import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomeDefault from "./components/defaultHome/home/HomeDefault";
import HomeAdmin from "./components/admin/home/HomeAdmin";
import HomeOverviewUser from "./components/admin/overviewUser/HomeOverviewUser";

import HomeAddMovie from "./components/admin/addMovie/HomeAddMovie";
import HomeEventsAdmin from "./components/admin/events/HomeEventsAdmin";
import HomeAboutUsAdmin from "./components/admin/aboutUs/HomeAboutUsAdmin";
import HomeLoginDefault from "./components/defaultHome/userLogin/HomeLoginDefault";
import HomeOverviewMovies from "./components/admin/overviewMovies/HomeOverviewMovies";
import HomeRegisterDefault from "./components/defaultHome/userRegister/HomeRegisterDefault";
import HomeMovies from "./components/defaultHome/movies/HomeMovies";

import HomeTicket from "./components/user/ticketReservation/HomeTicket";
import HomeAboutUs from "./components/defaultHome/aboutUs/HomeAboutUs";
import HomeProfile from "./components/user/profile/HomeProfile";
import HomeEvents from "./components/defaultHome/events/HomeEvents";

import HomeUser from "./components/user/home/HomeUser";
import HomeMoviesUser from "./components/user/movies/HomeMoviesUser";
import HomeAboutUsUser from "./components/user/aboutUs/HomeAboutUsUser";
import HomeEventsUser from "./components/user/events/HomeEventsUser";
import HomeForYou from "./components/user/forYou/HomeForYou";

import HomeAddUser from "./components/admin/addUser/HomeAddUser";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<HomeDefault />} />
          <Route path="/aboutus" element={<HomeAboutUs />} />
          <Route path="/events" element={<HomeEvents />} />

          <Route path="/homeAdmin" element={<HomeAdmin />} />

          <Route path="/homeUser" element={<HomeUser />} />
          <Route path="/MoviesUser" element={<HomeMoviesUser />} />
          <Route path="/aboutUsUser" element={<HomeAboutUsUser />} />
          <Route path="/eventsUser" element={<HomeEventsUser />} />
          <Route path="/forYou" element={<HomeForYou />} />
          <Route path="/profile" element={<HomeProfile />} />

          <Route path="/ticket/:kolicinaKarata/:idFilma" element={<HomeTicket />} />

          <Route path="/overviewUser" element={<HomeOverviewUser />} />

          <Route path="/eventsAdmin" element={<HomeEventsAdmin />} />
          <Route path="/aboutUsAdmin" element={<HomeAboutUsAdmin />} />
          <Route path="/overviewMovies" element={<HomeOverviewMovies />} />
          <Route path="/addMovie" element={<HomeAddMovie />} />
          <Route path="/addUser" element={<HomeAddUser />} />

          <Route path="/login" element={<HomeLoginDefault />} />
          <Route path="/register" element={<HomeRegisterDefault />} />
          <Route path="/movies" element={<HomeMovies />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
