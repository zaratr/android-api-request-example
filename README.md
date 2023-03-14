# How to make API calls in Java and Android Apps?
### Instructions
- add api key to local.properties located at root of the project. For my project called, testRapidAPI2 should have testRapidAPI2/local.properties. if not simply right clicking root folder and selecting new file and call it local.properties. Otherwise hard code API key works too [your key for testing](app/src/main/java/com/example/testrapidapi/MainActivity.java#L38)!
- Add or double check volley exists in build.gradle(<app name>: app)
```
dependencies{
//... check dependencies for volley
implementation 'com.android.volley:volley:1.0.0'
}
```
-  AndroidManifest.xml -> dependencies must have permissions for internet access
```
<uses-permission android:name="android.permission.INTERNET"/>
```
- I use TextView to show result on XML UI [activity_main.xml](app/src/main/res/layout/activity_main.xml)
- The two example methods below in MainActivity class are ways to access api using Volley. 
### Example 1: Rapid Api or any api call with header
- Link to working Method [apiRequstHeader](app/src/main/java/com/example/testrapidapi/MainActivity.java#L53)
### Example 2: API call with no header - ZenQuotes.io
- Link to working Method [apiRequstWithoutHeader](app/src/main/java/com/example/testrapidapi/MainActivity.java#L86)
