//프로젝트 메인 어플리케이션 파일

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링 부트 자동 설정 + 컴포넌트 스캔 기능
public class DemoApplication {

	public static void main(String[] args) {
		// 어플리케이션 실행 진입점
        SpringApplication.run(DemoApplication.class, args);
	}

}
