# News Reader

Server application that serves worldwide news data over REST API. Powered by newsapi.org.

## Building

In projet's root directory open terminal and execute following: `mvn package`.
If Maven is not installed, run: `./mvnw package` on Linux/Mac or `mvnw.cmd package` on Windows.

Package will be generated in ./target directory.

## Running

To run generated application, execute following: `java -jar news-reader-${version}.jar`