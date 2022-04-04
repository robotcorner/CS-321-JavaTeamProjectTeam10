@startuml
title Class Diagram

circle JsonMovieFile

Interface IJsonOperator {
  +IJsonOperator(string filename)
  +Get(int id): json
  +Search(string key, string value): json
  +Sort(string key): json
}

class MovieJsonOperator {
  +JsonOperator(string filename)
  +Get(int id): Movie
  +Search(string key, string value): Movie
  +Sort(string key): Movie
}

class LoginManager {
  +createUser()
  +verifyLogIn()
  +logInUser()
  +logOutUser()
  +updateUserStatus()
  +updateUserPermission()
}

class Ratings {
  +source
  +value
}

class Ratings_List {
  -Ratings[]
  +appendRating()
  -removeRating()
}

class Movie {
  -jsonSingleEntry
  -title
  -year
  -rated
  -released
  -runtime
  -genre
  -director
  -writer
  -actor_list
  -plot
  -language
  -country
  -plot
  -language_list
  -country
  -awards
  -poster
  -Rating_List
  -metascore
  -imdbRating
  -imdbVotes
  -imdbID
  -type
  -dvd_release
  -box_office
  -production
  -website
  -response
}

class User {
  -username
  -password_hashed
  -privledge_type
  -movie_collections: MovieCollectionList
}

class LoginView {
  +initialize()
}

class MovieCollectionList {
  +appendCollection()
  +removeCollection()
}

class MovieCollection {
  +addMovie(MovieID)
  +removeMovie(MovieID)
  List<Movie>
}

class loginView {
  +displayUsername()
  +displayPassword()
}

class MovieCollectionView {
  +initialize()
  +showMoviesForCollection()
}

class MovieList {
  List<Movie>
}

class MovieManagerView {
  +initalize()
  +paginate()
}

MovieManagerView <|-- MovieList
MovieList <|-- Movie
User <|-- MovieCollection
MovieCollectionView <|-- MovieCollectionList
loginView <|-- LoginManager
LoginManager <|-- User
MovieCollectionList <|-- MovieCollection
IJsonOperator <|-- MovieJsonOperator
Ratings_List <|-- Ratings
MovieCollection <|-- Movie
MovieJsonOperator <|-- Movie
MovieJsonOperator <|-- JsonMovieFile
LoginView <|-- User

@enduml
