package com.moim.file.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
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
@ActiveProfiles("test")
public class FileControllerTest extends BaseControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext ctx;

	@Autowired
	private ObjectMapper objectMapper;
	
	private FileDto.Res res;
	private FileDto.ImageRes imageRes;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글깨짐 방지 처리
				.alwaysDo(print()) // 항상 결과 print
				.build();
		
		res = FileDto.Res.builder().filePath(String.format("%s/%s.%s", "/avatar", UUID.randomUUID().toString(), "jpg")).build();
		imageRes = FileDto.ImageRes.builder().id(1L).filePath(String.format("%s/%s.%s", "/avatar", UUID.randomUUID().toString(), "jpg")).build();
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
	
	@Test
	public void testUploadImage() throws Exception {
		// given
		// 서비스 호출시 무조건 1L 리턴
		given(fileService.uploadImage(any(), any())).willReturn(imageRes);
		String fileNm = "test_file.jpg";
		MockMultipartFile file = new MockMultipartFile("file", fileNm, "text/plain", "file content".getBytes());
		
		// when
		final MvcResult result = mvc.perform(multipart("/image")
				.file(file)
				.param("group", "image"))
				.andExpect(status().isOk()) // 200 확인
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		// then
		log.info(content);		
	}
	
	@Test
	public void testGetImage() throws Exception {
		// given
		byte[] data = new byte[100];
		Arrays.fill(data, (byte)1);
		FileDto.InfoRes res = FileDto.InfoRes.builder().orgFileNm("test.jpg").data(data).build();
		given(fileService.getImage(any())).willReturn(res);
		
		// when
		final MvcResult result = mvc.perform(get("/image/1")
				.contentType(MediaType.APPLICATION_OCTET_STREAM))
				.andExpect(status().isOk()) // 200 확인
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		// then
		log.info(content);		
	}
	
	@Test
	public void testGetImagesPath() throws Exception {
		// given
		List<Long> fileList = new ArrayList<>();
		fileList.add(1L);
		fileList.add(2L);
		fileList.add(3L);
		FileDto.ListReq dto = FileDto.ListReq.builder().fileList(fileList).build();
		
		List<FileDto.ListRes> list = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			FileDto.ListRes res = FileDto.ListRes.builder()
					.orgFileNm("original_file_name_" + i)
					.chgFileNm("change_file_name_" + i)
					.path("/path/" + i)
					.build();
			list.add(res);
		}
		given(fileService.getImagesPath(any())).willReturn(list);
		
		// when
		final MvcResult result = mvc.perform(post("/images/path")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk()) // 200 확인
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		// then
		log.info(content);		
	}	
}
