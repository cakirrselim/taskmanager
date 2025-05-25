import { NavLink, useNavigate } from "react-router-dom";
import "./Navbar.css";

function Navbar({ username, setUsername, roles }) {
    const navigate = useNavigate();

    const handleLogout = () => {
        setUsername(null);
        navigate("/login");
    };

    return (
        <div className="nav">
            <NavLink
                to="/roles"
                className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
            >
                🎯 Roller
            </NavLink>

            <NavLink
                to="/tasks"
                className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
            >
                📝 Görevler
            </NavLink>

            {roles?.includes("admin") && (
                <NavLink
                    to="/users"
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    👤 Kullanıcılar
                </NavLink>
            )}

            {roles?.includes("admin") && (
                <NavLink
                    to="/assign-role"
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    🔧 Rol Ata/Kaldır
                </NavLink>
            )}

            {roles?.includes("admin") && (
                <NavLink
                    to="/task-assignment"
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    📋 Görev Ata/Kaldır
                </NavLink>
            )}

            {roles?.includes("admin") && (
                <NavLink
                    to="/task-assign"
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    ✨ Görev Oluştur
                </NavLink>
            )}

            {roles?.includes("admin") && (
                <NavLink
                    to="/user-management"
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    👥 Kullanıcı Ekle/Sil
                </NavLink>
            )}

            {roles?.includes("admin") && (
                <NavLink
                    to="/role-management"
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    🎭 Rol Ekle/Sil
                </NavLink>
            )}

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