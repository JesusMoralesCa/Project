import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { getSingleCard } from "../Utils/ApiFunctions";

const CardPageInfo = () => {
  const { cardName } = useParams();
  const [card, setCard] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getSingleCard(cardName);
        setCard(data);
      } catch (error) {
        console.error("Error al obtener los datos", error);
      }
    };

    fetchData();
  }, [cardName]); // Agrega cardName como dependencia para que useEffect se ejecute cuando cambie

  if (!card) {
    // Muestra un mensaje de carga o maneja la carga de alguna manera mientras se obtienen los datos
    return <p>Cargando...</p>;
  }

  return (
    <div className="card-container">
      <div className="image-container">
        <a href={`/${card.boosterPack}/${card.name}`}>
          <img
            src={`data:image/jpg;base64, ${card.image}`}
            alt={card.name}
            className="card-image"
          />
        </a>
      </div>
      <div className="text-container">
        <p className="card-name">
          <a
            href={`/${card.boosterPack}/${card.name}`}
            title={card.name}
            style={{ textDecoration: "none", color: "white" }}
          >
            {card.name} {card.description} {card.boosterPack}
          </a>
        </p>
      </div>
    </div>
  );
};

export default CardPageInfo;
