CREATE USER [##MS_PolicyEventProcessingLogin##] FOR LOGIN [##MS_PolicyEventProcessingLogin##] WITH DEFAULT_SCHEMA=[dbo]
GO
CREATE USER [##MS_AgentSigningCertificate##] FOR LOGIN [##MS_AgentSigningCertificate##]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bank](
	[AccNo] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Balance] [float] NOT NULL
)

GO
INSERT [dbo].[Bank] ([AccNo], [Balance]) VALUES (N'C001      ', 10000)
GO
INSERT [dbo].[Bank] ([AccNo], [Balance]) VALUES (N'V001      ', 9010)
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TransactionDetails](
	[AccNo] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Name] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[ProductID] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[VendorAccNo] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Date_Time] [datetime] NOT NULL
)

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Query](
	[SenderAcc] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Subject] [nchar](40) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[Query] [nvarchar](100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Response] [nvarchar](100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[SendingDate] [datetime] NOT NULL,
	[AnsDate] [datetime] NULL
)

GO
INSERT [dbo].[Query] ([SenderAcc], [Subject], [Query], [Response], [SendingDate], [AnsDate]) VALUES (N'C001      ', N'Password                                ', N'How to change Password?', N'Use option which is at bottom of ur MyAccount Page', CAST(0x00009FEB00000000 AS DateTime), CAST(0x0000A0FF000220EC AS DateTime))
GO
INSERT [dbo].[Query] ([SenderAcc], [Subject], [Query], [Response], [SendingDate], [AnsDate]) VALUES (N'C002      ', N'No subject                              ', N'Hoe to share Money Points?', NULL, CAST(0x00009E7F00000000 AS DateTime), NULL)
GO
INSERT [dbo].[Query] ([SenderAcc], [Subject], [Query], [Response], [SendingDate], [AnsDate]) VALUES (N'C001      ', N'No subject                              ', N'How to Send Query?', N'Use Query option from your Home Page', CAST(0x0000A01100000000 AS DateTime), CAST(0x0000A10600000000 AS DateTime))
GO
INSERT [dbo].[Query] ([SenderAcc], [Subject], [Query], [Response], [SendingDate], [AnsDate]) VALUES (N'C001      ', N'No subject                              ', N'How to buy', NULL, CAST(0x0000A01700000000 AS DateTime), NULL)
GO
INSERT [dbo].[Query] ([SenderAcc], [Subject], [Query], [Response], [SendingDate], [AnsDate]) VALUES (N'V001      ', N'No subject                              ', N'How to Share Money Point?', NULL, CAST(0x0000A01A00000000 AS DateTime), NULL)
GO
INSERT [dbo].[Query] ([SenderAcc], [Subject], [Query], [Response], [SendingDate], [AnsDate]) VALUES (N'C001      ', N'Account Balance                         ', N'How to check Account Balance', N'You can see details by selection option My Profile From Home Page', CAST(0x0000A01A00000000 AS DateTime), CAST(0x0000A0FF00DEAB84 AS DateTime))
GO
INSERT [dbo].[Query] ([SenderAcc], [Subject], [Query], [Response], [SendingDate], [AnsDate]) VALUES (N'C001      ', N'Share Money                             ', N'How to share Money Points?', NULL, CAST(0x0000A01B00000000 AS DateTime), NULL)
GO
INSERT [dbo].[Query] ([SenderAcc], [Subject], [Query], [Response], [SendingDate], [AnsDate]) VALUES (N'V001      ', N'No subject                              ', N'How to change PassWord?', NULL, CAST(0x0000A03600000000 AS DateTime), NULL)
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profile](
	[AccNo] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Password] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Name] [nchar](20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Gender] [nchar](6) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[DOB] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[ContactNo] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Addr] [nchar](40) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Type] [nchar](8) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Email] [nchar](40) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[BankName] [nchar](20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[BankAccNo] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[MoneyPoints] [float] NULL
)

GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'C001      ', N'c1234567  ', N'Mr.Kumar Madukar    ', N'Male  ', N'12-2-1967 ', N'65748337  ', N'Pune                                    ', N'CUSTOMER', N'kumars@gamil.com                        ', N'SBI                 ', N'321312    ', 165335.25)
GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'C003      ', N'c1234567  ', N'Mr.Pratik Rane      ', N'Male  ', N'2-21950   ', N'53829269  ', N'Paud Road,Kothrud                       ', N'CUSTOMER', N'pratik@yahoo.co.in                      ', N'SBI,Paud Road       ', N'73295887  ', 31150)
GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'A001      ', N'a1234567  ', N'Miss Puja Thakur    ', N'Female', N'2-2-1989  ', N'79739261  ', N'Kondhava                                ', N'ADMIN   ', N'pujac@gmail.com                         ', N'BOM                 ', N'53445666  ', 30000)
GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'V002      ', N'v1234567  ', N'Mrs.Shital Ranade   ', N'Female', N'3-3-2000  ', N'64291873  ', N'Deccan                                  ', N'VENDOR  ', N'shital@yahoo.co.in                      ', N'BOI                 ', N'76547724  ', 10114.75)
GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'V003      ', N'v1234567  ', N'Anand               ', N'Male  ', N'3-09-1988 ', N'58235281  ', N'Kothrud                                 ', N'VENDOR  ', N'ana@gmail.com                           ', N'BOM                 ', N'46787869  ', 42555)
GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'C002      ', N'c1234567  ', N'Mr.Rahul Joshi      ', N'Male  ', N'5-5-1976  ', N'42820373  ', N'london                                  ', N'CUSTOMER', N'rahulD@rediffmail.com                   ', N'AXIS                ', N'65756679  ', 11205)
GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'V001      ', N'v1234567  ', N'Mrs.Priya Dixit     ', N'Female', N'6-10-1978 ', N'16392383  ', N'Pune                                    ', N'VENDOR  ', N'priyag@gmail.com                        ', N'CANARA              ', N'67577909  ', 10630)
GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'C004      ', N'c1234567  ', N'Tanishq             ', N'male  ', N'20-04-1982', N'538758383 ', N'Pune                                    ', N'customer', N't@gmail.com                             ', N'BOI                 ', N'346745    ', 20000)
GO
INSERT [dbo].[Profile] ([AccNo], [Password], [Name], [Gender], [DOB], [ContactNo], [Addr], [Type], [Email], [BankName], [BankAccNo], [MoneyPoints]) VALUES (N'C005      ', N'c1234567  ', N'Miss. Yogita        ', N'female', N'02-12-1990', N'647483937 ', N'Samgamner                               ', N'customer', N'yogita@gmail.com                        ', N'ICICI               ', N'648399    ', 0)
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Name] [nchar](20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Quantity] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[Category] [nchar](20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Notes] [nchar](30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[Manufacturer] [nchar](20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[Discount] [float] NOT NULL,
	[ShippingCost] [float] NOT NULL,
	[ProcessingTime] [nchar](20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[VendorAcc] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL
)

GO
INSERT [dbo].[Product] ([ProductID], [Name], [Quantity], [Price], [Category], [Notes], [Manufacturer], [Discount], [ShippingCost], [ProcessingTime], [VendorAcc]) VALUES (N'P003      ', N'Cake                ', 18, 50, N'Food                ', N'Eat                           ', N'Baker''s Basket      ', 5, 10, N'2                   ', N'V001      ')
GO
INSERT [dbo].[Product] ([ProductID], [Name], [Quantity], [Price], [Category], [Notes], [Manufacturer], [Discount], [ShippingCost], [ProcessingTime], [VendorAcc]) VALUES (N'P006      ', N'Pen                 ', 2, 55, N'General             ', N'Write                         ', N'Cello               ', 5, 5, N'3                   ', N'V001      ')
GO
INSERT [dbo].[Product] ([ProductID], [Name], [Quantity], [Price], [Category], [Notes], [Manufacturer], [Discount], [ShippingCost], [ProcessingTime], [VendorAcc]) VALUES (N'P002      ', N'Pepsi               ', 14, 15, N'Cold Drink          ', N'Coke                          ', N'Pepsi               ', 2, 5, N'1 day               ', N'V001      ')
GO
INSERT [dbo].[Product] ([ProductID], [Name], [Quantity], [Price], [Category], [Notes], [Manufacturer], [Discount], [ShippingCost], [ProcessingTime], [VendorAcc]) VALUES (N'P005      ', N'Dell                ', 16, 30000, N'Laptop              ', N'500 HDD                       ', N'Dell                ', 10, 300, N'2 Days              ', N'V001      ')
GO
INSERT [dbo].[Product] ([ProductID], [Name], [Quantity], [Price], [Category], [Notes], [Manufacturer], [Discount], [ShippingCost], [ProcessingTime], [VendorAcc]) VALUES (N'P007      ', N'Samsang             ', 9, 3000, N'Mobile              ', N'3 MP Camera                   ', N'Samsang             ', 2, 5, N'2 Days              ', N'V001      ')
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Logs](
	[SenderAcc] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Date_Time] [datetime] NOT NULL,
	[ReceiverAcc] [nchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Type] [nchar](20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[Amount] [float] NOT NULL
)

GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'C001      ', CAST(0x0000A02100000000 AS DateTime), N'C002      ', N'Share Money Points  ', 100)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'V002      ', CAST(0x0000A02100000000 AS DateTime), N'C002      ', N'Share Money Points  ', 105)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'C001      ', CAST(0x0000A02200000000 AS DateTime), N'C003      ', N'Share Money Points  ', 150)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'C003      ', CAST(0x0000A02200000000 AS DateTime), N'V003      ', N'Share Money Points  ', 200)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'C001      ', CAST(0x0000996400000000 AS DateTime), N'V009      ', N'Share               ', 100)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'C001      ', CAST(0x0000960C00000000 AS DateTime), N'V007      ', N'Share               ', 200)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'C002      ', CAST(0x0000A02400000000 AS DateTime), N'V002      ', N'Product - Pen       ', 57.25)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'C001      ', CAST(0x0000A02E00000000 AS DateTime), N'V002      ', N'Product - Cake      ', 57.5)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'V001      ', CAST(0x0000A02E00000000 AS DateTime), N'C002      ', N'Share Money Points  ', 200)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'V003      ', CAST(0x0000A03600000000 AS DateTime), N'C002      ', N'Share Money Points  ', 500)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'V001      ', CAST(0x0000A03600000000 AS DateTime), N'C001      ', N'Share Money Points  ', 10)
GO
INSERT [dbo].[Logs] ([SenderAcc], [Date_Time], [ReceiverAcc], [Type], [Amount]) VALUES (N'C001      ', CAST(0x0000A03700000000 AS DateTime), N'V003      ', N'Product - Samsang   ', 2945)
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE dbo.CursorForListOfProduct
(
       @sVendorAccNo nchar(20)
)
	AS
	BEGIN
	
		SELECT  ProductID, Name,Price
FROM Product where VendorAcc=@sVendorAccNo;
END


GO
