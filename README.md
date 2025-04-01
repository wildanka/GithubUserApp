# A Simple Github User App

## Overview:
This is just a simple app that trying to display Github users public data,  app was built using View+Jetpack Compose

### How To Build and Run the project
- Make sure you change the GITHUB_TOKEN with your own **Github Personal Access Token**, you can get your own **Personal Access Tokens (PAT)** by visiting https://github.com/settings/personal-access-tokens and use Fine-grained tokens. (or you can just reach me via email in case you need the Github PAT for testing the source code/app)
- This App was build using AGP 8.7.3, and Gradle Version 8.9 with Android Studio Ladybug 2024.2.1 Patch 3.
  In case this app doesn't build in your machine, please feel free to reach me at dankurniadi10@gmail.com I will be glad to help.
- And if you just want to run the app feel free to [check here](https://drive.google.com/drive/u/0/folders/1AnIuf70OgH-UOdqmEVw1-LqMVnl5Fuep), and use the **app-debug.apk**. 


### Features: 
1. **Search User Screen** (View Based) + Offline support 
2. **User List Screen** (View Based)
3. **User Detail Screen** (Compose Based) + Offline support
4. Local data persistence using room to support offline usage in the **User List Screen**
5. Local caching support for the profile data in **User Detail Screen** (excluding the followers and following list)
#### Improvements: 
- display followers and following data in the User Detail Screen
#### Challenges: 
- one really challenging is deciding on how the app workflow will be done, because having a Search List and Displaying List Screen, previously I was thinking whether I should combine the normal **Displaying List Screen** and **Search Display Screen** altogether (which will have more complexity), or should I separate it. In the end I choose to separate the feature to make it more simple (The only thing that I was think about to improve the User Experience is to display the list of recently searched Github User into the cache, but couldn't do it due to time limit). TLDR; imagining on how the whole app will works, and designing the UI color, the UX by myself.
- other things that I think challenging here is the time that I have near the Ied Day, :D 

### Architecture:
Clean Architecture, with MVVM design pattern and Modularization.
#### why it was chosen?
- **Clean Architecture** -> helping us separate the concern between code which will make the code to be easily testable.
- **Modularization** -> because it was instructed(hehe). But yeah we're all know that there is so many advantages of having a modularized code, starting from helping a team at grows, forcing the code concerns to be cleaner (whether by layer or feature), and many more.
- **MVVM** -> as we are still using View-Based UI(because we need to use Glide in the list screen) which will really work well with MVVM approach. But yeah, MVVM with ViewModels, of course ViewModel help us reduce effort to handle configuration changes and unexpected data loss during configuration changes.

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

