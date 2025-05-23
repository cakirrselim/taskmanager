import { NavLink, useNavigate } from "react-router-dom";
import "./Navbar.css";

function Navbar({ username, setUsername, roles }) {
    console.log("Roles in Navbar:", roles);


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

            <NavLink
                to="/tasks"
                className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
            >
                📝 Görevler
            </NavLink>

            {/* 👤 Sadece admin rolüne sahip kullanıcılar görür */}
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
                    🔧 Rol Ata / Kaldır
                </NavLink>
            )}
            {/* 👤 Sadece admin rolüne sahip kullanıcılar görür */}
            {roles?.includes("admin") && (
                <NavLink
                    to="/task-assign"
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    📝 Görev Oluştur
                </NavLink>
            )}
            {roles?.includes("admin") && (
                <NavLink
                    to="/user-management"  // /task-assign yerine /user-management
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    📝 Kullanıcı Ekle / Sil
                </NavLink>
            )}
            {roles?.includes("admin") && (
                <NavLink
                    to="/role-management"  // /task-assign yerine /role-management
                    className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                >
                    📝 Role Ekle / Sil
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
