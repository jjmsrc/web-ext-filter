package com.jm.webextfilter.controller.dto;

import lombok.Builder;

public record FileExtensionDto(Integer id, String name, Boolean fixed, Boolean checked) {
	@Builder
	public FileExtensionDto {
	}
}
