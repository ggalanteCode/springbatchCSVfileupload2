package com.soprasteria.springbatchCSVfileupload2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private Integer id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phone;

}
