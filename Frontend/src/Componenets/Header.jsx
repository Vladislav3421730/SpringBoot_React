import Button from "./Button/Button";

export default function Header({contentType,onChange}) {

    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Button isActive={contentType==="main"} onClick={()=>onChange("main")} >Главная</Button>
                        </li>
                        <li className="nav-item">
                            <Button isActive={contentType==="employees"} onClick={()=>onChange("employees")}>Работники</Button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}