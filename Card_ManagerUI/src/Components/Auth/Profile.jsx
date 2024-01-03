import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { deleteUser, getUser } from "../Utils/ApiFunctions";

const Profile = () => {
  const [user, setUser] = useState({
    id: "",
    username: "",
    email: "",
  });

  const [message, setMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const userId = localStorage.getItem("userId");
  const token = localStorage.getItem("token");

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const userData = await getUser(userId, token);
        setUser(userData);
      } catch (error) {
        console.error("Error fetching user:", error);
        setErrorMessage("Error fetching user data");
      }
    };

    fetchUser();
  }, [userId, token]);

  const handleDeleteAccount = async () => {
    const confirmed = window.confirm("¿Seguro que quieres borrar tu cuenta?");
    if (confirmed) {
      try {
        const response = await deleteUser(userId);
        setMessage(response.data);
        localStorage.removeItem("token");
        localStorage.removeItem("userId");
        localStorage.removeItem("userRole");
        navigate("/");
      } catch (error) {
        setErrorMessage(error.response.data);
      }
    }
  };

  return (
    <div className="container">
      {errorMessage && <p className="text-danger">{errorMessage}</p>}
      {message && <p className="text-success">{message}</p>}
      {user ? (
        <div
          className="card p-5 mt-5"
          style={{ backgroundColor: "whitesmoke" }}
        >
          <h4 className="card-title text-center">User Information</h4>
          <div className="card-body">
            <div className="col-md-10 mx-auto">
              <div className="card mb-3 shadow">
                <div className="row g-0">
                  <div className="col-md-2">
                    <div className="d-flex justify-content-center align-items-center mb-4">
                      <img
                        src="https://themindfulaimanifesto.org/wp-content/uploads/2020/09/male-placeholder-image.jpeg"
                        alt="Profile"
                        className="rounded-circle"
                        style={{
                          width: "150px",
                          height: "150px",
                          objectFit: "cover",
                        }}
                      />
                    </div>
                  </div>

                  <div className="col-md-10">
                    <div className="card-body">
                      <div className="form-group row">
                        <label className="col-md-2 col-form-label fw-bold">
                          ID:
                        </label>
                        <div className="col-md-10">
                          <p className="card-text">{user.id}</p>
                        </div>
                      </div>
                      <hr />

                      <div className="form-group row">
                        <label className="col-md-2 col-form-label fw-bold">
                          Email:
                        </label>
                        <div className="col-md-10">
                          <p className="card-text">{user.email}</p>
                        </div>
                      </div>
                      <hr />

                      <div className="form-group row">
                        <label className="col-md-2 col-form-label fw-bold">
                          Username
                        </label>
                        <div className="col-md-10">
                          <p className="card-text">{user.username}</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div className="d-flex justify-content-center">
                <div className="mx-2">
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={handleDeleteAccount}
                  >
                    Close account
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      ) : (
        <p>Loading user data...</p>
      )}
    </div>
  );
};

export default Profile;
