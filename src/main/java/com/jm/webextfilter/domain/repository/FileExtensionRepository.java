package com.jm.webextfilter.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jm.webextfilter.domain.model.FileExtension;

public interface FileExtensionRepository extends JpaRepository<FileExtension, Integer> {
	Optional<FileExtension> findByName(String name);
}
