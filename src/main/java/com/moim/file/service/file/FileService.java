package com.moim.file.service.file;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

	FileDto.Res uploadAvatar(final String group, final MultipartFile file);
	FileDto.ImageRes uploadImage(final String group, final MultipartFile file);
	FileDto.InfoRes getImage(final Long fileId) throws IOException;
	List<FileDto.ListRes> getImagesPath(final FileDto.ListReq dto);
}
