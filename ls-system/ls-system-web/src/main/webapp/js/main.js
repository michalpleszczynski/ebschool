
var baseURL = 'http://localhost:8080/ls-system/rest';

mainPage = $("#mainPage");

function init(){

}

function getLoggedUser(login, password){
    console.log('getLoggedUser: ' + login + ' ' + password);
    $.ajax({
        type: 'GET',
        url: baseURL + '/users/' + login + '/' + password,
        dataType: "json",
        success: redirectToMainPage,
        error: tryAgain
    });
}

function getClassById(id){
    console.log('getClassById: ' + id);
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/classes/' + id,
        dataType: 'json'
    });
    if (response.status == 200){
        return response.entity;
    }
}

function tryAgain(){
    $("#errorMessage").html("Wrong login or password! Try Again!");
}

function showOrHideElement(element, show){
    if (show){
        element.show();
    } else {
        element.hide();
    }
}

function redirectToMainPage(user){

    switch (user.type.toLowerCase()){
        case "student":
            renderStudent(user);
            break;
        case "teacher":
            renderTeacher(user);
            break;
        case "parent":
            renderParent(parent);
            break;
    }
}

function renderUser(user){
    $("#title").html('Hello ' + user.login + '!');
    $("#firstName").html(user.firstName);
    $("#lastName").html(user.lastName);
    $("#login").html(user.login);
    $("#email").html(user.email);
}

function renderStudent(student){
    renderUser(student);
    $("#levelName").html(student.level.name);
}

function renderTeacher(teacher){
    renderUser(teacher);
    $("#pin").html(teacher.detailedInfo.pin);
}

function renderParent(parent){
    renderUser(parent);
}


