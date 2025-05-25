// RoleManagement.js
import React, { useState, useEffect } from "react";
import axios from "axios";
import "./RoleManagement.css";

function RoleManagement({ roles }) {
    const [allRoles, setAllRoles] = useState([]);
    const [newRole, setNewRole] = useState({
        name: ""
    });

    const BASE_URL = "http://localhost:8080/role";

    useEffect(() => {
        fetchRoles();
    }, []);

    const fetchRoles = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/show`);
            console.log("Mevcut roller:", response.data);
            setAllRoles(response.data);
        } catch (error) {
            console.error("Roller yüklenirken hata:", error);
            alert("Roller yüklenemedi: " + (error.response?.data || error.message));
        }
    };

    const handleAddRole = async (e) => {
        e.preventDefault();
        if (!newRole.name.trim()) {
            alert("Rol adı boş olamaz!");
            return;
        }
        try {
            const roleData = {
                name: newRole.name
            };

            await axios.post(`${BASE_URL}/add`, roleData);
            alert("Rol başarıyla eklendi!");
            setNewRole({ name: "" });
            fetchRoles();
        } catch (error) {
            console.error("Rol eklenirken hata:", error);
            alert("Rol eklenemedi: " + (error.response?.data || error.message));
        }
    };

    const handleDeleteRole = async (roleId) => {
        if (window.confirm("Bu rolü silmek istediğinizden emin misiniz?")) {
            try {
                await axios.delete(`${BASE_URL}/deleteRole/${roleId}`);
                alert("Rol başarıyla silindi!");
                fetchRoles();
            } catch (error) {
                console.error("Rol silinirken hata detayı:", {
                    message: error.message,
                    response: error.response?.data,
                    status: error.response?.status,
                    roleId: roleId
                });
                alert("Rol silinemedi: " + (error.response?.data || error.message));
            }
        }
    };

    if (!roles?.includes("admin")) {
        return <p className="no-access">Bu sayfayı görüntüleme yetkiniz yok.</p>;
    }

    return (
        <div className="role-assignment-container">
            <div className="left-panel">
                <div className="action-section">
                    <h3>🎭 Yeni Rol Ekle</h3>
                    <form onSubmit={handleAddRole}>
                        <input
                            type="text"
                            placeholder="Rol Adı"
                            value={newRole.name}
                            onChange={(e) => setNewRole({...newRole, name: e.target.value})}
                            required
                        />
                        <button type="submit" className="create-button">Rol Ekle</button>
                    </form>
                </div>
            </div>

            <div className="right-panel">
                <div className="lists-container">
                    <div className="roles-column">
                        <h4>🎭 Mevcut Roller</h4>
                        <div className="roles-list">
                            {allRoles.map((role) => (
                                <div key={role.id} className="info-card">
                                    <strong>{role.name}</strong>
                                    <small>ID: {role.id}</small>
                                    <button
                                        onClick={() => handleDeleteRole(role.id)}
                                        className="delete-button"
                                    >
                                        Sil
                                    </button>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default RoleManagement;