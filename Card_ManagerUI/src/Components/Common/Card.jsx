import React from "react";

const Card = () => {
  return (
    <div className="col-sm-6 col-md-12 col-lg-4 mt-3 col-12">
      <div className="w-100">
        <div className="card-img-top img-fluid">
          <img
            src={`data:image/jpg;base64, ${card.image}`}
            alt={card.name}
            className="card-img-top img-fluid"
          />
        </div>
      </div>
      <div className="card-body d-flex flex-column px-2 py-2">
        <p className="card-text">
          <a href={`data:image/jpg;base64, ${card.image}`} title={card.name}>
            {card.name}
          </a>
        </p>
      </div>
    </div>
  );
};

export default Card;
