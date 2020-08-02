package com.moim.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moim.file.entity.Profile;

/**
 * ProfileRepository.java
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
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}