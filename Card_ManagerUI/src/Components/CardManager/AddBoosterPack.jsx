import React, { useState } from "react";
import { createPack } from "../Utils/ApiFunctions";
import { Link } from "react-router-dom";

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
    <section className="container mt-5 mb5">
      <div className="row justify-content-center">
        <div className="col-md-8 col-lg-6">
          <h2 className="mt-5 mb-2">Add a New Pack</h2>
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
              <label htmlFor="packName" className="form-label">
                Booster Pack Name
              </label>
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

            <div className="mb-3">
              <label htmlFor="file" className="form-label">
                Pack image
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
              <Link to={"/"} className="btn btn-outline-info">
                Cards
              </Link>
              <button type="submit" className="btn btn-outline-primary ml-5">
                Save Pack
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
};

export default AddBoosterPack;
