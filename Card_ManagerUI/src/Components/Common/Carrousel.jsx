import React from "react";
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
const Carrousel = () => {

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  };

  return (
    <div className="product">
      <div className="container">
        <Slider {...settings} className="product__slider">
          <div className="product__items">
            <img src="../src/img/digimonLogo.jpg" alt="Digimon Logo" className="product__image" />
            <div className="row">
              <div className="col-lg-6">
                <div className="product__text">

                  <h2>Digimon Card Game</h2>
                  <p>Check out the product</p>
                  <a href="#"><span>Watch Now</span> <i className="fa fa-angle-right"></i></a>
                </div>
              </div>
            </div>
          </div>
          <div className="product__items">
            <img src="../src/img/magicLogo.jpg" alt="Magic Logo" className="product__image" />
            <div className="row">
              <div className="col-lg-6">
                <div className="product__text">

                  <h2>Magic The Gathering</h2>
                  <p>Check out the product</p>
                  <a href="#"><span>Watch Now</span> <i className="fa fa-angle-right"></i></a>
                </div>
              </div>
            </div>
          </div>
          <div className="product__items">
            <img src="../src/img/yugiohLogo.jpg" alt="Yu-Gi-Oh! Logo" className="product__image" />
            <div className="row">
              <div className="col-lg-6">
                <div className="product__text">

                  <h2>Yu-Gi-Oh!</h2>
                  <p>Check out the product</p>
                  <a href="#"><span>Watch Now</span> <i className="fa fa-angle-right"></i></a>
                </div>
              </div>
            </div>
          </div>
        </Slider>

      </div>
    </div>
  );
};

export default Carrousel;
