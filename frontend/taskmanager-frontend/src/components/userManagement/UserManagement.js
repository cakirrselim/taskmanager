// UserManagement.js
import React, { useState, useEffect } from "react";
import axios from "axios";
import "./UserManagement.css";

function UserManagement({ roles }) {
    const [users, setUsers] = useState([]);
    const [newUser, setNewUser] = useState({
        name: "",
        surname: "",
        username: "",
        password: "",
        email: ""
    });

    const BASE_URL = "http://localhost:8080/users";

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/show`);
            console.log("Mevcut kullanıcılar:", response.data);
            setUsers(response.data);
        } catch (error) {
            console.error("Kullanıcılar yüklenirken hata:", error);
            alert("Kullanıcılar yüklenemedi: " + (error.response?.data || error.message));
        }
    };

    const handleAddUser = async (e) => {
        e.preventDefault();
        try {
            console.log("Gönderilecek veri:", newUser);

            await axios.post(`${BASE_URL}/add`, newUser);
            alert("Kullanıcı başarıyla eklendi!");
            setNewUser({ name: "", surname: "", username: "", password: "", email: "" });
            fetchUsers();
        } catch (error) {
            console.error("Kullanıcı eklenirken hata:", error);
            alert("Kullanıcı eklenemedi: " + (error.response?.data || error.message));
        }
    };

    const handleDeleteUser = async (userId) => {
        if (window.confirm("Bu kullanıcıyı silmek istediğinizden emin misiniz?")) {
            try {
                await axios.delete(`${BASE_URL}/delete/${userId}`);
                alert("Kullanıcı başarıyla silindi!");
                fetchUsers();
            } catch (error) {
                console.error("Kullanıcı silinirken hata:", error);
                alert("Kullanıcı silinemedi: " + (error.response?.data || error.message));
            }
        }
    };

    if (!roles?.includes("admin")) {
        return <p className="no-access">Bu sayfayı görüntüleme yetkiniz yok.</p>;
    }

    return (
        <div className="task-assignment-container">
            <div className="left-panel">
                <div className="action-section">
                    <h3>👤 Yeni Kullanıcı Ekle</h3>
                    <form onSubmit={handleAddUser}>
                        <input
                            type="text"
                            placeholder="Ad"
                            value={newUser.name}
                            onChange={(e) => setNewUser({...newUser, name: e.target.value})}
                            required
                        />
                        <input
                            type="text"
                            placeholder="Soyad"
                            value={newUser.surname}
                            onChange={(e) => setNewUser({...newUser, surname: e.target.value})}
                            required
                        />
                        <input
                            type="text"
                            placeholder="Kullanıcı Adı"
                            value={newUser.username}
                            onChange={(e) => setNewUser({...newUser, username: e.target.value})}
                            required
                        />
                        <input
                            type="password"
                            placeholder="Şifre"
                            value={newUser.password}
                            onChange={(e) => setNewUser({...newUser, password: e.target.value})}
                            required
                        />
                        <input
                            type="email"
                            placeholder="E-posta"
                            value={newUser.email}
                            onChange={(e) => setNewUser({...newUser, email: e.target.value})}
                            required
                        />
                        <button type="submit" className="create-button">Kullanıcı Ekle</button>
                    </form>
                </div>
            </div>

            <div className="right-panel">
                <h3>📋 Mevcut Kullanıcılar</h3>
                <div className="tasks-grid">
                    {users.map((user) => (
                        <div key={user.id} className="task-card">
                            <div>
                                <h4>{user.name} {user.surname}</h4>
                                <p>@{user.username}</p>
                                <p>{user.email}</p>
                            </div>
                            <button
                                onClick={() => handleDeleteUser(user.id)}
                                className="delete-button"
                            >
                                Sil
                            </button>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default UserManagement;