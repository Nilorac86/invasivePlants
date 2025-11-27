import RegisterUserForm from "../components/RegisterUserForm";
import "../components/RegisterUserForm.css"

function RegisterUserFormPage(){

    return(
    <div className="register-user">
        <h2>Registrera användare</h2>
        <RegisterUserForm/>
    </div>
    );
} export default RegisterUserFormPage;