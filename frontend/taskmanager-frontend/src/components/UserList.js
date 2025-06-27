import React, { useEffect, useState } from "react";
import "./UserList.css"; // Stil istersen oluşturabilirsin

function UserList() {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState(null);
    const [message, setMessage] = useState("Kullanıcılar yükleniyor...");

    useEffect(() => {
        fetch("http://localhost:8080/users/show")
            .then((res) => {
                if (!res.ok) throw new Error("Kullanıcılar alınamadı.");
                return res.json();
            })
            .then((data) => {
                setUsers(data);
                if (data.length === 0) {
                    setMessage("Henüz kullanıcı yok.");
                }
            })
            .catch((err) => {
                setError(err.message);
            });
    }, []);

    return (
        <div className="userlist-container">
            <button className="userlist-button" disabled>
                Kullanıcılar
            </button>

            {error ? (
                <p className="userlist-error">{error}</p>
            ) : users.length === 0 ? (
                <p className="userlist-message">{message}</p>
            ) : (
                <ul className="userlist-ul">
                    {users.map((user) => (
                        <li key={user.id} className="userlist-li">
                            <strong>{user.name} {user.surname}</strong> - {user.username}<br />
                            <em>Email:</em> {user.email}<br />
                            <em>Roller:</em>{" "}
                            {user.roles.map((role) => role.name).join(", ") || "Yok"}<br />
                            <em>Görevler:</em>{" "}
                            {user.tasks.map((task) => task.name).join(", ") || "Yok"}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default UserList;
