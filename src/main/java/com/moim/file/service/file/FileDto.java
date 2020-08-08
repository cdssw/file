package com.moim.file.service.file;

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
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static abstract class BaseRes {
		private String filePath;
	}
	
	// 결과 DTO
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder // 부모의 생성자에 대하여 builder를 사용할수 있게 해준다.
	public static class Res extends BaseRes {

	}
}
