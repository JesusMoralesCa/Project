import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { getPackLow, getAllCardsFromBoosterPack } from "../Utils/ApiFunctions";
import PackCardListing from "../Common/PackCardListing";

const PackPageInfo = () => {

    const { packName } = useParams();
    const [pack, setPack] = useState(null);
    const [cardList, setCardList] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getPackLow("BT01_New_Evolution");
                setPack(data);
            } catch (error) {
                console.error("Error al obtener los datos del paquete", error);
            }
        };

        fetchData();
    }, []);

    useEffect(() => {
        const fetchCards = async () => {
            try {
                const cardsData = await getAllCardsFromBoosterPack("BT01_New_Evolution");
                
                setCardList(cardsData);
            } catch (error) {
                console.error("Error al obtener las cartas del paquete", error);
            }
        };

        fetchCards();
    }, []);
  
    if (!pack) {
      return <p>Cargando...</p>;
    }


  return (
    <>
    <div class="card-details spad">
      <div class="container">
        <div class="card__details__content">
          <div class="row">
            <div class="col-lg-3">
              <div class="card__details__pic set-bg">
              <img
                src={`data:image/jpg;base64, ${pack.image}`}
                alt={pack.name}
                className="card-image"
                style={{ display: "block", width: "100%", height: "100%",marginLeft:"20px" }}
            />
              </div>
            </div>
            <div class="col-lg-9">
              <div class="card__details__text">
                <div class="card__details__title">
                  <h3>{pack.name}</h3>
                </div>
                <p>{pack.name}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <PackCardListing cardList={cardList} />
    </>
  );
};

export default PackPageInfo;
