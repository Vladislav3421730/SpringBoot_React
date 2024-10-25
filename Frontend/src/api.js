export async function fetchUsers(setUsers, setLoading) {
    setLoading(true);
    try {
        const response = await fetch("http://localhost:8080/emp");
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setUsers(data);
    } catch (error) {
        console.log(error);
    }
    setLoading(false);
}

export async function handleDelete(id, users, setUsers) {
    try {
        const response = await fetch(`http://localhost:8080/emp/${id}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.info);
        }
        setUsers(users.filter(user => user.id !== id));
    } catch (error) {
        console.log(error);
    }
}

export async function handleAdd(users,body,setUsers){
    try{
        const response=await fetch("http://localhost:8080/emp",{
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method : 'POST',
            body : JSON.stringify(body)
        })
        if (!response.ok) {
            throw new Error("Failed add new user");
        }
        const data=await response.json()
        console.log(data)
        setUsers([...users, data]);
    }
    catch(error){
        console.log()
    }
}

export async function handlePut(users,body,setUsers){
    try{
        const response=await fetch("http://localhost:8080/emp",{
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method : 'PUT',
            body : JSON.stringify(body)
        })
        if (!response.ok) {
            throw new Error("Failed add new user");
        }
        const data=await response.json()
        const updatedUsers = users.map(user => 
            user.id === data.id ? data : user
        );
        setUsers(updatedUsers);
    }
    catch(error){
        console.log(error)
    }
}
