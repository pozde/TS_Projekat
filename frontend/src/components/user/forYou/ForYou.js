import React, { useEffect, useState } from "react";
import SingleContent from "../SingleContent/SingleContent";
import "./Movies.css";
import axios from "axios";

const ForYou = () => {
  const [filmovi, setFilmovi] = useState([]);
  const [selectedGenres, setSelectedGenres] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredMovies, setFilteredMovies] = useState([]);

  const [korisnik, setKorisnik] = useState({});
  const email = localStorage.getItem("email");

  let [films, setFilms] = useState([]);
  const [nazivZanraArray, setNazivZanraArray] = useState([]);

  const fetchKorisnik = async () => {
    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
      const response = await axios.get(`${BASE_URL}/korisnik/email/${email}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setKorisnik(response.data);
    } catch (error) {
      console.error("Failed to fetch users:", error);
    }
  };

  const fetchFilmovi = async () => {
    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
      const response = await axios.get(`${BASE_URL}/filmovi`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setFilmovi(response.data);
    } catch (error) {
      console.error("Failed to fetch movies:", error);
    }
  };

  const fetchFilms = async () => {
    if (filmovi.length > 0) {
      setFilms(korisnik.karte.map((karta) => karta.film));

      if (films && films.length > 0) {
        setNazivZanraArray(
          films.flatMap((film) => {
            if (film.zanrovi && film.zanrovi.length > 0) {
              return film.zanrovi.map((zanr) => zanr.nazivZanra);
            }
            return [];
          })
        );
      }
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      await fetchKorisnik();
      fetchFilmovi();
    };

    fetchData();
    fetchFilms().then(() => {
      filterMovies();
    });

    //filterMovies();
  }, [searchTerm, selectedGenres, filmovi, nazivZanraArray, films]);

  const filterMovies = () => {
    const filteredMovies = filmovi.filter((movie) => {
      // Filter based on selected genres
      if (nazivZanraArray.length > 0) {
        const allGenresCurr = movie.zanrovi.map((el) => el.nazivZanra);
        const hasCommonElement = allGenresCurr.some((genre) => nazivZanraArray.includes(genre));

        if (!hasCommonElement) {
          return false;
        }
      }

      // Filter out movies from the `films` array
      const hasMovie = films.some((film) => film.nazivFilma === movie.nazivFilma);
      if (hasMovie) {
        return false;
      }

      return true;
    });

    setFilteredMovies(filteredMovies);
  };

  //const filteredContent = selectedGenres.length > 0 ? filteredMovies : filmovi;
  //console.log("Filtrirano", filteredContent);

  return (
    <div>
      <h1 style={{ marginTop: "20px" }}>FILMOVI ZA VAS</h1>
      <div className="movies">
        {filteredMovies.length > 0 ? (
          filteredMovies.map((c) => (
            <div key={c.id}>
              <SingleContent id={c.id} naziv={c.nazivFilma} trajanje={c.trajanje} opis={c.opis} poster={c.posterPath} />
            </div>
          ))
        ) : (
          <p>
            <b>Ne postoje filmovi sa odabranim žanrovima.</b>
          </p>
        )}
      </div>
    </div>
  );
};

export default ForYou;
