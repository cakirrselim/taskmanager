import React from "react";
import { useNavigate } from "react-router-dom";

function LogoutButton({ setUsername }) {
    const navigate = useNavigate();

    const handleLogout = () => {
        setUsername(null);
        navigate("/login");
    };

    return (
        <button onClick={handleLogout} className="logout-btn" style={{ float: "right", margin: "10px" }}>
            🚪 Çıkış Yap
        </button>
    );
}

export default LogoutButton;
