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
      <li>
        <Link to={"/profile"}>
          Profile
        </Link>
      </li>
      <li onClick={handleLogout}>
        Logout
      </li>
    </>
  );
};

export default Logout;
