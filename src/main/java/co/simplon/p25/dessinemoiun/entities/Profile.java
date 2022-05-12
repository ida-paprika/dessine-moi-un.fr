package co.simplon.p25.dessinemoiun.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile extends AbstractEntity {

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "email")
    private String email;

    public Profile() {
	//
    }

    public Profile(UUID uuid, String email) {
	this.uuid = uuid;
	this.email = email;
    }

    public UUID getUuid() {
	return uuid;
    }

    public void setUuid(UUID uuid) {
	this.uuid = uuid;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return String.format("Profile [uuid=%s, email=%s]", uuid, email);
    }

}
