package utils;

import com.ebschool.ejb.model.*;

/**
 * User: michau
 * Date: 5/5/13
 * Time: 12:45 PM
 */
// TODO: make it work...
public class DataBuilder {

    private static int pinCounter = 1;
    private static int emailCounter = 1;
    private static int loginCounter = 1;

    // DEFAULT VALUES

    // user
    public static final String DEFAULT_PASSWORD = "ff12bbd8c907af067070211d87bdf098be17375b";
    public static final String DEFAULT_PHONE_NUMBER = "345123789";
    public static final String DEFAULT_EMAIL_PREFIX = "default";
    public static final String DEFAULT_EMAIL_SUFFIX = "email.com";
    public static final String DEFAULT_LOGIN = "login";

    // level
    public static final String DEFAULT_LEVEL_NAME = "default_level";

    // test
    public static final long DEFAULT_TEST_DATE = 1367755677;
    public static final String DEFAULT_TEST_DESC = "test description";

    // grade
    public static final String DEFAULT_GRADE_COMMENT = "grade_comment";
    public static final byte DEFAULT_GRADE_PERCENTAGE = 75;
    public static final byte DEFAULT_GRADE_WEIGHT = 4;

    // address
    public static final String DEFAULT_STREET = "street";
    public static final String DEFAULT_COUNTRY = "country";
    public static final String DEFAULT_ZIP_CODE = "00-000";
    public static final String DEFAULT_CITY = "city";

    // detailedInfo
    public static final long DEFAULT_DATE_OF_BIRTH = 1367758677;
    public static final long DEFAULT_DATE_JOINED = 1363058677;
    public static final String DEFAULT_PIN = "457265345";

    // student
    public static final String DEFAULT_STUDENT_F_NAME = "student fname";
    public static final String DEFAULT_STUDENT_L_NAME = "student lname";

    // teacher
    public static final String DEFAULT_TEACHER_L_NAME = "teacher lname";
    public static final String DEFAULT_TEACHER_F_NAME = "teacher fname";

    // parent
    public static final String DEFAULT_PARENT_F_NAME = "parent fname";
    public static final String DEFAULT_PARENT_L_NAME = "parent lname";

    // class
    public static final String DEFAULT_CLASS_NAME = "class_name";
    public static final String DEFAULT_CLASS_DESC = "class description";
    public static final long DEFAULT_CLASS_DATE = 1369758677;
    public static final String DEFAULT_CLASS_WHERE = "class location";


    // keep last created objects not to repeat creation
    private static Level lastCreatedLevel;
    private static ClassInfo lastCreatedClass;
    private static Test lastCreatedTest;
    private static Grade lastCreatedGrade;
    private static Student lastCreatedStudent;
    private static Teacher lastCreatedTeacher;
    private static Parent lastCreatedParent;
    private static Address lastCreatedAddress;
    private static DetailedInfo lastCreatedDetailedInfo;

    public static Test buildTest(){
        Test test = new Test();
        test.setDescription(DEFAULT_TEST_DESC);
        test.setWhen(DEFAULT_TEST_DATE);
        lastCreatedTest = test;
        return test;
    }

    public static Grade buildGrade(){
        Grade grade = new Grade();
        grade.setComment(DEFAULT_GRADE_COMMENT);
        grade.setPercentage(DEFAULT_GRADE_PERCENTAGE);
        grade.setWeight(DEFAULT_GRADE_WEIGHT);
        lastCreatedGrade = grade;
        return grade;
    }

    public static ClassInfo buildClass(){
        ClassInfo classInfo = new ClassInfo();
        classInfo.setDescription(DEFAULT_CLASS_DESC);
        classInfo.setLevel(getLastCreatedEntity(Level.class));
        classInfo.setWhen(DEFAULT_CLASS_DATE);
        classInfo.setWhere(DEFAULT_CLASS_WHERE);
        lastCreatedClass = classInfo;
        return classInfo;
    }

    public static Level buildLevel(){
        Level level = new Level();
        level.setName(DEFAULT_LEVEL_NAME);
        lastCreatedLevel = level;
        return level;
    }

    public static Student buildStudent(){
        Student student = new Student();
        student.setDetailedInfo(getLastCreatedEntity(DetailedInfo.class));
        student.setLevel(getLastCreatedEntity(Level.class));
        student.setActive(true);
        student.setEmail(getNextEmail());
        student.setLogin(getNextLogin());
        student.setPassword(DEFAULT_PASSWORD);
        student.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        student.setFirstName(DEFAULT_STUDENT_F_NAME);
        student.setLastName(DEFAULT_STUDENT_L_NAME);
        lastCreatedStudent = student;
        return student;
    }

    public static Teacher buildTeacher(){
        Teacher teacher = new Teacher();
        teacher.setDetailedInfo(getLastCreatedEntity(DetailedInfo.class));
        teacher.setEmail(getNextEmail());
        teacher.setLogin(getNextLogin());
        teacher.setPassword(DEFAULT_PASSWORD);
        teacher.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        teacher.setFirstName(DEFAULT_TEACHER_F_NAME);
        teacher.setLastName(DEFAULT_TEACHER_L_NAME);
        lastCreatedTeacher = teacher;
        return teacher;
    }

    public static Parent buildParent(){
        Parent parent = new Parent();
        parent.setFirstName(DEFAULT_PARENT_F_NAME);
        parent.setLastName(DEFAULT_PARENT_L_NAME);
        parent.setActive(true);
        parent.setEmail(getNextEmail());
        parent.setLogin(getNextLogin());
        parent.setPassword(DEFAULT_PASSWORD);
        parent.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        lastCreatedParent = parent;
        return parent;
    }

    public static DetailedInfo buildDetailedInfo(){
        DetailedInfo detailedInfo = new DetailedInfo();
        detailedInfo.setDateJoined(DEFAULT_DATE_JOINED);
        detailedInfo.setDateOfBirth(DEFAULT_DATE_OF_BIRTH);
        detailedInfo.setIdentificationNumber(getNextPin());
        detailedInfo.setAddress(getLastCreatedEntity(Address.class));
        lastCreatedDetailedInfo = detailedInfo;
        return detailedInfo;
    }

    public static Address buildAddress(){
        Address address = new Address();
        address.setCity(DEFAULT_CITY);
        address.setCountry(DEFAULT_COUNTRY);
        address.setStreet(DEFAULT_STREET);
        address.setZipCode(DEFAULT_ZIP_CODE);
        lastCreatedAddress = address;
        return address;
    }

    private static <T> T getLastCreatedEntity(Class<T> entityClass){
        if (entityClass.isAssignableFrom(Address.class)){
            if (lastCreatedAddress == null){
                return (T)buildAddress();
            }
            return (T)lastCreatedAddress;
        }
        else if (entityClass.isAssignableFrom(Level.class)){
            if (lastCreatedLevel == null){
                return (T)buildLevel();
            }
            return (T)lastCreatedLevel;
        }
        else if (entityClass.isAssignableFrom(ClassInfo.class)){
            if (lastCreatedClass == null){
                return (T)buildClass();
            }
            return (T)lastCreatedClass;
        }
        else if (entityClass.isAssignableFrom(Test.class)){
            if (lastCreatedTest == null){
                return (T)buildTest();
            }
            return (T) lastCreatedTest;
        }
        else if (entityClass.isAssignableFrom(DetailedInfo.class)){
            return (T)buildDetailedInfo();
        }
        else if (entityClass.isAssignableFrom(Grade.class)){
            if (lastCreatedGrade == null){
                return (T)buildGrade();
            }
            return (T)lastCreatedGrade;
        }
        else if (entityClass.isAssignableFrom(Teacher.class)){
            if (lastCreatedTeacher == null){
                return (T)buildTeacher();
            }
            return (T)lastCreatedTeacher;
        }
        else if (entityClass.isAssignableFrom(Student.class)){
            if (lastCreatedStudent == null){
                return (T)buildStudent();
            }
            return (T)lastCreatedStudent;
        }
        else if (entityClass.isAssignableFrom(Parent.class)){
            if (lastCreatedParent == null){
                return (T)buildParent();
            }
            return (T)lastCreatedParent;
        }
        return null;
    }

    private static String getNextPin(){
        Long nextPin = Long.valueOf(DEFAULT_PIN) + pinCounter++;
        return nextPin.toString();
    }

    private static String getNextEmail(){
        StringBuilder sb = new StringBuilder();
        sb.append(DEFAULT_EMAIL_PREFIX);
        sb.append(emailCounter++);
        sb.append("@");
        sb.append(DEFAULT_EMAIL_SUFFIX);
        return sb.toString();
    }

    private static String getNextLogin(){
        StringBuilder sb = new StringBuilder();
        sb.append(DEFAULT_LOGIN);
        sb.append(loginCounter++);
        return sb.toString();
    }

    public static String getLastLogin(){
        StringBuilder sb = new StringBuilder();
        sb.append(DEFAULT_LOGIN);
        sb.append(loginCounter-1);
        return sb.toString();
    }

}
