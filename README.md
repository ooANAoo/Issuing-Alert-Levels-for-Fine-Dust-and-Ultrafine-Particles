프로젝트 기본 세팅 방법
1. json파일의 날짜를 Time_value_preprocessing.java 클래스를 이용해서
날짜를 xxxx-xx-xx 24 -> xxxx-xx-(xx+1) 00  형식으로 변환 할것.
2. DataInitializer.java 클래스 실행해서 변환된 json데이터를 db의 AIRQUALITY테이블에 적재 할것. 
3. MainSystem 실행 <br>

프로그램 실행 방법
1. 프로젝트 실행
2. 웹페이지를 열고, http://localhost:8080/ 접속

샘플 테스팅 방법
1. MainSystem의 테스트코드 주석해제, 실제 동작코드 주석처리하기
2. 프로젝트 실행
3. 웹페이지를 열고, http://localhost:8080/ 접속

프로젝트 부가 설명
1. index.html 클라이언트 웹페이지 소스코드
2. json파일은 전처리가 끝난파일임.  xxxx-xx-xx 24 -> xxxx-xx-(xx+1) 00 

