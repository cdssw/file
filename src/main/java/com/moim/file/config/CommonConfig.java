package com.moim.file.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CommonConfig.java
 * 
 * @author cdssw
 * @since 2020. 9. 12.
 * @description 공통 Config  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 9. 12.    cdssw            최초 생성
 * </pre>
 */
@Configuration
public class CommonConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
