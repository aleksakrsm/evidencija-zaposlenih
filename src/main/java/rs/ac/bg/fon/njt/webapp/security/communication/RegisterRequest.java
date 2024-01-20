/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.security.communication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 *
 * @author aleks
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    
    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 3, max = 30, message = "Firstname must contain between 3 and 30 characters")
    @Pattern(regexp = "^[A-Z][a-z]+$",
            message = "Name must start with capital letter and contain only letters. Other letters must be lowercase.")
    private String firstname;
    
    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 30, message = "Lastname must contain between 3 and 30 characters")
    @Pattern(regexp = "^[A-Z][a-z]+$",
            message = "Lastname must start with capital letter and contain only letters. Other letters must be lowercase.")
    private String lastname;
    
    @NotBlank(message = "Username is mandatory")
    private String username;
    
    @NotBlank(message = "Email is mandatory")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Please provide valid email adress")
    private String email;    
    
    @NotBlank(message = "Password is mandatory")
    private String password;
    
}
