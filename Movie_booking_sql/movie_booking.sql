CREATE TABLE `Booked_Seats` (
  `id` int NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Seat_Id` int NOT NULL,
  `Show_Id` int NOT NULL,
  `Booking_Id` int DEFAULT NULL,
  `Price` double DEFAULT NULL,
  PRIMARY KEY (`Status`,`Seat_Id`,`Show_Id`),
  KEY `Booked_Booking_Id_idx` (`Booking_Id`),
  KEY `Booked_Seat_id_FK_idx` (`Seat_Id`),
  KEY `Booked_seat_shows_fk_idx` (`Show_Id`),
  CONSTRAINT `Booked_Booking_Id` FOREIGN KEY (`Booking_Id`) REFERENCES `Booking` (`id`),
  CONSTRAINT `Booked_Seat_id_FK` FOREIGN KEY (`Seat_Id`) REFERENCES `Seat` (`id`),
  CONSTRAINT `Booked_seat_shows_fk` FOREIGN KEY (`Show_Id`) REFERENCES `Shows` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Booking` (
  `id` int NOT NULL,
  `User_Id` int DEFAULT NULL,
  `Total_Seats_booked` int DEFAULT NULL,
  `Booked_Date` datetime DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Show_Id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`User_Id`),
  KEY `booking_shows_fk_idx` (`Show_Id`),
  CONSTRAINT `booking_shows_fk` FOREIGN KEY (`Show_Id`) REFERENCES `Shows` (`id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`User_Id`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `City` (
  `id` int NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `State` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Locked_Seats` (
  `id` int NOT NULL,
  `Seat_Id` int NOT NULL,
  `Status` enum('LOCKED','RELEASED') NOT NULL,
  `Show_id` int NOT NULL,
  `Updated_DateTime` datetime NOT NULL,
  PRIMARY KEY (`Seat_Id`,`Status`,`Show_id`),
  KEY `PRIMARY KEY` (`Seat_Id`,`Status`,`Show_id`),
  KEY `Locked_seats_show_id_idx` (`Show_id`),
  CONSTRAINT `Locked_Seats-Seat_id_fk` FOREIGN KEY (`Seat_Id`) REFERENCES `Seat` (`id`),
  CONSTRAINT `Locked_seats_show_id` FOREIGN KEY (`Show_id`) REFERENCES `Shows` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Movie` (
  `Id` int NOT NULL,
  `Name` varchar(200) DEFAULT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `Genre` varchar(50) DEFAULT NULL,
  `Release_Date` datetime DEFAULT NULL,
  `Duration_In_Minutes` int DEFAULT NULL,
  `Cast_Details` varchar(500) DEFAULT NULL,
  `Language` varchar(50) DEFAULT NULL,
  `Image_url` varchar(100) DEFAULT NULL,
  `Format` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Payment` (
  `id` int NOT NULL,
  `Price_paid` double DEFAULT NULL,
  `Payment_Method` varchar(45) DEFAULT NULL,
  `Transaction_Date` datetime DEFAULT NULL,
  `TransactionId` varchar(100) DEFAULT NULL,
  `Coupon_Code` varchar(100) DEFAULT NULL,
  `Booking_Id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_id_fk_idx` (`Booking_Id`),
  CONSTRAINT `booking_id_fk` FOREIGN KEY (`Booking_Id`) REFERENCES `Booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Screen` (
  `id` int NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Total_Seats` int DEFAULT NULL,
  `Theater_ID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Theater_idx` (`Theater_ID`),
  CONSTRAINT `Theater` FOREIGN KEY (`Theater_ID`) REFERENCES `Theater` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Seat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Screen_Id` int DEFAULT NULL,
  `RowId` varchar(10) DEFAULT NULL,
  `RowNum` int DEFAULT NULL,
  `Class_Id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Screen_Id_idx` (`Screen_Id`),
  KEY `Class_Id_FK_idx` (`Class_Id`),
  CONSTRAINT `Class_Id_FK` FOREIGN KEY (`Class_Id`) REFERENCES `Seat_Class` (`id`),
  CONSTRAINT `Screen_Id_FK` FOREIGN KEY (`Screen_Id`) REFERENCES `Screen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Seat_Class` (
  `id` int NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `List_Price` double DEFAULT NULL,
  `Display_Order` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Shows` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Show_Date` datetime DEFAULT NULL,
  `Start_Time` datetime DEFAULT NULL,
  `End_Time` datetime DEFAULT NULL,
  `Movie_ID` int DEFAULT NULL,
  `Screen_ID` int DEFAULT NULL,
  `Theater_ID` int DEFAULT NULL,
  `Available_Seats` int DEFAULT NULL,
  `Total_Seats` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Screen_Id_idx` (`Screen_ID`),
  KEY `Movie_Id_idx` (`Movie_ID`),
  KEY `Theater_id_fk_idx` (`Theater_ID`),
  CONSTRAINT `Movie_Id` FOREIGN KEY (`Movie_ID`) REFERENCES `Movie` (`Id`),
  CONSTRAINT `Screen_Id` FOREIGN KEY (`Screen_ID`) REFERENCES `Screen` (`id`),
  CONSTRAINT `Theater_id_fk` FOREIGN KEY (`Theater_ID`) REFERENCES `Theater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Theater` (
  `id` int NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `City_Id` int DEFAULT NULL,
  `Total_Halls` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `City_ID_idx` (`City_Id`),
  CONSTRAINT `City_ID` FOREIGN KEY (`City_Id`) REFERENCES `City` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `User` (
  `Id` int NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Email_ID` varchar(100) DEFAULT NULL,
  `Password` varchar(30) DEFAULT NULL,
  `Phone_No` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


