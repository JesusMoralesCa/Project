import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";

import NavBar from "./Components/Layout/NavBar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "../src/Components/Auth/Login";
import Registration from "../src/Components/Auth/Registration";
import Profile from "../src/Components/Auth/Profile";
import Home from "../src/Components/CardManager/Home";
import Footer from "../src/Components/Layout/Footer";
import AddBoosterPack from "./Components/CardManager/AddBoosterPack";
import AddNewCard from "../src/Components/CardManager/AddNewCard";
import CardPageInfo from "../src/Components/CardManager/CardPageInfo";
import { AuthProvider } from "./Components/Auth/AuthProvider";

function App() {
  return (
    <>
      <AuthProvider>
        <main>
          <Router>
            <NavBar></NavBar>
            <Routes>
              <Route index element={<Home />}></Route>
              <Route path="/" element={<Home />}></Route>
              <Route path="/Login" element={<Login />}></Route>
              <Route path="/registration" element={<Registration />}></Route>
              <Route path="/profile" element={<Profile />}></Route>
              <Route path="/NewPack" element={<AddBoosterPack />}></Route>
              <Route path="/NewCard" element={<AddNewCard />}></Route>
              <Route
                path="/:packName/:cardName"
                element={<CardPageInfo />}
              ></Route>
            </Routes>
          </Router>
          <Footer></Footer>
        </main>
      </AuthProvider>
    </>
  );
}

export default App;
