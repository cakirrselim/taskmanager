import { Link } from "react-router-dom";
import "./Home.css";

function Home() {
    return (
        <div className="home-container">
            <h1>👋 Hoş Geldiniz!</h1>
            <div className="home-buttons">
                <Link to="/roles" className="home-btn">🎯 Roller</Link>
                <Link to="/users" className="home-btn">👤 Kullanıcılar</Link>
                <Link to="/tasks" className="home-btn">📝 Görevler</Link>
            </div>
        </div>
    );
}

export default Home;
