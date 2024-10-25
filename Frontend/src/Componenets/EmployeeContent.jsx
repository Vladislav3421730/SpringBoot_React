import { useState, useEffect } from "react";
import Button from "./Button/Button";
import Select from "./Select";
import Modal from "./Modal/Modal";
import Form from "./Form";
import { fetchUsers, handleDelete } from "../api";

export default function EmployeeContent() {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);
    const [value, setValue] = useState('');
    const [openAdd, setOpenAdd] = useState(false);
    const [currentUser, setCurrentUser] = useState(null);
    const [isEditing, setIsEditing] = useState(false);

    useEffect(() => {
        fetchUsers(setUsers, setLoading);
    }, []);

    const handleEdit = (user) => {
        setCurrentUser(user);
        setIsEditing(true);
        setOpenAdd(true);
    };

    return (
        <div className="container mt-4">
            <Modal open={openAdd}>
                <Form setOpenAdd={setOpenAdd} users={users} setUsers={setUsers} currentUser={currentUser} isEditing={isEditing} />
            </Modal>
            <div className="d-flex mb-4">
                <input type="text" className="form-control" style={{ marginRight: "20px" }} onChange={(event) => { setValue(event.target.value) }}
                    aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></input>
                <Select users={users} setUsers={setUsers} />
            </div>
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Сфера</th>
                        <th>Зарплата</th>
                        <th>Удалить</th>
                        <th>Редактировать</th>
                    </tr>
                </thead>
                <tbody>
                    {!loading && users.length > 0 ? (
                        users
                            .filter(user => user.name.toLowerCase().includes(value.toLowerCase())
                                || user.surname.toLowerCase().includes(value.toLowerCase()))
                            .map(user => (
                                <tr key={user.id}>
                                    <td>{user.id}</td>
                                    <td>{user.name}</td>
                                    <td>{user.surname}</td>
                                    <td>{user.department}</td>
                                    <td>{user.salary}</td>
                                    <td><button onClick={() => handleDelete(user.id, users, setUsers)} className="btn btn-danger">Удалить</button></td>
                                    <td><button onClick={() => handleEdit(user)} className="btn btn-success">Редактировать</button></td>
                                </tr>
                            ))
                    ) : (
                        <tr>
                            <td colSpan="7">Loading...</td>
                        </tr>
                    )}
                </tbody>
            </table>
            <button className="btn btn-primary mb-2" onClick={() => {
                setCurrentUser(null);
                setIsEditing(false);
                setOpenAdd(true);
            }}>Добавить</button>
        </div>
    );
}
