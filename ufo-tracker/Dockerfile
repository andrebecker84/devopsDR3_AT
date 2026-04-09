# =============================================================================
# Dockerfile — UfoTracker (Spring Boot)
# Disciplina: DevOps (DR3) — Assessment Final (AT) — Missao 3
# Aluno: Andre Luis Becker — Instituto Infnet — 2026
#
# Build multi-stage:
#   Estagio 1 (build): Maven + JDK 21 Alpine — compila o projeto e gera o JAR
#   Estagio 2 (runtime): JRE 21 Alpine — executa apenas o JAR, sem ferramentas de build
#
# Build: docker build -t andrebecker84/ufo-tracker:latest .
# Push:  docker push andrebecker84/ufo-tracker:latest
# Run:   docker run -p 8080:8080 andrebecker84/ufo-tracker:latest
# =============================================================================

# -----------------------------------------------------------------------------
# Estagio 1: build
# Compila o projeto Spring Boot com Maven e gera o JAR executavel
# -----------------------------------------------------------------------------
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copia o pom.xml separado do codigo-fonte para aproveitar o cache de dependencias do Docker
# Se apenas o codigo mudar (sem alterar o pom.xml), esta camada e reutilizada do cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o codigo-fonte e executa o build sem rodar os testes
# Os testes sao responsabilidade do pipeline de CI (gradle-ci.yml)
COPY src ./src
RUN mvn package -DskipTests -B

# -----------------------------------------------------------------------------
# Estagio 2: runtime
# Imagem minima com apenas JRE 21 e o JAR compilado
# Sem Maven, sem JDK, sem codigo-fonte — menor footprint e superficie de ataque
# -----------------------------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o JAR gerado no estagio de build
COPY --from=build /app/target/*.jar app.jar

# Porta padrao do Spring Boot
EXPOSE 8080

# Ponto de entrada da aplicacao
ENTRYPOINT ["java", "-jar", "app.jar"]
