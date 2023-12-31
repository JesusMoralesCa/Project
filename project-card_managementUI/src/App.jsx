import React from "react";
import NavBar from "./Layout/NavBar";
import { AuthProvider } from "./components/Auth/AuthProvider";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/Auth/Login";
import Registration from "./components/Auth/Registration";
import Profile from "./components/Auth/Profile";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";

function App() {
  return (
    <AuthProvider>
      <NavBar />
      <Router>
        <Routes>
          <Route index element={<Login />} />
          <Route path="/" element={<Registration />} />
          <Route path="/login" element={<Login />} />
          <Route path="/profile" element={<Profile />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
