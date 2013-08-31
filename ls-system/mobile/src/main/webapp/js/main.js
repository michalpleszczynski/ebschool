// navigation variables
var baseURL = 'https://' + window.location.host + '/ls-rest/resource';
var currentClass;

// maps for objects
var classMap = {};
var userMap = {};
var levelMap = {};


function getClassById(id){
    console.log('getClassById: ' + id);
    if (classMap.hasOwnProperty(id.toString())){
        return classMap[id.toString()];
    }
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/classes/' + id,
        dataType: 'json',
        async: false,
        error: logError
    });
    if (response.status == 200){
        classMap[id.toString()] = response.responseJSON;
        return response.responseJSON;
    }
}

function getUserById(id){
    console.log('getUserById: ' + id);
    if (userMap.hasOwnProperty(id.toString())){
        return userMap[id.toString()];
    }
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/users/id/' + id,
        dataType: 'json',
        async: false,
        error: logError
    });
    if (response.status == 200){
        userMap[id.toString()] = response.responseJSON;
        return response.responseJSON;
    }
}

function getAllClasses(){
    console.log('getAllClasses');
    $.ajax({
        type: 'GET',
        url: baseURL + '/classes',
        dataType: 'json',
        success: function(response){
            var classArray = response;
            for (i = 0;i<classArray.length; ++i){
                classMap[classArray[i].id.toString()] = classArray[i];
            }
        },
        error: logError
    });
}

function getAllLevels(){
    console.log('getAllLevels');
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/levels',
        dataType: 'json',
        success: function(response){
            var levelArray = response;
            for (i = 0;i<levelArray.length; ++i){
                levelMap[levelArray[i].id.toString()] = levelArray[i];
            }
        },
        error: logError
    });
}


// navigation

function redirect(page){
    console.log('redirect to: ' + page);
    location.href = page;
}

// view

function showUserDetails(id){
    console.log("showUserDetails");
    var isLoggedUser = false;
    if (sessionStorage.getItem('currentUser') && JSON.parse(sessionStorage.getItem('currentUser')).id == id){
        isLoggedUser = true;
        user = JSON.parse(sessionStorage.getItem('currentUser'));
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
    $("#messageSection").hide();
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
    var user = JSON.parse(sessionStorage.getItem('currentUser'));
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

// view END

// utils

function logError(jqXHR, status, error){
    console.log(status + ": " + error)
}