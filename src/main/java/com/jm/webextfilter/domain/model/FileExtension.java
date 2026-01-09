package com.jm.webextfilter.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file_extension")
@Getter
@NoArgsConstructor
public class FileExtension {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true, length = 20)
	private String name;

	private Boolean fixed;

	private Boolean checked;

	@Builder
	public FileExtension(Integer id, String name, Boolean fixed, Boolean checked) {
		this.id = id;
		this.name = name;
		this.fixed = fixed;
		this.checked = checked;
	}

	public void check() {
		this.checked = !this.checked;
	}

}
