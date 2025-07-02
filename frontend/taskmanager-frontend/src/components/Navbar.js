import { NavLink, useNavigate } from "react-router-dom";
import "./Navbar.css";

function Navbar({ username, setUsername, roles }) {
    const navigate = useNavigate();
    console.log("Navbar'daki roller:", roles);

    const handleLogout = () => {
        setUsername(null);
        navigate("/login");
    };

    // Kullanıcı login değilse navbar boş döndürülüyor
    if (!username) {
        return (
            <div className="nav"></div>
        );
    }

    // Kullanıcı giriş yapmış, rol kontrolü yapalım:
    const isAdmin = roles?.includes("admin");

    return (
        <div className="nav">
            {/* Roller ve Görevler butonları tüm giriş yapanlarda görünür */}
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

            {/* Sadece admin rolündeyse diğer butonlar görünür */}
            {isAdmin && (
                <>
                    <NavLink
                        to="/users"
                        className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                    >
                        👤 Kullanıcılar
                    </NavLink>

                    <NavLink
                        to="/role-assignment"
                        className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                    >
                        🔧 Rol Ata/Kaldır
                    </NavLink>

                    <NavLink
                        to="/task-assignment"
                        className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                    >
                        📋 Görev Ata/Kaldır
                    </NavLink>

                    <NavLink
                        to="/task/add"
                        className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                    >
                        ✨ Görev Oluştur
                    </NavLink>

                    <NavLink
                        to="/user-management"
                        className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                    >
                        👥 Kullanıcı Ekle/Sil
                    </NavLink>

                    <NavLink
                        to="/role-management"
                        className={({ isActive }) => (isActive ? "nav-link active" : "nav-link")}
                    >
                        🎭 Rol Ekle/Sil
                    </NavLink>
                </>
            )}

            {/* Kullanıcı adı ve çıkış butonu */}
            <div className="nav-logout">
                <span>👋 {username}</span>
                <button onClick={handleLogout} className="logout-btn">
                    🚪 Çıkış Yap
                </button>
            </div>
        </div>
    );
}

export default Navbar;