import React, { useState, useEffect } from "react";
import "./TaskAdd.css";

const TaskAdd = () => {
    const [title, setTitle] = useState("");
    const [tasks, setTasks] = useState([]);

    // Tüm görevleri çek
    const fetchTasks = async () => {
        const response = await fetch("http://localhost:8080/task/show");
        const data = await response.json();
        console.log("Gelen görevler", data); // Hata tespiti için
        if (response.ok) {
            setTasks(data);
        } else {
            alert("Görevler getirilemedi!");
        }
    };

    useEffect(() => {
        fetchTasks();
    }, []);

    // Görev Ekle
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/task/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name: title }),
            });
            if (response.ok) {
                setTitle("");
                fetchTasks();
            } else {
                const errorText = await response.text();
                alert("Görev oluşturulamadı!\nDetay: " + errorText);
            }
        } catch (err) {
            alert("Sunucuya bağlanılamadı!");
        }
    };

    // Görev Sil
    const handleDelete = async (taskId) => {
        if (!window.confirm("Bu görevi silmek istediğinize emin misiniz?")) return;
        const response = await fetch(`http://localhost:8080/task/delete/${taskId}`, {
            method: "DELETE",
        });
        if (response.ok) {
            setTasks(tasks.filter((task) => task.id !== taskId));
        } else {
            alert("Görev silinemedi!");
        }
    };

    return (
        <div className="task-add-container">
            <form className="task-add-form" onSubmit={handleSubmit}>
                <div>
                    <label>Başlık:</label>
                    <input
                        type="text"
                        value={title}
                        onChange={e => setTitle(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Görev Ekle/Sil</button>
            </form>
            <div className="task-list-panel">
                <h3>Mevcut Görevler</h3>
                {tasks.length === 0 && <p>Henüz görev yok.</p>}
                <ul className="tasks-ul">
                    {tasks.map(task => (
                        <li key={task.id}>
                            <div>
                                <strong>{task.name}</strong>
                            </div>
                            <button className="delete-btn" onClick={() => handleDelete(task.id)}>
                                Sil
                            </button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default TaskAdd;