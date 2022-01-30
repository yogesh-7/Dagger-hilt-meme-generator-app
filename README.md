# Dagger-hilt-meme-generator-app
Dagger -hilt +retrofit +sealed classes+ mvvm + stateflow + Network helper

Meme generator application using Retrofit, glide, dagger- hilt, stateflow, sealed classes using public api https://meme-api.herokuapp.com/gimme


## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

# Package Structure
    
    com.example.dagger_hiltdemo    # Root Package
    .
    ├── data                # For data handling.
    │   └── repository      # Single source of data.   
    |
    ├── di                  # Dependency Injection             
    |
    ├── ui                  # Activity/View layer
    │   ├── viewmodel       # ViewModels
    │   └── view            # Activity
    |
    └── utils               # Utility Classes / Kotlin extensions


## 📸 Screenshots



[Imgur](https://s3.amazonaws.com/khata/bill/054b1655-b37d-435b-b82f-3491b95c2a93.jpg)



## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

## Contact
If you need any help, you can connect with me.

Visit: [Yogesh bansal](https://www.helloyogesh.com)

