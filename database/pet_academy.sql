-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2014 at 07:29 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pet_academy`
--
CREATE DATABASE IF NOT EXISTS `pet_academy` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `pet_academy`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteClass`(IN `ClassID` INT(8))
    NO SQL
BEGIN
DELETE FROM class WHERE class_id = ClassID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteGradeLevel`(IN `GradeLevel` VARCHAR(2))
    NO SQL
BEGIN
DELETE FROM gr_lvl WHERE gr_lvl = GradeLevel;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteStudent`(IN `StudentID` INT(4))
    NO SQL
BEGIN
DELETE FROM student WHERE student_id = StudentID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteSubject`(IN `SubjectCode` INT(8))
    NO SQL
BEGIN
DELETE FROM subject WHERE subject_code = SubjectCode;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteTeacher`(IN `TeacherID` INT(4))
    NO SQL
BEGIN
DELETE FROM teacher WHERE teacher_id = TeacherID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getClass`(IN `class` VARCHAR(8))
    NO SQL
SELECT class_id, gr_lvl, teacher_id, start_time, end_time
FROM class
WHERE class_id = class$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getClassList`()
BEGIN
	Select * from class;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getGradeLvlList`()
BEGIN
	Select * from gr_lvl;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getGrLvl`(IN `gLvl` VARCHAR(2))
    NO SQL
SELECT gr_lvl, description
FROM gr_lvl
WHERE gr_lvl=gLvl$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getStudent`(IN `stud_id` INT(4))
    NO SQL
SELECT l_name,
    f_name,
    mi,
    birthdate,
    g_name,
    g_contact_num,
    class_id
FROM student
WHERE student_id=stud_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getStudentList`()
BEGIN
	SELECT student_id, CONCAT( l_name,  ', ', f_name,  ' ', mi,  '.' ) name, class_id, birthdate, g_name, g_contact_num
	FROM student;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getSubject`(IN `subj_cd` VARCHAR(8))
    NO SQL
SELECT subject_code, subject_name, gr_lvl
FROM subject
WHERE subject_code = subj_cd$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getSubjectList`()
BEGIN
	Select * from subject;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getTeacher`(IN `t_id` INT(4))
    NO SQL
SELECT l_name, f_name, mi, birthdate, contact_num
FROM teacher
WHERE teacher_id = t_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getTeacherList`()
BEGIN
	SELECT teacher_id, CONCAT( l_name,  ', ', f_name,  ' ', mi,  '.' ) name, birthdate, contact_num
	FROM teacher;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertClass`(in classID varchar(8), in gradeLvl varchar(2), in teacherID Int(4), in startTime time, endtime time)
BEGIN
	Insert into class(class_id, gr_lvl,teacher_id, start_time, end_time) values (classID, gradeLvl, teacherID, startTime, endTime);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertGradeLvl`(in gradeLvl VARCHAR(2), in gradeDesc VARCHAR(200))
BEGIN
	Insert into gr_lvl(gr_lvl, description) values (gradeLvl, gradeDesc);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertStudent`(IN `studentLastName` VARCHAR(20), IN `studentFirstName` VARCHAR(25), IN `studentMI` VARCHAR(1), IN `studentBday` DATE, IN `guardianName` VARCHAR(50), IN `guardianContactNum` VARCHAR(16), IN `classID` VARCHAR(8))
BEGIN
	Insert into student(l_name, f_name, mi, birthdate, g_name, g_contact_num, class_id) values (studentLastName, studentFirstName, studentMI, studentBday, guardianName, guardianContactNum, classID);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertSubject`(in subCode varchar(8), in subName varchar(30), in gradeLvl varchar(2))
BEGIN
	insert into subject(subject_code, subject_name, gr_lvl) values (subCode, subName, gradeLVl);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertTeacher`(in teacherLastName varchar(20), in teacherFirstName varchar(25), in teacherMI varchar(1), in teacherBday date, in teacherContactNum varchar(16))
BEGIN
	Insert into teacher(l_name, f_name, mi, birthdate, contact_num) values (teacherLastName, teacherFirstName, teacherMI, teacherBday, teacherContactNum);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateClass`(IN `classID` VARCHAR(8), IN `gradeLvl` VARCHAR(2), IN `teacherID` INT(4), IN `startTime` TIME, IN `endTime` TIME)
BEGIN
	Update class set gr_lvl= gradeLvl, teacher_id= teacherID, start_time= startTime, end_time= endTime where class_id= classID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateGradeLvl`(IN `gradeLvl` VARCHAR(2), IN `gradeDesc` VARCHAR(200))
BEGIN
	Update gr_lvl set description= gradeDesc where gr_lvl= gradeLvl;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudent`(IN `studentID` INT(4), IN `studentLAstName` VARCHAR(20), IN `studentFirstName` VARCHAR(25), IN `studentMI` VARCHAR(1), IN `studentBday` DATE, IN `guardianName` VARCHAR(50), IN `guardianContactNum` VARCHAR(16), IN `classID` VARCHAR(8))
BEGIN
	UPDATE student set l_name= studentLastName, f_name= studentFirstName, mi= studentMI, birthdate= studentBday, g_name= guardianName, g_contact_num= guardianContactNum, class_id= classID where student_id= studentID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateSubject`(IN `subCode` VARCHAR(8), IN `subName` VARCHAR(30), IN `gradeLvl` VARCHAR(2))
BEGIN
	Update subject set subject_name= subName, gr_lvl= gradeLVl where subject_code= subCode;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTeacher`(IN `teacherID` INT(4), IN `teacherLastName` VARCHAR(20), IN `teacherFirstName` VARCHAR(25), IN `teacherMI` VARCHAR(1), IN `teacherBday` DATE, IN `contactNum` VARCHAR(16))
BEGIN
	Update teacher set l_name= teacherLastName, f_name= teacherFirstName, mi= teacherMI, birthdate= teacherBday, contact_num= contactNum where teacher_id= teacherID;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `class_id` varchar(8) NOT NULL,
  `gr_lvl` varchar(2) NOT NULL,
  `teacher_id` int(4) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  KEY `gr_lvl` (`gr_lvl`),
  KEY `teacher_id` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Synonymous to "section". Has the grade level, schedule and adviser for a class.';

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`class_id`, `gr_lvl`, `teacher_id`, `start_time`, `end_time`) VALUES
('K1-A', 'K1', 1, '08:00:00', '10:00:00'),
('K1-B', 'K1', 4, '08:00:00', '10:00:00'),
('K1-C', 'K1', 2, '13:00:00', '15:00:00'),
('K1-D', 'K1', 6, '10:00:00', '12:00:00'),
('K2-A', 'K2', 3, '13:00:00', '15:00:00'),
('K2-B', 'K2', 3, '13:00:00', '15:00:00'),
('K2-C', 'K2', 2, '10:00:00', '12:00:00'),
('K2-D', 'K2', 6, '08:00:00', '10:00:00'),
('N-A', 'N', 2, '08:00:00', '10:00:00'),
('N-B', 'N', 4, '08:00:00', '10:00:00'),
('N-C', 'N', 1, '13:00:00', '15:00:00'),
('N-D', 'N', 5, '10:00:00', '12:00:00');

--
-- Triggers `class`
--
DROP TRIGGER IF EXISTS `insertClassTigger`;
DELIMITER //
CREATE TRIGGER `insertClassTigger` BEFORE INSERT ON `class`
 FOR EACH ROW BEGIN
    IF (NEW.end_time IS NULL OR NEW.end_time = '00:00:00' OR 
        NEW.end_time = '') THEN
        SET NEW.end_time = ADDTIME(NEW.start_time, '2:00:00');
    END IF;
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `updateClassTrigger`;
DELIMITER //
CREATE TRIGGER `updateClassTrigger` BEFORE UPDATE ON `class`
 FOR EACH ROW BEGIN
	IF(NEW.end_time IS NULL OR NEW.end_time = '00:00:00'
      OR NEW.end_time = '') THEN
		SET NEW.end_time = ADDTIME(NEW.start_time, '2:00:00');
	END IF;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `gr_lvl`
--

CREATE TABLE IF NOT EXISTS `gr_lvl` (
  `gr_lvl` varchar(2) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`gr_lvl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Code Table for the grade levels.';

--
-- Dumping data for table `gr_lvl`
--

INSERT INTO `gr_lvl` (`gr_lvl`, `description`) VALUES
('K1', 'Kinder 1'),
('K2', 'Kinder 2'),
('N', 'Nursery');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `student_id` int(4) NOT NULL AUTO_INCREMENT,
  `l_name` varchar(20) NOT NULL,
  `f_name` varchar(25) NOT NULL,
  `mi` varchar(1) DEFAULT NULL,
  `birthdate` date NOT NULL,
  `g_name` varchar(50) NOT NULL,
  `g_contact_num` varchar(16) NOT NULL,
  `class_id` varchar(8) NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `class_id` (`class_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Contains data about the students to enroll.' AUTO_INCREMENT=7 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `l_name`, `f_name`, `mi`, `birthdate`, `g_name`, `g_contact_num`, `class_id`) VALUES
(1, 'Yu', 'Stanley', 'P', '2010-05-20', 'Ava Torrion', '419-5822', 'K1-B'),
(2, 'Liu', 'Clarence', 'C', '2009-06-18', 'Clarence Liu', '261-4546', 'K2-C'),
(3, 'Chan', 'Generlie', 'F', '2010-07-27', 'Leslie Chan', '262-8874', 'K1-C'),
(4, 'Lu', 'Kris', 'P', '1993-01-22', 'Hehehe', '0912302718', 'K2-B'),
(5, 'Li', 'Ji', 'K', '1994-12-15', 'JLo', '15445363', 'K1-A'),
(6, 'Li', 'Gindy', 'L', '2012-11-01', 'Gindy Locion', '123456', 'K1-B');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `subject_code` varchar(8) NOT NULL,
  `subject_name` varchar(30) NOT NULL,
  `gr_lvl` varchar(2) NOT NULL,
  PRIMARY KEY (`subject_code`),
  KEY `gr_lvl` (`gr_lvl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cotains data about the subjects being offered';

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subject_code`, `subject_name`, `gr_lvl`) VALUES
('BASIC1', 'Alphabets', 'N'),
('BASIC2', 'Numbers', 'N'),
('BASIC3', 'Objects', 'N'),
('ENG1', 'English 1', 'K1'),
('ENG2', 'English 2', 'K2'),
('MATH1', 'Mathematics 1', 'K1'),
('MATH2', 'Mathematics 2', 'K2'),
('SCI1', 'Science 1', 'K2');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_id` int(4) NOT NULL AUTO_INCREMENT,
  `l_name` varchar(20) NOT NULL,
  `f_name` varchar(25) NOT NULL,
  `mi` varchar(1) DEFAULT NULL,
  `birthdate` date NOT NULL,
  `contact_num` varchar(16) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Contains data about the teachers of the academy' AUTO_INCREMENT=11 ;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `l_name`, `f_name`, `mi`, `birthdate`, `contact_num`) VALUES
(1, 'Sarrosa', 'Layla', 'F', '1985-06-23', '418-4452'),
(2, 'delos Santos', 'Kris', 'C', '1978-09-29', '418-5692'),
(3, 'Cruz', 'Jake', 'R', '1981-08-11', '261-4826'),
(4, 'Andola', 'Hera', '', '1987-12-09', '432-4421'),
(5, 'Kamiya', 'Hiroshi', 'K', '1992-12-17', '4159962'),
(6, 'Satou', 'Hiroshi', 'D', '1992-12-17', '9875463'),
(7, 'Andou', 'Kunio', 'D', '1992-12-17', '9875463');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_ibfk_1` FOREIGN KEY (`gr_lvl`) REFERENCES `gr_lvl` (`gr_lvl`),
  ADD CONSTRAINT `class_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`);

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`gr_lvl`) REFERENCES `gr_lvl` (`gr_lvl`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
