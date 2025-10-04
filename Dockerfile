# Etapa 1: build da aplicação
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copia pom.xml e baixa dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do código e builda
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagem final mais leve
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copia o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
