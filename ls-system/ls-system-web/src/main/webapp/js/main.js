
var baseURL = 'http://localhost:8080/ls-system/rest';

loginSection = $("#loginSection");
mainPage = $("#mainPage");

function init(){
    showOrHideElement(loginSection, true);
    showOrHideElement(mainPage, false);
}

function doSomething() {
    var login = document.getElementById("username").value;
    var hash = CryptoJS.SHA1(document.getElementById("password").value);
    getLoggedUser(login, hash);
    return false;
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

    showOrHideElement(loginSection, false);
    showOrHideElement(mainPage, true);

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


