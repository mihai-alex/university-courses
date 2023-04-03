import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container-fluid">
        <a className="navbar-brand" href="/">
          University Manager
        </a>
        <Link to="/professors" className="btn btn-outline-light">
          Professors
        </Link>
      </div>
    </nav>
  );
};

export default Navbar;
