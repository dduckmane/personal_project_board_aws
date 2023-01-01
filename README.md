# 맛집 커뮤니티 사이트
- Demo : http://ec2-43-201-132-89.ap-northeast-2.compute.amazonaws.com

## 목차

1. [설명 및 목적](#설명-및-목적)
2. [사용 기술](#사용-기술)
3. [Advanced Feature](#Advanced-Feature)
4. [개선 사항](#개선-사항)


## 1. 설명 및 목적
### 1) 설명: 사람들이 편리하게 맛집을 찾을 수 있도록 도와주는 커뮤니티 사이트입니다.
### 2) 목적
- 비지니스적인 목적보다는 개인 학습 목표의 개인 프로젝트 입니다.
- 맞춤 추천 알고리즘을 개발한다.
- JavaScript 를 이용하여 원격 API 호출을 한다.
- Bootstrap 의 grid 시스템을 사용한다.
- DDD 방식을 적용한다.
- 검색 정보 수집과 리스트 페이지에 Adapter 패턴을 적용한다.
- 찜 기능을 개발한다.



## 2. 사용 기술
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

## 3. Advanced Feature
### 1) 맞춤 추천 알고리즘 개발
  
  - SearchInfo Entity를 생성 
    https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/SearchInfo.java
    
    : 회원의 검색 정보들을 각각의 종류별로 임베디드 타입으로 Search Info 테이블에 저장된다.
  - AddCnt 라는 adapter interface 생성
    https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/searchCnt/AddCnt.java
    : 회원이 검색한 정보를 바탕으로 횟수와 내용을 저장한다.
    
    - 어뎁터 예시_1
      https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/searchCnt/TagCnt.java  
    - 어텝터 예시_2
      : 회원의 검색한 내용을 리스트로 반환하여 검색한 횟수를 저장한다.
      https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/searchCnt/NameInfoAdd.java
      
  - SearchByRecommendAdapter 를 생성
  
    - 회원이 추천 페이지를 요청하면 회원의 searchinfo 테이블을 바탕으로 각 board 의 점수를 환산
    - 높은 점수별로 환산을 한 후에 상위 12개를 회원에게 보여준다.
    
    
  
  
  
  
  


## 4. 개선 사항
