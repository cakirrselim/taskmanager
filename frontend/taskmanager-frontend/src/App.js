import React, { useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from "react-router-dom";
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
import Register from "./components/Register";

function Roles({ userId }) {
    const [roles, setRoles] = React.useState([]);
    const [error, setError] = React.useState(null);
    const [message, setMessage] = React.useState("Roller yükleniyor...");

    React.useEffect(() => {
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

// --- Bu bileşen login değilse hangi URL'de olursa olsun login'e zorlar ---
// register ekranı da istisna olarak açıldı
function ForceLogin({ children, username }) {
    const location = useLocation();

    useEffect(() => {
        if (
            !username &&
            location.pathname !== "/login" &&
            location.pathname !== "/register"
        ) {
            window.location.replace("/login");
        }
    }, [username, location.pathname]);

    if (
        !username &&
        location.pathname !== "/login" &&
        location.pathname !== "/register"
    ) {
        return null;
    }
    return children;
}

function App() {
    const [username, setUsername] = React.useState(null);
    const [userId, setUserId] = React.useState(null);
    const [roles, setRoles] = React.useState([]);

    return (
        <Router>
            <ForceLogin username={username}>
                <Navbar username={username} setUsername={setUsername} roles={roles} />
                <div style={{ padding: "1rem" }}>
                    <Routes>
                        {!username ? (
                            <>
                                <Route path="/login" element={
                                    <Login
                                        setUsername={setUsername}
                                        setUserId={setUserId}
                                        setRoles={setRoles}
                                    />
                                } />
                                <Route path="/register" element={<Register />} />
                                <Route path="*" element={<Navigate to="/login" replace />} />
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
                                <Route path="*" element={<Navigate to="/" replace />} />
                            </>
                        )}
                    </Routes>
                </div>
            </ForceLogin>
        </Router>
    );
}

export default App;