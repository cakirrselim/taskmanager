import React from "react";
import "./RoleList.css";

function RoleList({ roles, message, error }) {
    return (
        <div className="role-list-container">
            <button className="role-button" disabled>
                Roller
            </button>

            {error ? (
                <p className="role-error">{error}</p>
            ) : (
                <>
                    {roles.length === 0 ? (
                        <p className="role-message">{message || "Henüz rol yok."}</p>
                    ) : (
                        <ul className="role-list">
                            {roles.map((role) => (
                                <li key={role.id} className="role-item">
                                    {role.name}
                                </li>
                            ))}
                        </ul>
                    )}
                </>
            )}
        </div>
    );
}

export default RoleList;
