const Navbar = () => {
  return (
    
    <header className="bg-blue-500 p-4 w-full">
    <nav className="container mx-auto flex items-center justify-between">
      <div className="text-white text-xl font-semibold">Mi Sitio</div>
      <div className="flex space-x-4">
        <a href="#" className="text-white">Inicio</a>
        <a href="#" className="text-white">Acerca de</a>
        <a href="#" className="text-white">Contacto</a>
      </div>
    </nav>
  </header>
      
  )
}

export default Navbar