# pjt

 pjt kidari 
 
1. open jdk 11
2. gradle
3. SpringBoot 
4. jpa
5. Swagger 3.0 : http://localhost:8089/swagger-ui/index.html

# DB 테이블 컬럼 타입 변경 처리 
1. 시간은 time stamp 형식
2. ALTER TABLE lecture convert to charset UTF8;

4. 
ALTER TABLE `lecture`
CHANGE COLUMN `create_date` `create_date` DATETIME NULL DEFAULT current_timestamp() AFTER `id`,
CHANGE COLUMN `update_date` `update_date` DATETIME NULL DEFAULT current_timestamp() AFTER `create_date`;