# 😊. 맛집 커뮤니티 사이트 

- Demo : http://matjip.online
- 시연 영상 : https://youtu.be/1hrabt-Bwqk

<img width="500" alt="스크린샷 2023-01-03 오후 12 40 14" src="https://user-images.githubusercontent.com/108928206/210297422-8044a640-38cb-4647-a7db-ece79a2893e8.png">

## 목차

[ERD](#erd)

[1. 설명 및 목적](#설명-및-목적)

[2. 사용 기술](#사용-기술)

[3. Advanced Feature](#advanced-feature)

[4. 개선 사항](#개선-사항)

## ERD

<img width="958" alt="image" src="https://user-images.githubusercontent.com/108928206/225642540-6594f43c-c5c3-4925-92a1-9761376663b0.png">

ErdCloud : https://www.erdcloud.com/d/Y7epHbrf8qXS7qFtb

## 설명 및 목적

### 1) 설명: 사용자들이 서로 맛집을 공유하고 사용자의 검색기록을 바탕으로 유저에게 맞춤 추천을 해주는 커뮤니티 사이트 입니다.
### 2) 목적
- 맞춤 추천 알고리즘을 개발한다.
- JavaScript 를 이용하여 원격 API 호출을 한다.
- 소셜 로그인 기능 과 찜 기능을 개발
- Bootstrap 의 grid 시스템을 사용한다.
- DDD 방식을 적용한다.
- 검색 정보 수집과 리스트 페이지에 Adapter 패턴을 적용한다.
- Custom File Utils 를 제작한다.
- @PostConstruct 와 @ModelAttribute 로 불필요하 리소스 소모 방지
- 배포 쉘 스크립트를 작성한다.

## 사용 기술
### 1) Back-End
  - Java 11, Spring Framework 2.7.7, Spring boot, RESTFUL API, QueryDsl 5.0.0, Spring Data Jpa 2.7.6, JPQL, JSTL, Spring Security 5.7.6
### 2) Server
  - Apache, Apache Tomcat 9.0.7, AWS EC2
### 3) DB
 - MySQL, MariaDB 3.0.6, H2 2.1.214
### 4) Front-End
  - HTML5, CSS3, JavaScript, BootStrap 5.0.2
### 5) Tools
  - IntelliJ IDEA, Post Man, putty
### 6) OS
  - Mac OS
### 7) Build 자동화 도구
  - Gradle 7.6
### 8) Test
  - junit5, AssertJ Library

## 서버구축

1. AWS-EC2에 인스턴스를 생성 Apache - tomcat 과 git 을 설치
2. server.xml 에서 포트번호를 기본 http 포트번호로 수정
    
    <img width="551" alt="image" src="https://user-images.githubusercontent.com/108928206/225644506-d3a252e2-e78b-40a8-9c16-07fa1b19ff85.png">

1. git 을 통해 프로젝트를 불러와 war 파일을 생성
2. tomcat 의 webapps 디렉토리로 이동시켜 배포
    
  <img width="593" alt="image" src="https://user-images.githubusercontent.com/108928206/225644607-94edb8e4-c52f-4af1-8fb6-1233dffe5dd3.png">

- DB관련 내용은 보안상 기재 x

## Advanced Feature
### 1) 맞춤 추천 알고리즘 개발
  
  - SearchInfo Entity를 생성 
    <details>
    <summary>코드 링크</summary>
    <div markdown="1">
    https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/SearchInfo.java
    </div>
    </details>
    
    : 회원의 검색 정보들을 각각의 종류별로 임베디드 타입으로 Search Info 테이블에 저장된다.
  - AddCnt 라는 adapter interface 생성

    : 회원들의 검색 내용 바탕으로 내용을 저장하고 내용별 검색 횟 수를 증가시킨다.
    <details>
    <summary>코드 링크</summary>
    <div markdown="1">
        https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/searchCnt/AddCnt.java

    </div>
    </details>
    
    - 어뎁터 예시_1 : 회원이 검색한 필터 조건을 저장하고 검색 횟 수를 저장한다.
    
      <img width="700" alt="스크린샷 2023-01-03 오후 2 28 36" alt="스크린샷 2023-01-03 오후 6 47 50" src="https://user-images.githubusercontent.com/108928206/210333645-c956d8d0-92d9-49d6-b8d2-8eebf6a7d5e6.png">
        
         <details>
          <summary>예시_1 코드 링크</summary>
          <div markdown="1">
      
          https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/adapter/SearchByRegionAdapter.java

        </div>
        </details>
      
      
      
    - 어텝터 예시_2
      
      : 회원의 검색한 내용에서 공백을 제거한 단어들을 리스트로 반환하여 검색한 내용과 횟수를 저장한다.
      
      <img width="714" alt="스크린샷 2023-01-03 오후 2 29 39" src="https://user-images.githubusercontent.com/108928206/210304370-905c17bf-83ed-4b08-80b4-a89fb3d7b086.png">

      
      <details>
      <summary>예시_2 코드 링크</summary>
      <div markdown="1">
        https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/searchCnt/NameInfoAdd.java
      </div>
      </details>

      
  - SearchByRecommendAdapter 를 생성

      <details>
      <summary>코드 링크</summary>
      <div markdown="1">
                   https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/adapter/SearchByRecommendAdapter.java
        
      </div>
      </details>
    - 회원이 추천 페이지를 요청하면 회원의 searchinfo 테이블을 바탕으로 각 board 의 점수를 환산
    
    - 높은 점수별로 환산을 한 후에 상위 12개를 회원에게 보여준다.

### 2) JavaScript 를 이용하여 원격 API 호출을 한다.

ex) 썸네일 호출
![스크린샷 2023-01-01 오후 10 56 25](https://user-images.githubusercontent.com/108928206/210173097-e9dafb2d-f252-44e5-a792-21d4b546fa2d.png)  

  - 예시_1: 
    - 맛집 리스트 페이지에 썸네일을 JavaScript 의 fetch를 이용하여 rest api 를 호출한다.
    - response data 의 image 를 blob 타입으로 받는다.
    - 리스트들의 img 부분을 querySelectorAll를 이용해서 유사배열을 받아 배열로 변경후
    - src 속성값을 변경한다.
    
### 3) Bootstrap 의 grid 시스템을 사용한다.

<img width="507" alt="스크린샷 2023-01-03 오후 12 58 47" src="https://user-images.githubusercontent.com/108928206/210298412-7ddbae54-7884-4880-9092-541a846f923c.png">


### 4) 리스트 페이지에 Adapter 패턴을 적용하여 확장성과 유지보수성을 증가시킨다.

![image](https://user-images.githubusercontent.com/108928206/210305042-1699fd07-3a6e-48a1-aab9-23731aa4297c.png)


  - 리스트 페이지에 맞는 adapter인 findQueryAdapter interface 생성
      <details>
      <summary>코드 링크</summary>
      <div markdown="1">
                   https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/adapter/findQueryAdapter.java
        
      </div>
      </details>
  - 카테고리 adater, 찜 목록 adapter, 추천 페이지 adapter 를 생성

      <details>
      <summary>코드 링크</summary>
      <div markdown="1">

      https://github.com/dduckmane/personal_project_board_aws/tree/master/src/main/java/com/project/board/domain/board/controller/adapter
       
      </div>
      </details>

  - adapterHandler인 QueryAdapterHandler 생성
  
      <details>
      <summary>코드 링크</summary>
      <div markdown="1">
                   
      https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/QueryAdapterHandler.java
        
      </div>
      </details>
  
 - 리스트를 담당하는 controller에 적용
  
      <details>
      <summary>코드 링크</summary>
      <div markdown="1">
                   https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/BoardController.java
        
      </div>
      </details>

### 5) Custom File Utils 제작
  
  - 날짜별 폴더를 생성하고 고유한 이름을 생성해주는 custom file utils 생성

      <details>
      <summary>코드 링크</summary>
      <div markdown="1">
      
      (https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/global/util/FileUtils.java)
        
      </div>
      </details>


### 6) @ModelAttribute 와 @PostConstruct 로 불필요한 리소스 소모 방지

  - 계속해서 model 에 담겨야 하는 데이터들을 @PostConstruct를 이용하여 의존성 주입이 이루어진 후 초기화를 시켜서 성능 최적화를 시킨다.

      <details>
      <summary>코드 링크_1</summary>
      <div markdown="1">
                  
      https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/init/BoardInit.java
        
      </div>
      </details>
      
       <details>
      <summary>코드 링크_2</summary>
      <div markdown="1">
                  
      https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/BoardController.java
        
      </div>
      </details>

### 7) 소셜 로그인 기능을 구현
설명: 네이버, 카카오, 구글 로그인 기능을 구현한다.

- 구글은 https에서만 가능하기에 로컬에서만 구현
- 네이버는 검수 요청을 보냈으나 포트폴리오 용도라는 이유로 검수 거부
- 카카오만 현재 가능

참고 url : https://github.com/codingspecialist/-Springboot-Security-OAuth2.0-V3

1) `.antMatchers("/user/**").authenticated().anyRequest().permitAll()`

: 로그인 한 유저만 볼 수 있는 url 은 /user/** 로 설정

2) Authorization Code Grant Type 방식로 진행


### 8) 배포 쉘 스크립트를 작성
      
<img width="1439" alt="image" src="https://user-images.githubusercontent.com/108928206/225643872-9218c569-b1f2-403e-9d27-05b83e42957d.png">



## 개선 사항

- csrf disable
  - 지금 에플리케이션은 세션기반인증이기에 csrf 공격에 안전하지 않다.
  - 하지만 csrf 를 able 상태로 둔다면 해당 에플리케이션에 error 가 많아진다.
  - 추후 공부 후 적용 예정이다.

- git commit 기록

  - 개발 부분별로 커밋을 하고 그에 맞는 커밋 메시지를 작성해야 하나 그러지 못했다.
  - 원래는 팀 프로젝트 시작하였으나 팀원이 참여하지 않는 문제로 repo 를 다시 생성하였다.
  - aws 지식 부족으로 몇 번이나 공부하며 다시 올리는 과정에서 repo 가 늘어 났다.

- 함수와 변수명

  - 함수와 변수명을 비지니스적으로 잘 작성하지 못했다.

- web 과 was 의 분리

  - 이번 프로젝트의 application 에서 정적인 콘텐츠를 담당하는 (예를 들어 파일 업로드) 부분과 그 외 동적인 콘텐츠를 담당하는 부분을 분리하고 싶었으나 지식 부족으로 분리시키지 못함

