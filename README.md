# Gradle for Android and Java 

This app was created for the Udacity Android Developer Nanodegree course
to showcase how Android and Java libraries can be integrated with Gradle.
It consists of multiple flavours that use
multiple libraries and Google Cloud Endpoints. The app is made up
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

## The main tasks for this project were to:

* Add free and paid flavours to an app, and set up your build to share code between them
* Add an ad banner and an interstitial ad for the free version
* Factor reusable functionality into a Java library
* Factor reusable Android functionality into an Android library
* Configure a multi-project build to compile libraries and app
* Use the Gradle App Engine plugin to deploy a backend
* Configure an integration test suite that runs against the local App Engine development server
* Configure task that launches the Google Cloud Endpoints local development server, runs all test and shuts the server down

## How to use the application?

As the app was made mainly to present Gradle features,
it runs in Android Emulator. In order to run it correctly on
a physical device, you need to change the API Root URL in the
"EndpointsAsyncTask" class to match your physical device's root.

Before the first launch of the application, you need to run the 
"appengineStart" task to start the Google Cloud Endpoint API
on your emulator / physical device.

## License

The contents of this repository are covered under the [MIT License](./LICENSE.md).
