package com.moim.file.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import com.moim.file.component.CommonComponent;
import com.moim.file.entity.File;

/**
 * CommonComponentTest.java
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
@RunWith(MockitoJUnitRunner.class)
public class CommonComponentTest {

	private CommonComponent commonComponent;
	
	@Before
	public void setUp() {
//		Field field = FileServiceImpl.class.getDeclaredField("basePath");
//		field.setAccessible(true);
//		field.set(null, "/volume1/file");
		
		commonComponent = new CommonComponent();
		commonComponent.setBasePath("/volume1/file");
	}
	
	@Test
	public void testSaveFile() {
		// given
		String fileNm = "test_file.jpg";
		MockMultipartFile file = new MockMultipartFile("file", fileNm, "text/plain", "file content".getBytes());
		
		// when
		File res = commonComponent.saveFile("avatar", file);
		
		// then
		assertEquals(res.getOrgFileNm(), fileNm);
	}
}
