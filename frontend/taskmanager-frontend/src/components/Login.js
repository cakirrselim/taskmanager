import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login({ setUsername }) {
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

            console.log("Giriş başarılı", response.data);

            setUsername(response.data.username);  // Kullanıcı adını global state'e gönder
            setError(""); // Önceki hataları temizle
            navigate("/"); // Anasayfaya yönlendir
        } catch (err) {
            console.error("Giriş başarısız", err);
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
