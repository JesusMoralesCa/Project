import React from "react";

const Breadcrumb = ({ backgroundImage, page, text }) => {
    return (
        <div className="normal-breadcrumb" style={{ backgroundImage: `url(${backgroundImage})` }}>
            <div className="container">
                <div className="row">
                    <div className="col-lg-12 text-center">
                        <div className="normal__breadcrumb__text">
                            <h2>{page}</h2>
                            <p>{text}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Breadcrumb;
