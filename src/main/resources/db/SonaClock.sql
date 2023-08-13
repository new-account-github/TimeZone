USE [master]
GO
/****** Object:  Database [SonaClock]    Script Date: 8/5/2023 4:17:07 AM ******/
CREATE DATABASE [SonaClock]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SonaHotel', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SonaHotel.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SonaHotel_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SonaHotel_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SonaClock] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SonaClock].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SonaClock] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SonaClock] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SonaClock] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SonaClock] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SonaClock] SET ARITHABORT OFF 
GO
ALTER DATABASE [SonaClock] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SonaClock] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SonaClock] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SonaClock] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SonaClock] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SonaClock] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SonaClock] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SonaClock] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SonaClock] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SonaClock] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SonaClock] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SonaClock] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SonaClock] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SonaClock] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SonaClock] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SonaClock] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SonaClock] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SonaClock] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SonaClock] SET  MULTI_USER 
GO
ALTER DATABASE [SonaClock] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SonaClock] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SonaClock] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SonaClock] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SonaClock] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SonaClock] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SonaClock] SET QUERY_STORE = OFF
GO
USE [SonaClock]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 8/5/2023 4:17:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](max) NULL,
	[Fullname] [nvarchar](50) NULL,
	[Firstname] [nvarchar](50) NULL,
	[Lastname] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[Phone] [char](10) NULL,
 CONSTRAINT [PK__Accounts__536C85E5C00863D6] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Authorities]    Script Date: 8/5/2023 4:17:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authorities](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NULL,
	[RoleId] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 8/5/2023 4:17:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[Id] [char](4) NOT NULL,
	[Name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 8/5/2023 4:17:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[OrderId] [bigint] NULL,
	[ProductId] [int] NULL,
	[Price] [float] NULL,
	[Quantity] [int] NULL,
 CONSTRAINT [PK__OrderDet__3214EC0704395FCB] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 8/5/2023 4:17:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NULL,
	[CreateDate] [datetime] NULL,
	[Address] [nvarchar](100) NULL,
 CONSTRAINT [PK__Orders__3214EC0774ADB725] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 8/5/2023 4:17:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Image] [nvarchar](50) NULL,
	[Price] [float] NULL,
	[CreateDate] [date] NULL,
	[Available] [bit] NULL,
	[category_id] [char](4) NULL,
 CONSTRAINT [PK__Products__3214EC073896D647] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 8/5/2023 4:17:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[Id] [nvarchar](10) NOT NULL,
	[Name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[verification_token]    Script Date: 8/5/2023 4:17:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[verification_token](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[token] [varchar](255) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[expiry_date] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

SET IDENTITY_INSERT [dbo].[Authorities] ON 

INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (5, N'cuong', N'DIR')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (7, N'toan', N'STA')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (8, N'hung', N'STA')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (9, N'danh', N'STA')
INSERT [dbo].[Authorities] ([Id], [Username], [RoleId]) VALUES (10, N'director', N'DIR')
SET IDENTITY_INSERT [dbo].[Authorities] OFF
GO
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'102 ', N'Ðồng hồ đeo tay')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'103 ', N'Ðồng hồ thông minh')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'104 ', N'Ðồng hồ treo tường')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'105 ', N'Ðồng hồ cơ')
INSERT [dbo].[Categories] ([Id], [Name]) VALUES (N'106 ', N'Ðồng hồ bấm giờ')
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (101, N'Apple Watch', N'apple.jpg', 1200000, CAST(N'2023-07-17' AS Date), 1, N'102 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (102, N'Tag Heurer', N'tag.jpg', 780000, CAST(N'2023-07-17' AS Date), 1, N'103 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (104, N'QUARTZ', N'quartz.jpg', 186000, CAST(N'2023-07-17' AS Date), 0, N'104 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (106, N'Kashi', N'kashi.jpg', 278000, CAST(N'2023-07-17' AS Date), 1, N'102 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (108, N'Stoch Match', N'stooch-match.jpg', 474000, CAST(N'2023-07-17' AS Date), 1, N'104 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (109, N'Movado', N'movado.jpg', 56000, CAST(N'2023-07-17' AS Date), 1, N'103 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (110, N'Citizen', N'citizen.jpg', 507000, CAST(N'2023-07-17' AS Date), 1, N'103 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (111, N'Casio', N'casio.jpg', 457000, CAST(N'2023-07-17' AS Date), 1, N'104 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (113, N'Micheal Kors', N'micheal.jpg', 842000, CAST(N'2023-07-17' AS Date), 1, N'103 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (114, N'Olympia', N'olympia.jpg', 615000, CAST(N'2023-07-17' AS Date), 1, N'102 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (115, N'DW', N'dw.jpg', 570000, CAST(N'2023-07-17' AS Date), 0, N'102 ')
INSERT [dbo].[Products] ([Id], [Name], [Image], [Price], [CreateDate], [Available], [category_id]) VALUES (116, N'Guess', N'guess.jpg', 672000, CAST(N'2023-07-17' AS Date), 1, N'104 ')
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'AD', N'ADMIN')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'CUS', N'CUSTOMER')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'DIR', N'DIRECTOR')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'STA', N'STAFF')
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [FK__Authoriti__RoleI__2B3F6F97] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [FK__Authoriti__RoleI__2B3F6F97]
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [FK__Authoriti__Usern__2A4B4B5E] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [FK__Authoriti__Usern__2A4B4B5E]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK__OrderDeta__Order__33D4B598] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Orders] ([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK__OrderDeta__Order__33D4B598]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK__OrderDeta__Produ__34C8D9D1] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Products] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK__OrderDeta__Produ__34C8D9D1]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__Username__30F848ED] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__Username__30F848ED]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK__Products__Catego__2E1BDC42] FOREIGN KEY([category_id])
REFERENCES [dbo].[Categories] ([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK__Products__Catego__2E1BDC42]
GO
ALTER TABLE [dbo].[verification_token]  WITH CHECK ADD  CONSTRAINT [FK__verificat__usern__5AEE82B9] FOREIGN KEY([username])
REFERENCES [dbo].[Accounts] ([Username])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[verification_token] CHECK CONSTRAINT [FK__verificat__usern__5AEE82B9]
GO
USE [master]
GO
ALTER DATABASE [SonaClock] SET  READ_WRITE 
GO
