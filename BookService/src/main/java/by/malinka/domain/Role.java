package by.malinka.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
public class Role {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;
	@Column(nullable = false)
	private String name;

	@OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> users;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}