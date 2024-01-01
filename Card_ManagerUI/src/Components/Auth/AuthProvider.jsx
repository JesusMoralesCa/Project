import React, { createContext, useState, useContext } from "react";
//import * as jwtDecode from "jwt-decode"; // Cambiado de import { decode } a import * as jwtDecode
import jwtDecode from "jwt-decode";

export const AuthContext = createContext({
  user: null,
  handleLogin: (token) => {},
  handleLogout: () => {},
});

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const handleLogin = (token) => {
    if (!token) {
      console.error("No se proporcionó un token válido.");
      // Manejar el caso en el que no se proporciona un token válido
      return;
    }

    try {
      const decodedUser = jwtDecode(token); // Cambiado de jwtDecode a decode
      localStorage.setItem("userId", decodedUser.sub);
      localStorage.setItem("userRole", decodedUser.roles);
      localStorage.setItem("token", token);
      setUser(decodedUser);
    } catch (error) {
      console.error("Error decoding token:", error);
      // Manejar el error de decodificación aquí (puede ser un token inválido)
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("userId");
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, handleLogin, handleLogout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};
