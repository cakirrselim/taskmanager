import { NavLink } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
    return (
        <div className="nav">
            <NavLink to="/" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>🏠 Ana Sayfa</NavLink>
            <NavLink to="/roles" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>🎯 Roller</NavLink>
            <NavLink to="/users" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>👤 Kullanıcılar</NavLink>
            <NavLink to="/tasks" className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>📝 Görevler</NavLink>
        </div>
    );
}

export default Navbar;
