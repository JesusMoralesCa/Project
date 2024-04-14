import React, { useContext } from "react";
import { AuthContext } from "./AuthProvider";
import { Link, useNavigate } from "react-router-dom";

const Logout = () => {
  const auth = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    auth.handleLogout();
    navigate("/", { state: { message: "Desconectado" } });
    window.location.reload();
  };

  return (
    <>
      <ul>
        <li>
          <Link to={"/profile"}>
            Profile
          </Link>
        </li>
        <li>
          <Link onClick={handleLogout}>
            Logout
          </Link>
        </li>
      </ul>
    </>

  );
};

export default Logout;
