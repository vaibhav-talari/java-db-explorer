package org.common.entities.dtamodel;

import java.sql.Timestamp;

public class CustomersDTO {

	private Long id;
	private String name;
	private String email;
	private Timestamp createdAt;
	private Boolean active;

	public CustomersDTO(Long id, String name, String email, Timestamp createdAt, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.createdAt = createdAt;
		this.active = active;
	}

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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "CustomersDTO [id=" + id + ", name=" + name + ", email=" + email + ", createdAt=" + createdAt
				+ ", active=" + active + "]";
	}

}
