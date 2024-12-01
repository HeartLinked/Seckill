# 使用 OpenJDK 18 官方镜像
FROM openjdk:18

# 设置时区（避免 apt-get）
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 设置应用程序的工作目录
WORKDIR /app

# 将当前目录下的 jar 文件复制到容器的 /app 目录
COPY target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# 暴露应用运行的端口（默认假设为 8080）
EXPOSE 8080

# 运行应用程序
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
