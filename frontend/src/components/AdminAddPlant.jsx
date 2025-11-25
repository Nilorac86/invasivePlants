// AdminAddPlant.jsx

import {useState} from "react";
import { adminPostPlant } from "../service/AdminAddPlantService";
import "./AdminAddPlant.css"

function AdminAddPlant(){
    const [form, setForm] = useState(
        {
            SpeciesName: "",
            description: "",
            speciesStatus: "",
            biologicalCharacteristics: "",
            plantHandeling: "",
            photo: null
        }
    );

    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value});
        setErrorMsg("");
        setSuccessMsg("");
    };

    const handleFileChange = (e) =>{
        const file = e.target.files[0];
        setForm({...form,photo: file});//store file object
        setErrorMsg("");
        setSuccessmsg("");
    };

    if(file){
        const reader = new FileReader();
        reader.onloadend = () =>{
            setPreviewUrl(reader.result);
        };
        reader.readAsDataURL(file);
    }else{
        setPreviewUrl(null);
    }

};

    const handleSubmit = async (e) =>{
        e.preventDefault();

        const formData = new formData();
        Object.entries(form).forEach(([key, value])=>{
            formData.append(key, value);
        });
    

    try{
        await adminPostPlant(formData);
        alert("växt registrerad!");
        //reset form
        setForm({
          SpeciesName: "",
          description: "",
          speciesStatus: "",
          biologicalCharacteristics: "",
          plantHandeling: "",
          photo: null,
        });
    }catch (error){
        console.error(error);
        alert("något gick fel.");
    }
};

return (
<div className="addPlant-form">
  
    <form onSubmit={handleSubmit} className="form-box">
        <h2>Lägg till en invasive växt</h2>

        <label>Växt namn: </label>
        <input name="speciesName" onChange={handleChange}/>

        <label>Beskrivning: </label>
        <textarea name="description" onChange={handleChange}/>

        <label>Status: </label>
        <input name="speciesStatus" onChange={handleChange}/>

        <label>Biologiska kännetecken: </label>
        <textarea name="biologicalCharacteristics" onChange={handleChange}/>

        <label>Hantera växten: </label>
        <textarea name="plantHandeling" onChange={handleChange}/>

        <label>Foto: </label>
        <input type="file" acceps="image/*" onChange={handleChange}/>
     

      <button type="submit">Spara</button>

    </form>
</div>
);
}
export default AdminAddPlant;
