package com.moim.file.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Profile.java
 * 
 * @author cdssw
 * @since 2020. 8. 2.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 8. 2.    cdssw            최초 생성
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Profile extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String orgFileNm;
	private String chgFileNm;
	private Long fileSize;
	private String path;
	
	@Builder
	public Profile(String orgFileNm, String chgFileNm, Long fileSize, String path) {
		this.orgFileNm = orgFileNm;
		this.chgFileNm = chgFileNm;
		this.fileSize = fileSize;
		this.path = path;
	}
}
