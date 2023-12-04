import React, { useEffect, useState } from "react";
import Chip from "@mui/material/Chip";
import axios from "axios";

const Genres = ({ selectedGenres, setSelectedGenres, genres, setGenres, onGenreSelect, onGenreDeselect }) => {
  const handleAdd = (genre) => {
    onGenreSelect(genre);
    setSelectedGenres([...selectedGenres, genre]);
    setGenres(genres.filter((g) => g.id !== genre.id));
  };

  const handleRemove = (genre) => {
    onGenreDeselect(genre);
    setSelectedGenres(selectedGenres.filter((selected) => selected.id !== genre.id));
    setGenres([...genres, genre]);
  };

  /*const fetchGenres = async () => {
    const token = localStorage.getItem("access_token");
    try {
      const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8080";
      const response = await axios.get(`${BASE_URL}/rezervacija-karata/zanrovi/`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      console.log("resp", response.data);
      setGenres(response.data);
    } catch (error) {
      console.error("Failed to fetch genres:", error);
    }
  };*/

  /*const fetchGenres = () => {
    fetch("http://localhost:8081/zanrovi/")
      .then((res) => res.json())
      .then((result) => {
        setGenres(result);
      });
  };*/

  useEffect(() => {
    const fetchZanrovi = async () => {
      const token = localStorage.getItem("access_token");
      try {
        const BASE_URL = process.env.REACT_APP_BASE_URL || "http://localhost:8081";
        const response = await axios.get(`${BASE_URL}/zanrovi/`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setGenres(response.data);
      } catch (error) {
        console.error("Failed to fetch movies:", error);
      }
    };

    fetchZanrovi();

    return () => {
      setGenres([]); // unmounting
    };
    // eslint-disable-next-line
  }, []);

  /*useEffect(() => {
    //fetchGenres();

    

    return () => {
      setGenres([]); // unmounting
    };
    // eslint-disable-next-line
  }, []);*/

  return (
    <div style={{ padding: "6px 0" }}>
      {selectedGenres.map((genre) => (
        <Chip style={{ margin: 2, backgroundColor: "black", color: "white", fontSize: "16px", padding: "5px" }} label={genre.nazivZanra} key={genre.id} color="primary" clickable onDelete={() => handleRemove(genre)} />
      ))}
      {genres.map((genre) => (
        <Chip style={{ margin: 2, backgroundColor: "white", color: "black", fontSize: "16px", padding: "5px" }} label={genre.nazivZanra} key={genre.id} clickable size="small" onClick={() => handleAdd(genre)} />
      ))}
    </div>
  );
};

export default Genres;
