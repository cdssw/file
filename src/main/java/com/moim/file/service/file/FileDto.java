package com.moim.file.service.file;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * FileDto.java
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
public class FileDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class ListReq {
		
		private List<Long> fileList;
		
		@Builder
		public ListReq(List<Long> fileList) {
			this.fileList = fileList;
		}
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static abstract class BaseRes {
	}
	
	// 결과 DTO
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder // 부모의 생성자에 대하여 builder를 사용할수 있게 해준다.
	public static class Res extends BaseRes {
		private String filePath;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static class ImageRes extends BaseRes {
		private Long id;
		private String filePath;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static class InfoRes extends BaseRes {
		private Long id;
		private String orgFileNm;
		private byte[] data;
	}	
	
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static class ListRes extends BaseRes {
		private Long id;
		private String orgFileNm;
		private String chgFileNm;
		private String path;
	}
}
