docker pull openjdk:11

docker build -t cysce/quarkusdev . 

docker run -d -v /Volumes/DATA/CYSCE/quarkus:/app -p 8080:8080 --name quarkus cysce/quarkusdev

docker exec -it quarkus bash


curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio

quarkus create prueba && cd prueba