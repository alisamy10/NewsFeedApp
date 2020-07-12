# News App 
News application using the API from https://newsapi.org/
API News Is JSON API for live news and blog articles from the media.
News Feed is a simple  that gives you latest and best news from multiple sources so you no longer need to swap between apps to stay informed.
# Architecture
I choose to use MVVM (Model-View-ViewModel) as my project architecture in order to provide a cleaner code, with clear separation between the view, the data and the business logic.
The following diagram shows all the modules and how each module interact with one another after. This architecture using a layered software architecture.
![68747470733a2f2f646576656c6f7065722e616e64726f69642e636f6d2f746f7069632f6c69627261726965732f6172636869746563747572652f696d616765732f66696e616c2d6172636869746563747572652e706e67](https://user-images.githubusercontent.com/55722619/81968739-a8bec700-95d1-11ea-8682-48fe879c25ff.png)
# Built With 🛠
* [Kotlin](https://kotlinlang.org/) - official programming language for Android development .
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - for asynchronous programming .
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - Access your app's SQLite database with in-app objects and compile-time checks.
  - [Navigation](https://developer.android.com/guide/navigation) - 
* [Koin](https://start.insert-koin.io/) - Dependency Injection Framework (Kotlin).
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
* [Fragment](https://developer.android.com/guide/components/fragments)
* [Glide](https://bumptech.github.io/glide/) for image loading
* [pretty time ](https://www.ocpsoft.org/prettytime/) - format time 

# screens of the app
* Splash
![Screenshot_20200515-202418 1](https://user-images.githubusercontent.com/55722619/82084068-957a2d00-969f-11ea-9e06-569f1928f568.png)
* Home from api
![Screenshot_20200515-202423 1](https://user-images.githubusercontent.com/55722619/82084228-d1ad8d80-969f-11ea-9eb5-eb566068c229.png)
* Home Cached from api using room
![Screenshot_20200515-202114 1](https://user-images.githubusercontent.com/55722619/82084328-fd307800-969f-11ea-8c5c-948c05c4d7e1.png)
* Favourite
![Screenshot_20200515-180027 2](https://user-images.githubusercontent.com/55722619/82084661-792ac000-96a0-11ea-9ce9-1fc3232b1cfb.png)
* Delete All 
![Screenshot_20200515-180102 1](https://user-images.githubusercontent.com/55722619/82084744-a4adaa80-96a0-11ea-8fc3-5dcb6c9e94ca.png)
* Details
![Screenshot_20200515-180039 1](https://user-images.githubusercontent.com/55722619/82084824-cd35a480-96a0-11ea-9327-5eae49ad2c4b.png)
* Web View 
![Screenshot_20200515-180051 1](https://user-images.githubusercontent.com/55722619/82084863-e3436500-96a0-11ea-8d31-338296ed6a3f.png)




