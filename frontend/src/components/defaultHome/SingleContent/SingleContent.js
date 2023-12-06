import React from "react";
import { img_300, unavailable } from "../movies/config";
import "./SingleContent.css";
import ContentModal from "../contentModal/ContentModal";

const SingleContent = ({ id, naziv, trajanje, opis, poster }) => {
  return (
    <ContentModal id={id} className="media">
      <img className="poster" src={poster ? `${img_300}${poster}` : unavailable} alt={naziv} />

      <b className="title">{naziv}</b>
      <span className="sunTitle">Trajanje: {trajanje} minuta</span>
    </ContentModal>
  );
};

export default SingleContent;
