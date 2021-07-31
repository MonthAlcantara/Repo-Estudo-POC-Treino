#De qual imagem vou gerar a minha imagem
FROM openjdk:15
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
#Ponto de entrada da aplicação. Qual classe será chamada ao executar o projeto
ENTRYPOINT ["java", "-cp","app:app/lib/*","io.github.monthalcantara.aws_project02.AwsProject02Application"]