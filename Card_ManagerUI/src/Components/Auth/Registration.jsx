import React, { useState } from "react";
import { registerUser } from "../Utils/ApiFunctions";
import { Link } from "react-router-dom";
import Breadcrumb from "../Layout/Breadcrumb";
import { CgProfile } from "react-icons/cg";
import { CiMail } from "react-icons/ci";
import { RiLockPasswordLine } from "react-icons/ri";

const Registration = () => {
  const [registration, setRegistration] = useState({
    username: "",
    email: "",
    password: "",
  });

  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleInputChange = (e) => {
    setRegistration({ ...registration, [e.target.name]: e.target.value });
  };

  const handleRegistration = async (e) => {
    e.preventDefault();
    try {
      const result = await registerUser(registration);
      setSuccessMessage(result);
      setErrorMessage("");
      setRegistration({ username: "", email: "", password: "" });
    } catch (error) {
      setSuccessMessage("");
      setErrorMessage(`Registration error : ${error.message}`);
    }
    setTimeout(() => {
      setErrorMessage("");
      setSuccessMessage("");
    }, 5000);
  };

  return (
    <>
      <Breadcrumb
        backgroundImage="../src/img/break_RE.jpg"
        page="Registration"
        text="Welcome to the TCG Manager"
      />


      <div class="signup spad">
        <div class="container">
          <div class="row">
            <div class="col-lg-6">
              <div class="login__form">
                <h3>Sign Up</h3>
                <form onSubmit={handleRegistration}>
                  <div class="input__item">
                    <input
                      placeholder="Username"
                      id="username"
                      name="username"
                      type="username"
                      className="form-control"
                      value={registration.username}
                      onChange={handleInputChange}
                    />
                    <span class="icon_profile"><CgProfile /></span>
                  </div>
                  <div class="input__item">
                    <input placeholder="Email"
                      id="email"
                      name="email"
                      type="email"
                      className="form-control"
                      value={registration.email}
                      onChange={handleInputChange}
                    />
                    <span class="icon_mail"><CiMail /></span>
                  </div>
                  <div class="input__item">
                    <input
                      placeholder="Password"
                      type="password"
                      className="form-control"
                      id="password"
                      name="password"
                      value={registration.password}
                      onChange={handleInputChange}
                    />
                    <span class="icon_lock"><RiLockPasswordLine /></span>
                  </div>
                  <button type="submit" class="site-btn">Login Now</button>
                </form>
                <h5>Already have an account? <Link to={"/login"}>Log in!</Link></h5>
              </div>
            </div>
            <div class="col-lg-6">

            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Registration;
