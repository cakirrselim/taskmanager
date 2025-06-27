import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import "./Login.css";

function Login({setUsername, setUserId, setRoles}) {
    const [usernameInput, setUsernameInput] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const BACKEND_URL = "http://localhost:8080";

            const response = await axios.post(`${BACKEND_URL}/auth/login`, {
                username: usernameInput,
                password,
            });

            const {username, id, roles} = response.data;
            console.log("Gelen roller:", roles);

            if (username && id !== undefined) {
                setUsername(username);
                setUserId(id);

                if (roles && Array.isArray(roles)) {
                    const roleNames = roles
                        .map(r => r.toLowerCase())
                        .filter(Boolean);
                    localStorage.setItem("roles", JSON.stringify(roleNames));
                    localStorage.setItem("role", roleNames.includes("admin") ? "admin" : "user");
                    setRoles(roleNames);  // burada da düzelt
                } else {
                    setRoles([]);
                    localStorage.setItem("roles", JSON.stringify([]));
                    localStorage.setItem("role", "user");
                }

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
