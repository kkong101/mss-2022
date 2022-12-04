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




### 3. 핵심 폴더 구조
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
    
    ex)  **localhost:8080/users/1/point?sort=date,asc&size=10&page=0**
    
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


---

- `/users/{userId}/attendance - POST`
    
    
    - 해당 사용자 출석 포인트 획득 요청
    
    ex)  **localhost:8080/users/1/attendance**
    
     - 필요 Body 값
    
    `none`
    
     - Response Example - 200 OK
    
    ```json
    {
        "code": "0000",
        "message": "성공",
        "result": {
            "pointType": [
                "THREE_DAYS",
                "EVERY_DAY"
            ],
            "currentPoint": 1000
        }
    }
    ```
    
    | Value | 설명 |
    | --- | --- |
    | pointType | 획득 포인트 enum 타입 |
    | currentPoint | 획득 후 해당 유저의 현재 포인트 |
    
     - Response Example - 200 OK
    
    ```json
    {
        "code": "P001",
        "message": "오늘의 출석이 마감되었습니다."
    }
    ```
    
     - Response Example - 200 OK
    
    ```json
    {
        "code": "P002",
        "message": "이미 오늘 출석을 하였습니다."
    }
    ```
    

---
- `/points/history - GET`
    
    
    - 해당 날짜의 포인트 기록을 조회
    
    ex)  **localhost:8080/points/history?date=2022-12-03&size=1&sort=date,desc**
    
     - Query String
    
    | Value | 필수 여부 | 설명 | 예시 |
    | --- | --- | --- | --- |
    | size | false(default 10) | 한페이지 보이는 요소 개수 | size=20 |
    | page | false(default 0) | 페이지 | page=0 |
    | sort | false(default desc) | 포인트 획득 정렬 기준 | sort=date,asc |
    | date | false(default today) | 조회하고자 하는 날짜 | date=2022-12-03 |
    
     - Response Example - 200 OK
    
    ```json
    {
        "code": "0000",
        "message": "성공",
        "result": {
            "content": [
                {
                    "userName": "user1",
                    "point": 300,
                    "pointHistory": 900,
                    "pointType": "THREE_DAYS",
                    "acquiredAt": "2022-12-03T23:46:15"
                },
                {
                    "userName": "user1",
                    "point": 100,
                    "pointHistory": 1000,
                    "pointType": "EVERY_DAY",
                    "acquiredAt": "2022-12-03T23:46:15"
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
    | userName | 사용자 이름 |
    | point | 획득 포인트 |
    | pointHistory | 획득 후 사용자 포인트 |
    | pointType | 획득 포인트 enum 타입 |
    | acquiredAt | 획득 시간 |




### 5. Database ERD

![캡처](/ERD_IMAGE.png)


### 6. 코드값

`에러 코드`

| Value | Message | HttpStatus |
| --- | --- | --- |
| U001 | 존재하지 않는 사용자 | 400 Bad Request |
| P001 | 오늘의 출석이 마감되었습니다 | 200 OK |
| P002 | 이미 오늘 출석을 하였습니다 | 200 OK |

`응답 예시`

```json
{
    "code": "P001",
    "message": "오늘의 출석이 마감되었습니다."
}
```

`포인트 코드`

| Value | Point | Code |
| --- | --- | --- |
| EVERY_DAY | 100 | P1 |
| THREE_DAYS | 300 | P2 |
| FIVE_DAYS | 500 | P3 |
| TEN_DAYS | 1000 | P4 |

Code 값은 DB에 저장되는 값이며, Value는 Front에 제공되는 값입니다.

`응답 예시`

```json
"pointType": [
            "THREE_DAYS",
            "EVERY_DAY"
        ],
```

### 7. 부가 설명

1. 10명 선착순 포인트 지급이 완료 되었을 시 `SingletonPointState.class`에서 지역 변수로 false를 해줘서 이후 요청에 대해서는 DB 조회 없이 응답을 전달하도록 만들었습니다. 서버를 재가동(배포)하거나 매일 00 시 00 분 00 초가 되면(스케쥴러) true로 변환되어 디비에서 조회하여 체크한뒤 포인트 지급 로직을 타게 했습니다. 
이유 => 10명 이후 조회에 대해서 매번 DB 조회하는게 비효율적이라 생각.

2. 동시성 문제는 트렌젝션과,synchronized block를 사용하여 해결했습니다. 더불어 위에 사용된 SingletonPointState 클래스 내부 변수는 AtomicBoolean를 사용하여 추가적인 외부 환경에서 경쟁 상태일때 동시성을 해결하고자 했습니다.

3. 연속 출석을 파악하기 위해 `continuous_attendance_cnt` 이라는 컬럼을 하나 추가했습니다. 컬럼 추가라는 비용이 발생되지만, 이를 추가하지 않으면 연속 포인트 획득 여부를 확인하는 로직이 매우 복잡해지고 코드도 복잡하게 되어 이를 추가하는것이 더 효율적이라 생각했습니다. 


