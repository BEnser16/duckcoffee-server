FROM openjdk:17

# 設定工作目錄
WORKDIR /duckserver

# 複製Spring Boot應用程式的JAR檔到容器中的/工作目錄
COPY target/duckcoffee-0.0.1-SNAPSHOT.jar /duckserver

# 指定容器執行的命令
CMD ["java", "-jar", "duckcoffee-0.0.1-SNAPSHOT.jar"]

LABEL authors="ryandai"
