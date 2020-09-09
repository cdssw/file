package com.moim.file.service.file;

import org.springframework.web.multipart.MultipartFile;

import com.moim.file.service.file.FileDto.ImageRes;
import com.moim.file.service.file.FileDto.Res;

/**
 * FileService.java
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
public interface FileService {

	Res uploadAvatar(final String group, final MultipartFile file);
	ImageRes uploadImage(final String group, final MultipartFile file);
}
