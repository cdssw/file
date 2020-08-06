package com.moim.file.controller;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.moim.file.service.file.FileService;

/**
 * BaseControllerTest.java
 * 
 * @author cdssw
 * @since 2020. 8. 6.
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 8. 6.    cdssw            최초 생성
 * </pre>
 */
public abstract class BaseControllerTest {

	// service 기능을 테스트 하는것이 아님
	// controller 테스트시 필요한 service mock 정의
	@MockBean protected FileService fileService;
}
