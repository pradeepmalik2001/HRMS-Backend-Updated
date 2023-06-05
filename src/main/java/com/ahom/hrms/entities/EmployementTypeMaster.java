package com.ahom.hrms.entities;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "employment_type_master")
public class EmployementTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int id;
    @NotEmpty(message = "Employment Type is not empty")
//	@Pattern(regexp = "[/^[a-zA-Z ]*$/]{1,10}")
	private String employmentType;
	@NotEmpty(message = "Write some description here")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployementTypeMaster() {

		employmentType = null;
	}

		public @NotNull String getEmploymentType() {
			return employmentType;
		}

	public void setEmploymentType(@NotNull String employmentType) {
		this.employmentType = employmentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmployementTypeMaster(int id, @NotNull String employmentType, String description) {
		this.id = id;
		this.employmentType = employmentType;
		this.description = description;
	}
}
