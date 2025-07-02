import React, { useState, useEffect } from "react";
import axios from "axios";
import "./RoleAdd.css";

const BASE_URL = "http://localhost:8080/role";

function getErrorMessage(error) {
    if (!error) return "Bilinmeyen hata oluştu.";
    if (typeof error === "string") return error;
    if (typeof error === "object") {
        if (error.error) return error.error;
        if (error.message) return error.message;
        if (error.status && error.error)
            return `${error.status} - ${error.error}`;
        return JSON.stringify(error);
    }
    return "Bilinmeyen hata oluştu.";
}

function RoleAdd({ roles }) {
    const [allRoles, setAllRoles] = useState([]);
    const [newRoleName, setNewRoleName] = useState("");
    const [newRoleDesc, setNewRoleDesc] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState(null);

    const fetchRoles = async () => {
        try {
            const res = await axios.get(`${BASE_URL}/show`);
            setAllRoles(res.data);
        } catch (error) {
            setMessage({ type: "error", text: "Roller listelenemedi: " + getErrorMessage(error.response?.data || error.message) });
        }
    };

    useEffect(() => {
        fetchRoles();
    }, []);

    const handleAddRole = async (e) => {
        e.preventDefault();
        if (!newRoleName.trim()) {
            setMessage({ type: "error", text: "Rol adı boş olamaz!" });
            return;
        }
        if (!newRoleDesc.trim()) {
            setMessage({ type: "error", text: "Açıklama boş olamaz!" });
            return;
        }
        setLoading(true);
        try {
            await axios.post(`${BASE_URL}/add`, {
                name: newRoleName,
                description: newRoleDesc
            });
            setMessage({ type: "success", text: "Rol başarıyla eklendi!" });
            setNewRoleName("");
            setNewRoleDesc("");
            fetchRoles();
        } catch (error) {
            setMessage({
                type: "error",
                text: getErrorMessage(error.response?.data || error.message)
            });
        } finally {
            setLoading(false);
        }
    };

    const handleDeleteRole = async (roleId) => {
        if (!window.confirm("Bu rolü silmek istediğinize emin misiniz?")) return;
        setLoading(true);
        try {
            await axios.delete(`${BASE_URL}/deleteRole/${roleId}`);
            setMessage({ type: "success", text: "Rol başarıyla silindi!" });
            fetchRoles();
        } catch (error) {
            setMessage({
                type: "error",
                text: getErrorMessage(error.response?.data || error.message)
            });
        } finally {
            setLoading(false);
        }
    };

    if (!roles || !roles.includes("admin")) {
        return <div className="roleadd-no-access">Bu alanı görüntüleme yetkiniz yok.</div>;
    }

    return (
        <div className="roleadd-container">
            <div className="roleadd-section">
                <h3>🎭 Yeni Rol Ekle</h3>
                <form onSubmit={handleAddRole} className="roleadd-form" style={{ flexDirection: "column" }}>
                    <input
                        type="text"
                        placeholder="Rol Adı"
                        value={newRoleName}
                        onChange={(e) => { setNewRoleName(e.target.value); setMessage(null); }}
                        disabled={loading}
                        required
                    />
                    <input
                        type="text"
                        placeholder="Açıklama"
                        value={newRoleDesc}
                        onChange={(e) => { setNewRoleDesc(e.target.value); setMessage(null); }}
                        disabled={loading}
                        required
                        style={{ marginTop: "0.7rem" }}
                    />
                    <button
                        type="submit"
                        disabled={loading}
                        style={{ marginTop: "0.7rem" }}
                    >
                        {loading ? "Ekleniyor..." : "Ekle"}
                    </button>
                </form>
                {message && (
                    <p className={message.type === "error" ? "roleadd-error" : "roleadd-success"}>
                        {message.text}
                    </p>
                )}
            </div>
            <div className="roleadd-list-section">
                <h4>Mevcut Roller</h4>
                <div className="roleadd-list">
                    {allRoles.length === 0 && <span>Henüz rol yok.</span>}
                    {allRoles.map(role => (
                        <div key={role.id} className="roleadd-role-card">
                            <span>
                                <strong>{role.name}</strong>
                                {role.description && (
                                    <span className="roleadd-role-desc"> — {role.description}</span>
                                )}
                                <small> (ID: {role.id})</small>
                            </span>
                            <button
                                className="roleadd-delete-btn"
                                onClick={() => handleDeleteRole(role.id)}
                                disabled={loading}
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

export default RoleAdd;