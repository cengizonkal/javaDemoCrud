import React, { useState, useEffect } from 'react';

const UserList = () => {
    const [users, setUsers] = useState([]);
    const username = 'admin';
    const password = 'admin';

    useEffect(() => {
       const fetchUsers = async() => {
           const requestOptions = {
               mode: 'cors',
               headers: new Headers({
                   'Authorization': 'Basic ' + btoa(username + ":" + password),
               }),
               method: 'GET',
           };
           const search = document.getElementById('search').value;

           fetch('http://localhost:8080/users?search='+search, requestOptions)

               .then(response => response.json())
               .then(data => setUsers(data))
               .catch(error => console.error('Error fetching users:', error));
       }
        fetchUsers();
       //on search
        document.getElementById('search').addEventListener('input',fetchUsers);

    }, []);

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
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserList;
