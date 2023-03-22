# blog-seasrch-service

## Download url
https://drive.google.com/file/d/1OcZVyMwsWWEtnE1lbLwLzypNo7e-7K9z/view?usp=share_link

## SuccessResponse
timestamp : 요청이 처리되어 나간 시간  <br>
path : 요청url  <br>
body : API 결과값<br>

Request 예시: http://localhost:8080/blogs?query="맛집"&sort="accuracy"&page=10&size=10
example
```json
{  
    "timestamp": "2023-03-22T00:03:37.172+09:00",  
    "path": "/blogs",  
    "body": [{
      "title": "서귀포 <b>맛집</b> BEST",
      "contents": "서귀포 <b>맛집</b> BEST 색달식당 방문기 안녕하세요 크로노입니다. 이번에 거의 몇년만에 가족들과 제주도 여행에 다녀왔습니다. 둘째날 주변 지인들에게 소개받은 서귀포 <b>맛집</b> 색달식당엘 다녀왔어요. 방송에도 출연할 만큼 갈치 요리가 일품인 곳으로 세트 요리를 주문하면 같이 차려지는 기본 반찬들까지 맛깔났던 곳...",
      "url": "http://croboda.tistory.com/45",
      "blogname": "크로노",
      "thumbnail": "https://search4.kakaocdn.net/argon/130x130_85_c/B5EoggjDI3j",
      "dateTime": "2023-03-08T13:44:19.000+09:00"
    }], 
    "message": null,
    "error": null,
    "status": 200
}
```
## ErrorResponse
timestamp : 요청이 처리되어 나간 시간  <br>
path : 요청url  <br>
message : API의 에러 상세  <br>
error : API의 HttpStatus error  <br>
status : API의 HttpStatus errorCode<br>

example<br>
```json
{
    "timestamp": "2023-03-22T09:54:57.733+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/blogs"
}
```

## 1. 블로그 검색
GET /blogs?query={query}&sort={sort}&page={page}&size={size}<br>
- 카카오 블로그 검색 API 조회 후, 에러 시 -> 네이버 블로그 검색 API 조회한다.<br>

RequestParam :   <br>
String query; // 검색을 원하는 단어<br>
String sort; // 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy<br>
Integer page;  // 결과 페이지 번호, 1-50 사이의 값, 기본 값 1<br>
Integer size;  // 한 페이지에 보여질 문서 수, 1-50 사이의 값, 기본 값 10<br>

ResponseBody.body[] :  <br>
String title;<br>
String contents;<br>
String url;<br>
String blogname;<br>
String thumbnail;<br>
Datetime dateTime;<br>

ErrorResponse :  <br>
400 Bad Request // request 형식 오류<br>

## 2. 인기 키워드 조회
GET /popularKeywords<br>

ResponseBody.body[] : // count 내림차순 정렬  <br>
keyword  <br>
count<br>

ErrorResponse :  <br>


### 1. 트래픽이 많고, 저장되어 있는 데이터가 많음을 염두에 둔 구현
#### caching 작업
- Blog테이블을 만들어 두어, 캐싱한다.<br>
- 캐싱 작업이 되어있는 경우 캐시를 사용하여 값을 불러오고, 없는 경우 API 호출하여 데이터를 가져온다.<br>

### 2. 동시성 이슈가 발생할 수 있는 부분을 염두에 둔 구현 (예시. 키워드 별로 검색된 횟수의 정확도)
- 동시성 이슈 발생할 수 있는 원인 : 검색을 동시에 연속적으로 하는 경우, update할 때 DB에서 락 걸릴수 있다.<br>
- 방법1. 비관적 락을 사용해준다.<br>
- 방법2. native query를 사용하여, 정확한 쿼리를 사용한다.<br>

### 3. 인기 검색어 키워드 검색 횟수 증가
- 키워드 있는 경우, count 증가 없는 경우 새로 추가해준다.

### 4. 카카오 블로그 검색 API에 장애가 발생한 경우, 네이버 블로그 검색 API를 통해 데이터 제공
- 카카오, 네이버 dev 사이트를 확인해보면, request가 sort, size, page 요청 쿼리가 같은걸 알 수 있다.<br>
- Kakao, Naver 에서 발생 가능한 에러코드를 통일한다.<br>
- Kakao, Naver 의 리턴값을 받아 custom DTO 에 넣음으로써 Response 통일한다.<br>

