import React, { useState } from "react";
import { loginUser } from "../Utils/ApiFunctions";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "./AuthProvider";
import Breadcrumb from "../Layout/Breadcrumb";
import { CiMail } from "react-icons/ci";
import { RiLockPasswordLine } from "react-icons/ri";

const Login = () => {
  const [errorMessage, setErrorMessage] = useState("");
  const [login, setLogin] = useState({
    email: "",
    password: "",
  });

  const navigate = useNavigate();
  const auth = useAuth();
  const location = useLocation();
  const redirectUrl = location.state?.path || "/";

  const handleInputChange = (e) => {
    setLogin({ ...login, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const success = await loginUser(login);
    if (success) {
      const token = success.token;
      auth.handleLogin(token);
      navigate(redirectUrl, { replace: true });
      window.location.reload();
    } else {
      setErrorMessage("Usuario o contraseña inválido.");
    }
    setTimeout(() => {
      setErrorMessage("");
    }, 4000);
  };

  return (
    <>
      <Breadcrumb
        backgroundImage="../src/img/break.jpg"
        page="Login"
        text="Welcome to the TCG Manager"
      />
      <div className="login spad">
        <div className="container">
          <div className="row">
            <div className="col-lg-6">
              <div className="login__form">
                <h3>Login</h3>
                <form onSubmit={handleSubmit}>
                  <div className="input__item">
                    <input
                      id="email"
                      name="email"
                      type="email"
                      className="form-control"
                      value={login.email}
                      onChange={handleInputChange}
                    />
                    <span className="icon_mail"><CiMail /></span>
                  </div>
                  <div className="input__item">
                    <input
                      id="password"
                      name="password"
                      type="password"
                      className="form-control"
                      value={login.password}
                      onChange={handleInputChange}
                    />
                    <span className="icon_lock"><RiLockPasswordLine /></span>
                  </div>
                  <button type="submit" className="site-btn">
                    Login Now
                  </button>
                </form>
                <Link to="#" className="forget_pass">
                  Forgot Your Password?
                </Link>
              </div>
            </div>
            <div className="col-lg-6">
              <div className="login__register">
                <h3>Don’t Have An Account?</h3>
                <Link to="/Registration" className="primary-btn">
                  Register Now
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
