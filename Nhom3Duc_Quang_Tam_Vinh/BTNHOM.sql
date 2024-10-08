USE [master]
GO
/****** Object:  Database [QuanLyQuayThuoc]    Script Date: 14/05/2023 1:31:27 PM ******/
CREATE DATABASE [QuanLyQuayThuoc]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLQT_DATA', FILENAME = N'G:\QuanLyQuayThuoc\QLQT_DATA.MDF' , SIZE = 10240KB , MAXSIZE = 102400KB , FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QLQT_LOG', FILENAME = N'G:\QuanLyQuayThuoc\QLQT_LOG.LDF' , SIZE = 10240KB , MAXSIZE = 102400KB , FILEGROWTH = 1024KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLyQuayThuoc] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyQuayThuoc].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyQuayThuoc] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyQuayThuoc] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyQuayThuoc] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QuanLyQuayThuoc] SET QUERY_STORE = OFF
GO
USE [QuanLyQuayThuoc]
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDCV]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDCV]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MACV) FROM CHUCVU) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MACV, 3)) FROM CHUCVU
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'CV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'CV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDHDBH]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDHDBH]()
RETURNS VARCHAR(7)
AS
BEGIN
	DECLARE @ID VARCHAR(7)
	IF (SELECT COUNT(MAHDBH) FROM HoaDonBanHang) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MAHDBH, 3)) FROM HoaDonBanHang
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'HDBH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'HDBH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDHDNH]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDHDNH]()
RETURNS VARCHAR(7)
AS
BEGIN
	DECLARE @ID VARCHAR(7)
	IF (SELECT COUNT(MAHDNH) FROM HoaDonNhapHang) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MAHDNH, 3)) FROM HoaDonNhapHang
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'HDNH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'HDNH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDKH]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDKH]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MAKH) FROM KHACHHANG) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MAKH, 3)) FROM KHACHHANG
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'KH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'KH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDLT]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDLT]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MALOAI) FROM LOAITHUOC) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MALOAI, 3)) FROM LOAITHUOC
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'LT00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'LT0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDNCC]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDNCC]()
RETURNS VARCHAR(6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(MANHACC) FROM NHACUNGCAP) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MANHACC, 3)) FROM NHACUNGCAP
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NCC00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NCC0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDNV]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDNV]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MANV) FROM NHANVIEN) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MANV, 3)) FROM NHANVIEN
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDT]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDT]()
RETURNS VARCHAR(4)
AS
BEGIN
	DECLARE @ID VARCHAR(4)
	IF (SELECT COUNT(MAT) FROM THUOC) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MAT, 3)) FROM THUOC
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'T00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'T0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  Table [dbo].[CT_HoaDonBanHang]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_HoaDonBanHang](
	[MaHDBH] [char](7) NOT NULL,
	[MAT] [char](4) NOT NULL,
	[SOLUONG] [int] NULL,
	[DONGIA] [float] NULL,
 CONSTRAINT [pk_CTHDBH] PRIMARY KEY CLUSTERED 
(
	[MaHDBH] ASC,
	[MAT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_HoaDonNhapHang]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_HoaDonNhapHang](
	[MaHDNH] [char](7) NOT NULL,
	[MAT] [char](4) NOT NULL,
	[SOLUONG] [int] NULL,
	[DONGIA] [float] NULL,
 CONSTRAINT [pk_CTHDNH] PRIMARY KEY CLUSTERED 
(
	[MaHDNH] ASC,
	[MAT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[MACV] [char](5) NOT NULL,
	[TENCV] [nvarchar](30) NOT NULL,
	[HESOLUONG] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MACV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonBanHang]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonBanHang](
	[MaHDBH] [char](7) NOT NULL,
	[MAKH] [char](5) NOT NULL,
	[MANV] [char](5) NOT NULL,
	[NGAYLAPHD] [date] NULL,
	[TONGTIEN] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHDBH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonNhapHang]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonNhapHang](
	[MaHDNH] [char](7) NOT NULL,
	[MANHACC] [char](6) NOT NULL,
	[MANV] [char](5) NOT NULL,
	[NGAYLAPHD] [date] NULL,
	[TONGTIEN] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHDNH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MAKH] [char](5) NOT NULL,
	[TENKH] [nvarchar](30) NOT NULL,
	[GIOITINH] [nvarchar](5) NOT NULL,
	[NGAYSINH] [date] NULL,
	[EMAIL] [char](30) NULL,
	[DIENTHOAI] [char](12) NULL,
	[CMND] [int] NULL,
	[DIACHI] [nvarchar](40) NULL,
PRIMARY KEY CLUSTERED 
(
	[MAKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiThuoc]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiThuoc](
	[MALOAI] [char](5) NOT NULL,
	[TENLOAI] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MALOAI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MANHACC] [char](6) NOT NULL,
	[TENNHACC] [nvarchar](30) NOT NULL,
	[DIACHI] [nvarchar](50) NOT NULL,
	[SODT] [char](30) NOT NULL,
	[EMAIL] [char](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MANHACC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MANV] [char](5) NOT NULL,
	[MACV] [char](5) NOT NULL,
	[TENNV] [nvarchar](30) NOT NULL,
	[GIOITINH] [nvarchar](5) NOT NULL,
	[NGAYSINH] [date] NULL,
	[EMAIL] [char](30) NULL,
	[DIENTHOAI] [char](12) NULL,
	[CMND] [int] NULL,
	[DIACHI] [nvarchar](40) NULL,
	[NGAYVAOLAM] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[TENDN] [char](15) NOT NULL,
	[MATKHAU] [char](30) NOT NULL,
	[MA] [char](7) NOT NULL,
	[TEN] [nvarchar](40) NOT NULL,
	[QUYEN] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TENDN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Thuoc]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Thuoc](
	[MAT] [char](4) NOT NULL,
	[MALOAI] [char](5) NOT NULL,
	[TENTHUOC] [nvarchar](30) NOT NULL,
	[THANHPHAN] [nvarchar](100) NULL,
	[CONGDUNG] [nvarchar](50) NOT NULL,
	[DONVITINH] [nvarchar](70) NULL,
	[XUATXU] [nvarchar](50) NULL,
	[SOLUONG] [int] NULL,
	[GIABAN] [float] NULL,
	[NGAYHETHAN] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[MAT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH017', N'T001', 40, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH019', N'T001', 20, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH024', N'T001', 10, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH030', N'T001', 10, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH031', N'T001', 10, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH032', N'T001', 10, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH033', N'T002', 40, 1323)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH034', N'T001', 10, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH035', N'T002', 10, 1323)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH036', N'T002', 10, 1323)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH037', N'T002', 10, 1323)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH038', N'T002', 10, 1323)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH039', N'T001', 30, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH040', N'T002', 50, 1323)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH041', N'T005', 40, 33)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH042', N'T002', 10, 1323)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH043', N'T005', 10, 33)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH044', N'T006', 24, 213)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH044', N'T009', 53, 4)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH045', N'T001', 73, 80000)
INSERT [dbo].[CT_HoaDonBanHang] ([MaHDBH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDBH045', N'T007', 24, 213)
GO
INSERT [dbo].[CT_HoaDonNhapHang] ([MaHDNH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDNH006', N'T002', 220, 1323)
INSERT [dbo].[CT_HoaDonNhapHang] ([MaHDNH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDNH007', N'T003', 1200, 1123)
INSERT [dbo].[CT_HoaDonNhapHang] ([MaHDNH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDNH008', N'T004', 220, 22333)
INSERT [dbo].[CT_HoaDonNhapHang] ([MaHDNH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDNH009', N'T005', 232, 33)
INSERT [dbo].[CT_HoaDonNhapHang] ([MaHDNH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDNH010', N'T006', 324, 213)
INSERT [dbo].[CT_HoaDonNhapHang] ([MaHDNH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDNH010', N'T007', 324, 213)
INSERT [dbo].[CT_HoaDonNhapHang] ([MaHDNH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDNH011', N'T008', 453, 4)
INSERT [dbo].[CT_HoaDonNhapHang] ([MaHDNH], [MAT], [SOLUONG], [DONGIA]) VALUES (N'HDNH011', N'T009', 453, 4)
GO
INSERT [dbo].[ChucVu] ([MACV], [TENCV], [HESOLUONG]) VALUES (N'CV001', N'Nhân Viên Kinh Doanh', 2)
INSERT [dbo].[ChucVu] ([MACV], [TENCV], [HESOLUONG]) VALUES (N'CV002', N'Nhân Viên Thu Ngân', 3)
GO
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH001', N'KH001', N'NV001', CAST(N'2023-05-02' AS Date), 2315413)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH002', N'KH001', N'NV001', CAST(N'2023-05-02' AS Date), 2123)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH003', N'KH002', N'NV001', CAST(N'2023-05-02' AS Date), 0)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH004', N'KH001', N'NV001', CAST(N'2023-05-02' AS Date), 0)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH005', N'KH001', N'NV001', CAST(N'2023-05-02' AS Date), 0)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH006', N'KH001', N'NV001', CAST(N'2023-05-02' AS Date), 0)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH007', N'KH003', N'NV001', CAST(N'2023-05-02' AS Date), 0)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH008', N'KH002', N'NV001', CAST(N'2023-05-02' AS Date), 0)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH009', N'KH001', N'NV001', CAST(N'2023-05-02' AS Date), 0)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH010', N'KH001', N'NV001', CAST(N'2023-05-02' AS Date), 0)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH011', N'KH001', N'NV001', CAST(N'2023-05-03' AS Date), 1680000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH012', N'KH003', N'NV001', CAST(N'2023-05-03' AS Date), 126)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH013', N'KH001', N'NV001', CAST(N'2023-05-03' AS Date), 72.45)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH014', N'KH001', N'NV001', CAST(N'2023-05-03' AS Date), 217.35)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH015', N'KH002', N'NV001', CAST(N'2023-05-03' AS Date), 241.5)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH016', N'KH001', N'NV001', CAST(N'2023-05-03' AS Date), 5880000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH017', N'KH001', N'NV001', CAST(N'2023-05-03' AS Date), 3360000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH018', N'KH001', N'NV001', CAST(N'2023-05-03' AS Date), 37.8)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH019', N'KH003', N'NV001', CAST(N'2023-05-03' AS Date), 1680000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH020', N'KH001', N'NV001', CAST(N'2023-05-03' AS Date), 126)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH021', N'KH001', N'NV001', CAST(N'2023-05-03' AS Date), 728377650)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH022', N'KH003', N'NV001', CAST(N'2023-05-03' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH023', N'KH004', N'NV001', CAST(N'2023-05-03' AS Date), 1682970.45)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH024', N'KH003', N'NV001', CAST(N'2023-05-03' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH025', N'KH001', N'NV001', CAST(N'2023-05-04' AS Date), 291373.95)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH026', N'KH002', N'NV001', CAST(N'2023-05-04' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH027', N'KH001', N'NV001', CAST(N'2023-05-04' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH028', N'KH001', N'NV001', CAST(N'2023-05-04' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH029', N'KH001', N'NV001', CAST(N'2023-05-04' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH030', N'KH003', N'NV001', CAST(N'2023-05-09' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH031', N'KH001', N'NV001', CAST(N'2023-05-09' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH032', N'KH003', N'NV001', CAST(N'2023-05-09' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH033', N'KH003', N'NV001', CAST(N'2023-05-09' AS Date), 83349)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH034', N'KH002', N'NV001', CAST(N'2023-05-09' AS Date), 840000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH035', N'KH001', N'NV001', CAST(N'2023-05-09' AS Date), 13891.5)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH036', N'KH002', N'NV001', CAST(N'2023-05-09' AS Date), 13891.5)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH037', N'KH001', N'NV001', CAST(N'2023-05-09' AS Date), 13891.5)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH038', N'KH001', N'NV001', CAST(N'2023-05-09' AS Date), 13891.5)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH039', N'KH001', N'NV001', CAST(N'2023-05-09' AS Date), 2520000)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH040', N'KH002', N'NV001', CAST(N'2023-05-12' AS Date), 69457.5)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH041', N'KH001', N'NV001', CAST(N'2023-05-12' AS Date), 1386)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH042', N'KH002', N'NV001', CAST(N'2023-05-12' AS Date), 13891.5)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH043', N'KH001', N'NV001', CAST(N'2023-05-12' AS Date), 346.5)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH044', N'KH002', N'NV001', CAST(N'2023-05-13' AS Date), 5590.2)
INSERT [dbo].[HoaDonBanHang] ([MaHDBH], [MAKH], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDBH045', N'KH001', N'NV001', CAST(N'2023-05-14' AS Date), 9413367.6)
GO
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH001', N'NCC001', N'NV001', CAST(N'2023-05-02' AS Date), 94.5)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH002', N'NCC001', N'NV001', CAST(N'2023-05-03' AS Date), 0)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH003', N'NCC001', N'NV001', CAST(N'2023-05-03' AS Date), 21546)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH004', N'NCC001', N'NV001', CAST(N'2023-05-03' AS Date), 8.4)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH005', N'NCC001', N'NV001', CAST(N'2023-05-03' AS Date), 72.45)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH006', N'NCC001', N'NV001', CAST(N'2023-05-09' AS Date), 305613)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH007', N'NCC001', N'NV001', CAST(N'2023-05-09' AS Date), 1414980)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH008', N'NCC001', N'NV001', CAST(N'2023-05-09' AS Date), 5158923)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH009', N'NCC001', N'NV001', CAST(N'2023-05-09' AS Date), 8038.8)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH010', N'NCC001', N'NV001', CAST(N'2023-05-12' AS Date), 144925.2)
INSERT [dbo].[HoaDonNhapHang] ([MaHDNH], [MANHACC], [MANV], [NGAYLAPHD], [TONGTIEN]) VALUES (N'HDNH011', N'NCC001', N'NV001', CAST(N'2023-05-12' AS Date), 3805.2)
GO
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CMND], [DIACHI]) VALUES (N'KH001', N'Mai Lưu Hữu Vinh', N'Nam', CAST(N'2003-10-13' AS Date), N'gg4@gmail.com                 ', N'096969999   ', 13489654, N'Tân Phú, TP.Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CMND], [DIACHI]) VALUES (N'KH002', N'asd', N'Nam', CAST(N'2003-10-24' AS Date), N'gg4@gmail.com                 ', N'096969999   ', 13489654, N'Tân Phú, TP.Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CMND], [DIACHI]) VALUES (N'KH003', N'df', N'Nam', CAST(N'2003-10-24' AS Date), N'gg4@gmail.com                 ', N'096969999   ', 13489654, N'Tân Phú, TP.Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CMND], [DIACHI]) VALUES (N'KH004', N'dfas', N'Nam', CAST(N'2003-10-24' AS Date), N'gg4@gmail.com                 ', N'096969999   ', 13489654, N'Tân Phú, TP.Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CMND], [DIACHI]) VALUES (N'KH005', N'ádasd', N'Nam', CAST(N'2023-05-13' AS Date), N'ádasd                         ', N'001441465   ', 153468, N'ádas')
GO
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT001', N'Thuốc Kháng Dị Ứng')
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT002', N'Thuốc Kháng Viêm')
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT003', N'Thuốc Cảm Lạnh,Ho')
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT004', N'Thuốc Da Liễu')
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT005', N'Thuốc Giảm Cân')
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT006', N'Thuốc Thần Kinh')
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT007', N'Thuốc Giảm Đau, Hạ Sốt')
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT008', N'Thuốc Tiêu Hóa')
INSERT [dbo].[LoaiThuoc] ([MALOAI], [TENLOAI]) VALUES (N'LT009', N'Thuốc Cơ Xương Khớp')
GO
INSERT [dbo].[NhaCungCap] ([MANHACC], [TENNHACC], [DIACHI], [SODT], [EMAIL]) VALUES (N'NCC001', N'Pharmacity', N'Hồ Chí Minh', N'098888888                     ', N'pharmacity@gmail.com          ')
INSERT [dbo].[NhaCungCap] ([MANHACC], [TENNHACC], [DIACHI], [SODT], [EMAIL]) VALUES (N'NCC002', N'ádas', N'ádasd', N'102313                        ', N'ád                            ')
GO
INSERT [dbo].[NhanVien] ([MANV], [MACV], [TENNV], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CMND], [DIACHI], [NGAYVAOLAM]) VALUES (N'NV001', N'CV001', N'Vũ Nguyễn Minh Đức', N'Nam', CAST(N'2000-10-24' AS Date), N'gg@gmail.com                  ', N'0369160539  ', 13489654, N'Tân Phú, TP.Hồ Chí Minh', CAST(N'2020-01-20' AS Date))
INSERT [dbo].[NhanVien] ([MANV], [MACV], [TENNV], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CMND], [DIACHI], [NGAYVAOLAM]) VALUES (N'NV002', N'CV002', N'Hoàng Công Khánh Quang', N'Nam', CAST(N'2000-05-12' AS Date), N'ád                            ', N'0954623123  ', 14156, N'TPHCM', CAST(N'2023-05-13' AS Date))
INSERT [dbo].[NhanVien] ([MANV], [MACV], [TENNV], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CMND], [DIACHI], [NGAYVAOLAM]) VALUES (N'NV003', N'CV001', N'Mai Lưu Hữu Vinh', N'Nam', CAST(N'2002-10-11' AS Date), N'mlhv@gmail.com                ', N'0369160539  ', 134896544, N' TP.Hồ Chí Minh', CAST(N'2020-01-09' AS Date))
GO
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T001', N'LT001', N'Zyrtec', N'Cetirizine dihydrochloride', N'Kháng Dị Ứng', N'Hộp', N'Anh', 127, 80000, CAST(N'2000-08-22' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T002', N'LT003', N'Panadol', N'Panadol', N'Cảm lạnh', N'Viên', N'Pháp', 80, 1323, CAST(N'2023-05-09' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T003', N'LT007', N'paracetamol ', N'acetaminophen', N'Giảm đau hạ sốt', N'Viên', N'Nga', 1200, 1123, CAST(N'2023-05-09' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T004', N'LT003', N'acetaminophen', N'histamin', N'Cảm lạnh ho', N'Hộp', N'Mỹ', 220, 22333, CAST(N'2023-05-09' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T005', N'LT001', N'zczxc', N'zx', N'zzxcxc', N'Tuýp', N'zxc', 182, 33, CAST(N'2023-05-09' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T006', N'LT001', N'á', N'fd', N'á', N'Vỉ', N'fd', 300, 213, CAST(N'2023-05-12' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T007', N'LT001', N'á', N'fd', N'á', N'Vỉ', N'fd', 300, 213, CAST(N'2023-05-12' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T008', N'LT003', N'sáa', N'sa', N'sa', N'Viên', N'sa', 453, 4, CAST(N'2023-05-12' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T009', N'LT001', N'sáa', N'sa', N'sa', N'Viên', N'sa', 400, 4, CAST(N'2023-05-12' AS Date))
INSERT [dbo].[Thuoc] ([MAT], [MALOAI], [TENTHUOC], [THANHPHAN], [CONGDUNG], [DONVITINH], [XUATXU], [SOLUONG], [GIABAN], [NGAYHETHAN]) VALUES (N'T010', N'LT007', N'ád', N'ád', N'ád', N'Viên', N'ád', 123, 123156, CAST(N'2023-05-13' AS Date))
GO
ALTER TABLE [dbo].[ChucVu] ADD  CONSTRAINT [IDCV]  DEFAULT ([DBO].[AUTO_IDCV]()) FOR [MACV]
GO
ALTER TABLE [dbo].[HoaDonBanHang] ADD  CONSTRAINT [IDHDBH]  DEFAULT ([DBO].[AUTO_IDHDBH]()) FOR [MaHDBH]
GO
ALTER TABLE [dbo].[HoaDonNhapHang] ADD  CONSTRAINT [IDHDNH]  DEFAULT ([DBO].[AUTO_IDHDNH]()) FOR [MaHDNH]
GO
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [IDKH]  DEFAULT ([DBO].[AUTO_IDKH]()) FOR [MAKH]
GO
ALTER TABLE [dbo].[LoaiThuoc] ADD  CONSTRAINT [IDLT]  DEFAULT ([DBO].[AUTO_IDLT]()) FOR [MALOAI]
GO
ALTER TABLE [dbo].[NhaCungCap] ADD  CONSTRAINT [IDNCC]  DEFAULT ([DBO].[AUTO_IDNCC]()) FOR [MANHACC]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [IDNV]  DEFAULT ([DBO].[AUTO_IDNV]()) FOR [MANV]
GO
ALTER TABLE [dbo].[Thuoc] ADD  CONSTRAINT [IDT]  DEFAULT ([DBO].[AUTO_IDT]()) FOR [MAT]
GO
ALTER TABLE [dbo].[CT_HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [fk_MaHDBH] FOREIGN KEY([MaHDBH])
REFERENCES [dbo].[HoaDonBanHang] ([MaHDBH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_HoaDonBanHang] CHECK CONSTRAINT [fk_MaHDBH]
GO
ALTER TABLE [dbo].[CT_HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [fk_MAT] FOREIGN KEY([MAT])
REFERENCES [dbo].[Thuoc] ([MAT])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_HoaDonBanHang] CHECK CONSTRAINT [fk_MAT]
GO
ALTER TABLE [dbo].[CT_HoaDonNhapHang]  WITH CHECK ADD  CONSTRAINT [fk_MaHDNH] FOREIGN KEY([MaHDNH])
REFERENCES [dbo].[HoaDonNhapHang] ([MaHDNH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_HoaDonNhapHang] CHECK CONSTRAINT [fk_MaHDNH]
GO
ALTER TABLE [dbo].[CT_HoaDonNhapHang]  WITH CHECK ADD  CONSTRAINT [fk_MALKN] FOREIGN KEY([MAT])
REFERENCES [dbo].[Thuoc] ([MAT])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_HoaDonNhapHang] CHECK CONSTRAINT [fk_MALKN]
GO
ALTER TABLE [dbo].[HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [fk_MaKHMH] FOREIGN KEY([MAKH])
REFERENCES [dbo].[KhachHang] ([MAKH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonBanHang] CHECK CONSTRAINT [fk_MaKHMH]
GO
ALTER TABLE [dbo].[HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [fk_MaNV] FOREIGN KEY([MANV])
REFERENCES [dbo].[NhanVien] ([MANV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonBanHang] CHECK CONSTRAINT [fk_MaNV]
GO
ALTER TABLE [dbo].[HoaDonNhapHang]  WITH CHECK ADD  CONSTRAINT [fk_MaNCCLK] FOREIGN KEY([MANHACC])
REFERENCES [dbo].[NhaCungCap] ([MANHACC])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonNhapHang] CHECK CONSTRAINT [fk_MaNCCLK]
GO
ALTER TABLE [dbo].[HoaDonNhapHang]  WITH CHECK ADD  CONSTRAINT [fk_MaNVNH] FOREIGN KEY([MANV])
REFERENCES [dbo].[NhanVien] ([MANV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonNhapHang] CHECK CONSTRAINT [fk_MaNVNH]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [fk_MaChucVu] FOREIGN KEY([MACV])
REFERENCES [dbo].[ChucVu] ([MACV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [fk_MaChucVu]
GO
ALTER TABLE [dbo].[Thuoc]  WITH CHECK ADD  CONSTRAINT [fk_MaLoai] FOREIGN KEY([MALOAI])
REFERENCES [dbo].[LoaiThuoc] ([MALOAI])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Thuoc] CHECK CONSTRAINT [fk_MaLoai]
GO
/****** Object:  StoredProcedure [dbo].[select_CV]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_CV]
as
	SELECT * FROM [dbo].[ChucVu]
GO
/****** Object:  StoredProcedure [dbo].[select_KH]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_KH]
as
	SELECT * FROM [dbo].[KhachHang]
GO
/****** Object:  StoredProcedure [dbo].[select_LT]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_LT]
as
	SELECT * FROM [dbo].[LoaiThuoc]
GO
/****** Object:  StoredProcedure [dbo].[select_NCC]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_NCC]
as
	SELECT * FROM [dbo].[NhaCungCap]
GO
/****** Object:  StoredProcedure [dbo].[select_NV]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_NV]
as
	SELECT * FROM [dbo].[NhanVien]
GO
/****** Object:  StoredProcedure [dbo].[select_T]    Script Date: 14/05/2023 1:31:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_T]
as
	SELECT * FROM [dbo].[Thuoc]
GO
USE [master]
GO
ALTER DATABASE [QuanLyQuayThuoc] SET  READ_WRITE 
GO
