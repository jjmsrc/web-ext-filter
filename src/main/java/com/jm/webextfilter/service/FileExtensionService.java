package com.jm.webextfilter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jm.webextfilter.controller.dto.FileExtensionDto;
import com.jm.webextfilter.controller.dto.FileExtensionRequestDto;
import com.jm.webextfilter.domain.model.FileExtension;
import com.jm.webextfilter.domain.repository.FileExtensionRepository;
import com.jm.webextfilter.global.exception.CustomException;
import com.jm.webextfilter.global.exception.ErrorCode;

import jakarta.transaction.Transactional;

@Service
public class FileExtensionService {

	private final int MAX_CUSTOM_EXTENSIONS = 200;
	private final FileExtensionRepository fileExtensionRepository;

	public FileExtensionService(FileExtensionRepository fileExtensionRepository) {
		this.fileExtensionRepository = fileExtensionRepository;
	}

	public List<FileExtensionDto> getFileExtensions() {
		List<FileExtension> extensions = fileExtensionRepository.findAll();
		return extensions.stream().map(FileExtensionDto::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public FileExtensionDto checkFileExtension(Integer id) {
		Optional<FileExtension> opt = fileExtensionRepository.findById(id);

		// 해당 id의 확장자가 없을 경우, 에러 반환
		opt.orElseThrow(() -> new CustomException(ErrorCode.EXTENSION_NOT_FOUND));

		FileExtension ext = opt.get();

		// 해당 확장자가 고정 확장자가 아닐 경우, 에러 반환
		if (!ext.getFixed()) throw new CustomException(ErrorCode.INVALID_REQUEST);

		// 체크 or 체크 해제
		ext.check();

		return FileExtensionDto.from(ext);
	}

	@Transactional
	public FileExtensionDto makeFileExtension(FileExtensionRequestDto dto) {

		long count = fileExtensionRepository.count();

		if (count >= MAX_CUSTOM_EXTENSIONS) throw new CustomException(ErrorCode.NO_MORE_CUSTOM_EXTENSION);

		Optional<FileExtension> opt = fileExtensionRepository.findByName(dto.name());

		// 이미 등록된 확장자가 있을 경우 에러 반환
		opt.ifPresent((ext) -> {
			throw new CustomException(ErrorCode.DUPLICATE_EXTENSION);
		});

		FileExtension ext = FileExtension.builder()
			.name(dto.name())
			.fixed(false)
			.checked(true)
			.build();

		fileExtensionRepository.save(ext);

		return FileExtensionDto.from(ext);
	}

	@Transactional
	public void deleteFileExtension(Integer id) {
		// 요청한 확장자가 없을 경우, 에러 반환
		FileExtension ext = fileExtensionRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.EXTENSION_NOT_FOUND));
		
		// 요청한 확장자가 고정 확장자일 경우, 에러 반환
		if (ext.getFixed()) throw new CustomException(ErrorCode.INVALID_REQUEST);

		fileExtensionRepository.delete(ext);
	}
}
