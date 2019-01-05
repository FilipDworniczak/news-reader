# News Reader

Server application built on Spring Boot that serves worldwide news data over REST API. Powered by newsapi.org.

Please use dedicated client app [news-reader-frontend](https://github.com/filipdworniczak/news-reader-frontend) .

## Building

In projet's root directory open terminal and execute following: `mvn package`.
If Maven is not installed, run: `./mvnw package` on Linux/Mac or `mvnw.cmd package` on Windows.

Package will be generated in ./target directory.

## Running

To run generated application, execute following in ./target directory: `java -jar news-reader-${version}.jar -Dservices.newsapi.key=${NEWS_API_KEY}`,
or to run application basing on source code run following in project root directory:
`./mvnw spring-boot:run -Dservices.newsapi.key=${NEWS_API_KEY}`

To receive NEWS_API_KEY you can register to newsapi.org and get one.

## Rest API

Rest API documentation is generated with Swagger. When server application is running, please go [here](http://localhost:8080/swagger-ui.html) to get familiar with it.
