
# JPA App

 - **Compulsory** - all bullets
 - **Optional** - all bullets
 - **Bonus** - all bullets 



## Tasks regarding Compulsory part:

 - [x] Create a _persistence unit_ with the name *MusicAlbumsPU*
 - src\resources\META-INF\persistence.xml
 
 - [x] Create the package _entity_ in your project and define the entity classes _Artist_ and _Album_
 - src\main\java\jpa\entity
 - [x] Create the package _util_ containing a class called _PersistenceUtil_. This class must contain a method for creating/returning an _EntityManagerFactory_ object. Implement the _Singleton_ desing pattern
 - src\main\java\jpa\util\PersistenceUtil
 - [x] Create the package _repo_ in your project and define the classes _ArtistRepository_ and _AlbumRepository_
 -  src\main\java\jpa\repo
 - [x] Create the package _app_ and the main class _AlbumManager_ in order to test your application
 - src\main\java\app\AlbumManager


## Tasks regarding Optional part:

 - [x] Add support for _charts_
 - src\main\java\jpa\entity\Chart.java
 - src\main\java\jpa\entity\ChartAlbum
 - src\main\java\jpa\repo\ChartRepository
 - src\main\java\jpa\repo\ChartAlbumRepository
 
 - [x] Create a generic _AbstractRepository_ using _generics_ in order to simplify the creation of the _repository_ classes
 - src\main\java\jpa\repo\AbstractRepository
 
 - [x] Use an _AbstractFactory_ in order to create the DAO objects (the repositories)
 - src\main\java\factory
 
 
 - [x] JDBC implementation
 - src\main\java\jdbc
 
 - [x] The application will use JDBC or JPA depending on a parameter given in an initialization file. (At least for one entity!) - **I did it for all entities**
 - init.txt

## Task regarding Bonus part

 - [x] Create support for specifying music _genres_
 - src\main\java\jpa\entity\MusicGenre
 - src\main\java\jpa\repo\MusicGenreRepository
 
 - [x] Generate fake data in order to populate your database with a large number of albums.
 - src\main\java\app\AlgorithmManager\insertRandomAlbums
 
 - [x] Implement an efficient algorithm that returns the largest set of albums such that no two albums have the same artist or belong to the same genre.
 - src\main\java\app\AlgorithmManager\solve

Output example:

    Album(id=1, name=Revival, releaseYear=2010, artist=Artist(id=1, name=Eminem, country=USA), musicGenre=MusicGenre(id=1, name=Rap))
    Album(id=2, name=Best, releaseYear=2016, artist=Artist(id=2, name=Stromae, country=France), musicGenre=MusicGenre(id=2, name=Rock))
    Album(id=4, name=Harp, releaseYear=2000, artist=Artist(id=16, name=Bernini, country=Zambia), musicGenre=MusicGenre(id=6, name=Pop))
    Album(id=5, name=Acoustic Guitar, releaseYear=2014, artist=Artist(id=6, name=Pissarro, country=Sudan), musicGenre=MusicGenre(id=5, name=Classical))
    Album(id=6, name=Harmonica, releaseYear=2000, artist=Artist(id=42, name=Donatello, country=Sweden), musicGenre=MusicGenre(id=3, name=Jazz))
    Album(id=9, name=Organ, releaseYear=2013, artist=Artist(id=27, name=Vincent, country=Sierra Leone), musicGenre=MusicGenre(id=8, name=Experimental))
    Album(id=19, name=Xylophone, releaseYear=2009, artist=Artist(id=4, name=Picasso, country=Monaco), musicGenre=MusicGenre(id=4, name=Electronic))
    Album(id=25, name=Piano, releaseYear=2001, artist=Artist(id=34, name=Vincent, country=Kiribati), musicGenre=MusicGenre(id=9, name=Country))
    Album(id=42, name=Harmonica, releaseYear=2018, artist=Artist(id=8, name=Matisse, country=Eswatini), musicGenre=MusicGenre(id=7, name=Blues))

 - [x] Create test units for your algorithm using JUnit or other framework.
 - src\test\java\AlgorithmManagerTest

## Additional files

 - [x] Tables
 - src\resources\script.sql
