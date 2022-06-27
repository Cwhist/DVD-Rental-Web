# DVD-Rental-Web

### 1. 개요

  PostgreSQL의 Sample DB인 *dvdrental을 기반으로 만든 영화 대여 서비스를 제공하는 웹 사이트
  
  *dvdrental DB : https://www.postgresqltutorial.com/postgresql-sample-database/
  
### 2. 기술 스택

  **Back-end** &rarr; Spring Boot + Spring Data JPA + REST API + PostgreSQL DB
  
  **Front-end** &rarr; React + TypeScript
  
  
### 3. 기능

| 기능                                              | 상세설명                               |
|--------------------------------------------------|------------------------------------|
| 검색                                             |  배우 이름으로 배우 검색           |
|                                                  |  검색된 배우의 출연작품(영화) 보기  |
|                                                  |  영화 제목으로 검색               |
| 대여 가능 여부 표시                               |   영화별 대여 가능 여부 표시        |
|                                                  |  수납장 번호별 대여 가능 여부 표시 |
| 영화 대여                                         | 대여 가능한 수납장을 눌러 영화 대여 |
|                                                  |  대여가 완료되면 반납시 입력할 대여번호 안내 |
| 영화 반납                                         |  대여번호를 입력하여 반납             |
