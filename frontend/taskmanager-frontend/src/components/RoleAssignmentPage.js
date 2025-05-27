import React, { useEffect, useState } from "react";
import axios from "axios";
import "./RoleAssignmentPage.css";

const RoleAssignmentPage = () => {
    const [users, setUsers] = useState([]);
    const [roles, setRoles] = useState([]);
    const [selectedUserId, setSelectedUserId] = useState("");
    const [selectedRoleId, setSelectedRoleId] = useState("");
    const [lastAssignedUser, setLastAssignedUser] = useState(null); // ✅ EKLENDİ
    const [lastAssignedRole, setLastAssignedRole] = useState(null); // ✅ EKLENDİ

    useEffect(() => {
        const role = localStorage.getItem("role");
        if (role !== "admin") {
            alert("Bu sayfaya sadece admin erişebilir.");
            window.location.href = "/";
        }

        fetchUsersAndRoles();
    }, []);

    const fetchUsersAndRoles = async () => {
        try {
            const usersRes = await axios.get("http://localhost:8080/users/show");
            const rolesRes = await axios.get("http://localhost:8080/role/show");

            setUsers(usersRes.data);
            setRoles(rolesRes.data);
        } catch (err) {
            console.error("Veriler alınırken hata:", err);
        }
    };

    const assignRole = async () => {
        if (!selectedUserId || !selectedRoleId) {
            alert("Kullanıcı ve rol seçin.");
            return;
        }

        try {
            await axios.post(`http://localhost:8080/roleUserRelation/${selectedUserId}/assignRole/${selectedRoleId}`);
            alert("Rol atama başarılı!");

            // ✅ Son atama bilgilerini al
            const assignedUser = users.find((u) => u.id === parseInt(selectedUserId));
            const assignedRole = roles.find((r) => r.id === selectedRoleId);

            setLastAssignedUser(assignedUser);
            setLastAssignedRole(assignedRole);

            fetchUsersAndRoles(); // verileri yenile
        } catch (err) {
            alert("Rol atama sırasında hata oluştu.");
        }
    };

    return (
        <div className="role-assignment-container">
            {/* Sol Kısım */}
            <div className="left-panel">
                <h3>👥 Kullanıcıya Rol Ata</h3>
                <select value={selectedUserId} onChange={(e) => setSelectedUserId(e.target.value)}>
                    <option value="">Kullanıcı Seç</option>
                    {users.map((user) => (
                        <option key={user.id} value={user.id}>
                            {user.username}
                        </option>
                    ))}
                </select>

                <select value={selectedRoleId} onChange={(e) => setSelectedRoleId(e.target.value)}>
                    <option value="">Rol Seç</option>
                    {roles.map((role) => (
                        <option key={role.id} value={role.id}>
                            {role.name}
                        </option>
                    ))}
                </select>

                <button onClick={assignRole}>Rol Ata</button>

                {/* ✅ SON ATAMA BİLGİSİ */}
                {lastAssignedUser && lastAssignedRole && (
                    <div className="last-assignment-box">
                        <h4>✅ Son Atama</h4>
                        <p><strong>Kullanıcı:</strong> {lastAssignedUser.username}</p>
                        <p><strong>Rol:</strong> {lastAssignedRole.name}</p>
                    </div>
                )}
            </div>

            {/* Sağ Kısım */}
            <div className="right-panel">
                <h4>👤 Tüm Kullanıcılar</h4>
                <ul>
                    {users.map((user) => (
                        <li key={user.id}>
                            <strong>{user.username}:</strong>{" "}
                            {user.roles && user.roles.length > 0
                                ? user.roles.map((r) => r.name).join(", ")
                                : "Atanmış rol yok"}
                        </li>
                    ))}
                </ul>

                <h4>🎭 Tüm Roller</h4>
                <ul>
                    {roles.map((role) => (
                        <li key={role.id}>{role.name}</li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default RoleAssignmentPage;
