package co.simplon.p25.dessinemoiun.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ProfileCreate {

    @NotBlank
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

    private String role;

    public ProfileCreate() {
	//
    }

    public ProfileCreate(
	    @NotBlank @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String email,
	    @NotBlank @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") String password,
	    String role) {
	this.email = email;
	this.password = password;
	this.role = role;
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

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    @Override
    public String toString() {
	return String.format("ProfileCreate [email=%s, password=%s, role=%s]",
		email, "[PROTECTED]", role);
    }

}
