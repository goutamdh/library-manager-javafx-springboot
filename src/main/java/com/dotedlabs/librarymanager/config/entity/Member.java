package com.dotedlabs.librarymanager.config.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	protected UUID id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "email")
	private String email;
}
