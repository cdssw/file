package com.moim.file.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.moim.file.component.CommonComponent;
import com.moim.file.entity.File;
import com.moim.file.repository.FileRepository;
import com.moim.file.service.file.FileDto.ImageRes;
import com.moim.file.service.file.FileDto.Res;

import lombok.AllArgsConstructor;

/**
 * FileServiceImpl.java
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
@Service
public class FileServiceImpl implements FileService {

	private CommonComponent commonComponent;
	private FileRepository fileRepository;
	
	@Override
	public Res uploadAvatar(String group, MultipartFile file) {
		
		// 파일저장
		File info = commonComponent.saveFile(group, file);
		
		// DB 저장
		fileRepository.save(info);
		
		return Res.builder().filePath(String.format("%s/%s.%s", info.getPath(), info.getUuid(), info.getExtension())).build();
	}

	@Override
	public ImageRes uploadImage(String group, MultipartFile file) {
		
		// 파일저장
		File info = commonComponent.saveFile(group, file);
		
		// DB 저장
		fileRepository.save(info);
		
		return ImageRes.builder().id(info.getId()).filePath(String.format("%s/%s.%s", info.getPath(), info.getUuid(), info.getExtension())).build();
	}

}
