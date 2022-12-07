FROM amazoncorretto:11
# java 11의 환경을 구성한다
ARG JAR_FILE=build/libs/*.jar
# build가 되는 시점에 JAR_FILE 이라는 변수명에 build/libs/*.jar 표현식을 선언함
COPY ${JAR_FILE} app.jar
# JAR_FILE 을 app.jar로 복사함
ENTRYPOINT ["java","-jar","/app.jar"]