package com.carolin.invasiveplants.RequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.parameters.P;

public class UserRegisterRequestDto {

    @NotBlank (message = "Förnamn får inte lämnas tomt")
    @Size (min = 2, max = 64, message = "Förnamn måste vara minst 2 och max 64 bokstäver långt")
    @Pattern(regexp = "^[A-Za-zÅÄÖåäö\\- ]+$",
            message = "Förnamn får bara innehålla bokstäver")
    private String firstName;


    @NotBlank(message = "Efternamn får inte lämnas tomt")
    @Size (min = 2, max = 64, message = "Efternamn måste vara minst 2 och max 64 bokstäver långt")
    @Pattern(regexp = "^[A-Za-zÅÄÖåäö\\- ]+$",
            message = "Efternamn får bara innehålla bokstäver")
    private String lastName;


    @NotBlank(message = "Email får inte lämnas tomt")
    @Email(message = "Måste vara en giltig e-mailadress ")
    @Size(max = 128)
    private String email;


    @NotBlank (message= "Lösenord får inte lämnas tomt")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,64}$",
            message = "Lösenordet måste vara 8-64 tecken och innehålla minst en stor och en liten bokstav, samt en siffra")
    private String password;


    @Pattern(regexp = "^(\\+46|0)[0-9]{7,10}$",
           message = "Ogiltigt telefonnummer")
    private String phoneNumber;

    public UserRegisterRequestDto() {
    }

    public UserRegisterRequestDto(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
