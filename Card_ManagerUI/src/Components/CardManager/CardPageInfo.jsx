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
  }, [cardName]);

  if (!card) {
    return <p>Cargando...</p>;
  }

  return (
    <div class="card-details spad">
      <div class="container">
        <div class="card__details__content">
          <div class="row">
            <div class="col-lg-3">
              <div class="card__details__pic set-bg" style={{ backgroundImage: `/${card.boosterPack}/${card.name}` }}></div>
            </div>
            <div class="col-lg-9">
              <div class="card__details__text">
                <div class="card__details__title">
                  <h3>{card.name}</h3>
                  <span>{card.boosterPack}</span>
                </div>
                <p>{card.description}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CardPageInfo;
