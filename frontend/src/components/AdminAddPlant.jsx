// AdminAddPlant.jsx

import {useState} from "react";
import { adminPostPlant } from "../service/AdminAddPlantService";
import "./AdminAddPlant.css"

function AdminAddPlant(){
    const [form, setForm] = useState(
        {
            speciesName: "",
            description: "",
            speciesStatus: "",
            biologicalCharacteristics: "",
            plantHandling: "",
            photo: null,
            pointsReport: "",
            pointsRemove: "",
        });

    const [loading, setLoading] = useState(false);
    const [errorMsg, setErrorMsg] = useState("");
    const [successMsg, setSuccessMsg] = useState("");
    const [previewUrl, setPreviewUrl] = useState(null);


    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value});
        setErrorMsg("");
        setSuccessMsg("");
    };

    const handleFileChange = (e) =>{
        const file = e.target.files[0];
        setForm({...form, photo: file});//store file object
        setErrorMsg("");
        setSuccessMsg("");

        // file preview 
        if(file){
            const reader = new FileReader();
            reader.onloadend = () => setPreviewUrl(reader.result);
            reader.readAsDataURL(file);
        }else{
            setPreviewUrl(null);
    }
};

//check input
const validateForm =() =>{

     if(!form.speciesName.trim()){
        setErrorMsg("Växt namn krävs");
        return false;
     }

     if(!form.description.trim()){
        setErrorMsg("Beskrivning krävs.");
        return false;
     }

     if(!form.speciesStatus.trim()){
        setErrorMsg("Status krävs.");
        return false;
     }

     if(!form.biologicalCharacteristics.trim()){
        setErrorMsg("Biologiska kännetecken krävs");
        return false;
     }

     if (!form.plantHandling.trim()) {
       setErrorMsg("Hantering av växten krävs");
       return false;
     }

     if(!form.photo){
        setErrorMsg("Foto krävs");
        return false;
     }

     if (!form.pointsReport || isNaN(form.pointsReport)) {
       setErrorMsg("Hur mycket poäng det är värt att rapotera krävs");
       return false;
     }

     if (!form.pointsRemove || isNaN(form.pointsRemove)) {
       setErrorMsg("Hur mycket poäng det är värt att ta bort krävs");
       return false;
     }

     return true;
};


    const handleSubmit = async (e) =>{
        e.preventDefault();
        setErrorMsg("");
        setSuccessMsg("");

        if(!validateForm()) return;
        
    const formData = new FormData();
    Object.entries(form).forEach(([key,value])=>
        formData.append(key, value));

    //setLoading(true);
    console.log("Payload:");
    for (let pair of formData.entries()) {
      console.log(pair[0], pair[1]);
    }

    try{
        await adminPostPlant(formData);
        setSuccessMsg("växt registrerad!");
        //reset form and prewuie
        setForm({
          speciesName: "",
          description: "",
          speciesStatus: "",
          biologicalCharacteristics: "",
          plantHandling: "",
          photo: null,
          pointsReport: "",
          pointsRemove: "",
        });

        setPreviewUrl(null);

    }catch (error){
        console.error(error);
        alert("något gick fel vid registreringen.");
    } finally{
        setLoading(false);
    }
};

return (
  <div className="addPlant-form">
    <form onSubmit={handleSubmit} className="form-box">
      <h2>Lägg till en invasive växt</h2>

      {errorMsg && <div className="error-msg">{errorMsg}</div>}
      {successMsg && <div className="success-msg">{successMsg}</div>}

      <label>Växt namn: </label>
      <input
        name="speciesName"
        value={form.speciesName}
        onChange={handleChange}
        disabled={loading}
        required
      />

      <label>Beskrivning: </label>
      <textarea
        name="description"
        value={form.description}
        onChange={handleChange}
        disabled={loading}
      />

      <label>Status: </label>
      <input
        name="speciesStatus"
        value={form.speciesStatus}
        onChange={handleChange}
        disabled={loading}
      />

      <label>Biologiska kännetecken: </label>
      <textarea
        name="biologicalCharacteristics"
        value={form.biologicalCharacteristics}
        onChange={handleChange}
        disabled={loading}
      />

      <label>Hantera växten: </label>
      <textarea
        name="plantHandling"
        value={form.plantHandling}
        onChange={handleChange}
        disabled={loading}
      />

      <label>Foto: </label>
      <input
        type="file"
        accept="image/*"
        onChange={handleFileChange}
        disabled={loading}
      />

      <label>Poäng för att rapotera: </label>
      <input
        type="number"
        name="pointsReport"
        value={form.pointsReport}
        onChange={handleChange}
        disabled={loading}
      />

      <label>Poäng för att ta bort: </label>
      <input
        type="number"
        name="pointsRemove"
        value={form.pointsRemove}
        onChange={handleChange}
        disabled={loading}
      />

      {previewUrl && (
        <div className="image-preview">
          <img src={previewUrl} alt="förhandsvisning" />
        </div>
      )}

      <button type="submit" disabled={loading}>
        {loading ? "sparar..." : "spara"}
      </button>
    </form>
  </div>
);
}
export default AdminAddPlant;
