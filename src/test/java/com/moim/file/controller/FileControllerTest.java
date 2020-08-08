package com.moim.file.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.moim.file.service.file.FileDto;

import lombok.extern.slf4j.Slf4j;

/**
 * MeetControllerTest.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@WebMvcTest // controller 관련 bean만 로딩
@Slf4j
public class FileControllerTest extends BaseControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	private FileDto.Res res;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글깨짐 방지 처리
				.alwaysDo(print()) // 항상 결과 print
				.build();
		
		res = FileDto.Res.builder().filePath(String.format("%s/%s.%s", "/avatar", UUID.randomUUID().toString(), "jpg")).build();
	}
	
	// 테스트 하는것은 dto를 가지고 controller 호출이 잘 되는지 확인
	@Test
	public void testUploadAvatar() throws Exception {
		// given
		// 서비스 호출시 무조건 1L 리턴
		given(fileService.uploadAvatar(any(), any())).willReturn(res);
		String fileNm = "test_file.jpg";
		MockMultipartFile file = new MockMultipartFile("file", fileNm, "text/plain", "file content".getBytes());
		
		// when
		final MvcResult result = mvc.perform(multipart("/avatar")
				.file(file)
				.param("group", "avatar"))
				.andExpect(status().isOk()) // 200 확인
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		// then
		log.info(content);		
	}
}
