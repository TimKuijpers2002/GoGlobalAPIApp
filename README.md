# GoGlobal

[Run Application](#Run-Application)

[Information regarding application](#Information-regarding-techniques-used-in-project)

[Quality Assurance](#Quality-Assurance)

### Run Application

The entire application is setup to run using Docker images, this means that running it either in production or development there should not be any platform changes that could break the code.
<br>
!HOWEVER, THERE IS A BUG WHICH CAUSES THE CONTAINER TO GET INTO AN INFINITE RESTARTING LOOP. AS OF RIGHT NOW, ONLY AVAILABLE ON LOCALHOST!

> **Docker should be install before trying to run the application**

#### Development:

In development the Backend and Frontend is split up so that Reacts auto refresh on code update can still be used.

##### Backend

-   **Windows** 1. Run the docker-compose up to run all the backend containers needed to communicate
    <ins>
    <br>
    Note: If the you don't have the images on your device, run docker-compose up --build -d to build and run these images</ins>
    <br>

##### Front-end-Dashboard

##### Repository for the [FrontEnd](https://github.com/TimKuijpers2002/goglobaldashboard)

1. Open Terminal in yourFolder/goglobaldashboard folder
2. Run `npm i` (This will install all packages in package.json);
3. Run `npm start`

If followed the steps for backend and front-end correctly you should now have a fully functioning webapp running at [**React app**](http://localhost:3000)

### Information regarding techniques used in project:

-   #### Frontend
    -   React application in combination with javascrypt & HTTML/CSS.
-   #### Backend
    -   Backend services are using `JAVA 15`
    -   The admin and location backend service are using `Hibernate` to get data from database

### Quality Assurance:

-   ##### Quality control using sonarqube:

    1. Code coverage is at least (Currently 0.0%, due to configurations) **70%**
    2. Duplication code is less then **1%**
    3. All tests run succesfully
