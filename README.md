# Test Backend Dveloper MasivMovies
This project shows the translation of the requirements proposed by the technical test provided by Masiv.
The project was carried out with the JAVA language using JDK-17 and using a non-relational database, Redis.

Requiriments:
- JDK 17 or higher
- Maven 3.9.8 or higher
- Docker (optional)
- Redis

## Install
git clone https://github.com/richardmtz7/movies
You can also download the zip file and unzip it, then open it in your favorite IDE

### Compilation and execution
Windows
Compilation: mvn clean install
Excecution: mvn spring-boot:run

Linux
compilation: ./mvnw clean install
excecution: ./mvnw spring-boot:run

With Docker
docker-compose build masiv_movies
docker-compose up
