import React, { useState, useEffect } from 'react';

const UserList = ({ users,onSelect }) => {
    const handleUserSelect = (user) => {
        onSelect(user);
    };
    return (
        <div>
            <h2>User List</h2>
            <input type="text" placeholder="Search..." id="search"/>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last name</th>
                    <th>Email</th>
                </tr>
                </thead>
                <tbody>
                {users.map(user => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.firstName}</td>
                        <td>{user.lastName}</td>
                        <td>{user.email}</td>
                        <td><button onClick={() => handleUserSelect(user)} >Edit</button></td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserList;
