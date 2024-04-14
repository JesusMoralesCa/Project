import React, { useState, useEffect } from "react";
import { getAllBoosterPackName } from "../Utils/ApiFunctions";
import Pack from "./Pack";

const AllPackListing = () => {
    const [packs, setPacks] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getAllBoosterPackName();
                setPacks(data);
            } catch (error) {
                console.error("Error al obtener los packs:", error);
            }
        };

        fetchData();
    }, []);

    return (
        <div className="product-page spad">
            <div className="container">
                
                    <div className="col-lg-10">
                        <div className="product__page__content">
                            <div className="row">
                                {packs.map((pack) => (
                                    <Pack key={pack.id} pack={pack} />
                                ))}
                            </div>
                        </div>
                    </div>
                
            </div>
        </div>
    );
};

export default AllPackListing;
