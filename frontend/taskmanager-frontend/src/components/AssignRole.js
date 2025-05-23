// components/AssignRole.js
import React, { useState, useEffect } from "react";
import axios from "axios";
import "./AssignRole.css";

function AssignRole({ roles }) {
    const [userId, setUserId] = useState("");
    const [roleId, setRoleId] = useState("");
    const [users, setUsers] = useState([]);
    const [allRoles, setAllRoles] = useState([]);
    const [lastAssignedUser, setLastAssignedUser] = useState(null);
    const [lastAssignedRole, setLastAssignedRole] = useState(null);
    const [deleteUserId, setDeleteUserId] = useState("");
    const [deleteRoleId, setDeleteRoleId] = useState("");
    const [lastDeletedUser, setLastDeletedUser] = useState(null);
    const [lastDeletedRole, setLastDeletedRole] = useState(null);

    useEffect(() => {
        fetchUsersAndRoles();
    }, []);

    const fetchUsersAndRoles = async () => {
        try {
            const usersRes = await axios.get("http://localhost:8080/users/show");
            const rolesRes = await axios.get("http://localhost:8080/role/show");
            setUsers(usersRes.data);
            setAllRoles(rolesRes.data);
        } catch (err) {
            console.error("Veriler alınırken hata:", err);
        }
    };

    const handleAssign = async () => {
        if (!userId || !roleId) {
            alert("Lütfen kullanıcı ve rol seçin!");
            return;
        }

        try {
            // Bu satır düzeltildi - POST isteği olmalı
            await axios.post(`http://localhost:8080/roleUserRelation/${userId}/assignRole/${roleId}`);
            alert("Rol başarıyla atandı!");

            const assignedUser = users.find((u) => u.id === parseInt(userId));
            const assignedRole = allRoles.find((r) => r.id === roleId);
            setLastAssignedUser(assignedUser);
            setLastAssignedRole(assignedRole);

            fetchUsersAndRoles();
            setUserId("");
            setRoleId("");
        } catch (error) {
            console.error("Rol atanamadı:", error);
            alert("Rol atanamadı.");
        }
    };

    const handleDelete = async () => {
        if (!deleteUserId || !deleteRoleId) {
            alert("Lütfen silmek için kullanıcı ve rol seçin!");
            return;
        }

        try {
            await axios.delete(`http://localhost:8080/roleUserRelation/${deleteUserId}/deleteRole/${deleteRoleId}`);
            alert("Rol başarıyla silindi!");

            const deletedUser = users.find((u) => u.id === parseInt(deleteUserId));
            const deletedRole = allRoles.find((r) => r.id === deleteRoleId);
            setLastDeletedUser(deletedUser);
            setLastDeletedRole(deletedRole);

            fetchUsersAndRoles();
            setDeleteUserId("");
            setDeleteRoleId("");
        } catch (error) {
            console.error("Rol silinemedi:", error);
            alert("Rol silinemedi: " + (error.response?.data || error.message));
        }
    };

    if (!roles.includes("admin")) {
        return <p>🚫 Bu sayfayı görüntüleme yetkiniz yok.</p>;
    }

    return (
        <div className="role-assignment-container">
            <div className="left-panel">
                <div className="action-section">
                    <h3>🎯 Kullanıcıya Rol Ata</h3>
                    <select
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                    >
                        <option value="">Kullanıcı Seçin</option>
                        {users.map((user) => (
                            <option key={user.id} value={user.id}>
                                {user.username}
                            </option>
                        ))}
                    </select>

                    <select
                        value={roleId}
                        onChange={(e) => setRoleId(e.target.value)}
                    >
                        <option value="">Rol Seçin</option>
                        {allRoles.map((role) => (
                            <option key={role.id} value={role.id}>
                                {role.name}
                            </option>
                        ))}
                    </select>

                    <button className="assign-button" onClick={handleAssign}>Rol Ata</button>

                    {lastAssignedUser && lastAssignedRole && (
                        <div className="status-box success">
                            <h4>✅ Son Atanan Rol</h4>
                            <p><strong>Kullanıcı:</strong> {lastAssignedUser.username}</p>
                            <p><strong>Rol:</strong> {lastAssignedRole.name}</p>
                        </div>
                    )}
                </div>

                <div className="action-section">
                    <h3>🗑️ Kullanıcıdan Rol Sil</h3>
                    <select
                        value={deleteUserId}
                        onChange={(e) => setDeleteUserId(e.target.value)}
                    >
                        <option value="">Kullanıcı Seçin</option>
                        {users.map((user) => (
                            <option key={user.id} value={user.id}>
                                {user.username}
                            </option>
                        ))}
                    </select>

                    <select
                        value={deleteRoleId}
                        onChange={(e) => setDeleteRoleId(e.target.value)}
                    >
                        <option value="">Rol Seçin</option>
                        {allRoles.map((role) => (
                            <option key={role.id} value={role.id}>
                                {role.name}
                            </option>
                        ))}
                    </select>

                    <button className="delete-button" onClick={handleDelete}>Rol Sil</button>

                    {lastDeletedUser && lastDeletedRole && (
                        <div className="status-box danger">
                            <h4>❌ Son Silinen Rol</h4>
                            <p><strong>Kullanıcı:</strong> {lastDeletedUser.username}</p>
                            <p><strong>Rol:</strong> {lastDeletedRole.name}</p>
                        </div>
                    )}
                </div>
            </div>

            <div className="right-panel">
                <div className="lists-container">
                    <div className="users-column">
                        <h4>👤 Tüm Kullanıcılar</h4>
                        <div className="users-list">
                            {users.map((user) => (
                                <div key={user.id} className="info-card">
                                    <strong>{user.username}</strong>
                                    <p>{user.email}</p>
                                    <small>ID: {user.id}</small>
                                </div>
                            ))}
                        </div>
                    </div>

                    <div className="roles-column">
                        <h4>🎭 Tüm Roller</h4>
                        <div className="roles-list">
                            {allRoles.map((role) => (
                                <div key={role.id} className="info-card">
                                    <strong>{role.name}</strong>
                                    {role.description && <p>{role.description}</p>}
                                    <small>ID: {role.id}</small>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AssignRole;