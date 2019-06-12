# Revolut Challenge

This is a currency rate app, which fetches a list of currencies from a webservice and allows the user to change the rate between them.

# Architecture and technologies

I choose to use MVVM (Model-View-ViewModel) as my project architecture in order to provide a cleaner code, with clear separation between the view, the data and the business logic.

I used databinding and livedata to provide two-way awareness between UI events and data changes, aswell as discarding the need of handling data change callbacks and UI event listeners.

I used kotlin coroutines as a seemingly effortless way of achieving background network operation with UI callback, which proved to be much cleaner than using RxJava.
Opted to use RxJava to control the fetch scheduled interval because the coroutine timer component is still experimental.

# Tech-stack

Kotlin, Koin, ViewModel, LiveData, Lifecycle, Databinding/Binding Adapters, Kotlin Coroutines

# Next steps

With more time, I would focus into implementing unit, integration and UI tests.
For the tests I would adopt JUnit, Mockito, Roboletric, Koin mocking features and Espresso.

I would also focus a bit more in the user experience, handling the loading state of the list and eventual network errors, optimizing the performance of the dataset changes and making the list respond to user action more fluidly.
