# GoGlobal

[Run Application](#Run-Application)

[Information regarding application](#Information-regarding-techniques-used-in-project)

[Quality Assurance](#Quality-Assurance)

### Run Application

The entire application is setup to run using Docker images, this means that running it either in production or development there should not be any platform changes that could break the code.

> **Docker should be install before trying to run the application**

#### Production

1. Install Kubectl and cluster tool of choice (e.g. Minikube)
2. Create a cluster using e.g. Minikube
3. Run all deployments found in ./Kubernetes

#### Development:

In development the Backend and frontend is split up so that Reacts auto refresh on code update can still be used.

##### Backend

-   **Windows** 1. Run the start.bat to run all the backend containers needed to communicate
    <ins>Note: If the start.bat does not work on your windows machine, follow step 1 and 2 of MacOS/Linux</ins>
    <br>
-   **MacOS/Linux**
    1. (MacOS/Linux) Open the terminal in the root directory of this repository
    2. (MacOS/Linux) Run: `docker-compose --env-file ./.env up -d`

##### Front-end

1. Open Terminal in Frontend/Gymshare-Frontend folder
2. Run `npm i` (This will install all packages in package.json);
3. Run `npm start`

If followed the steps for backend and front-end correctly you should now have a fully functioning webapp running at [**React app**](http://localhost:3000)

### Information regarding techniques used in project:

-   #### Frontend
    -   React application in combination with typescript
-   #### Backend
    -   Backend services are using `.NET 5.0`
    -   The user and workout backend service are using `EF core` to get data from database

### Quality Assurance:

-   ##### Quality control using sonarqube:

    1. Code coverage is at least **70%**
    2. Duplication code is less then **3%**
    3. Less then **10** Code smells
    4. All tests run succesfully

-   ##### Frontend tests <span style="font-size: 16px">:white_check_mark:</span>
    -   Run all tests manually by going into `Frontend/Gymshare-Frontend` and running `npm test`
    -   Tests are run using **Jest** and **Enzyme**
    -   Api requests are mocked using **msw**
-   ##### End-to-end tests <span style="font-size: 16px">:white_check_mark:</span>
    -   E2E tests are running using a In-Memory database from ef core
-   ##### Unit tests <span style="font-size: 16px">:white_check_mark:</span>
    -   Unit tests are run using xUnit in a seperate project.
