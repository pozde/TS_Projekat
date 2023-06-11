import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomeDefault from "./components/defaultHome/home/HomeDefault";
import HomeAdmin from "./components/admin/home/HomeAdmin";
import HomeMachineOrder from "./components/admin/machine/HomeMachineOrder";
import HomeOverviewUser from "./components/admin/overviewUser/HomeOverviewUser";
import HomeOverviewMachines from "./components/admin/overviewMachine/HomeOverviewMachines";
import HomeAddMovie from "./components/admin/addMovie/HomeAddMovie";
import HomeLoginDefault from "./components/defaultHome/userLogin/HomeLoginDefault";
import HomeOverviewReview from "./components/admin/overviewReview/HomeOverviewReview";
import HomeRegisterDefault from "./components/defaultHome/userRegister/HomeRegisterDefault";
import HomeMovies from "./components/defaultHome/movies/HomeMovies";

import HomeTicket from "./components/logged/ticketReservation/HomeTicket";
import HomeAboutUs from "./components/defaultHome/aboutUs/HomeAboutUs";
import HomeProfile from "./components/logged/profile/HomeProfile";
import HomeEvents from "./components/defaultHome/events/HomeEvents";

import HomeUser from "./components/user/home/HomeUser";
import HomeMoviesUser from "./components/user/movies/HomeMoviesUser";
import HomeAboutUsUser from "./components/user/aboutUs/HomeAboutUsUser";
import HomeEventsUser from "./components/user/events/HomeEventsUser";

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
          <Route path="/aboutusUser" element={<HomeAboutUsUser />} />
          <Route path="/eventsUser" element={<HomeEventsUser />} />
          <Route path="/profile" element={<HomeProfile />} />

          <Route path="/ticket/:kolicinaKarata" element={<HomeTicket />} />

          <Route path="/machineOrder" element={<HomeMachineOrder />} />
          <Route path="/overviewUser" element={<HomeOverviewUser />} />
          <Route path="/overviewMachine" element={<HomeOverviewMachines />} />
          <Route path="/overviewReview" element={<HomeOverviewReview />} />
          <Route path="/addMovie" element={<HomeAddMovie />} />

          <Route path="/login" element={<HomeLoginDefault />} />
          <Route path="/register" element={<HomeRegisterDefault />} />
          <Route path="/movies" element={<HomeMovies />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
