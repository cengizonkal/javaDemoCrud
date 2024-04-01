import logo from './logo.svg';
import './App.css';
import UserTable from "./components/UserTable";
import UserDetail from "./components/UserDetail";
import {useState, useEffect} from "react";

function App() {
    const [selectedUser, setSelectedUser] = useState(null);
    const [users, setUsers] = useState([]);
    const username = 'admin';
    const password = 'admin';

    const handleUserSelect = (user) => {
        setSelectedUser(user);
    };

    const updateUser = (user) => {
        const requestOptions = {
            mode: 'cors',
            headers: new Headers({
                'Authorization': 'Basic ' + btoa(username + ":" + password),
                'Content-Type': 'application/json',

            }),
            method: 'POST',
            body: JSON.stringify(user)
        };
        fetch('http://localhost:8080/users/'+user.id, requestOptions)
            .then(response => response.json())
            .then(data => {
                const updatedUsers = users.map(u => u.id === user.id ? data : u);
                setUsers(updatedUsers);
                setSelectedUser(data);
            })
            .catch(error => console.error('Error updating user:', error));
    }

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
    <div className="App">
      <h1>User List</h1>
        <UserTable users={users} onSelect={handleUserSelect} />
        {selectedUser && <UserDetail user={selectedUser}  onUpdate={updateUser}/>}
    </div>

);
}

export default App;
