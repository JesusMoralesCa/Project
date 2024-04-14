import React, { useState, useEffect } from "react";
import { createCard, getAllBoosterPackName } from "../Utils/ApiFunctions";
import Breadcrumb from "../Layout/Breadcrumb";

const AddNewCard = () => {
  const [newCard, setNewCard] = useState({
    cardName: "",
    cardImage: null,
    description: "",
    packName: "",
  });

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [imagePreview, setImagePreview] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Current newCard state:", newCard);
    try {
      const success = await createCard(
        newCard.cardName,
        newCard.cardImage,
        newCard.description,
        newCard.packName
      );

      if (success !== undefined) {
        setSuccessMessage("New Card created: " + newCard.cardName);
        setNewCard({
          cardName: "",
          cardImage: null,
          description: "",
          packName: "",
        });
        setImagePreview("");
        setErrorMessage("");
      } else {
        setErrorMessage("Error creating card");
      }
    } catch (error) {
      setErrorMessage(error.message);
    }
    setTimeout(() => {
      setSuccessMessage("");
      setErrorMessage("");
    }, 3000);
  };

  const [packNames, setPackNames] = useState([]);

  useEffect(() => {
    async function fetchPackNames() {
      try {
        const packs = await getAllBoosterPackName();
        setPackNames(packs.map((pack) => pack.name));
      } catch (error) {
        console.error("Error obteniendo los nombres de los paquetes", error);
      }
    }

    fetchPackNames();
  }, []);

  const handlePackChange = (e) => {
    const selectedPack = e.target.value;
    console.log("Selected Pack:", selectedPack);
    setNewCard((prevCard) => ({ ...prevCard, packName: selectedPack }));
  };

  const handleInputChange = (e) => {
    setNewCard({ ...newCard, [e.target.name]: e.target.value });
  };

  const handleImageChange = (e) => {
    const selectedImage = e.target.files[0];
    setNewCard({ ...newCard, cardImage: selectedImage });
    setImagePreview(URL.createObjectURL(selectedImage));
  };

  return (
    <>
            <Breadcrumb 
                backgroundImage="../src/img/break_yu.jpg" 
                page="New Card" 
                text="Create a new Card" 
            />

      <div className="signup spad">
        <div className="container">
          <div className="row">
            <div className="col-lg-6">
              <div className="login__form">
                <form onSubmit={handleSubmit}>
                  <h3 htmlFor="cardName">
                    Card Name
                  </h3>
                  <div className="input__item">
                    <input
                      required
                      type="text"
                      className="form-control"
                      id="cardName"
                      name="cardName"
                      value={newCard.cardName}
                      onChange={handleInputChange}
                    />
                    
                  </div>
                  <div className="input__item">
                    <h3 htmlFor="description" >
                      Card Description
                    </h3>
                    <input
                      required
                      type="text"
                      className="form-control"
                      id="description"
                      name="description"
                      value={newCard.description}
                      onChange={handleInputChange}
                    />
                    
                  </div>
                  <div className="input__item">
                    <h3 htmlFor="packName" >
                      Pack
                    </h3>
                    <select
                      className="form-select"
                      name="packName"
                      size="1"
                      value={newCard.packName}
                      onChange={handlePackChange}
                    >
                      <option>Select</option>
                      {packNames.map((name, index) => (
                        <option key={index} value={name}>
                          {name}
                        </option>
                      ))}
                    </select>
                  </div>
                  <button type="submit" className="site-btn">Save Card</button>
                </form>
              </div>
            </div>
            <div className="col-lg-6">
                
              <h3 htmlFor="cardImage" className="Card__Image__h3">
                Card image
              </h3>
              <input
                required
                name="cardImage"
                id="cardImage"
                type="file"
                className="form-control"
                onChange={handleImageChange}
              />
              {imagePreview && (
                <img
                  src={imagePreview}
                  alt="Preview"
                  style={{ maxWidth: "200px", maxHeight: "200px" }}
                  className="border border-5 mb-3 mt-3"
                ></img>
              )}

              {successMessage && (
                <div className="alert alert-success fade show">
                  {" "}
                  {successMessage}
                </div>
              )}

              {errorMessage && (
                <div className="alert alert-danger fade show"> {errorMessage}</div>
              )}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AddNewCard;
