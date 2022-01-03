package by.malinka.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@NotNull(message = "Name is required")
	@Pattern(regexp = "[a-zA-Z]{1,40}", message = "Name can contain only letters")
	@Column(nullable = false)
	private String name;

	@Email
	@NotNull(message = "Email must be present")
	@Column(nullable = false)
	private String email;

	@NotNull(message = "Mobile must be present")
	@Column(nullable = false)
	private String mobile;

	@NotNull(message = "Password is required")
	@Column(nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {

	}

	public User(Long id, String name, String email, String mobile, String password, Role role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
	}

}