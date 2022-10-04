FROM openjdk:11
RUN echo 'making quarkus image base for dev'
RUN curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
RUN curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio
WORKDIR /app
CMD ["sleep", "5h"]
EXPOSE 8080
RUN echo 'Imagen done!'