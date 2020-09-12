package com.moim.file.service.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.moim.file.component.CommonComponent;
import com.moim.file.entity.File;
import com.moim.file.repository.FileRepository;
import com.moim.file.service.file.FileDto.ImageRes;
import com.moim.file.service.file.FileDto.InfoRes;
import com.moim.file.service.file.FileDto.ListReq;
import com.moim.file.service.file.FileDto.ListRes;
import com.moim.file.service.file.FileDto.Res;

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
@Service
public class FileServiceImpl implements FileService {

	@Value("${file.basepath}")
	private String basePath;
	
	private ModelMapper modelMapper;
	private CommonComponent commonComponent;
	private FileRepository fileRepository;
	
	@Autowired
	public FileServiceImpl(CommonComponent commonComponent, FileRepository fileRepository, ModelMapper modelMapper) {
		this.commonComponent = commonComponent;
		this.fileRepository = fileRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public Res uploadAvatar(String group, MultipartFile file) {
		
		// 파일저장
		File info = commonComponent.saveFile(group, file);
		
		// DB 저장
		fileRepository.save(info);
		
		return Res.builder().filePath(String.format("%s/%s", info.getPath(), info.getChgFileNm())).build();
	}

	@Override
	public ImageRes uploadImage(String group, MultipartFile file) {
		
		// 파일저장
		File info = commonComponent.saveFile(group, file);
		
		// DB 저장
		fileRepository.save(info);
		
		return ImageRes.builder().id(info.getId()).filePath(String.format("%s/%s", info.getPath(), info.getChgFileNm())).build();
	}

	@Override
	public InfoRes getImage(Long fileId) throws IOException {
		File file = commonComponent.findById(fileRepository, fileId, File.class);
		RandomAccessFile f = new RandomAccessFile(String.format("%s/%s/%s", basePath, file.getPath(), file.getChgFileNm()), "r");
		byte[] data = new byte[(int)f.length()];
		f.readFully(data);

		InfoRes res = InfoRes.builder().orgFileNm(file.getOrgFileNm()).data(data).build();
		return res;
	}

	@Override
	public List<ListRes> getImages(ListReq dto) {
		return fileRepository.findByIdIn(dto.getFileList()).stream().map(m -> modelMapper.map(m, FileDto.ListRes.class)).collect(Collectors.toList());
	}
}
