
// set up database
docker build -t mango-db .
docker run -d -p 5432:5432 mango-db


// compile the backend
mvn clean install