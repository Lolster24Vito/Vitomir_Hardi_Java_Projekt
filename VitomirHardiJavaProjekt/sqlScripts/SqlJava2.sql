CREATE DATABASE MoviesJavaVH
GO
USE MoviesJavaVH
GO

CREATE TABLE Movie
(
	Id INT PRIMARY KEY IDENTITY,
	Title NVARCHAR(300),
	PublishDate NVARCHAR(90),
    [Description] NVARCHAR(1200),
	OriginalName NVARCHAR(300),
	[Duration] int,
	Link NVARCHAR(300),
	PosterPath NVARCHAR(300),
	ReleasedDate Date
)

GO


alter table Movie
ADD  PublishDate NVARCHAR(90)
GO

	Alter PROCEDURE createMovie
		@Title NVARCHAR(300),
		@PublishDate NVARCHAR(90),
		@Description NVARCHAR(1200),
		@OriginalName NVARCHAR(300),
		@Duration int,
		@Link NVARCHAR(300),
		@PosterPath NVARCHAR(300),
		@ReleasedDate Date,
			@Id INT OUTPUT

	AS 
	IF NOT EXISTS (SELECT * FROM Movie WHERE @Title =Title) 
BEGIN
  INSERT INTO Movie(Title,Description,OriginalName,Duration,Link,PosterPath,ReleasedDate,PublishDate)
		VALUES(@Title,@Description,@OriginalName,@Duration,@Link,@PosterPath,@ReleasedDate,@PublishDate)
		SET @Id = SCOPE_IDENTITY()
END
	GO

create table Actor(
	Id INT PRIMARY KEY IDENTITY,
    [Name] NVARCHAR(300)
)
GO
create table DIRECTOR(
	Id INT PRIMARY KEY IDENTITY,
    [Name] NVARCHAR(300)
)
GO

create table GENRE(
	Id INT PRIMARY KEY IDENTITY,
    [Name] NVARCHAR(300)
)
GO

create table ActorMovie(
	Id INT PRIMARY KEY IDENTITY,
	MovieId int FOREIGN KEY REFERENCES Movie(Id),
	ActorId int FOREIGN KEY REFERENCES Actor(Id)
)
GO
create table DirectorMovie(
	Id INT PRIMARY KEY IDENTITY,
	MovieId int FOREIGN KEY REFERENCES Movie(Id),
	DirectorId int FOREIGN KEY REFERENCES Director(Id)
)
GO
create table GenreMovie(
	Id INT PRIMARY KEY IDENTITY,
	MovieId int FOREIGN KEY REFERENCES Movie(Id),
	GenreId int FOREIGN KEY REFERENCES Genre(Id)
)
GO


CREATE OR ALTER PROCEDURE SetMovieActor
	@MovieTitle nvarchar(300),
	@name nvarchar(300)
AS
	Declare @foreignKey as int=null
	Declare @movieId as int=null
	select top 1 @movieId=Id from  Movie where Title=@MovieTitle
	if( @movieId is not null)
	begin

	

  	IF ( NOT EXISTS (SELECT * FROM Actor WHERE @name =Name) )
		BEGIN
	INSERT INTO Actor(Name) VALUES (@name)
	SET  @foreignKey=SCOPE_IDENTITY()
		END
	IF(EXISTS (SELECT * FROM Actor WHERE @name =Name)) 
		BEGIN
	select @foreignKey=Id from Actor where @name=name 
		END

	if(@foreignKey is not null AND NOT EXISTS (select * from ActorMovie where MovieId=@movieId AND ActorId=@foreignKey))
			BEGIN	
			
			INSERT INTO ActorMovie(MovieId,ActorId) VALUES(@MovieId,@foreignKey)
			END
	end

GO


CREATE OR ALTER PROCEDURE SetMovieDirector
	@MovieTitle nvarchar(300),
	@name nvarchar(300)
AS
	Declare @foreignKey as int=null
	Declare @movieId as int=null
	select top 1 @movieId=Id from  Movie where Title=@MovieTitle
	if( @movieId is not null)
	begin

	

  	IF ( NOT EXISTS (SELECT * FROM DIRECTOR WHERE @name =Name) )
		BEGIN
	INSERT INTO DIRECTOR(Name) VALUES (@name)
	SET  @foreignKey=SCOPE_IDENTITY()
		END
	IF(EXISTS (SELECT * FROM DIRECTOR WHERE @name =Name)) 
		BEGIN
	select @foreignKey=Id from DIRECTOR where @name=name 
		END

	if(@foreignKey is not null AND NOT EXISTS (select * from DirectorMovie where MovieId=@movieId AND DirectorId=@foreignKey))
			BEGIN	
			
			INSERT INTO DirectorMovie(MovieId,DirectorId) VALUES(@MovieId,@foreignKey)
			END
	end

GO


CREATE OR ALTER PROCEDURE SetMovieGenre
	@MovieTitle nvarchar(300),
	@name nvarchar(300)
AS
	Declare @foreignKey as int=null
	Declare @movieId as int=null
	select top 1 @movieId=Id from  Movie where Title=@MovieTitle
	if( @movieId is not null)
	begin

	

  	IF ( NOT EXISTS (SELECT * FROM GENRE WHERE @name =Name) )
		BEGIN
	INSERT INTO GENRE(Name) VALUES (@name)
	SET  @foreignKey=SCOPE_IDENTITY()
		END
	IF(EXISTS (SELECT * FROM GENRE WHERE @name =Name)) 
		BEGIN
	select @foreignKey=Id from GENRE where @name=name 
		END

	if(@foreignKey is not null AND NOT EXISTS (select * from GenreMovie where MovieId=@movieId AND GenreId=@foreignKey))
			BEGIN	
			
			INSERT INTO GenreMovie(MovieId,GenreId) VALUES(@MovieId,@foreignKey)
			END
	end

GO

CREATE OR ALTER PROCEDURE DeleteAllData
as
delete from DirectorMovie
delete from ActorMovie
delete from GenreMovie
delete from GENRE
DELEte from Actor
DELETE FROM DIRECTOR
DELETE FROM Movie
GO


CREATE OR ALTER PROCEDURE SelectMovies
	
AS
	

SELECT [Id]
      ,[Title]
      ,[Description]
      ,[OriginalName]
      ,[Duration]
      ,[Link]
      ,[PosterPath]
      ,[ReleasedDate]
      ,[PublishDate]
  FROM [MoviesJavaVH].[dbo].[Movie]

GO

CREATE OR ALTER PROCEDURE SelectActors
AS
SELECT  [Id]
      ,[Name]
  FROM [MoviesJavaVH].[dbo].[Actor]
  GO


  CREATE OR ALTER PROCEDURE SelectDirectors
AS
SELECT  [Id]
      ,[Name]
  FROM [MoviesJavaVH].[dbo].DIRECTOR
  GO

    CREATE OR ALTER PROCEDURE SelectGenres
AS
SELECT  [Id]
      ,[Name]
  FROM [MoviesJavaVH].[dbo].GENRE
  GO

  /* DELETE PLS */
  CREATE OR ALTER PROCEDURE SelectMovieActor
AS
  SELECT  [Id]
      ,[MovieId]
      ,[ActorId]
  FROM [MoviesJavaVH].[dbo].[ActorMovie]

GO


    CREATE OR ALTER PROCEDURE SelectActorsInMovie
		@MovieId int
AS
  SELECT  am.Id,am.ActorId,am.MovieId,a.Name
  FROM  [MoviesJavaVH].[dbo].[ActorMovie] as am
  INNER JOIN Actor as a on a.Id=am.ActorId
  where am.MovieId=@MovieId
  


  GO

      CREATE OR ALTER PROCEDURE SelectDirectorsInMovie
		@MovieId int
AS
  SELECT  dm.Id,dm.DirectorId,dm.MovieId,d.Name
  FROM  [MoviesJavaVH].[dbo].[DirectorMovie] as dm
  INNER JOIN DIRECTOR as d on d.Id=dm.DirectorId
  where dm.MovieId=@MovieId
  


  GO

  
      CREATE OR ALTER PROCEDURE SelectGenresInMovie
		@MovieId int
AS
  SELECT  gm.Id,gm.GenreId,gm.MovieId,g.Name
  FROM  [MoviesJavaVH].[dbo].GenreMovie as gm
  INNER JOIN GENRE as g on g.Id=gm.GenreId
  where gm.MovieId=@MovieId
  


  GO

  /*DELETE PLS*/

  CREATE OR ALTER PROCEDURE SelectMovieDirector
AS
  SELECT [Id]
      ,[MovieId]
      ,[DirectorId]
  FROM [MoviesJavaVH].[dbo].[DirectorMovie]

  GO

  /*DELETE PLS*/
  CREATE OR ALTER PROCEDURE SelectMovieGenre
AS
SELECT  [Id]
      ,[MovieId]
      ,[GenreId]
  FROM [MoviesJavaVH].[dbo].[GenreMovie]

  GO

CREATE TABLE UserRoles(
Id INT PRIMARY KEY IDENTITY,
RoleName nvarchar(300)
)
GO
INSERT INTO UserRoles(RoleName) Values ('Admin')
INSERT INTO UserRoles(RoleName) Values ('User')

GO

CREATE TABLE Users
(
Id INT PRIMARY KEY IDENTITY,
Username nvarchar(300),
PasswordHash nvarchar(300),
RoleId int FOREIGN KEY REFERENCES UserRoles(Id)
)
GO
/* Password is 123*/
INSERT INTO Users(Username,PasswordHash,RoleId)Values('Admin','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1)
INSERT INTO Users(Username,PasswordHash,RoleId)Values('Adminjo','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1)

GO

create or alter proc CreateUser
	@username nvarchar(300),
	@passwordHash nvarchar(300)
as
Insert into Users(Username,PasswordHash,RoleId)
VALUES (@username,@passwordHash,2)
GO


create or alter proc CheckIfUserExists
	@username nvarchar(300)
as
  select 
    iif(COUNT(*) > 0, 1, 0) as DoesExists
from [MoviesJavaVH].[dbo].[Users] 
  where Username=@username 
GO


create or alter proc UserLogin
	@username nvarchar(300),
	@passwordHash nvarchar(300)
as 
SELECT *
  FROM [dbo].[Users]
  where Username=@username AND PasswordHash=@passwordHash


  GO
CREATE OR ALTER PROCEDURE SelectMoviesFromActor
		@ActorId int
AS
  SELECT  am.Id,am.ActorId,am.MovieId,m.Title
  FROM  [MoviesJavaVH].[dbo].[ActorMovie] as am
  INNER JOIN Movie as m on m.Id=am.MovieId
  where am.ActorId=@ActorId
  


/*ovdje stao*/




CREATE PROCEDURE updateArticle
	@Title NVARCHAR(300),
	@Link NVARCHAR(300),
	@Description NVARCHAR(900),
	@PicturePath NVARCHAR(90),
	@PublishedDate NVARCHAR(90),
	@IDArticle INT
	 
AS 
BEGIN 
	UPDATE Article SET 
		Title = @Title,
		Link = @Link,
		Description = @Description,
		PicturePath = @PicturePath,
		PublishedDate = @PublishedDate		
	WHERE 
		IDArticle = @IDArticle
END
GO


CREATE PROCEDURE deleteArticle
	@IDArticle INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Article
	WHERE 
		IDArticle = @IDArticle
END
GO

CREATE PROCEDURE selectArticle
	@IDArticle INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Article
	WHERE 
		IDArticle = @IDArticle
END
GO

CREATE PROCEDURE selectArticles
AS 
BEGIN 
	SELECT * FROM Article
END
GO


