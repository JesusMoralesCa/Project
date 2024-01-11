import React, { useState, useEffect } from "react";
import { createCard, getAllBoosterPackName } from "../Utils/ApiFunctions";

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

      if (success) {
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
    <section className="container mt-5 mb5">
      <div className="row justify-content-center">
        <div className="col-lg-3">
          <h2 className="mt-5 mb-2">Add a New Card</h2>
          {successMessage && (
            <div className="alert alert-success fade show">
              {" "}
              {successMessage}
            </div>
          )}

          {errorMessage && (
            <div className="alert alert-danger fade show"> {errorMessage}</div>
          )}

          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="cardName" className="form-label">
                Card Name
              </label>
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

            <div className="mb-3">
              <label htmlFor="description" className="form-label">
                Card Description
              </label>
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

            <div className="mb-3">
              <label htmlFor="packName" className="form-label">
                Pack
              </label>

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

            <div className="mb-3">
              <label htmlFor="cardImage" className="form-label">
                Card image
              </label>
              <input
                required
                name="file"
                id="file"
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
            </div>

            <div className="d-grid gap-2 d-md-flex mt-2">
              <button type="submit" className="btn btn-outline-primary ml-5">
                Save Card
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
};

export default AddNewCard;
