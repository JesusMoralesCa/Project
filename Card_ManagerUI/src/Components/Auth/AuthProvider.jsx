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
    try {
      const decodedUser = jwtDecode(token);
      localStorage.setItem("userId", decodedUser.jti);
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
