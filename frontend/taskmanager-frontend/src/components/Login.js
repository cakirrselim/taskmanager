import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login({ setUsername, setUserId }) {
    const [usernameInput, setUsernameInput] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/auth/login", {
                username: usernameInput,
                password,
            });

            const { username, id } = response.data;

            if (username && id !== undefined) {
                setUsername(username);
                setUserId(id);
                setError("");
                navigate("/");
            } else {
                setError("Sunucudan geçerli kullanıcı verisi alınamadı.");
                console.error("Eksik kullanıcı verisi:", response.data);
            }
        } catch (err) {
            console.error("Giriş başarısız:", err);
            setError("Kullanıcı adı veya şifre hatalı.");
        }
    };

    return (
        <div className="login-container">
            <h2>Giriş Yap</h2>
            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Kullanıcı Adı"
                    value={usernameInput}
                    onChange={(e) => setUsernameInput(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Şifre"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button type="submit">Giriş</button>
                {error && <p className="error">{error}</p>}
            </form>
        </div>
    );
}

export default Login;
