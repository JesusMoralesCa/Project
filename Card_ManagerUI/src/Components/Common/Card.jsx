import React from "react";

const Card = ({ card }) => {
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
            {card.name}
          </a>
        </p>
      </div>
    </div>
  );
};

export default Card;
