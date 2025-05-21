import { NavLink, useNavigate } from "react-router-dom";
import "./Navbar.css";

function Navbar({ username, setUsername }) {
    const navigate = useNavigate();

    const handleLogout = () => {
        setUsername(null);
        navigate("/login");
    };

    return (
        <div className="nav">
            <NavLink
                to="/"
                className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
            >
                🏠 Ana Sayfa
            </NavLink>
            <NavLink
                to="/roles"
                className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
            >
                🎯 Roller
            </NavLink>
            {/*<NavLink*/}
            {/*    to="/users"*/}
            {/*    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}*/}
            {/*>*/}
            {/*    👤 Kullanıcılar*/}
            {/*</NavLink>*/}
            <NavLink
                to="/tasks"
                className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
            >
                📝 Görevler
            </NavLink>

            {username ? (
                <div className="nav-logout">
                    <span>👋 {username}</span>
                    <button onClick={handleLogout} className="logout-btn">
                        🚪 Çıkış Yap
                    </button>
                </div>
            ) : (
                <NavLink
                    to="/login"
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    🔐 Giriş
                </NavLink>
            )}
        </div>
    );
}

export default Navbar;
