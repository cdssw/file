package com.moim.file.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.moim.file.config.JpaAuditingConfig;
import com.moim.file.entity.File;
import com.moim.file.repository.FileRepository;

/**
 * FileRepositoryTest.java
 * 
 * @author cdssw
 * @since 2020. 08. 06
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 08.06    cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = {JpaAuditingConfig.class}))
@ActiveProfiles("test")
public class FileRepositoryTest {

	@Autowired
	FileRepository fileRepository;
	
	@Value("${file.basepath}")
	String basePath; 
	
	@After
	public void cleanup() {
		fileRepository.deleteAll();
	}
	
	@Test
	public void testSave() {
		// given
		String uuid = UUID.randomUUID().toString();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dirPath = String.format("%s/%s/%s", basePath, "avatar", formatter.format(new Date()));
		
		File avatar = File.builder()
				.uuid(uuid)
				.groupDir("avatar")
				.orgFileNm("original_file_name")
				.chgFileNm("change_file_name")
				.extension("jpg")
				.fileSize(1000L)
				.path(dirPath)
				.build();
		
		// when
		File s = fileRepository.save(avatar);
		File res = fileRepository.findById(s.getId()).get();
		
		// then
		assertEquals(res.getUuid(), s.getUuid());;
	}
	
	
}
