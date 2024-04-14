import React, { useState } from "react";
import { createPack } from "../Utils/ApiFunctions";
import { Link } from "react-router-dom";
import Breadcrumb from "../Layout/Breadcrumb";

const AddBoosterPack = () => {
  const [newPack, setNewPack] = useState({
    packName: "",
    file: null,
  });

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [imagePreview, setImagePreview] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const success = await createPack(newPack.packName, newPack.file);
      if (success !== undefined) {
        setSuccessMessage("New pack created: " + newPack.packName);
        setNewPack({
          packName: "",
          file: null,
        });
        setImagePreview("");
        setErrorMessage("");
      } else {
        setErrorMessage("Error creating pack");
      }
    } catch (error) {
      setErrorMessage(error.message);
    }
    setTimeout(() => {
      setSuccessMessage("");
      setErrorMessage("");
    }, 3000);
  };

  const handleInputChange = (e) => {
    setNewPack({ ...newPack, [e.target.name]: e.target.value });
  };

  const handleImageChange = (e) => {
    const selectedImage = e.target.files[0];
    setNewPack({ ...newPack, file: selectedImage });
    setImagePreview(URL.createObjectURL(selectedImage));
  };

  return (
    <>
            <Breadcrumb 
                backgroundImage="../src/img/break_NP.jpg" 
                page="New Pack" 
                text="Create a new Booster Pack" 
            />

      <div className="signup spad">
        <div className="container">
          <div className="row">
            <div className="col-lg-6">
              <div className="login__form">
                <form onSubmit={handleSubmit}>
                  <h3 htmlFor="cardName">
                    Pack Name
                  </h3>
                  <div className="input__item">
                    <input
                      required
                      type="text"
                      className="form-control"
                      id="packName"
                      name="packName"
                      value={newPack.packName}
                      onChange={handleInputChange}
                    />
                    
                  </div>
                  <button type="submit" className="site-btn">Save Pack</button>
                </form>
              </div>
            </div>
            <div className="col-lg-6">
                
              <h3 htmlFor="cardImage" className="Card__Image__h3">
                Pack image
              </h3>
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

export default AddBoosterPack;
