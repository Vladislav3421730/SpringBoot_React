import EmployeeContent from './Componenets/EmployeeContent';
import Header from './Componenets/Header';
import Main from './Componenets/Main';
import { useState } from 'react';
function App() {

  const[contentType,SetContentType]=useState("main")
  return (
    <>
    <Header contentType={contentType} onChange={(current)=>SetContentType(current)} ></Header>
    {contentType==="main" && <Main/>}
    {contentType==="employees" && <EmployeeContent/>}
    </>
  );
}

export default App;
