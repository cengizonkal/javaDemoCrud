import React, { useState } from 'react';

function UserDetail({ user, onUpdate }) {
    const [editedUser, setEditedUser] = useState(user);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditedUser({ ...editedUser, [name]: value });
    };

    const handleUpdate = () => {
        onUpdate(editedUser);
    };

    return (
        <div>
            <h2>Edit</h2>
            {editedUser && (
                <div>
                    <p>
                        <label>
                            Name:
                            <input
                                type="text"
                                name="firstName"
                                value={editedUser.firstName}
                                onChange={handleChange}
                            />
                        </label>
                    </p>
                    <p>
                        <label>
                            Surname:
                            <input
                                type="text"
                                name="lastName"
                                value={editedUser.lastName}
                                onChange={handleChange}
                            />
                        </label>
                    </p>
                    <p>
                        <label>
                            Email:
                            <input
                                type="text"
                                name="email"
                                value={editedUser.email}
                                onChange={handleChange}
                            />
                        </label>
                    </p>
                    <button onClick={handleUpdate}>Update</button>
                </div>
            )}
        </div>
    );
}

export default UserDetail;
