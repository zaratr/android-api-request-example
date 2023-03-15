# How to make API calls in Java and Android Apps?

This app uses Volley to get API calls. there are much more use cases for Volley and different ways to do the same thing. This is a useful guide to explain how code may look like in two specific scenerios.

### Instructions
- Clone this project.
- if using RapidAPI, then make sure you are subscribed to the api you want to use. you can find out if you are by testing the request on the api website (blue button that will say "Test Connection" after being subscribed.)
- Add API key at this [location of code.](app/src/main/java/com/example/testrapidapi/MainActivity.java#L52)
- Add or double check okhttp3 exists in build.gradle(<app name>: app) shown below:
```
dependencies{
//... check dependencies for okhttp3 and volley but only use volley if you have no headers.
    implementation 'com.squareup.okhttp3:okhttp:4.10.0'
    implementation 'com.android.volley:volley:1.2.1'
}
```
-  AndroidManifest.xml -> dependencies must have permissions for internet access code shown below:
```
<uses-permission android:name="android.permission.INTERNET"/>
```
- I use TextView to show result on XML UI [activity_main.xml](app/src/main/res/layout/activity_main.xml)
- The two example methods below in MainActivity class are ways to access api using Volley. 
### Example 1: Rapid Api or any api call with header
- Link to working Method [apiRequstHeader](app/src/main/java/com/example/testrapidapi/MainActivity.java#L53)
### Example 2: API call with no header - ZenQuotes.io
- Link to working Method [apiRequstWithoutHeader](app/src/main/java/com/example/testrapidapi/MainActivity.java#L86)
