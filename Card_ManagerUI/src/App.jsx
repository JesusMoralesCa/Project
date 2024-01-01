import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";

import NavBar from "./Components/Layout/NavBar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "../src/Components/Auth/Login";
import Registration from "../src/Components/Auth/Registration";

function App() {
  return (
    <>
      <Router>
        <NavBar></NavBar>
        <Routes>
          <Route index element={<Login />}></Route>
          <Route path="/Login" element={<Login />}></Route>
          <Route path="/registration" element={<Registration />}></Route>
        </Routes>
      </Router>
    </>
  );
}

export default App;
