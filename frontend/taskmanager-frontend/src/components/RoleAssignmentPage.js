import React, { useEffect, useState } from "react";
import axios from "axios";
import "./RoleAssignmentPage.css";

const RoleAssignmentPage = () => {
    const [users, setUsers] = useState([]);
    const [roles, setRoles] = useState([]);

    const [selectedUserIdAssign, setSelectedUserIdAssign] = useState("");
    const [selectedRoleIdAssign, setSelectedRoleIdAssign] = useState("");

    const [selectedUserIdRemove, setSelectedUserIdRemove] = useState("");
    const [selectedRoleIdRemove, setSelectedRoleIdRemove] = useState("");

    const [userRoles, setUserRoles] = useState({});

    useEffect(() => {
        fetchUsersAndRoles();
    }, []);

    const fetchUsersAndRoles = async () => {
        try {
            const usersRes = await axios.get("http://localhost:8080/users/show");
            const rolesRes = await axios.get("http://localhost:8080/role/show");

            setUsers(usersRes.data);
            setRoles(rolesRes.data);

            // Kullanıcıların rollerini map'e koy
            const rolesMap = {};
            usersRes.data.forEach(user => {
                rolesMap[user.id] = user.roles || [];
            });
            setUserRoles(rolesMap);
        } catch (err) {
            console.error("Veriler alınırken hata:", err);
        }
    };

    const assignRole = async () => {
        if (!selectedUserIdAssign || !selectedRoleIdAssign) {
            alert("Kullanıcı ve rol seçin.");
            return;
        }

        try {
            await axios.post(
                `http://localhost:8080/roleUserRelation/${selectedUserIdAssign}/assignRole/${selectedRoleIdAssign}`
            );
            alert("Rol atama başarılı!");
            setSelectedUserIdAssign("");
            setSelectedRoleIdAssign("");
            fetchUsersAndRoles();
        } catch (err) {
            alert("Rol atama sırasında hata oluştu.");
            console.error(err);
        }
    };

    const removeRole = async () => {
        if (!selectedUserIdRemove || !selectedRoleIdRemove) {
            alert("Kullanıcı ve kaldırılacak rolü seçin.");
            return;
        }

        try {
            await axios.delete(
                `http://localhost:8080/roleUserRelation/${selectedUserIdRemove}/deleteRole/${selectedRoleIdRemove}`
            );
            alert("Rol kaldırma başarılı!");
            setSelectedUserIdRemove("");
            setSelectedRoleIdRemove("");
            fetchUsersAndRoles();
        } catch (err) {
            alert("Rol kaldırma sırasında hata oluştu.");
            console.error(err);
        }
    };


    const rolesOfSelectedUser = selectedUserIdRemove ? userRoles[selectedUserIdRemove] || [] : [];

    return (
        <div className="role-assignment-container">
            <div className="left-panel">
                {/* Rol Atama */}
                <div className="section">
                    <h3>👥 Kullanıcıya Rol Ata</h3>
                    <select
                        value={selectedUserIdAssign}
                        onChange={(e) => setSelectedUserIdAssign(e.target.value)}
                    >
                        <option value="">Kullanıcı Seç</option>
                        {users.map((user) => (
                            <option key={user.id} value={user.id}>
                                {user.username}
                            </option>
                        ))}
                    </select>

                    <select
                        value={selectedRoleIdAssign}
                        onChange={(e) => setSelectedRoleIdAssign(e.target.value)}
                    >
                        <option value="">Rol Seç</option>
                        {roles.map((role) => (
                            <option key={role.id} value={role.id}>
                                {role.name}
                            </option>
                        ))}
                    </select>

                    <button onClick={assignRole}>Rol Ata</button>
                </div>

                {/* Rol Kaldırma */}
                <div className="section" style={{ marginTop: "40px" }}>
                    <h3>❌ Kullanıcıdan Rol Kaldır</h3>
                    <select
                        value={selectedUserIdRemove}
                        onChange={(e) => {
                            setSelectedUserIdRemove(e.target.value);
                            setSelectedRoleIdRemove("");
                        }}
                    >
                        <option value="">Kullanıcı Seç</option>
                        {users.map((user) => (
                            <option key={user.id} value={user.id}>
                                {user.username}
                            </option>
                        ))}
                    </select>

                    <select
                        value={selectedRoleIdRemove}
                        onChange={(e) => setSelectedRoleIdRemove(e.target.value)}
                        disabled={!selectedUserIdRemove || rolesOfSelectedUser.length === 0}
                    >
                        <option value="">Kaldırılacak Rolü Seç</option>
                        {rolesOfSelectedUser.length === 0 && selectedUserIdRemove && (
                            <option disabled>Bu kullanıcıya atanmış rol yok</option>
                        )}
                        {rolesOfSelectedUser.map((role) => (
                            <option key={role.id} value={role.id}>
                                {role.name}
                            </option>
                        ))}
                    </select>

                    <button onClick={removeRole} disabled={!selectedRoleIdRemove}>
                        Rol Kaldır
                    </button>
                </div>
            </div>

            {/* Sağ panel - kullanıcılar ve roller */}
            <div className="right-panel">
                <h3>👤 Tüm Kullanıcılar ve Rolleri</h3>
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
            </div>
        </div>
    );
};

export default RoleAssignmentPage;
