import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import "./Register.css";

function Register() {
    const [form, setForm] = useState({
        name: "",
        surname: "",
        username: "",
        password: "",
        email: ""
    });
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        try {
            await axios.post("http://localhost:8080/register", form);
            alert("Kayıt başarılı! Giriş yapabilirsiniz.");
            navigate("/login");
        } catch (err) {
            setError("Kayıt sırasında hata oluştu: " + (err.response?.data || err.message));
        }
    };

    return (
        <div className="register-container">
            <form onSubmit={handleSubmit}>
                <input name="name" value={form.name} onChange={handleChange} placeholder="Ad" required />
                <input name="surname" value={form.surname} onChange={handleChange} placeholder="Soyad" required />
                <input name="username" value={form.username} onChange={handleChange} placeholder="Kullanıcı Adı" required />
                <input name="password" type="password" value={form.password} onChange={handleChange} placeholder="Parola" required />
                <input name="email" value={form.email} onChange={handleChange} placeholder="E-Posta" required />
                <button type="submit">Kayıt Ol</button>
                {error && <p className="error">{error}</p>}
            </form>
            <div style={{ marginTop: "16px", textAlign: "center" }}>
                <p>
                    Zaten bir hesabınız var mı?{" "}
                    <Link to="/login" style={{ color: "#1976d2" }}>
                        Giriş Yap
                    </Link>
                </p>
            </div>
        </div>
    );
}

export default Register;