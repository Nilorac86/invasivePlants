import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUserForm } from "../service/RegisterUserService";
import "./RegisterUserForm.css"

function RegisterUserForm(){
    
    const navigate = useNavigate();

    // Creates object to keep track on the input
    const [form, setForm] = useState({
        firstName: "",
        lastName:"",
        email: "",
        password: "",
        phoneNumber: "",
    });

    // Updates the current field in the form
    const handleChange = (e) =>{
        setForm({
            ...form,
            [e.target.name]: e.target.value // This function works for every input
        });
    }


    // Usestate for error for input, backend, success and disable subbmitting
    const [errors, setErrors] = useState ({});
    const [generalError, setGeneralError] = useState ("");
    const [success, setSuccess] = useState ("");
    const [isSubmitting, setIsSubmitting] = useState(false);

    // Frontend validation regex
    const validateForm = () => {
    const newErrors = {};
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,64}$/;
    const phoneNumberRegex = /^(\+46|0)[0-9]{7,10}$/;

    // Checks the input
    if (!form.firstName) newErrors.firstName = "Förnamn krävs";
    if (!form.lastName) newErrors.lastName = "Efternamn krävs";

    if (!form.email.trim()) newErrors.email = "Email krävs"
    else if(!emailRegex.test(form.email)) newErrors.email = "Ogiltig email";

    if (!form.password.trim()) newErrors.password = "Lösenord krävs";
    else if (!passwordRegex.test(form.password)) newErrors.password = "Lösenordet måste vara minst 8 tecken, innehålla en siffra, en storbokstav och en liten bokstav";

    if (!form.phoneNumber.trim()) newErrors.phoneNumber = "Telefonnummer krävs";
    else if (!phoneNumberRegex.test(form.phoneNumber)) newErrors.phoneNumber = "Ogiltigt telefonnummer";

    return newErrors
    }

    // Prevent the form to render side
      const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors({});
        setGeneralError("");
        setSuccess("");
        setIsSubmitting(true);

        const validationErrors = validateForm();

        // Takes the keys in validationError and returns validation message to user
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            setIsSubmitting(false);
            return
        }

       // Try/ catch
        try {
            await registerUserForm(form);
            setSuccess ("Användare registerad!")

            setTimeout(() => { // So user can see the message before redirecting.
                navigate("/login");           
            }, 1000);

        } catch (error) {
            setGeneralError("Något gick fel, försök igen!");
        } finally {
            setIsSubmitting (false);
        }
    };

    // The actuall form to register a user with input field and catching errors.
    return (
        <div className="form-wrapper">
        <form className="forms-container" onSubmit={handleSubmit}>

            <div className="field">
                <label>Förnamn *</label>
            <input name = "firstName" 
            value = {form.firstName} 
            placeholder="Ditt förnamn"
            onChange = {handleChange} />
            {errors.firstName && <p className="error">{errors.firstName}</p>}
            </div>

            <div className="field">
                <label>Efternamn *</label>
            <input name = "lastName" 
            value = {form.lastName}
            placeholder="Ditt efternamn"
            onChange={handleChange}/>
            {errors.lastName && <p className="error">{errors.lastName }</p>}
            </div>

            <div className="field">
                <label>Email *</label>
            <input name = "email"
            type ="email"
            value = {form.email}
            placeholder="exempel@email.com"
            onChange={handleChange}/>
            {errors.email && <p className="error">{errors.email}</p>}
            </div>

            <div className="field">
                <label>Lösenord *</label>
            <input name = "password"
            type = "password"
            value = {form.password}
            placeholder="dittLösenord1"
            onChange={handleChange} />
            {errors.password && <p className="error">{errors.password}</p>}
            </div>

            <div className="field">
                <label>Telefonnummer *</label>
            <input name = "phoneNumber"
            value = {form.phoneNumber}
            placeholder="ex. 0707556677"
            onChange={handleChange}/>
            {errors.phoneNumber && <p className="error">{errors.phoneNumber}</p>}
            </div>

            <button className="form-btn" type="submit" disabled= {isSubmitting}>
                Registrera
            </button>

            {generalError && <p>{generalError}</p>}
            {success && <p>{success}</p>}
        </form>
        </div>
    
    );

} export default RegisterUserForm;