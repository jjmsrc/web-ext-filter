package com.jm.webextfilter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jm.webextfilter.controller.dto.FileExtensionDto;
import com.jm.webextfilter.service.FileExtensionService;

@RequestMapping("/exts")
@RestController
public class FileExtensionController {

	private final FileExtensionService fileExtensionService;

	public FileExtensionController(FileExtensionService fileExtensionService) {
		this.fileExtensionService = fileExtensionService;
	}

	@GetMapping
	public List<FileExtensionDto> index() {
		return fileExtensionService.getFileExtensions();
	}



}
