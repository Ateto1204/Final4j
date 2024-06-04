# 使用官方的 Maven 映像來進行建置過程
FROM maven:3.8.4-openjdk-17 AS build

# 設定工作目錄
WORKDIR /app

# 複製 Maven 的 POM 文件和源代碼到容器中
COPY pom.xml .
COPY src ./src

# 複製配置文件和服務賬戶密鑰文件到容器中
COPY config.properties .
COPY serviceAccountKey.json .

# 使用 Maven 來建置應用程式
RUN mvn clean package -DskipTests

# 檢查 JAR 文件是否存在
RUN ls -l /app/target

# 使用一個較小的映像來運行應用程式
FROM openjdk:17-jdk-alpine

# 設定工作目錄
WORKDIR /app

# 從建置階段中提取打包好的 JAR 文件
COPY --from=build /app/target/final4j-0.0.1-SNAPSHOT.jar app.jar

# 複製配置文件和服務賬戶密鑰文件到運行階段的映像中
COPY --from=build /app/config.properties .
COPY --from=build /app/serviceAccountKey.json .

# 暴露應用程式運行的端口
EXPOSE 8080

# 設定啟動命令
ENTRYPOINT ["java", "-jar", "app.jar"]