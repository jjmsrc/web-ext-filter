package com.jm.webextfilter.controller.dto;

import com.jm.webextfilter.domain.model.FileExtension;

import lombok.Builder;

public record FileExtensionDto(Integer id, String name, Boolean fixed, Boolean checked) {
	@Builder
	public FileExtensionDto {
	}

	public static FileExtensionDto from(FileExtension ext) {
		return FileExtensionDto.builder()
			.id(ext.getId())
			.name(ext.getName())
			.fixed(ext.getFixed())
			.checked(ext.getChecked())
			.build();
	}
}
