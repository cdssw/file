package com.moim.file.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

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
@RunWith(PowerMockRunner.class)
public class FileServiceImplTest {

	// 테스트 하고자 하는 class
	private FileServiceImpl fileServiceImpl;
	
	@InjectMocks
	private FileServiceImpl fileServiceImpl2;

	// 해당 클래스를 테스트 하지 않음
	@Mock private FileRepository fileRepository;
	@Mock private CommonComponent commonComponent;
	
	@Before
	public void setUp() {
		ModelMapper modelMapper = new ModelMapper();
		fileServiceImpl = new FileServiceImpl(commonComponent, fileRepository, modelMapper);
		
		CommonComponent c = new CommonComponent();
		fileServiceImpl2 = new FileServiceImpl(c, fileRepository, modelMapper);
		ReflectionTestUtils.setField(fileServiceImpl2, "basePath", "/volume1/docker/file");
	}
	
	@Test
	public void testUploadAvatar() {
		// given
		String fileNm = "test_file.jpg";
		MockMultipartFile file = new MockMultipartFile("file", fileNm, "text/plain", "file content".getBytes());
		File info = File.builder().path("path").chgFileNm(UUID.randomUUID().toString()).build();
		given(commonComponent.saveFile(any(), any())).willReturn(info);
		
		// when
		FileDto.Res res = fileServiceImpl.uploadAvatar("avatar", file);
		
		// then
		assertEquals(res.getFilePath(), String.format("%s/%s", info.getPath(), info.getChgFileNm()));
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
	
	@Test
	public void testGetImage() throws Exception {
		// given
		File info = File.builder().orgFileNm("org.jpg").chgFileNm("chg.jpg").path("test").build();
		given(fileRepository.findById(anyLong())).willReturn(Optional.of(info));
		
		RandomAccessFile r = mock(RandomAccessFile.class);
		PowerMockito.whenNew(RandomAccessFile.class).withArguments(anyString(), anyString()).thenReturn(r);
		
		// when
		FileDto.InfoRes res = fileServiceImpl2.getImage(1L);
		
		// then
		assertEquals(res.getOrgFileNm(), "org.jpg");
	}
	
	@Test
	public void testGetImagesPath() {
		// given
		List<Long> fileList = new ArrayList<>();
		fileList.add(1L);
		fileList.add(2L);
		fileList.add(3L);
		FileDto.ListReq dto = FileDto.ListReq.builder().fileList(fileList).build();
		
		File file1 = mock(File.class);
		File file2 = mock(File.class);
		File file3 = mock(File.class);
		given(file1.getId()).willReturn(1L);
		given(file2.getId()).willReturn(2L);
		given(file3.getId()).willReturn(3L);
		given(fileRepository.findByIdIn(any())).willReturn(Arrays.asList(file1, file2, file3));
		
		// when
		List<FileDto.ListRes> res = fileServiceImpl.getImagesPath(dto);
		
		// then
		assertEquals(res.size(), 3);
		
	}
}
