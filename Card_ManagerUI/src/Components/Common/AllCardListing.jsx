import React, { useState, useEffect } from "react";
import { getAllCards } from "../Utils/ApiFunctions";
import Card from "./Card";

const AllCardListing = () => {
  const [cards, setCards] = useState([]);

  useEffect(() => {
    // Llama a la función para obtener todas las tarjetas cuando el componente se monta
    const fetchData = async () => {
      try {
        const data = await getAllCards();
        setCards(data);
      } catch (error) {
        console.error("Error al obtener las tarjetas:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="dark-container">
      <div className="container-sm d-flex justify-content-center align-items-center">
        <div className="row">
          {cards.map((card) => (
            <Card key={card.id} card={card} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default AllCardListing;
