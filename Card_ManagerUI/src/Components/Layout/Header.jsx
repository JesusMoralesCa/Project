import React from "react";
import { Link, NavLink } from "react-router-dom";
import Logout from "../Auth/Logout";

const Header = () => {


    const isLoggedIn = localStorage.getItem("token");
    const userRole = localStorage.getItem("userRole");



    return (
        <header class="header">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2">
                        <div class="header__logo">
                            <Link to={"/"}>
                                <img src="../src/img/Logo.png" alt=""></img>
                            </Link>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="">
                            <nav class="header__menu mobile-menu">
                                <ul>
                                    <li class="active"><a href="/">Homepage</a></li>
                                    <li><Link to={"/BoosterPacks"}>Boosters <span class="arrow_carrot-down"></span></Link></li>
                                    <li><Link to={"/NewCard"}>Cards <span class="arrow_carrot-down"></span></Link></li>
                                    <li><Link to={"/"}>Decks <span class="arrow_carrot-down"></span></Link></li>
                                    {isLoggedIn && userRole === "ROLE_ADMIN" && (
                                        <li>
                                            <NavLink to={"/admin"}>
                                                Admin
                                            </NavLink>
                                        </li>
                                    )}
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-2">
                        <div class="header__right mobile-menu">
                            {isLoggedIn ? (
                                <Logout />
                            ) : (
                                <>
                                    <ul>
                                        <li><Link to={"/Registration"}>Sign Up</Link></li>
                                        <li><Link to={"/login"}>Login</Link></li>
                                    </ul>
                                    
                                    
                                </>
                            )}


                        </div>
                    </div>
                </div>
                <div id="mobile-menu-wrap"></div>
            </div>
        </header>
    );

}

export default Header;