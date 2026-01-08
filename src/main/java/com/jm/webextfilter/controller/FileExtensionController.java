package com.jm.webextfilter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jm.webextfilter.controller.dto.FileExtensionDto;
import com.jm.webextfilter.controller.dto.FileExtensionRequestDto;
import com.jm.webextfilter.service.FileExtensionService;

import jakarta.validation.Valid;

@RequestMapping("/exts")
@RestController
public class FileExtensionController {

	private final FileExtensionService fileExtensionService;

	public FileExtensionController(FileExtensionService fileExtensionService) {
		this.fileExtensionService = fileExtensionService;
	}

	@GetMapping
	public ResponseEntity<List<FileExtensionDto>> getFileExtensions() {
		return ResponseEntity.ok(fileExtensionService.getFileExtensions());
	}

	@PatchMapping("/{id}")
	public ResponseEntity<FileExtensionDto> checkFileExtension(@PathVariable Integer id) {
		return ResponseEntity.ok(fileExtensionService.checkFileExtension(id));
	}

	@PostMapping
	public ResponseEntity<FileExtensionDto> makeFileExtension(@Valid @RequestBody FileExtensionRequestDto dto) {
		return ResponseEntity.ok(fileExtensionService.makeFileExtension(dto));
	}

}
