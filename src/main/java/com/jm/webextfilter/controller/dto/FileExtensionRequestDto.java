package com.jm.webextfilter.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FileExtensionRequestDto (
	@Size(max = 20, message = "확장자 명은 20자 이내여야 합니다.")
	@NotBlank(message = "확장자 명은 공백일 수 없습니다.")
	String name
) {
}
