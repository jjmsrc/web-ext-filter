package com.jm.webextfilter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jm.webextfilter.controller.dto.FileExtensionDto;
import com.jm.webextfilter.domain.model.FileExtension;
import com.jm.webextfilter.domain.repository.FileExtensionRepository;

@Service
public class FileExtensionService {

	private final FileExtensionRepository fileExtensionRepository;

	public FileExtensionService(FileExtensionRepository fileExtensionRepository) {
		this.fileExtensionRepository = fileExtensionRepository;
	}

	public List<FileExtensionDto> getFileExtensions() {
		List<FileExtension> extensions = fileExtensionRepository.findAll();
		return extensions.stream().map(ext ->
				FileExtensionDto.builder()
					.id(ext.getId())
					.name(ext.getName())
					.fixed(ext.getFixed())
					.checked(ext.getChecked())
					.build())
			.collect(Collectors.toList());
	}

}
