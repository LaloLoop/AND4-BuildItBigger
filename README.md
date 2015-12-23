# AND4-BuildItBigger
Fourth Project for the Android Nanodegree program.

## About the project
In this project, topics about Gradle build system where implemented inside an app consisting of the following modules.
- app: Main content of the Android application.
- jokesource: java library configured as a subproject (module) of the app, to retrieve jokes.
- jokeview: Android library with an activity to show a joke passed as an intent extra.
- backend: Google Cloud Engine App using the jokesource (java) library to provide a joke to the main app via GCE remote method invocation.

### Additional specifications implemented
* The app displays an Intestitial add between the main activity and the jokeview activity when the user clicks the "Tell Joke" button. This only occurs on the free version of the app.
* The app shows a loading indicator and blocks the "Tell Joke" button when the user clicks it, until the joke has been recieved from the GCE backend module.
* A task was implemented inside the root build.gradle file, to excetue the local GCE app server, execute all the Android tests and shutdown the server.

# Important considerations
You may need to add a device or emulator Id for de MainActivity to get the Interstitial Unit working, since it was used in a real device.
The ID can be configured on the `free/res/values/ad-units.xml` by placing the ID inside the test_device resource tag.

## About the async task
The async task was configured to use a local network IP to be tested on the real device. Emulators used this IP as well without any errors. Just be sure to change it on your development environment inside the `main/java/<oackage>/EndpointAsyncTask.java` file.


# Important resources used
http://stackoverflow.com/questions/9774792/asynctask-onpostexecute-not-called-in-unit-test-case
http://stackoverflow.com/questions/7534967/is-there-any-way-to-access-gae-dev-app-server-in-the-local-network
