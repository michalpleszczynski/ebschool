package com.ebschool.test.ejb.utils;

import com.ebschool.ejb.model.*;
import com.ebschool.ejb.model.time.ClassTime;
import com.ebschool.ejb.model.time.Semester;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.Collections;

/**
 * User: michau
 * Date: 5/5/13
 * Time: 12:45 PM
 */
public class DataBuilder {

    private int pinCounter = 0;
    private int emailCounter = 0;
    private int loginCounter = 0;
    private int levelCounter = 0;

    // DEFAULT VALUES

    // user
    private final String PASSWORD = "/xK72MkHrwZwcCEdh73wmL4XN1s=";
    private final String PHONE_NUMBER = "345123789";
    private final String EMAIL_PREFIX = "default";
    private final String EMAIL_SUFFIX = "email.com";
    private final String LOGIN = "login";

    // level
    private final String LEVEL_NAME = "level";

    // test
    private final LocalDateTime TASK_DATE = new LocalDateTime(2012, DateTimeConstants.JANUARY, 26, 15, 30);
    private final String TASK_DESC = "test description";
    private final StudentTask.TaskType TASK_TYPE = StudentTask.TaskType.TEST;

    // grade
    private final String GRADE_COMMENT = "grade_comment";
    private final byte GRADE_PERCENTAGE = 75;
    private final byte GRADE_WEIGHT = 4;

    // address
    private final String STREET = "street";
    private final String COUNTRY = "country";
    private final String ZIP_CODE = "00-000";
    private final String CITY = "city";

    // detailedInfo
    private final LocalDate DATE_OF_BIRTH = new LocalDate(1990, DateTimeConstants.JANUARY, 13);
    private final LocalDate DATE_JOINED = new LocalDate(2011, DateTimeConstants.OCTOBER, 1);
    private final String PIN = "457265345";

    // student
    private final String STUDENT_F_NAME = "student fname";
    private final String STUDENT_L_NAME = "student lname";

    // teacher
    private final String TEACHER_L_NAME = "teacher lname";
    private final String TEACHER_F_NAME = "teacher fname";

    // parent
    private final String PARENT_F_NAME = "parent fname";
    private final String PARENT_L_NAME = "parent lname";

    // class
    private final String CLASS_DESC = "class description";
    private final String CLASS_WHERE = "class location";
    
    // semester
    private final String SEMESTER_NAME = "1st Semester of 2011";
    private final LocalDate SEMESTER_BEGIN_DATE = new LocalDate(2011, DateTimeConstants.OCTOBER, 1);
    private final LocalDate SEMESTER_END_DATE = new LocalDate(2012, DateTimeConstants.FEBRUARY, 15);
    
    // classTime
    private final ClassTime.Day CLASS_TIME_DAY = ClassTime.Day.WEDNESDAY;
    private final LocalTime CLASS_TIME_TIME = new LocalTime(14, 00);


    // keep last created for comparing purposes and such
    private Level lastCreatedLevel;
    private ClassInfo lastCreatedClass;
    private StudentTask lastCreatedStudentTask;
    private Grade lastCreatedGrade;
    private Student lastCreatedStudent;
    private Teacher lastCreatedTeacher;
    private Parent lastCreatedParent;
    private Address lastCreatedAddress;
    private Semester lastCreatedSemester;
    private ClassTime lastCreatedClassTime;

    public Level buildLevel(){
        return buildLevel(getNextLevelName());
    }
    
    public Level buildLevel(String name){
        Level level = new Level();
        level.setName(name);
        lastCreatedLevel = level;
        return lastCreatedLevel;
    }

    public StudentTask buildStudentTask(){
        return buildStudentTask(TASK_DESC, TASK_TYPE);
    }

    public StudentTask buildStudentTask(String description, StudentTask.TaskType type){
        StudentTask studentTask = new StudentTask();
        studentTask.setDescription(description);
        studentTask.setWhen(TASK_DATE.plusDays(1));
        studentTask.setType(type);
        lastCreatedStudentTask = studentTask;
        return lastCreatedStudentTask;
    }

    public Grade buildGrade(){
        return buildGrade(GRADE_COMMENT);
    }

    public Grade buildGrade(String comment){
        Grade grade = new Grade();
        grade.setComment(comment);
        grade.setPercentage(GRADE_PERCENTAGE);
        grade.setWeight(GRADE_WEIGHT);
        lastCreatedGrade = grade;
        return lastCreatedGrade;
    }

    public ClassInfo buildClass(){
        return buildClass(CLASS_DESC);
    }

    public ClassInfo buildClass(String description){
        ClassInfo classInfo = new ClassInfo();
        classInfo.setDescription(description);
        if (lastCreatedLevel == null){
            buildLevel();
        }
        if (lastCreatedClassTime == null){
            buildClassTime();
        }
        if (lastCreatedSemester == null){
            buildSemester();
        }
        classInfo.setSemester(lastCreatedSemester);
        classInfo.setLevel(lastCreatedLevel);
        lastCreatedLevel.setClasses(Collections.singleton(classInfo));
        classInfo.setWhen(lastCreatedClassTime);
        classInfo.setWhere(CLASS_WHERE);
        lastCreatedClass = classInfo;
        return lastCreatedClass;
    }

    public Semester buildSemester(){
        return buildSemester(SEMESTER_BEGIN_DATE, SEMESTER_END_DATE, SEMESTER_NAME);
    }

    public Semester buildSemester(LocalDate beginDate, LocalDate endDate, String name){
        Semester semester = new Semester();
        semester.setBeginDate(beginDate);
        semester.setEndDate(endDate);
        semester.setName(name);
        lastCreatedSemester = semester;
        return lastCreatedSemester;
    }

    public ClassTime buildClassTime(){
        return buildClassTime(CLASS_TIME_DAY, CLASS_TIME_TIME);
    }

    public ClassTime buildClassTime(ClassTime.Day day, LocalTime time){
        ClassTime classTime = new ClassTime();
        classTime.setDay(day);
        classTime.setTime(time);
        lastCreatedClassTime = classTime;
        return lastCreatedClassTime;
    }

    public Student buildStudent(){
        return buildStudent(getNextEmail(), getNextLogin());
    }

    public Student buildStudent(String email, String login){
        Student student = new Student();
        // always build new DetailedInfo
        student.setDetailedInfo(buildDetailedInfo());
        if (lastCreatedLevel == null){
            buildLevel();
        }
        student.setLevel(lastCreatedLevel);
        student.setActive(true);
        student.setEmail(email);
        student.setLogin(login);
        student.setPassword(PASSWORD);
        student.setPhoneNumber(PHONE_NUMBER);
        student.setFirstName(STUDENT_F_NAME);
        student.setLastName(STUDENT_L_NAME);
        student.setType(User.UserType.STUDENT);
        lastCreatedStudent = student;
        return lastCreatedStudent;
    }

    public Teacher buildTeacher(){
        return buildTeacher(getNextEmail(), getNextLogin());
    }

    public Teacher buildTeacher(String email, String login){
        Teacher teacher = new Teacher();
        // always build new DetailedInfo
        teacher.setDetailedInfo(buildDetailedInfo());
        teacher.setEmail(email);
        teacher.setLogin(login);
        teacher.setPassword(PASSWORD);
        teacher.setPhoneNumber(PHONE_NUMBER);
        teacher.setFirstName(TEACHER_F_NAME);
        teacher.setLastName(TEACHER_L_NAME);
        teacher.setType(User.UserType.TEACHER);
        lastCreatedTeacher = teacher;
        return lastCreatedTeacher;
    }

    public Parent buildParent(){
        return buildParent(getNextEmail(), getNextLogin());
    }

    public Parent buildParent(String email, String login){
        Parent parent = new Parent();
        parent.setFirstName(PARENT_F_NAME);
        parent.setLastName(PARENT_L_NAME);
        parent.setActive(true);
        parent.setEmail(email);
        parent.setLogin(login);
        parent.setPassword(PASSWORD);
        parent.setPhoneNumber(PHONE_NUMBER);
        parent.setType(User.UserType.PARENT);
        lastCreatedParent = parent;
        return lastCreatedParent;
    }

    public DetailedInfo buildDetailedInfo(){
        DetailedInfo detailedInfo = new DetailedInfo();
        detailedInfo.setDateJoined(DATE_JOINED);
        detailedInfo.setDateOfBirth(DATE_OF_BIRTH);
        detailedInfo.setIdentificationNumber(getNextPin());
        if (lastCreatedAddress == null){
            buildAddress();
        }
        detailedInfo.setAddress(lastCreatedAddress);
        return detailedInfo;
    }

    public Address buildAddress(){
        return buildAddress(STREET, CITY, ZIP_CODE, COUNTRY);
    }

    public Address buildAddress(String street, String city, String zipCode, String country){
        Address address = new Address();
        address.setCity(city);
        address.setCountry(country);
        address.setStreet(street);
        address.setZipCode(zipCode);
        lastCreatedAddress = address;
        return lastCreatedAddress;
    }

    // helper methods
    
    private String getNextPin(){
        Long nextPin = Long.valueOf(PIN) + pinCounter++;
        return nextPin.toString();
    }

    private String getNextEmail(){
        StringBuilder sb = new StringBuilder();
        sb.append(EMAIL_PREFIX);
        sb.append(emailCounter++);
        sb.append("@");
        sb.append(EMAIL_SUFFIX);
        return sb.toString();
    }

    private String getNextLogin(){
        StringBuilder sb = new StringBuilder();
        sb.append(LOGIN);
        sb.append(loginCounter++);
        return sb.toString();
    }

    private String getNextLevelName(){
        StringBuilder sb = new StringBuilder();
        sb.append(LEVEL_NAME);
        sb.append(levelCounter);
        return sb.toString();
    }

    public void clear(){
        lastCreatedAddress = null;
        lastCreatedStudentTask = null;
        lastCreatedStudent = null;
        lastCreatedLevel = null;
        lastCreatedSemester = null;
        lastCreatedClassTime = null;
        lastCreatedClass = null;
        lastCreatedGrade = null;
        lastCreatedParent = null;
        lastCreatedTeacher = null;
    }

    // getters


    public Level getLastCreatedLevel() {
        return lastCreatedLevel;
    }

    public ClassInfo getLastCreatedClass() {
        return lastCreatedClass;
    }

    public StudentTask getLastCreatedStudentTask() {
        return lastCreatedStudentTask;
    }

    public Grade getLastCreatedGrade() {
        return lastCreatedGrade;
    }

    public Student getLastCreatedStudent() {
        return lastCreatedStudent;
    }

    public Teacher getLastCreatedTeacher() {
        return lastCreatedTeacher;
    }

    public Parent getLastCreatedParent() {
        return lastCreatedParent;
    }

    public Address getLastCreatedAddress() {
        return lastCreatedAddress;
    }

    public Semester getLastCreatedSemester() {
        return lastCreatedSemester;
    }

    public ClassTime getLastCreatedClassTime() {
        return lastCreatedClassTime;
    }
}
