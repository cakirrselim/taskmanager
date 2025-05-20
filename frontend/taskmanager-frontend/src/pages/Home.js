import { useNavigate } from "react-router-dom";
import "./Home.css";

function Home({ username, setUsername }) {
    const navigate = useNavigate();

    const handleLogout = () => {
        setUsername(null);
        navigate("/login");
    };

    return (
        <div className="home-container">
            <header className="home-header">
                <h2>👋 Hoşgeldiniz, {username}!</h2>
                <button className="home-btn logout-btn" onClick={handleLogout}>
                    🚪 Çıkış Yap
                </button>
            </header>
        </div>
    );
}

export default Home;
