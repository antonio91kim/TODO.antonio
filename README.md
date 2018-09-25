TODO Application
================

# 빌드 및 실행 방법

```console
git clone https://github.com/antonio91kim/TODO.antonio.git
cd TODO.antonio
mvn clean package
cd target
java -jar todo.antonio-1.0-SNAPSHOT.jar
```
기본 port(8080)으로 서버를 구동하였다면 브라우저에서 http://localhost:8080/index.html 로 접속합니다.

# API Spec

### GET /api/todo
할일 목록을 조회합니다.

#### Parameter

|Parameter|Description|
|---------|-----------|
|pageNum| 페이지 번호|
|itemsPerPage| 페이지당 할일 갯수|

### POST /api/todo
할일을 등록합니다.

#### Parameter (json)

|Parameter|Description|
|---------|-----------|
|title| 할일 내용|

### PUT /api/todo/{id}
특정 아이디의 할일을 수정합니다.

#### Parameter (json)

|Parameter|Description|
|---------|-----------|
|id(Path| 할일 아이디|
|title| 변경된 할일 내용|

### DELETE /api/todo/{id}
특정 아이디의 할일을 삭제합니다.

#### Parameter

|Parameter|Description|
|---------|-----------|
|id(Path)| 할일 아이디|

### PUT /api/todo/{id}/complete
특정 아이디의 할일을 완료합니다.

#### Parameter

|Parameter|Description|
|---------|-----------|
|id(Path)| 할일 아아디|

### GET /api/todo/{id}
특정 아이디의 할일을 조회합니다.

#### Parameter

|Parameter|Description|
|---------|-----------|
|id(Path)| 할일 아이디|

# 문제 해결

## Backend

* SpringBoot를 기반으로 Repository를 사용하여 H2(HSQLDB)에 데이터를 입력, 수정, 삭제 그리고 조회합니다.
* pagination을 위해 PageRequest 를 사용하여 구현하였습니다.
* H2 디비에는 TODO라는 테이블을 자동으로 생성하고 SEQ_TODO라는 sequene를 생성하여 id의 increment 처리를 하였습니다.

## Frontend

* jquery, bootstrap, moment.js 를 이용하여 구성하였습니다.
* 사용자는 등록 버튼을 눌러 할일을 등록할 수 있습니다.
* 사용자는 리스트의 할일을 눌러 화면 상단에 수정화면을 보이고 하고 해당 내용을 수정하여 할일을 수정할 수 있습니다.
* 사용자는 리스트의 '완료 처리' 버튼을 클릭하여 완료 처리할 수 있습니다. 단, 참조가 걸린 경우 완료 처리 할 수 없습니다.


## 완료처리

* 완료처리시 참조에 걸리지 않은 경우 곧바로 완료처리가 가능하나 참조에 걸린 경우 like 검색으로 참조를 찾았습니다.
* like 검색으로는 정확하게 알아낼 수 없으므로 (정규표현식을 쓰려했으나 시간이 없음) 맨뒤에 공백을 추가한 후 @id+공백 을 찾는 방식으로 완전한 참조를 찾을 수 있게 하였습니다.

