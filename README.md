--PRERREQUISITOS
Redis-x64-5.0.14.1.msi











![image](https://github.com/user-attachments/assets/81529bf0-ba8a-41d6-acea-166e933545e0)


--spring.application.name=redis-bootcamp
cd "c:\WORKSPACE_DESARROLLO_ECLIPSE\redis-bootcamp"
mvn clean install
mvn spring-boot:run

http://localhost:9100/h2-console/
jdbc:h2:mem:testdb
SELECT * FROM PARAMETROS 

UPDATE PARAMETROS SET FECHA_ACTUALIZACION = LOCALTIMESTAMP(3) WHERE ID = 1;




C:\Users\femer>redis-cli
get map-parametros:param1
![image](https://github.com/user-attachments/assets/33bb7d86-1dbe-412c-9072-3791f3644568)
