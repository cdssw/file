package com.moim.file.component;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.moim.file.except.ErrorCode;
import com.moim.file.except.FileBusinessException;
import com.moim.file.except.NotFoundException;

/**
 * CommonComponent.java
 * 
 * @author cdssw
 * @since Apr 21, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 21, 2020   cdssw            최초 생성
 * </pre>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Component
public class CommonComponent {
	
	private String basePath;
	
	@Value("${file.basepath}")
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	// JpaRepository findById 처리를 위한 공통 Method
	@Transactional(readOnly = true) // 성능향상을 위해
	public <T, ID, C> C findById(T t, ID id, Class<C> type) {
		final Optional<C> m = ((JpaRepository)t).findById((Long)id);
		m.orElseThrow(() -> new NotFoundException(ErrorCode.ELEMENT_NOT_FOUND));
		return type.cast(m.get());
	}
	
	// JpaRepository findById 처리를 위한 공통 Method with ErrorCode
	@Transactional(readOnly = true) // 성능향상을 위해
	public <T, ID, C> C findById(T t, ID id, Class<C> type, ErrorCode errorCode) {
		final Optional<C> m = ((JpaRepository)t).findById((Long)id);
		m.orElseThrow(() -> new NotFoundException(errorCode));
		return type.cast(m.get());
	}
	
	public com.moim.file.entity.File saveFile(String group, MultipartFile file) {
		String uuid = UUID.randomUUID().toString();
		
		String orgFileNm = file.getOriginalFilename();
		String extension = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1, orgFileNm.length());
		String chgFileNm = uuid + "." + extension;
		Long fileSize = file.getSize();
		
		// create directory
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String filePath = String.format("%s/%s", group, formatter.format(new Date()));
		String dirPath = String.format("%s/%s", basePath, filePath);
		File path = new File(dirPath);
		if(!path.exists()) path.mkdirs();
		
		// save
		File target = new File(dirPath, chgFileNm);
		try {
			FileCopyUtils.copy(file.getBytes(), target);
		} catch (IOException e) {
			throw new FileBusinessException(ErrorCode.FAIL_TO_SAVE_FILE);
		}
		
		return com.moim.file.entity.File.builder()
				.uuid(uuid)
				.groupDir(group)
				.orgFileNm(orgFileNm)
				.chgFileNm(chgFileNm)
				.fileSize(fileSize)
				.extension(extension)
				.path(filePath)
				.build();
	}
}
