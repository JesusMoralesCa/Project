const Navbar = () => {
  return (
<header className="p-1 w-full bg-gray-800">
  <nav className="container mx-auto flex items-center justify-between">

    {/* Div izquierda */}
    <div className="text-white text-xl font-semibold">CARDMARKET</div>

    {/* Div centro */}
    <div className="flex items-center space-x-4">
      <div className="flex space-x-4 items-center">

      <div className="rounded-md border border-cyan-50">
        <input
          type="email"
          className="p-1 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          placeholder="nombre@ejemplo.com"
        />
      </div>

        <div className="rounded-md border border-cyan-50">
        <input
          type="password"
          className="p-1 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          placeholder="password"
        />
        </div>

      </div>
      <div className="space-x-4">
        <button className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-4 rounded focus:outline-none focus:shadow-outline border border-cyan-50">Iniciar Sesión</button>
        <button className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-4 rounded focus:outline-none focus:shadow-outline border border-cyan-50">Registrarse</button>
      </div>
    </div>

    {/* Div derecha */}
    <div className="flex items-center space-x-4">
      <div className="space-x-4">
        <button className="bg-gray-600 hover:bg-gray-700 text-white font-bold py-1 px-4 rounded focus:outline-none focus:shadow-outline border border-cyan-50">#</button>
        <button className="bg-gray-600 hover:bg-gray-700 text-white font-bold py-1 px-4 rounded focus:outline-none focus:shadow-outline border border-cyan-50">#</button>
      </div>
    </div>
  </nav>
</header>




      
  )
}

export default Navbar