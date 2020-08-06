package com.moim.file.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moim.file.service.file.FileDto;
import com.moim.file.service.file.FileService;

import lombok.AllArgsConstructor;

/**
 * FileController.java
 * 
 * @author cdssw
 * @since 2020. 8. 6.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 8. 6.    cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@RestController
@RequestMapping
public class FileController {

	private FileService fileService;
	
	@PostMapping("/avatar")
	public FileDto.Res uploadAvatar(@RequestParam("group") final String group, @RequestPart MultipartFile file) {
		return fileService.uploadAvatar(group, file);
	}
}
