import React from "react";
import { Link } from "react-router-dom";

const Pack = ({ pack }) => {
  return (
    <div className="col-lg-4 col-md-6 col-sm-6">
      <div className="Card__Item">
        <div className="Card__Item__pic set-bg"
            style={{
            width: "50%",
            height: "50%"
          }}>
          <Link to={`/${pack.name}`} title={pack.name}>
            <img
                src={`data:image/jpg;base64, ${pack.image}`}
                alt={pack.name}
                className="card-image"
                style={{ display: "block", width: "100%", height: "100%",marginLeft:"20px" }}
            />
          </Link>
        </div>
        <div className="Card__Item__text">
          <h5>
            <Link to={`/${pack.name}`} title={pack.name}>{pack.name}</Link>
          </h5>
        </div>
      </div>
    </div>
  );
};

export default Pack;