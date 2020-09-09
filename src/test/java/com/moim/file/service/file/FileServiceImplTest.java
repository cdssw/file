package com.moim.file.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import com.moim.file.component.CommonComponent;
import com.moim.file.entity.File;
import com.moim.file.repository.FileRepository;

/**
 * FileServiceImplTest.java
 * 
 * @author cdssw
 * @since 2020. 8. 6
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 8. 6.    cdssw            최초 생성
 * </pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class FileServiceImplTest {

	// 테스트 하고자 하는 class
	private FileServiceImpl fileServiceImpl;

	// 해당 클래스를 테스트 하지 않음
	@Mock private CommonComponent commonComponent;
	@Mock private FileRepository fileRepository;
	
	@Before
	public void setUp() {
		fileServiceImpl = new FileServiceImpl(commonComponent, fileRepository);
	}
	
	@Test
	public void testUploadAvatar() {
		// given
		String fileNm = "test_file.jpg";
		MockMultipartFile file = new MockMultipartFile("file", fileNm, "text/plain", "file content".getBytes());
		File info = File.builder().uuid(UUID.randomUUID().toString()).build();
		given(commonComponent.saveFile(any(), any())).willReturn(info);
		
		// when
		FileDto.Res res = fileServiceImpl.uploadAvatar("avatar", file);
		
		// then
		assertEquals(res.getFilePath(), String.format("%s/%s.%s", info.getPath(), info.getUuid(), info.getExtension()));
	}
	
	@Test
	public void testUploadImage() {
		// given
		String fileNm = "test_file.jpg";
		MockMultipartFile file = new MockMultipartFile("file", fileNm, "text/plain", "file content".getBytes());
		File info = mock(File.class);
		given(commonComponent.saveFile(any(), any())).willReturn(info);
		given(info.getId()).willReturn(1L);
		
		// when
		FileDto.ImageRes res = fileServiceImpl.uploadImage("image", file);
		
		// then
		assertEquals(res.getId(), 1L);
	}
}
