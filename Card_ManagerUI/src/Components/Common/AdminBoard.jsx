import React from "react";

const AdminBoard = () => {
  return (
    <div class="container-fluid admin-board">
      <div class="row col-12 justify-content-center">
        <div class="d-flex justify-content-center ">
          <div
            class="contenedor-creacion-pack col-3"
            onClick={() => (window.location.href = "/NewPack")}
            style={{ cursor: "pointer" }}
          >
            <div class="texto-creacion-pack">Creación de Pack nuevo</div>
          </div>
          <div
            class="contenedor-creacion-pack col-3"
            onClick={() => (window.location.href = "/NewCard")}
            style={{ cursor: "pointer" }}
          >
            <div class="texto-creacion-pack">Creación de Carta nueva</div>
          </div>
          <div
            class="contenedor-creacion-pack col-3"
            onClick={() => (window.location.href = "/NewPack")}
            style={{ cursor: "pointer" }}
          >
            <div class="texto-creacion-pack">Administrar Usuarios</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminBoard;
