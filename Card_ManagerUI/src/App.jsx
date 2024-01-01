import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";

import NavBar from "./Components/Layout/NavBar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "../src/Components/Auth/Login";
import Registration from "../src/Components/Auth/Registration";
import Profile from "../src/Components/Auth/Profile";
import { AuthProvider } from "./Components/Auth/AuthProvider";

function App() {
  return (
    <>
      <AuthProvider>
        <main>
          <Router>
            <NavBar></NavBar>
            <Routes>
              <Route index element={<Login />}></Route>
              <Route path="/Login" element={<Login />}></Route>
              <Route path="/registration" element={<Registration />}></Route>
              <Route path="/profile" element={<Profile />}></Route>
            </Routes>
          </Router>
        </main>
      </AuthProvider>
    </>
  );
}

export default App;
