// navigation variables
var baseURL = 'http://localhost:8080/ls-system/rest';
var homePage = 'http://localhost:8080/ls-system';
var userDetails = 'http://localhost:8080/ls-system/user_details.html'

var currentUser;
var currentClass;

var jerseyStarted = false;

function init(){
    console.log('init');
    if (!jerseyStarted){
        dummyRequest();
        jerseyStarted = true;
    }
    currentUser = getCurrentUser();
    console.log('loggedUser: ' + currentUser.login);
    // wait for DOM, init html values
    $(document).ready(initHtml);
}

function initHtml(){
    $("#username_link").html(currentUser.login);
    $("#userInfo").hide();
    $("#classInfo").hide();
}

function getCurrentUser(){
    console.log('getCurrentUser');
    if (currentUser){
        return currentUser;
    }
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/users/current',
        dataType: 'json',
        async: false
    });
    if (response.status == 200){
        return response.responseJSON;
    }
}

function logout(){
    console.log('logout!');
    $.ajax({
        type: 'GET',
        url: baseURL + '/session/logout',
        success: redirect(homePage),
        error: logError
    });
    currentUser = null;
    return false;
}

function getClassById(id){
    console.log('getClassById: ' + id);
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/classes/' + id,
        dataType: 'json',
        async: false,
        error: logError
    });
    if (response.status == 200){
        return response.responseJSON;
    }
}

function getUserById(id){
    console.log('getUserById: ' + id);
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/users/id/' + id,
        dataType: 'json',
        async: false,
        error: logError
    });
    if (response.status == 200){
        return response.responseJSON;
    }
}

function dummyRequest(){
    console.log("executing dummy rest request - workaround for jersey problem");
    $.ajax({
        url: baseURL,
        async: false
    })
}


// navigation

function redirect(page){
    console.log('redirect to: ' + page);
    location.href = page;
}

// view

function showUserDetails(id){
    console.log("showMyDetails");
    var isLoggedUser = false;
    if (currentUser.id == id){
        isLoggedUser = true;
        user = getCurrentUser();
    } else {
        isLoggedUser = false;
        user = getUserById(id);
    }
    if (!isLoggedUser){
        $("#classesBtn").hide();
    } else {
        $("#classesBtn").show();
    }
    $("#classInfo").hide();
    $("#myClasses").hide();
    $("#userInfo").show();
    switch(user.type.toLowerCase()){
        case 'student':
            renderStudent(user);
            break;
        case 'teacher':
            renderTeacher(user);
            break;
        case 'parent':
            renderParent(user);
            $("#classesBtn").hide();
            break;
    }
}

function renderUser(user){
    console.log("render user: " + user.login);
    $("#firstName").html(user.firstName);
    $("#lastName").html(user.lastName);
    $("#login").html(user.login);
    $("#email").html(user.email);
}

function renderStudent(student){
    console.log("render student: " + student.login);
    renderUser(student);
    $("#levelName").html(student.level.name);
}

function renderTeacher(teacher){
    console.log("render teacher: " + teacher.login);
    renderUser(teacher);
    $("#pin").html(teacher.detailedInfo.pin);
}

function renderParent(parent){
    console.log("render parent: " + parent.login);
    renderUser(parent);
}

function renderClass(classInfo){
    console.log("render class:" + classInfo.id);
    $("#userInfo").hide();
    $("#when").html(classInfo.when);
    $("#where").html(classInfo.where);
    $("#description").html(classInfo.description);
    $("#class_level").html(classInfo.level.name);
    currentClass = classInfo;
}

function showClasses() {
    console.log("show classes");
    var user = getCurrentUser();
    var list = document.getElementById('myClasses');
    $("#myClasses").show();
    $(list).empty();
    for (i = 0; i<user.classes.length; ++i){
        var newLi = document.createElement('li');
        list.appendChild(newLi);
        var classInfo = getClassById(user.classes[i]);
        var newLiHTML = '<a id="class_link[id]" href="javascript:void(0)" onclick="showClass([id]); return false;">' +
                        '[when] [description]</a>';
        newLiHTML = newLiHTML.replace(/\[id\]/g, classInfo.id);
        newLiHTML = newLiHTML.replace('[when]', classInfo.when);
        newLiHTML = newLiHTML.replace('[description]', classInfo.description);
        newLi.innerHTML = newLiHTML;
    }
}

function showTeachersByClass(id){
    var id = currentClass.id;
    console.log("show teachers of class: " + id);
    var teacherList = document.getElementById('class_teachers');
    $(teacherList).empty();
    $(teacherList).html("Teachers: ")
    for (i = 0; i<currentClass.teachers.length; ++i){
        var newLi = document.createElement('li');
        teacherList.appendChild(newLi);
        var teacher = getUserById(currentClass.teachers[i]);
        var newLiHTML = '<a id="teacher_link[id]" href="javascript:void(0)" onclick="showUserDetails([id]); return false;">' +
                        '[firstName] [lastName]</a>';
        newLiHTML = newLiHTML.replace(/\[id\]/g, teacher.id);
        newLiHTML = newLiHTML.replace('[firstName]', teacher.firstName);
        newLiHTML = newLiHTML.replace('[lastName]', teacher.lastName);
        newLi.innerHTML = newLiHTML;
    }
}

function showStudentsByClass(){
    var id = currentClass.id;
    console.log("show students of class: " + id);
    var studentList = document.getElementById('class_students');
    $(studentList).empty();
    $(studentList).html("Students: ")
    for (i = 0; i<currentClass.students.length; ++i){
        var newLi = document.createElement('li');
        studentList.appendChild(newLi);
        var student = getUserById(currentClass.students[i]);
        var newLiHTML = '<a id="student_link[id]" href="javascript:void(0)" onclick="showUserDetails([id]); return false;">' +
                        '[firstName] [lastName]</a>';
        newLiHTML = newLiHTML.replace(/\[id\]/g, student.id);
        newLiHTML = newLiHTML.replace('[firstName]', student.firstName);
        newLiHTML = newLiHTML.replace('[lastName]', student.lastName);
        newLi.innerHTML = newLiHTML;
    }
}

function showClass(id){
    var classInfo = getClassById(id);
    $("#classInfo").show();
    renderClass(classInfo);
}

// utils

function logError(jqXHR, status, error){
    console.log(status + ": " + error)
}