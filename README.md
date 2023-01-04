# pjt

 pjt kidari 
 
== 기술 
1. open jdk 11
2. gradle
3. SpringBoot 
4. jpa
5. MariaDB
6. Swagger 3.0 : http://localhost:8089/swagger-ui/index.html
7. docker 을 이용한 RDBMS 생성 

# SWAGGER 을 이용한 순서
1. 강연장 입력 (hall-rest-controller)
2. 강연목록 입력 (lecture-rest-controller)
3. 사용자 입력 (user-rest-controller)

# DB 셍성 및 테이블 생성
1. 시간은 time stamp 형식
2. lecture.sql 을 열어 데이터베이스를 생성 및 데이터 삽입 가능

# Data Insert JSON (SWAGGER 를 통한 데이터 입력 또는 조회 )
1. USER
   {
   "gender": "M",
   "userName": "이승기",
   "userNumber": "2301A"
   }

{
"gender": "F",
"userName": "김슬기",
"userNumber": "2301B"
}

{
"gender": "M",
"userName": "김날길",
"userNumber": "2301C"
}

{
"gender": "F",
"userName": "아이유",
"userNumber": "2301D"
}

{
"gender": "M",
"userName": "남궁민",
"userNumber": "2301E"
}

{
"gender": "F",
"userName": "이효리",
"userNumber": "2301F"
}
{
"gender": "M",
"userName": "이상순",
"userNumber": "2301G"
}

2. HAll
   {
     "capacityPeople": 3,  
     "endTime": "2023-01-05 15:00",
     "hallName": "KBS홀",  
     "startTime": "2023-01-02 13:00"  
   }
3. LECTURE

   {
    "endTime": "2023-01-02T16:00:00.694Z",
    "lectureContent": "심리학 내용입니다.",
    "lectureHall": "KBS홀",
    "lecturer": "이박사",
    "singUpPeople": 0,
    "startTime": "2023-01-02T15:00:00.694Z"  
    }
    
    {    
    "endTime": "2023-01-05T16:00:00.694Z",
    "lectureContent": "가정의학 내용입니다.",
    "lectureHall": "KBS홀",
    "lecturer": "이의사",
    "singUpPeople": 4,
    "startTime": "2023-01-05T15:00:00.694Z"  
    }
    

