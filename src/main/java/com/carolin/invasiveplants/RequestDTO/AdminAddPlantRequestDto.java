package com.carolin.invasiveplants.RequestDTO;

import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

public class AdminAddPlantRequestDto {

    @NotBlank(message=" Du måste lägga till namn på den invasiva växten")
    @Size(max = 20, message= "Växtnamnet kan bara vara max 20 tecken långt")
    private String speciesName;

    @NotBlank(message="Du måste lägga till en beskrivning")
    @Size(max= 200, message="Beskrivningen kan bara innehålla max 200 tecken")
    private String description;

    @NotBlank(message = "Du måste lägga till vad för status växten har")
    @Size(max= 50, message="Status kan bara innehålla max 50 tecken")
    private String speciesStatus;

    @NotBlank(message="Du måste lägga till biologiska karaktärer")
    @Size(max= 100, message="Biologiska karaktärer kan bara innehålla max 100 tecken")
    private String biologicalCharacteristics;

    @NotBlank(message="Du måste lägga till hur man ska hantera växten")
    @Size(max=50, message="Hantering kan bara innehålla max 50 tecken")
    private String plantHandling;

    // Cannot use annotation with multipartfile.
    // But the ones that only requires text it will work fine together photo only need other validation.
    private MultipartFile photo;

    /*
    @NotNull(message="Du måste ange hur många poäng rapotering ger")
    @Min(value = 1, message = "Poängen måste vara minst 1.")
    @Max(value= 1000, message= "Poängen får vara högst 1000.")
    private Integer pointsReport;

    @NotNull(message="Du måste ange hur många poäng bortagning ger")
    @Min(value = 1, message = "Poängen måste vara minst 1.")
    @Max(value= 1000, message= "Poängen får vara högst 1000.")
    private Integer pointsRemove;
*/

    public AdminAddPlantRequestDto() {
    }

    public AdminAddPlantRequestDto(String speciesName, String description, String speciesStatus,
                                   String biologicalCharacteristics, String plantHandling, MultipartFile photo) {
        this.speciesName = speciesName;
        this.description = description;
        this.speciesStatus = speciesStatus;
        this.biologicalCharacteristics = biologicalCharacteristics;
        this.plantHandling = plantHandling;
        this.photo = photo;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeciesStatus() {
        return speciesStatus;
    }

    public void setSpeciesStatus(String speciesStatus) {
        this.speciesStatus = speciesStatus;
    }

    public String getBiologicalCharacteristics() {
        return biologicalCharacteristics;
    }

    public void setBiologicalCharacteristics(String biologicalCharacteristics) {
        this.biologicalCharacteristics = biologicalCharacteristics;
    }

    public String getPlantHandling() {
        return plantHandling;
    }

    public void setPlantHandling(String plantHandling) {
        this.plantHandling = plantHandling;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
