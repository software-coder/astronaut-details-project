# Astronaut Information Project

### Documentation

This is a Spring Boot project which exposes 2 endpoints:

http://localhost:8080/api/astronauts/1.0<br/>
http://localhost:8080/api/astronauts/1.0/astronaut/{id}

Where id is the numeric id of the astronaut e.g. 276.

An additional health endpoint
http://localhost:8080/api/actuator/health shows the API status


### Swagger

The project is documented at http://localhost:8080/api/swagger-ui.html<br/>
Documentation built using Swagger Springfox.

### Building and running

The code was built in Eclipse using Maven. A minimum Java version of Java 8 is required. <br/>
To import this  project, go to Eclipse > File > Import... > Existing Maven projects, and choose this directory.<br/>
Install Lombok separately, see this guide: https://www.baeldung.com/lombok-ide<br/>
Turn on annotation processing in the IDE, by right-clicking the project, select Properties, search for "annotation", select Java Compiler > Annotation Processing, and turn on all the check-boxes.<br/>
To build this project, select Project > Build automatically in Eclipse<br/>
To run this project, right-click the AstroApplication.java and select Run As > Java Application.<br/>

### Implementation details

The API endpoint ```http://localhost:8080/api/astronauts/1.0/astronaut/{id}``` calls ```https://spacelaunchnow.me/api/3.5.0/astronaut/{id}/?format=json``` which is straightforward.

The API endpoint ```http://localhost:8080/api/astronauts/1.0``` calls ```https://spacelaunchnow.me/api/3.5.0/astronaut/?format=json&limit=100``` because the source API supports pagination, and 100 is the max page size. This url is called with an offset param until all the pages are read. There are a total of 634 records in the result, which is why with a page size of 100 we can make a max of 7 API hits (till next variable is null in the result). We had to fetch all the pages since the problem statement did not suggest whether to get only the first page or all pages results. We call https and not http because the http URL redirects to https.

### Things to watch out for

If you fire the requests too many times, the source API may respond with 429 Too Many Requests from ```GET https://spacelaunchnow.me/api/3.5.0/astronaut/276/?format=json```. Be mindful of this. The 429 response doesn't include a Retry-After header so we are unsure after how long it is safe to retry the request.

### Postman collection

I've included a POSTMAN collection with the 2 endpoints that can be fired locally when the code is running.
