function init(){
    console.log('init');
    if (!jerseyStarted){
        dummyRequest();
        jerseyStarted = true;
    }
    console.log('loggedUser: ' + getCurrentUser().login);
    // wait for DOM, init html values
    $(document).ready(initHtml);
}

function initHtml(){
    $("#username_link").html(getCurrentUser().login);
    $("#createStudentBtn").hide();
    $("#userInfo").hide();
    $("#classInfo").hide();
    $("#createStudent").hide();
}

function dummyRequest(){
    console.log("executing dummy rest request - workaround for jersey problem");
    $.ajax({
        url: baseURL,
        async: false
    })
}

function getCurrentUser(){
    console.log('getCurrentUser');
    if (sessionStorage.getItem('currentUser')){
        return JSON.parse(sessionStorage.getItem('currentUser'));
    }
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/users/current',
        dataType: 'json',
        async: false
    });
    if (response.status == 200){
        var user = response.responseJSON;
        sessionStorage.setItem("currentUser", JSON.stringify(user))
        return user;
    }
}
