/****** Script for SelectTopNRows command from SSMS  ******/
SELECT *
  FROM [MoviesJavaVH].[dbo].[Movie]


  DELETE 
  FROM [MoviesJavaVH].[dbo].[Movie]


  /****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP (1000) [Id]
      ,[MovieId]
      ,[ActorId]
  FROM [MoviesJavaVH].[dbo].[ActorMovie]