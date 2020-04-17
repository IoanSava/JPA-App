# JPA App

 - **Compulsory** - all bullets
 - **Optional** - all bullets
 - **Bonus** - 2/4 bullets 



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
 - src\main\java\app\AlgorithmManager
 
 - [ ] Implement an efficient algorithm that returns the largest set of albums such that no two albums have the same artist or belong to the same genre.
 - To do

## Additional files

 - [x] Tables
 - src\resources\script.sql
