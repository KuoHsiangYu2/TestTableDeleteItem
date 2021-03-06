/* Microsoft SQL Server 資料庫 create sample table 指令 */

USE [DB08]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 2021/1/27 下午 02:52:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
    [pk] [int] NOT NULL,
    [c_name] [nvarchar](200) NULL,
    [c_age] [int] NULL,
    [c_type] [nvarchar](100) NULL,
    [c_show] [nvarchar](10) NULL,
    PRIMARY KEY CLUSTERED 
    (
        [pk] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (1, N'殲滅卿-殲滅之『萊莎』', 35, N'付費玩家', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (2, N'不動卿-不動如山之『奧森』', 18, N'付費玩家', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (3, N'黎明卿-嶄新之『波多爾多』', 18, N'付費玩家', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (4, N'神秘卿-神秘之『斯拉喬』', 18, N'付費玩家', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (5, N'先導卿-被選上之『瓦科納』', 18, N'付費玩家', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (6, N'普魯修卡', 18, N'玩家', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (7, N'莉可', 12, N'玩家', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (8, N'雷古', 12, N'付費玩家', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (9, N'娜娜奇', 16, N'顧問', N'Y')
INSERT [dbo].[Customer] ([pk], [c_name], [c_age], [c_type], [c_show]) VALUES (10, N'吉路歐', 30, N'玩家', N'Y')
GO
