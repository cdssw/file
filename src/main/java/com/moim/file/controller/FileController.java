package com.moim.file.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@PostMapping("/image")
	public FileDto.ImageRes uploadImage(@RequestParam("group") final String group, @RequestPart MultipartFile file) {
		return fileService.uploadImage(group, file);
	}
	
	@GetMapping("/image/{fileId}")
	@ResponseBody
	public void getImage(@PathVariable("fileId") final Long fileId, HttpServletResponse response) throws IOException {
		FileDto.InfoRes info = fileService.getImage(fileId);
		String fileName = info.getOrgFileNm();
		
		response.setContentType("application/octet-stream");
	    response.setContentLength(info.getData().length);
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.getOutputStream().write(info.getData());
	     
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
}
