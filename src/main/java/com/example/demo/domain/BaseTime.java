package com.example.demo.domain;

import jakarta.persistence.*; // JPA (Java Persistence API) 에서 엔티티(Entity) 관련 어노테이션들을 불러오는 import 구문
import java.time.LocalDateTime;

@MappedSuperclass // 엔티티(테이블)들이 상속 받을 공통 필드 클래스임을 명시
public abstract class BaseTime {
    // class에 abstract 를 선언하면 추상화 되어 물리적으로 독립 된 엔티티가 되지 않는다.
    // 즉 물리적인 DB 테이블을 구성하거나 동작을 수행하지 않는 다른 클래스나 함수에서 가져다 쓸 수 있는 기능적 클래스가 된다

    // 사용할 변수(컬럼) 선언
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성 시각

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 수정 시각

    @PrePersist // 엔티티(테이블에) 저장 전 실행
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt; // 새로 등록 할 땐 생성 시각과 동일한 시간 상속
    }

    @PreUpdate // 엔티티(테이블에) 업데이트 전 실행
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now(); // 업데이트시엔 현재 시각 등록
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
