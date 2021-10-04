# 네오-프로도 메시징 시스템

## 프로젝트 구조
```shell
|-messaging  : root directory
|  |-neo     : 메시지 발송 클라이언트
|  |-prodo   : 메시지 수신 서버  
```

---

## 프로젝트 설명
### 개발 환경
- SpringBoot 2.5.5
- SpringBootWebflux 2.5.5
- Rxjava 2.2.2
- Gradle
- JUnit 5

### prodo
- 메시지 수신 api 서버
- 도메인 : localhost
  - 포트 : 8080

    - request
      ```shell
      POST http://localhost:8080/api/send
    
      {
        "userId"  : "사용자 명",
        "message" : "발송할 메시지"
      }
      ```
    - response
      ```shell
      {
        "status": 200,
        "data": {
            "userId": "입력한 사용자",
            "message": "입력한 메시지"
        }
      }
      ```

### neo
- 메시지 발송을 위한 클라이언트 모듈
- 사용 방법
  1. 사용자 입력
  2. 메시지 입력

---

## 실행 방법
### prodo 
#### Docker 빌드 및 실행
- gradle 이용한 docker image 생성
```shell
  gradle clean :prodo:bootBuildImage
```

- docker 실행
```shell
  docker run -p 8080:8080 prodo:1.0.0
```

### neo
- SpringApplication 실행
```shell
  java -jar ~/messaging/neo/neo-1.0.0.jar
```
 