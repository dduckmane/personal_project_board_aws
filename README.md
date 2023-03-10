# ๐. ๋ง์ง ์ปค๋ฎค๋ํฐ ์ฌ์ดํธ 

- Demo : http://ec2-43-201-132-89.ap-northeast-2.compute.amazonaws.com

<img width="500" alt="แแณแแณแแตแซแแฃแบ 2023-01-03 แแฉแแฎ 12 40 14" src="https://user-images.githubusercontent.com/108928206/210297422-8044a640-38cb-4647-a7db-ece79a2893e8.png">

## ๋ชฉ์ฐจ

[ERD](#erd)

[1. ์ค๋ช ๋ฐ ๋ชฉ์ ](#์ค๋ช-๋ฐ-๋ชฉ์ )

[2. ์ฌ์ฉ ๊ธฐ์ ](#์ฌ์ฉ-๊ธฐ์ )

[3. Advanced Feature](#advanced-feature)

[4. ๊ฐ์  ์ฌํญ](#๊ฐ์ -์ฌํญ)

## ERD

<img width="966" alt="แแณแแณแแตแซแแฃแบ 2023-01-03 แแฉแแฎ 3 48 41" src="https://user-images.githubusercontent.com/108928206/210311258-34496252-5668-481e-98d9-268dc61c7a24.png">

## ์ค๋ช ๋ฐ ๋ชฉ์ 

### 1) ์ค๋ช: ์ฌ๋๋ค์ด ํธ๋ฆฌํ๊ฒ ๋ง์ง์ ์ฐพ์ ์ ์๋๋ก ๋์์ฃผ๊ณ  ์๋ก ์ํตํ๋ ์ปค๋ฎค๋ํฐ ์ฌ์ดํธ์๋๋ค.
### 2) ๋ชฉ์ 
- ๋น์ง๋์ค์ ์ธ ๋ชฉ์ ๋ณด๋ค๋ ๊ฐ์ธ ํ์ต ๋ชฉํ์ ๊ฐ์ธ ํ๋ก์ ํธ ์๋๋ค.
- ๋ง์ถค ์ถ์ฒ ์๊ณ ๋ฆฌ์ฆ์ ๊ฐ๋ฐํ๋ค.
- JavaScript ๋ฅผ ์ด์ฉํ์ฌ ์๊ฒฉ API ํธ์ถ์ ํ๋ค.
- Bootstrap ์ grid ์์คํ์ ์ฌ์ฉํ๋ค.
- DDD ๋ฐฉ์์ ์ ์ฉํ๋ค.
- ๊ฒ์ ์ ๋ณด ์์ง๊ณผ ๋ฆฌ์คํธ ํ์ด์ง์ Adapter ํจํด์ ์ ์ฉํ๋ค.
- ์ฐ ๊ธฐ๋ฅ์ ๊ฐ๋ฐํ๋ค.
- Custom File Utils ๋ฅผ ์ ์ํ๋ค.
- @PostConstruct ์ @ModelAttribute ๋ก ๋ถํ์ํ ๋ฆฌ์์ค ์๋ชจ ๋ฐฉ์ง
- ๋ฐฐํฌ ์ ์คํฌ๋ฆฝํธ๋ฅผ ์์ฑํ๋ค.

## ์ฌ์ฉ ๊ธฐ์ 
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
### 7) Build ์๋ํ ๋๊ตฌ
  - Gradle 7.6
### 8) Test
  - junit5, AssertJ Library

## Advanced Feature
### 1) ๋ง์ถค ์ถ์ฒ ์๊ณ ๋ฆฌ์ฆ ๊ฐ๋ฐ
  
  - SearchInfo Entity๋ฅผ ์์ฑ 
    <details>
    <summary>์ฝ๋ ๋งํฌ</summary>
    <div markdown="1">
    https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/SearchInfo.java
    </div>
    </details>
    
    : ํ์์ ๊ฒ์ ์ ๋ณด๋ค์ ๊ฐ๊ฐ์ ์ข๋ฅ๋ณ๋ก ์๋ฒ ๋๋ ํ์์ผ๋ก Search Info ํ์ด๋ธ์ ์ ์ฅ๋๋ค.
  - AddCnt ๋ผ๋ adapter interface ์์ฑ

    : ํ์๋ค์ ๊ฒ์ ๋ด์ฉ ๋ฐํ์ผ๋ก ๋ด์ฉ์ ์ ์ฅํ๊ณ  ๋ด์ฉ๋ณ ๊ฒ์ ํ ์๋ฅผ ์ฆ๊ฐ์ํจ๋ค.
    <details>
    <summary>์ฝ๋ ๋งํฌ</summary>
    <div markdown="1">
        https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/searchCnt/AddCnt.java

    </div>
    </details>
    
    - ์ด๋ํฐ ์์_1 : ํ์์ด ๊ฒ์ํ ํํฐ ์กฐ๊ฑด์ ์ ์ฅํ๊ณ  ๊ฒ์ ํ ์๋ฅผ ์ ์ฅํ๋ค.
    
      <img width="700" alt="แแณแแณแแตแซแแฃแบ 2023-01-03 แแฉแแฎ 2 28 36" alt="แแณแแณแแตแซแแฃแบ 2023-01-03 แแฉแแฎ 6 47 50" src="https://user-images.githubusercontent.com/108928206/210333645-c956d8d0-92d9-49d6-b8d2-8eebf6a7d5e6.png">
        
         <details>
          <summary>์์_1 ์ฝ๋ ๋งํฌ</summary>
          <div markdown="1">
      
          https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/adapter/SearchByRegionAdapter.java

        </div>
        </details>
      
      
      
    - ์ดํํฐ ์์_2
      
      : ํ์์ ๊ฒ์ํ ๋ด์ฉ์์ ๊ณต๋ฐฑ์ ์ ๊ฑฐํ ๋จ์ด๋ค์ ๋ฆฌ์คํธ๋ก ๋ฐํํ์ฌ ๊ฒ์ํ ๋ด์ฉ๊ณผ ํ์๋ฅผ ์ ์ฅํ๋ค.
      
      <img width="714" alt="แแณแแณแแตแซแแฃแบ 2023-01-03 แแฉแแฎ 2 29 39" src="https://user-images.githubusercontent.com/108928206/210304370-905c17bf-83ed-4b08-80b4-a89fb3d7b086.png">

      
      <details>
      <summary>์์_2 ์ฝ๋ ๋งํฌ</summary>
      <div markdown="1">
        https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/member/domain/searchInfo/searchCnt/NameInfoAdd.java
      </div>
      </details>

      
  - SearchByRecommendAdapter ๋ฅผ ์์ฑ

      <details>
      <summary>์ฝ๋ ๋งํฌ</summary>
      <div markdown="1">
                   https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/adapter/SearchByRecommendAdapter.java
        
      </div>
      </details>
    - ํ์์ด ์ถ์ฒ ํ์ด์ง๋ฅผ ์์ฒญํ๋ฉด ํ์์ searchinfo ํ์ด๋ธ์ ๋ฐํ์ผ๋ก ๊ฐ board ์ ์ ์๋ฅผ ํ์ฐ
    
    - ๋์ ์ ์๋ณ๋ก ํ์ฐ์ ํ ํ์ ์์ 12๊ฐ๋ฅผ ํ์์๊ฒ ๋ณด์ฌ์ค๋ค.

### 2) JavaScript ๋ฅผ ์ด์ฉํ์ฌ ์๊ฒฉ API ํธ์ถ์ ํ๋ค.

ex) ์ธ๋ค์ผ ํธ์ถ
![แแณแแณแแตแซแแฃแบ 2023-01-01 แแฉแแฎ 10 56 25](https://user-images.githubusercontent.com/108928206/210173097-e9dafb2d-f252-44e5-a792-21d4b546fa2d.png)  

  - ์์_1: 
    - ๋ง์ง ๋ฆฌ์คํธ ํ์ด์ง์ ์ธ๋ค์ผ์ JavaScript ์ fetch๋ฅผ ์ด์ฉํ์ฌ rest api ๋ฅผ ํธ์ถํ๋ค.
    - response data ์ image ๋ฅผ blob ํ์์ผ๋ก ๋ฐ๋๋ค.
    - ๋ฆฌ์คํธ๋ค์ img ๋ถ๋ถ์ querySelectorAll๋ฅผ ์ด์ฉํด์ ์ ์ฌ๋ฐฐ์ด์ ๋ฐ์ ๋ฐฐ์ด๋ก ๋ณ๊ฒฝํ
    - src ์์ฑ๊ฐ์ ๋ณ๊ฒฝํ๋ค.
    
### 3) Bootstrap ์ grid ์์คํ์ ์ฌ์ฉํ๋ค.

<img width="507" alt="แแณแแณแแตแซแแฃแบ 2023-01-03 แแฉแแฎ 12 58 47" src="https://user-images.githubusercontent.com/108928206/210298412-7ddbae54-7884-4880-9092-541a846f923c.png">


### 4) ๋ฆฌ์คํธ ํ์ด์ง์ Adapter ํจํด์ ์ ์ฉํ์ฌ ํ์ฅ์ฑ๊ณผ ์ ์ง๋ณด์์ฑ์ ์ฆ๊ฐ์ํจ๋ค.

![image](https://user-images.githubusercontent.com/108928206/210305042-1699fd07-3a6e-48a1-aab9-23731aa4297c.png)


  - ๋ฆฌ์คํธ ํ์ด์ง์ ๋ง๋ adapter์ธ findQueryAdapter interface ์์ฑ
      <details>
      <summary>์ฝ๋ ๋งํฌ</summary>
      <div markdown="1">
                   https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/adapter/findQueryAdapter.java
        
      </div>
      </details>
  - ์นดํ๊ณ ๋ฆฌ adater, ์ฐ ๋ชฉ๋ก adapter, ์ถ์ฒ ํ์ด์ง adapter ๋ฅผ ์์ฑ

      <details>
      <summary>์ฝ๋ ๋งํฌ</summary>
      <div markdown="1">

      https://github.com/dduckmane/personal_project_board_aws/tree/master/src/main/java/com/project/board/domain/board/controller/adapter
       
      </div>
      </details>

  - adapterHandler์ธ QueryAdapterHandler ์์ฑ
  
      <details>
      <summary>์ฝ๋ ๋งํฌ</summary>
      <div markdown="1">
                   
      https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/QueryAdapterHandler.java
        
      </div>
      </details>
  
 - ๋ฆฌ์คํธ๋ฅผ ๋ด๋นํ๋ controller์ ์ ์ฉ
  
      <details>
      <summary>์ฝ๋ ๋งํฌ</summary>
      <div markdown="1">
                   https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/BoardController.java
        
      </div>
      </details>

### 5) Custom File Utils ์ ์
  
  - ๋ ์ง๋ณ ํด๋๋ฅผ ์์ฑํ๊ณ  ๊ณ ์ ํ ์ด๋ฆ์ ์์ฑํด์ฃผ๋ custom file utils ์์ฑ

      <details>
      <summary>์ฝ๋ ๋งํฌ</summary>
      <div markdown="1">
      
      (https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/global/util/FileUtils.java)
        
      </div>
      </details>


### 6) @ModelAttribute ์ @PostConstruct ๋ก ๋ถํ์ํ ๋ฆฌ์์ค ์๋ชจ ๋ฐฉ์ง

  - ๊ณ์ํด์ model ์ ๋ด๊ฒจ์ผ ํ๋ ๋ฐ์ดํฐ๋ค์ @PostConstruct๋ฅผ ์ด์ฉํ์ฌ ์์กด์ฑ ์ฃผ์์ด ์ด๋ฃจ์ด์ง ํ ์ด๊ธฐํ๋ฅผ ์์ผ์ ์ฑ๋ฅ ์ต์ ํ๋ฅผ ์ํจ๋ค.

      <details>
      <summary>์ฝ๋ ๋งํฌ_1</summary>
      <div markdown="1">
                  
      https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/init/BoardInit.java
        
      </div>
      </details>
      
       <details>
      <summary>์ฝ๋ ๋งํฌ_2</summary>
      <div markdown="1">
                  
      https://github.com/dduckmane/personal_project_board_aws/blob/master/src/main/java/com/project/board/domain/board/controller/BoardController.java
        
      </div>
      </details>
      


## ๊ฐ์  ์ฌํญ

- csrf disable
  - ์ง๊ธ ์ํ๋ฆฌ์ผ์ด์์ ์ธ์๊ธฐ๋ฐ์ธ์ฆ์ด๊ธฐ์ csrf ๊ณต๊ฒฉ์ ์์ ํ์ง ์๋ค.
  - ํ์ง๋ง csrf ๋ฅผ able ์ํ๋ก ๋๋ค๋ฉด ํด๋น ์ํ๋ฆฌ์ผ์ด์์ error ๊ฐ ๋ง์์ง๋ค.
  - ์ถํ ๊ณต๋ถ ํ ์ ์ฉ ์์ ์ด๋ค.

- git commit ๊ธฐ๋ก

  - ๊ฐ๋ฐ ๋ถ๋ถ๋ณ๋ก ์ปค๋ฐ์ ํ๊ณ  ๊ทธ์ ๋ง๋ ์ปค๋ฐ ๋ฉ์์ง๋ฅผ ์์ฑํด์ผ ํ๋ ๊ทธ๋ฌ์ง ๋ชปํ๋ค.
  - ์๋๋ ํ ํ๋ก์ ํธ ์์ํ์์ผ๋ ํ์์ด ์ฐธ์ฌํ์ง ์๋ ๋ฌธ์ ๋ก repo ๋ฅผ ๋ค์ ์์ฑํ์๋ค.
  - aws ์ง์ ๋ถ์กฑ์ผ๋ก ๋ช ๋ฒ์ด๋ ๊ณต๋ถํ๋ฉฐ ๋ค์ ์ฌ๋ฆฌ๋ ๊ณผ์ ์์ repo ๊ฐ ๋์ด ๋ฌ๋ค.

- ํจ์์ ๋ณ์๋ช

  - ํจ์์ ๋ณ์๋ช์ ๋น์ง๋์ค์ ์ผ๋ก ์ ์์ฑํ์ง ๋ชปํ๋ค.

- web ๊ณผ was ์ ๋ถ๋ฆฌ

  - ์ด๋ฒ ํ๋ก์ ํธ์ application ์์ ์ ์ ์ธ ์ฝํ์ธ ๋ฅผ ๋ด๋นํ๋ (์๋ฅผ ๋ค์ด ํ์ผ ์๋ก๋) ๋ถ๋ถ๊ณผ ๊ทธ ์ธ ๋์ ์ธ ์ฝํ์ธ ๋ฅผ ๋ด๋นํ๋ ๋ถ๋ถ์ ๋ถ๋ฆฌํ๊ณ  ์ถ์์ผ๋ ์ง์ ๋ถ์กฑ์ผ๋ก ๋ถ๋ฆฌ์ํค์ง ๋ชปํจ

