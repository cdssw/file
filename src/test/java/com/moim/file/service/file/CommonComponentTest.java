package com.moim.file.service.file;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import com.moim.file.component.CommonComponent;

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

	@InjectMocks
	private CommonComponent commonComponent;
	
	@Before
	public void setUp() {
		commonComponent = new CommonComponent();
		ReflectionTestUtils.setField(commonComponent, "basePath", "/volume1/docker/file");
	}
	
	@Test
	public void testSaveFile() {
		// given
		String fileNm = "test_file.jpg";
		MockMultipartFile file = new MockMultipartFile("file", fileNm, "text/plain", "file content".getBytes());
		
		// when
//		File res = commonComponent.saveFile("avatar", file);
		
		// then
//		assertEquals(res.getOrgFileNm(), fileNm);
	}
}
