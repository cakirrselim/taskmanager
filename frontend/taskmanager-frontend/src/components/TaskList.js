import React, { useEffect, useState } from "react";
import "./TaskList.css";

function TaskList({ userId }) {
    const [tasks, setTasks] = useState([]);
    const [message, setMessage] = useState("Görevler yükleniyor...");
    const [error, setError] = useState(null);

    useEffect(() => {

        console.log("userId:", userId);
        if (userId) {
            fetch(`http://localhost:8080/taskUserRelation/getTasksByUserId/${userId}`)
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("Sunucu hatası");
                    }
                    return response.json();
                })
                .then((data) => {
                    setTasks(data);
                    if (data.length === 0) {
                        setMessage("Henüz görev yok.");
                    }
                })
                .catch((err) => {
                    setError("Görevler alınamadı.");
                    console.error("Görev çekme hatası:", err);
                });
        }
    }, [userId]);

    return (
        <div className="tasklist-container">
            <button className="tasklist-button" disabled>
                Görevler
            </button>

            {error ? (
                <p className="tasklist-error">{error}</p>
            ) : tasks.length === 0 ? (
                <p className="tasklist-message">{message}</p>
            ) : (
                <ul className="tasklist-ul">
                    {tasks.map((task) => (
                        <li key={task.id} className="tasklist-li">
                            {task.name}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default TaskList;
