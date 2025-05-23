// TaskAssign.js
import React, { useState, useEffect } from "react";
import axios from "axios";
import "./TaskAssign.css";

function TaskAssign({ roles }) {
    const [users, setUsers] = useState([]);
    const [allTasks, setAllTasks] = useState([]);
    const [newTask, setNewTask] = useState({
        name: ""
    });

    const BASE_URL = "http://localhost:8080/task"; // Base URL'i değişkene atadık

    useEffect(() => {
        fetchTasks();
        fetchUsers();
    }, []);

    const fetchTasks = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/show`);
            console.log("Mevcut görevler:", response.data);
            setAllTasks(response.data);
        } catch (error) {
            console.error("Görevler getirilirken hata oluştu:", error);
        }
    };

    const fetchUsers = async () => {
        try {
            const response = await axios.get("http://localhost:8080/users/show");
            setUsers(response.data);
        } catch (error) {
            console.error("Kullanıcılar getirilirken hata oluştu:", error);
        }
    };

    const handleCreateTask = async (e) => {
        e.preventDefault();
        try {
            const taskData = {
                id: 0,
                name: newTask.name
            };

            console.log("Gönderilecek veri:", taskData);

            const response = await axios.post(
                `${BASE_URL}/add`,
                taskData,
                {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
            );

            console.log("Backend'den gelen cevap:", response);
            alert("Görev başarıyla oluşturuldu!");
            setNewTask({ name: "" });
            fetchTasks();
        } catch (error) {
            console.error("Görev oluşturulurken hata detayı:", {
                message: error.message,
                response: error.response?.data,
                status: error.response?.status
            });
            alert("Görev oluşturulamadı: " + (error.response?.data || error.message));
        }
    };

    const handleDeleteTask = async (taskId) => {
        if (window.confirm("Bu görevi silmek istediğinizden emin misiniz?")) {
            try {
                await axios.delete(`${BASE_URL}/delete/${taskId}`);
                alert("Görev başarıyla silindi!");
                fetchTasks();
            } catch (error) {
                console.error("Görev silinirken hata:", error);
                alert("Görev silinemedi: " + (error.response?.data || error.message));
            }
        }
    };

    if (!roles.includes("admin")) {
        return <p className="no-access">Bu sayfayı görüntüleme yetkiniz yok.</p>;
    }

    return (
        <div className="task-assignment-container">
            <div className="left-panel">
                <div className="action-section">
                    <h3>📝 Yeni Görev Oluştur</h3>
                    <form onSubmit={handleCreateTask}>
                        <input
                            type="text"
                            placeholder="Görev Adı"
                            value={newTask.name}
                            onChange={(e) => setNewTask({...newTask, name: e.target.value})}
                            required
                        />
                        <button type="submit" className="create-button">Görev Oluştur</button>
                    </form>
                </div>
            </div>

            <div className="right-panel">
                <h3>📋 Mevcut Görevler</h3>
                <div className="tasks-grid">
                    {allTasks.map((task) => (
                        <div key={task.id} className="task-card">
                            <h4>{task.name}</h4>
                            <button
                                onClick={() => handleDeleteTask(task.id)}
                                className="delete-button"
                            >
                                Görevi Sil
                            </button>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default TaskAssign;