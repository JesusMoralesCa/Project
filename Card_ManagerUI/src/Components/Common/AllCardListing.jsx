import React, { useState, useEffect } from "react";
import { getAllCards } from "../Utils/ApiFunctions";
import Card from "./Card";
import ReactPaginate from "react-paginate";

const AllCardListing = () => {
  const [cards, setCards] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [searchTerm, setSearchTerm] = useState("");
  const cardsPerPage = 4; // Número de tarjetas por página

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getAllCards();
        setCards(data);
      } catch (error) {
        console.error("Error al obtener las tarjetas:", error);
      }
    };

    fetchData();
  }, []);

  const filteredCards = cards.filter(
    (card) =>
      card.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      card.description.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const pageCount = Math.ceil(filteredCards.length / cardsPerPage);

  const handlePageChange = ({ selected }) => {
    setPageNumber(selected);
  };

  const displayCards = filteredCards
    .slice(pageNumber * cardsPerPage, (pageNumber + 1) * cardsPerPage)
    .map((card) => <Card key={card.id} card={card} />);

  return (
    <div className="dark-container">
      <div className="container-sm">
        <div className="search-container">
          <input
            type="text"
            placeholder="Buscar por nombre..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className="row">{displayCards}</div>
        <div className="pagination-container">
          <ReactPaginate
            previousLabel={"Anterior"}
            nextLabel={"Siguiente"}
            breakLabel={"..."}
            pageCount={pageCount}
            onPageChange={handlePageChange}
            containerClassName={"pagination"}
            previousLinkClassName={"pagination-link"}
            nextLinkClassName={"pagination-link"}
            disabledClassName={"pagination-disabled"}
            activeClassName={"pagination-active"}
          />
        </div>
      </div>
    </div>
  );
};

export default AllCardListing;
