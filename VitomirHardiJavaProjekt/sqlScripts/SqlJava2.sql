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

  CREATE OR ALTER PROCEDURE SelectMovieActor
AS
  SELECT  [Id]
      ,[MovieId]
      ,[ActorId]
  FROM [MoviesJavaVH].[dbo].[ActorMovie]
  
  GO


  CREATE OR ALTER PROCEDURE SelectMovieDirector
AS
  SELECT [Id]
      ,[MovieId]
      ,[DirectorId]
  FROM [MoviesJavaVH].[dbo].[DirectorMovie]

  GO

  
  CREATE OR ALTER PROCEDURE SelectMovieGenre
AS
SELECT  [Id]
      ,[MovieId]
      ,[GenreId]
  FROM [MoviesJavaVH].[dbo].[GenreMovie]

  GO


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