package com.moim.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JpaAuditingConfig.java
 * 
 * @author cdssw
 * @since 2020. 8. 2.
 * @description JPA LocalDateTime 자동 업데이트, BaseTimeEntity 참고
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 8. 2.    cdssw            최초 생성
 * </pre>
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

}
