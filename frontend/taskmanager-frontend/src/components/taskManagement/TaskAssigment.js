
import React, { useState, useEffect } from "react";
import axios from "axios";
import "./TaskAssigment.css";

function TaskAssignment({ roles }) {
    const [users, setUsers] = useState([]);
    const [tasks, setTasks] = useState([]);
    const [selectedUser, setSelectedUser] = useState("");
    const [selectedTask, setSelectedTask] = useState("");
    const [lastAssignment, setLastAssignment] = useState(null);
    const [lastRemoved, setLastRemoved] = useState(null);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const [usersRes, tasksRes] = await Promise.all([
                axios.get("http://localhost:8080/users/show"),
                axios.get("http://localhost:8080/task/show")
            ]);
            setUsers(usersRes.data);
            setTasks(tasksRes.data);
        } catch (err) {
            console.error("Veri yüklenirken hata:", err);
        }
    };

    const handleAssign = async () => {
        if (!selectedUser || !selectedTask) {
            alert("Lütfen kullanıcı ve görev seçin!");
            return;
        }

        try {
            await axios.post(`http://localhost:8080/taskUserRelation/${selectedUser}/assignTask/${selectedTask}`);

            const user = users.find(u => u.id === parseInt(selectedUser));
            const task = tasks.find(t => t.id === parseInt(selectedTask));

            setLastAssignment({ user, task });
            alert("Görev başarıyla atandı!");

            fetchData();
            setSelectedUser("");
            setSelectedTask("");
        } catch (error) {
            console.error("Görev atanamadı:", error);

            if (error.response) {
                console.error("Status:", error.response.status);
                console.error("Data:", error.response.data);
                alert("Görev atanamadı:\n" + JSON.stringify(error.response.data, null, 2));
            } else if (error.request) {
                console.error("Request yapıldı ama cevap alınamadı.");
                alert("Sunucudan yanıt alınamadı.");
            } else {
                console.error("Hata:", error.message);
                alert("İstek yapılamadı: " + error.message);
            }
        }

    };

    const handleRemove = async () => {
        if (!selectedUser || !selectedTask) {
            alert("Lütfen kullanıcı ve görev seçin!");
            return;
        }

        try {
            await axios.delete(`http://localhost:8080/taskUserRelation/${selectedUser}/deleteTask/${selectedTask}`);

            const user = users.find(u => u.id === parseInt(selectedUser));
            const task = tasks.find(t => t.id === parseInt(selectedTask));

            setLastRemoved({ user, task });
            alert("Görev başarıyla kaldırıldı!");

            fetchData();
            setSelectedUser("");
            setSelectedTask("");
        } catch (error) {
            console.error("Görev kaldırılamadı:", error);
            alert("Görev kaldırılamadı: " + (error.response?.data || error.message));
        }
    };

    if (!roles?.includes("admin")) {
        return <p className="no-access">Bu sayfayı görüntüleme yetkiniz yok.</p>;
    }

    return (
        <div className="task-assignment-container">
            <div className="left-panel">
                <div className="action-section">
                    <h3>📋 Görev Ata</h3>
                    <select
                        value={selectedUser}
                        onChange={(e) => setSelectedUser(e.target.value)}
                    >
                        <option value="">Kullanıcı Seçin</option>
                        {users.map((user) => (
                            <option key={user.id} value={user.id}>
                                {user.username}
                            </option>
                        ))}
                    </select>

                    <select
                        value={selectedTask}
                        onChange={(e) => setSelectedTask(e.target.value)}
                    >
                        <option value="">Görev Seçin</option>
                        {tasks.map((task) => (
                            <option key={task.id} value={task.id}>
                                {task.name}
                            </option>
                        ))}
                    </select>

                    <div className="button-group">
                        <button className="assign-button" onClick={handleAssign}>Görev Ata</button>
                        <button className="remove-button" onClick={handleRemove}>Görevi Kaldır</button>
                    </div>

                    {lastAssignment && (
                        <div className="status-box success">
                            <h4>✅ Son Atanan Görev</h4>
                            <p><strong>Kullanıcı:</strong> {lastAssignment.user.username}</p>
                            <p><strong>Görev:</strong> {lastAssignment.task.name}</p>
                        </div>
                    )}

                    {lastRemoved && (
                        <div className="status-box warning">
                            <h4>❌ Son Kaldırılan Görev</h4>
                            <p><strong>Kullanıcı:</strong> {lastRemoved.user.username}</p>
                            <p><strong>Görev:</strong> {lastRemoved.task.name}</p>
                        </div>
                    )}
                </div>
            </div>

            <div className="right-panel">
                <div className="lists-container">
                    <div className="users-column">
                        <h4>👤 Kullanıcılar ve Görevleri</h4>
                        <div className="users-list">
                            {users.map((user) => (
                                <div key={user.id} className="info-card">
                                    <strong>{user.username}</strong>
                                    <p>Görevler:</p>
                                    <ul>
                                        {user.tasks?.length > 0 ?
                                            user.tasks.map((task, index) => (
                                                <li key={index}>{task.name}</li>
                                            )) :
                                            <li>Atanmış görev yok</li>
                                        }
                                    </ul>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default TaskAssignment;