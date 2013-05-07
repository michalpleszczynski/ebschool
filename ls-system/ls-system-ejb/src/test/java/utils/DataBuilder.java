package utils;

import com.ebschool.model.*;

/**
 * User: michau
 * Date: 5/5/13
 * Time: 12:45 PM
 */
// TODO: make it work...
public class DataBuilder {

    private static int pinCounter = 1;

    // DEFAULT VALUES

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
    public static final String DEFAULT_STUDENT_F_NAME = "student_f_name";
    public static final String DEFAULT_STUDENT_L_NAME = "student_l_name";

    // teacher
    public static final String DEFAULT_TEACHER_L_NAME = "teacher_l_name";
    public static final String DEFAULT_TEACHER_F_NAME = "teacher_f_name";

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
        test.setClassInfo(getLastCreatedEntity(ClassInfo.class));
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
        classInfo.getLevel().getClasses().add(classInfo);
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
        student.getClasses().add(getLastCreatedEntity(ClassInfo.class));
        student.getGrades().add(getLastCreatedEntity(Grade.class));
        lastCreatedStudent = student;
        return student;
    }

    public static Teacher buildTeacher(){
        Teacher teacher = new Teacher();
        teacher.setDetailedInfo(getLastCreatedEntity(DetailedInfo.class));
        teacher.getClasses().add(getLastCreatedEntity(ClassInfo.class));
        lastCreatedTeacher = teacher;
        return teacher;
    }

    public static Parent buildParent(){
        Parent parent = new Parent();
        parent.getChildrenAccounts().add(getLastCreatedEntity(Student.class));
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
            return (T)lastCreatedTest;
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

//    private static Level getLastCreatedLevel(){
//        if (lastCreatedLevel == null){
//            return buildLevel();
//        }
//        return lastCreatedLevel;
//    }
//
//    private static void setLastCreatedLevel(Level level){
//        lastCreatedLevel = level;
//    }
//
//    private static ClassInfo getLastCreatedClass(){
//        if (lastCreatedClass == null){
//            return buildClass();
//        }
//        return lastCreatedClass;
//    }
//
//    private static void setLastCreatedClass(ClassInfo classInfo){
//        lastCreatedClass = classInfo;
//    }

    private static String getNextPin(){
        Long nextPin = Long.valueOf(DEFAULT_PIN) + pinCounter++;
        return nextPin.toString();
    }

}
