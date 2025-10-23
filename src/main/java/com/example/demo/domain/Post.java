// POST 테이블 관리 엔티티 클래스
package com.example.demo.domain;

import jakarta.persistence.*; // JPA (Java Persistence API) 에서 엔티티(Entity) 관련 어노테이션들을 불러오는 import 구문

@Entity // JAP 엔티티 선언 어노테이션 해당 클래스의 역할을 지정 (타입과 같음)
@Table(name = "posts") // 매핑 할 테이블 이름 지정
public class Post extends BaseTime {
    // extends 는 상속 받을 클래스를 의미하고 상속 받은 클래스의 모든 요소를 Post 클래스에서 사용 할 수 있게 한다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    private String author; // 작성자

    // JPA에서 기본 생성자는 protected로 두는 것을 권장
    protected Post() { }

    // 생성자
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 게시글 수정 메서드
    public void update(String title, String content) {
        // 수정 된 데이터만 반영
        if (title != null) this.title = title;
        if (content != null) this.content = content;
    }

    // Getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }

    // TODO
    // id 기준으로 데이터 가져오는 getter 생성해보기
    // JSON { title, content, author }

}
