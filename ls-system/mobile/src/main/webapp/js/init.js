var baseURL = 'https://' + window.location.host + '/ls-rest/resource';

function init(){
    console.log('init');
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

function getCurrentUser(){
    console.log('getCurrentUser');
    if (sessionStorage.getItem('currentUser')){
        return JSON.parse(sessionStorage.getItem('currentUser'));
    }
    var response = $.ajax({
        type: 'GET',
        url: baseURL + '/session/currentUser',
        dataType: 'json',
        async: false
    });
    if (response.status == 200){
        var user = response.responseJSON;
        sessionStorage.setItem("currentUser", JSON.stringify(user))
        return user;
    }
}
