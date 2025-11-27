import { apiPost } from "../util/Api";

export async function registerUserForm(form) {

    try{
       return await apiPost("/users/register", form)

    } catch (error) { 
        console.error ("Något gick fel vid registeringen: ", error);
        throw error
    }
}