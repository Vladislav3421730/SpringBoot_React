export default function Select({ users, setUsers }) {

    function sortUsers(value) {
        const sortedUsers = [...users];
        if (value === "name") {
            sortedUsers.sort((a, b) => a.name.localeCompare(b.name));
        } else if (value === "surname") {
            sortedUsers.sort((a, b) => a.surname.localeCompare(b.surname));
        } else if (value === "salary") {
            sortedUsers.sort((a, b) => a.salary - b.salary);
        }
        setUsers(sortedUsers);
    }

    return (
        <select className="form-select w-25" onChange={(event) => sortUsers(event.target.value)}>
            <option selected>Сортировка</option>
            <option value="name">Имя</option>
            <option value="surname">Фамилия</option>
            <option value="salary">Зарплата</option>
        </select>
    );
}
