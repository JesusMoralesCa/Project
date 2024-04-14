import React, { useEffect } from "react";
import Card from "./Card";

const PackCardListing = ({ cardList }) => {


  return (
    <div className="product-page spad">
      <div className="container">
        <div className="col-lg-10">
          <div className="product__page__content">
            <div className="row">
              {cardList.map((card) => (
                <Card key={card.id} card={card} />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PackCardListing;
