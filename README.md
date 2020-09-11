# Gradle for Android and Java - Udacity Android Developer Nanodegree

This project consists of multiple flavors that use
multiple libraries and Google Cloud Endpoints. The app is made up
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

## By completeing this project I learnt to:

* Add free and paid flavors to an app, and set up your build to share code between them
* Add an ad banner and an interstitial ad for the free version
* Factor reusable functionality into a Java library
* Factor reusable Android functionality into an Android library
* Configure a multi project build to compile libraries and app
* Use the Gradle App Engine plugin to deploy a backend
* Configure an integration test suite that runs against the local App Engine development server
* Configure task that lauches the Google Cloud Endpoints local development server, runs all test and shuts the server down
