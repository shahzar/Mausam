## Mausam
A weather application utilizing OpenWeatherMap Api 

### Libraries & Tools
- MVVM Architecture
- Android Jetpack
- Dagger2
- Retrofit/OkHttp
- Kotlin Coroutines 

### Screenshots
<img width="300" alt="screenshot1" src="screenshots/sc1.png">
<img width="300" alt="screenshot2" src="screenshots/sc2.png">

### How To Run
Build

```./gradlew assembleDebug```

Install 

``` adb install -r -t app/build/outputs/apk/debug/app-debug.apk ```

### Build test coverage report
``` ./gradlew testDebugUnitTestCoverage ```