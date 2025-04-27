create database Cinema
use Cinema

-- 1. Movies
CREATE TABLE Movies (
    MovieID NVARCHAR(50) PRIMARY KEY,
    Title NVARCHAR(100),
    Duration INT, -- phút
    Description NVARCHAR(500)
);

-- 2. MovieCategory
CREATE TABLE MovieCategory (
    CategoryID NVARCHAR(50) PRIMARY KEY,
    CategoryName NVARCHAR(100)
);

-- 3. MovieType (bảng trung gian giữa Movies và MovieCategory)
CREATE TABLE MovieType (
    MovieID NVARCHAR(50),
    CategoryID NVARCHAR(50),
    PRIMARY KEY (MovieID, CategoryID),
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),
    FOREIGN KEY (CategoryID) REFERENCES MovieCategory(CategoryID)
);

-- 4. Room
CREATE TABLE Room (
    RoomID NVARCHAR(50) PRIMARY KEY,
    RoomName NVARCHAR(100),
	Capacity int not null,
	RoomType nvarchar(20) check(RoomType in ('2D', '3D', 'IMAX')) default '3D',
	Status nvarchar(20) default N'Còn trống'
);

-- 5. Seat
CREATE TABLE Seat (
    SeatID NVARCHAR(50) PRIMARY KEY,
    SeatNumber NVARCHAR(10),
    SeatType NVARCHAR(20),
    RoomID NVARCHAR(50),
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);

-- 6. Showtimes
CREATE TABLE Showtimes (
    ShowtimeID NVARCHAR(50) PRIMARY KEY,
    MovieID NVARCHAR(50),
    RoomID NVARCHAR(50),
    ShowDateTime DATETIME,
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);

-- 7. Ticket
CREATE TABLE Ticket (
    TicketID NVARCHAR(50) PRIMARY KEY,
    CustomerID NVARCHAR(50),
    ShowtimeID NVARCHAR(50),
    SeatID NVARCHAR(50),
    Price MONEY,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ShowtimeID) REFERENCES Showtimes(ShowtimeID),
    FOREIGN KEY (SeatID) REFERENCES Seat(SeatID)
);

-- 8. Customer
CREATE TABLE Customer (
    CustomerID NVARCHAR(50) PRIMARY KEY,
    CustomerName NVARCHAR(100),
    Phone NVARCHAR(15),
    Email NVARCHAR(100)
);

-- 9. Employee
CREATE TABLE Employee (
    EmployeeID NVARCHAR(50) PRIMARY KEY,
    EmployeeName NVARCHAR(100),
    Role NVARCHAR(30) CHECK (Role IN (N'Nhân Viên', N'Quản Lý')),
    Phone NVARCHAR(15),
    Email NVARCHAR(100) UNIQUE,
    Address NVARCHAR(200),
    HireDate DATETIME DEFAULT GETDATE(),
    Salary MONEY
);

-- 10. Account
CREATE TABLE Account (
    AccountID NVARCHAR(50) PRIMARY KEY,
    Username NVARCHAR(50) UNIQUE,
    Password NVARCHAR(100),
    EmployeeID NVARCHAR(50) UNIQUE,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

-- 11. Orders
CREATE TABLE Orders (
    OrderID NVARCHAR(50) PRIMARY KEY,
    CustomerID NVARCHAR(50),
    EmployeeID NVARCHAR(50),
    OrderDate DATETIME DEFAULT GETDATE(),
    Total MONEY,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

-- 12. Products
CREATE TABLE Products (
    ProductID NVARCHAR(50) PRIMARY KEY,
    ProductName NVARCHAR(100),
    Price MONEY,
    ProductType NVARCHAR(50)
);

-- 13. OrderDetails
CREATE TABLE OrderDetails (
    OrderID NVARCHAR(50),
    ProductID NVARCHAR(50),
    Quantity INT,
    Price MONEY,
    PRIMARY KEY (OrderID, ProductID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);


ALTER TABLE Movies
ADD AgeRating int not null, ReleaseDate Date not null

ALTER TABLE Showtimes
ADD StartedTime datetime not null,
	EndTime datetime not null

ALTER TABLE OrderDetails
ADD Discount money null