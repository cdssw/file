package com.moim.file.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * File.java
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class File extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uuid;
	private String groupDir;
	private String orgFileNm;
	private String chgFileNm;
	private String extension;
	private Long fileSize;
	private String path;
	
	@Builder
	public File(String uuid, String groupDir, String orgFileNm, String chgFileNm, String extension, Long fileSize, String path) {
		this.uuid = uuid;
		this.groupDir = groupDir;
		this.orgFileNm = orgFileNm;
		this.chgFileNm = chgFileNm;
		this.extension = extension;
		this.fileSize = fileSize;
		this.path = path;
	}
}
