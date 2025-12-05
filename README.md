# Instagram Clone Project (Spring Boot)

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.1-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.3.1-green)
![JPA / Hibernate](https://img.shields.io/badge/JPA%20%2F%20Hibernate-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-blueviolet)
![Gradle](https://img.shields.io/badge/Gradle-8.8-yellow)

---

이 프로젝트는 Spring Boot를 사용하여 인스타그램의 핵심 기능을 클론 코딩한 웹 애플리케이션입니다. 
사용자 인증부터 게시물 관리, 팔로우, 좋아요, 댓글, 실시간 피드 및 검색 기능까지 다양한 웹 기술을 종합적으로 학습하고 구현하는 것을 목표로 합니다.

## 📖 주요 기능

- **👤 사용자 인증**: 회원가입, 로그인, 로그아웃 (Spring Security)
- **🖼️ 게시물 관리**: 게시물 생성(이미지 포함), 조회, 상세 보기
- **👥 소셜 기능**:
    - 다른 사용자 팔로우/언팔로우
    - 게시물 좋아요 토글
    - 게시물 댓글 작성 및 조회
- **👤 프로필**:
    - 사용자 프로필 페이지 (작성한 게시물, 팔로워/팔로잉 수)
    - 프로필 정보 및 프로필 사진 수정
- **🌐 피드 및 탐색**:
    - 팔로우한 사용자들의 게시물을 볼 수 있는 메인 피드 (무한 스크롤)
    - 모든 게시물을 탐색할 수 있는 탐색 페이지 (무한 스크롤)
- **🔍 검색**: 사용자 이름 또는 게시물 내용으로 검색
- **⚙️ 예외 처리**: 커스텀 예외 및 `GlobalExceptionHandler`를 통한 일관된 오류 처리
- **🎨 프론트엔드**: Thymeleaf와 Bootstrap을 사용한 동적 웹 페이지 구현

## 🛠️ 기술 스택

| 구분 | 기술 | 설명 |
| --- | --- | --- |
| **Backend** | `Java 21` | 프로젝트의 주요 개발 언어 |
| | `Spring Boot 3.3.1` | 애플리케이션의 핵심 프레임워크 |
| | `Spring Data JPA` | Hibernate를 사용하여 데이터베이스와 상호작용 |
| | `Spring Security` | 사용자 인증 및 인가 처리 |
| **Frontend**| `Thymeleaf` | 서버 사이드 템플릿 엔진 |
| | `JavaScript` | 무한 스크롤 등 동적 UI 구현 |
| | `Bootstrap 5` | 반응형 웹 디자인 및 UI 컴포넌트 |
| **Database**| `MySQL` | 주요 데이터 저장소 |
| **Build** | `Gradle` | 의존성 관리 및 빌드 자동화 |

## 📊 데이터베이스 ERD

![image](/src/main/resources/static/image/model.png)

## 🚀 시작하기

### 1. 프로젝트 클론

```bash
git clone https://github.com/qqqkyj/Instagram-Clone.git
cd instagram
```

### 2. 데이터베이스 설정

1. MySQL 데이터베이스를 설치하고, `instagram` 스키마를 생성합니다.

    ```sql
    CREATE DATABASE instagram
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
    ```

2. `src/main/resources/application.properties` 파일을 열고 본인의 MySQL 환경에 맞게 수정합니다.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/instagram?...
    spring.datasource.username=root
    spring.datasource.password=YOUR_MYSQL_PASSWORD
    ```

### 3. 애플리케이션 실행

IntelliJ 또는 선호하는 IDE에서 프로젝트를 열고 `InstagramApplication.java` 파일을 실행합니다. 애플리케이션이 정상적으로 실행되면 `localhost:8080`으로 접속할 수 있습니다.

## 📂 디렉토리 구조

```
src
├── main
│   ├── java/com/example/instagram
│   │   ├── config          # SecurityConfig, WebConfig 등 설정 파일
│   │   ├── controller      # MVC 컨트롤러 및 REST API 컨트롤러
│   │   ├── dto             # 데이터 전송 객체 (Request/Response)
│   │   ├── entity          # JPA 엔티티
│   │   ├── exception       # 커스텀 예외 및 핸들러
│   │   ├── repository      # Spring Data JPA 리포지토리
│   │   ├── security        # CustomUserDetails 등 Spring Security 관련 클래스
│   │   └── service         # 비즈니스 로직 서비스
│   └── resources
│       ├── static          # (사용하지 않음, 업로드는 외부 폴더)
│       ├── templates       # Thymeleaf 템플릿 (html)
│       │   ├── auth, error, layout, post, profile, user ...
│       └── application.properties  # 애플리케이션 설정
└── test                    # 테스트 코드
```

---


