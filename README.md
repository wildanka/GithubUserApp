A Simple Github User App

## Overview:
This is just a simple app that trying to display Github users public data,  app was built using View+Jetpack Compose

### Features: 
1. **Search User Screen**
2. **User List Screen**
3. **User Detail Screen**
4. Local data persistence using room to support offline usage in the **User List Screen**
5. Local caching support for the profile data in **User Detail Screen** (excluding the followers and following list) 

### Architecture:
Clean Architecture, with MVVM design pattern and Modularization.

## Tools Used:
#### Open Source Library
- Glide *(Image Loading)*
- Coil *(Image Loading)*
- Retrofit  *(Networking)*
- Okhttp *(Networking)*
- Moshi *(JSON operator)*
- Koin _(Dependency Injection)_
- Chucker _Network Debugging_
- Mockito _Unit Testing_
- JUnit _Unit Testing_
#### Android Jetpack Library Used: 
- Paging3
- Jetpack Compose
- LiveData
- Flow
- Kotlin Coroutine
- Room
- ViewModel _(of course)_

## How To Build and Run the project
