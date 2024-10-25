import { useEffect, useState } from 'react';
import { handleAdd,handlePut } from '../api';

export default function Form({setOpenAdd,users,currentUser,isEditing,setUsers}) {
    const [formData, setFormData] = useState({
        name: '',
        surname: '',
        department: '',
        salary: ''
    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    useEffect(()=>{
        if(currentUser && isEditing){
            setFormData({
                id : currentUser.id,
                name: currentUser.name,
                surname: currentUser.surname,
                department: currentUser.department,
                salary: currentUser.salary
            });
        }

    }, [currentUser,isEditing])

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(JSON.stringify(formData))
        isEditing? handlePut(users,formData,setUsers) : handleAdd(users,formData,setUsers);
        setOpenAdd(false)
        setFormData({
            name: '',
            surname: '',
            department: '',
            salary: ''
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-3">
                <label htmlFor="exampleInputName" className="form-label">Имя</label>
                <input
                    type="text"
                    className="form-control"
                    id="exampleInputName"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                />
            </div>
            <div className="mb-3">
                <label htmlFor="exampleInputSurName" className="form-label">Фамилия</label>
                <input
                    type="text"
                    className="form-control"
                    id="exampleInputSurName"
                    name="surname"
                    value={formData.surname}
                    onChange={handleChange}
                />
            </div>
            <div className="mb-3">
                <label htmlFor="exampleInputDepartment" className="form-label">Сфера</label>
                <input
                    type="text"
                    className="form-control"
                    id="exampleDepartment"
                    name="department"
                    value={formData.department}
                    onChange={handleChange}
                />
            </div>
            <div className="mb-3">
                <label htmlFor="exampleInputNumber" className="form-label">Зарплата</label>
                <input
                    type="number"
                    className="form-control"
                    id="exampleInputNumber"
                    name="salary"
                    value={formData.salary}
                    onChange={handleChange}
                />
            </div>
            <button
                className="btn btn-danger"
                onClick={(event) => {
                    event.preventDefault();
                    setOpenAdd(false);
                }}
            >
                Отмена
            </button>
            {!isEditing  &&<button className="btn btn-success mx-2" type="submit">Подтвердить </button>}
            {isEditing  &&<button className="btn btn-primary mx-2" type="submit">Редактировать</button>}
        </form>
    );
}
