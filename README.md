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




### 3. 핵심 폴더  설명
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


- `/users/{userId}/point - GET`
    
    
    - 해당 사용자ID 의 Point 기록을 조회
    
    ex)  localhost:8080/users/1/point?sort=date,asc&size=10&page=0
    
     - Query String
    
    | Value | 필수 여부 | 설명 | 예시 |
    | --- | --- | --- | --- |
    | size | false(default 10) | 한페이지 보이는 요소 개수 | size=20 |
    | page | false(default 0) | 페이지 | page=0 |
    | sort | false(default desc) | 포인트 획득 정렬 기준 | sort=date,asc |
    
     - Response Example - 200 OK
    
    ```json
    {
        "code": "0000",
        "message": "성공",
        "result": {
            "content": [
                {
                    "point": 100,
                    "pointHistory": 100,
                    "pointType": "EVERY_DAY",
                    "acquiredAt": "2022-12-01T23:30:15"
                },
                {
                    "point": 100,
                    "pointHistory": 200,
                    "pointType": "EVERY_DAY",
                    "acquiredAt": "2022-12-02T23:30:15"
                },
                {
                    "point": 300,
                    "pointHistory": 500,
                    "pointType": "THREE_DAYS",
                    "acquiredAt": "2022-12-03T23:30:49"
                },
                {
                    "point": 100,
                    "pointHistory": 600,
                    "pointType": "EVERY_DAY",
                    "acquiredAt": "2022-12-03T23:30:49"
                }
            ],
            "pageable": {
                "sort": {
                    "empty": false,
                    "sorted": true,
                    "unsorted": false
                },
                "offset": 0,
                "pageNumber": 0,
                "pageSize": 10,
                "paged": true,
                "unpaged": false
            },
            "last": true,
            "totalPages": 1,
            "totalElements": 4,
            "first": true,
            "size": 10,
            "number": 0,
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "numberOfElements": 4,
            "empty": false
        }
    }
    ```
    
    | Value | 설명 |
    | --- | --- |
    | point | 획득 포인트 |
    | pointHistory | 획득 후 사용자 포인트 |
    | pointType | 획득 포인트 enum 타입 |
    | acquiredAt | 획득 시간 |


### 5. Database ERD



### 6. 코드값



### 7. 부가 설명

