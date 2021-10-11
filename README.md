# Marvel list
##### Sergio González Ramos

This application is the result of a technical test and has been entirely developed by Sergio González Ramos.
The source of the resources used in it is the API https://developer.marvel.com/docs. 

## Features

- The application show a list of marvel characters from the API.
- The user can select those characters to see some details about him.
- The application shows a list of comics, events, series and stories where the character has been a part of.

## Libraries

Marvel list uses third-party libraries to carry out its function.

- Retrofit
- Kodein
- Picasso
- OkHttp3

And of course MarvelList itself is open source with a public repository.
 on GitHub.

## Architecture

Marvel list has been implemented in MVVM architecture separating each layer into different and independent modules.

#### Layer data

This layer obtains data and resources, in this case requesting data from the server using Retrofit.

#### Layer domain

The domain layer is in charge of data processing and transformation between the other two layers. It also contains the use cases that divide the tasks into simple elements.

#### Layer presentation

Finally, the presentation layer takes care of the interaction with the user, controlling the view to make the user experience as pleasant as possible.

## Coroutines

Marvel list uses corroutines for interaction with the server, so that the main thread is not blocked waiting for a response. The handling of the response is also done in a separate thread.

## Navigation

Marvel list only contains one Activity that contains the fragments with the functionalities.

The navigation of the application is done through the Navigation component, so we have an easy control of the fragment stack.

## Dependency injection

The third-party library Kodein has been used for dependency injection. 
