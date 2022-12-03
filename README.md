# mss-2022(무신사 사전과제 - 2022)

### 1. 사용 방법
1. Root Directory로 이동
1. docker-compose 설치
2. docker-compose 명령어 실행(`docker-compose up -d`)
3. 이미지 다운 => 컨테이너가 실행되기 까지 대기 
4. gradle 명령어를 사용하기 위해 설치
6. gradle 명령어 실행(`gradle bootRun`)



### 2. 사용 기술 스택
1. SpringBoot v2.6
2. Mysql v5.7
3. JPA v5




### 3. 구조 설명
```
src
 ┣ main
 ┃ ┣ java
 ┃ ┃ ┗ org
 ┃ ┃ ┃ ┗ exam
 ┃ ┃ ┃ ┃ ┣ common
 ┃ ┃ ┃ ┃ ┃ ┣ config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ DatabaseConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ QuerydslConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ SchedulerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ constants
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ SortConstants.java
 ┃ ┃ ┃ ┃ ┃ ┣ exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ point
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ AlreadyGotTodayPointException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ NoEnoughTodayPointException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ NoExistUserException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ BaseException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ErrorType.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┣ scheduler
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ PointTask.java
 ┃ ┃ ┃ ┃ ┃ ┣ singleton
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ SingletonPointState.java
 ┃ ┃ ┃ ┃ ┃ ┗ util
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ DateTimeUtil.java
 ┃ ┃ ┃ ┃ ┣ controller
 ┃ ┃ ┃ ┃ ┃ ┣ dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ CustomResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ PointDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ PointController.java
 ┃ ┃ ┃ ┃ ┃ ┗ UserController.java
 ┃ ┃ ┃ ┃ ┣ domain
 ┃ ┃ ┃ ┃ ┃ ┣ type
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ PointType.java
 ┃ ┃ ┃ ┃ ┃ ┣ PointLog.java
 ┃ ┃ ┃ ┃ ┃ ┗ User.java
 ┃ ┃ ┃ ┃ ┣ repository
 ┃ ┃ ┃ ┃ ┃ ┣ custom
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ PointLogCustomRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ PointLogCustomRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ PointLogLogRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ UserRepository.java
 ┃ ┃ ┃ ┃ ┣ service
 ┃ ┃ ┃ ┃ ┃ ┣ Impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ PointServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┣ PointService.java
 ┃ ┃ ┃ ┃ ┃ ┗ UserService.java
 ┃ ┃ ┃ ┃ ┗ Application.java
 ┃ ┗ resources
 ┃ ┃ ┗ application.yml
 ┗ test
 ┃ ┣ java
 ┃ ┗ resources
 mysql-init.d
 ┗ init.sql
```


### 4. API 명세서


### 5. 에러 코드



### 6. 부가 설명

