import React, { useState, useEffect } from "react";
import axios from "axios";
import "./AddUser.css";

const BASE_URL = "http://localhost:8080/users";

function AddUser({ roles }) {
    const [users, setUsers] = useState([]);
    const [newUser, setNewUser] = useState({
        name: "",
        surname: "",
        username: "",
        password: "",
        email: ""
    });

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/show`);
            setUsers(response.data);
        } catch (error) {
            alert("Kullanıcılar yüklenemedi: " + (error.response?.data || error.message));
        }
    };

    const handleChange = (e) => {
        setNewUser({ ...newUser, [e.target.name]: e.target.value });
    };

    const handleAddUser = async (e) => {
        e.preventDefault();
        try {
            await axios.post(`${BASE_URL}/add`, newUser);
            alert("Kullanıcı başarıyla eklendi!");
            setNewUser({
                name: "",
                surname: "",
                username: "",
                password: "",
                email: ""
            });
            fetchUsers();
        } catch (error) {
            alert("Kullanıcı eklenemedi: " + (error.response?.data || error.message));
        }
    };

    const handleDeleteUser = async (id) => {
        if (window.confirm("Bu kullanıcı silinsin mi?")) {
            try {
                await axios.delete(`${BASE_URL}/delete/${id}`);
                alert("Kullanıcı silindi!");
                fetchUsers();
            } catch (error) {
                alert("Kullanıcı silinemedi: " + (error.response?.data || error.message));
            }
        }
    };

    if (!roles?.includes("admin")) {
        return <p className="no-access">Bu sayfaya erişiminiz yok.</p>;
    }

    return (
        <div className="adduser-container">
            <form className="adduser-form" onSubmit={handleAddUser}>
                <h2>Kullanıcı Ekle</h2>
                <input
                    name="name"
                    type="text"
                    placeholder="Ad"
                    value={newUser.name}
                    onChange={handleChange}
                    required
                />
                <input
                    name="surname"
                    type="text"
                    placeholder="Soyad"
                    value={newUser.surname}
                    onChange={handleChange}
                    required
                />
                <input
                    name="username"
                    type="text"
                    placeholder="Kullanıcı Adı"
                    value={newUser.username}
                    onChange={handleChange}
                    required
                />
                <input
                    name="password"
                    type="password"
                    placeholder="Şifre"
                    value={newUser.password}
                    onChange={handleChange}
                    required
                />
                <input
                    name="email"
                    type="email"
                    placeholder="E-posta"
                    value={newUser.email}
                    onChange={handleChange}
                    required
                />
                <button type="submit">Ekle</button>
            </form>

            <div className="adduser-userlist">
                <h3>Mevcut Kullanıcılar</h3>
                <ul>
                    {users.map(user => (
                        <li key={user.id}>
                            {user.name} {user.surname} - {user.username} ({user.email})
                            <button
                                className="delete-btn"
                                onClick={() => handleDeleteUser(user.id)}
                            >
                                Sil
                            </button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default AddUser;