import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import RoleList from "./components/RoleList";
import TaskList from "./components/TaskList";
import Navbar from "./components/Navbar";
import Login from "./components/Login";
import "./components/Navbar.css";
import UserList from "./components/UserList";
import RoleAssignmentPage from "./components/RoleAssignmentPage";
import TaskAssignment from "./components/taskManagement/TaskAssigment";
import TaskAdd from "./components/taskManagement/TaskAdd";
import AddUser from "./components/userManagement/AddUser";
import RoleAdd from "./components/roleManagement/RoleAdd";


function Roles({ userId }) {
    const [roles, setRoles] = useState([]);
    const [error, setError] = useState(null);
    const [message, setMessage] = useState("Roller yükleniyor...");

    useEffect(() => {
        if (userId) {
            fetch(`http://localhost:8080/role/${userId}/roles`)
                .then((res) => {
                    if (!res.ok) throw new Error("Roller alınamadı.");
                    return res.json();
                })
                .then((data) => {
                    if (data.length === 0) {
                        setMessage("Bu kullanıcıya atanmış bir rol yok.");
                    }
                    setRoles(data);
                })
                .catch((err) => {
                    setError(err.message);
                });
        }
    }, [userId]);

    return <RoleList roles={roles} message={message} error={error} />;
}

function Tasks() {
    return <h2>Görevler Sayfası</h2>;
}
function Users() {
    return <h2>Kullanıcılar Sayfası</h2>;
}
function AssignRole() {
    return <h2>Rol Ata/Kaldır Sayfası</h2>;
}
function RoleManagement() {
    return <h2>Rol Ekle/Sil Sayfası</h2>;
}

function App() {
    const [username, setUsername] = useState(null);
    const [userId, setUserId] = useState(null);
    const [roles, setRoles] = useState([]);

    return (
        <Router>
            <Navbar username={username} setUsername={setUsername} roles={roles} />
            <div style={{ padding: "1rem" }}>
                <Routes>
                    {!username ? (
                        <>
                            <Route
                                path="/login"
                                element={
                                    <Login
                                        setUsername={setUsername}
                                        setUserId={setUserId}
                                        setRoles={setRoles}
                                    />
                                }
                            />
                            <Route
                                path="/"
                                element={<Navigate to="/login" replace />}
                            />
                        </>
                    ) : (
                        <>
                            <Route path="/roles-add" element={<RoleAdd roles={roles} />} />
                            <Route path="/task/add" element={<TaskAdd />} />
                            <Route path="/role-assignment" element={<RoleAssignmentPage />} />
                            <Route path="/roles" element={<Roles userId={userId} />} />
                            <Route path="/tasks" element={<TaskList userId={userId} />} />
                            <Route path="/users" element={<UserList />} />
                            <Route path="/assign-role" element={<AssignRole />} />
                            <Route path="/task-assignment" element={<TaskAssignment roles={roles} />} />
                            <Route path="/user-management" element={<AddUser roles={roles} />} />
                            <Route path="/role-management" element={<RoleAdd roles={roles} />} />
                            <Route path="/" element={<h1>Hoşgeldiniz, {username}</h1>} />
                        </>
                    )}
                </Routes>
            </div>
        </Router>
    );
}

export default App;