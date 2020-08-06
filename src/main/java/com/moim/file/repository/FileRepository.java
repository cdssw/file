package com.moim.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moim.file.entity.File;

/**
 * FileRepository.java
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
public interface FileRepository extends JpaRepository<File, Long> {

}
