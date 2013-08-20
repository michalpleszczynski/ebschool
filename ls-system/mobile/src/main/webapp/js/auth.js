var baseURL = 'http://' + window.location.host + '/ls-rest/resource';
var homePage = 'http://' + window.location.host + '/ls-mobile/index.html';

function logIn(){
    var username = $('#username').val();
    var password = $('#password').val();
    var url = baseURL + '/session/currentUser';

    var response = $.ajax({
        password: password,
        username: username,
        url: url,
        type: 'GET',
        async: false
    });
    // if success - success call back in ajax() doesn't work with async false
    // with async true it does but then it doesn't post the data
    if (response.status == 200){
        var user = response.responseJSON;
        sessionStorage.setItem("currentUser", JSON.stringify(user))
        return true;
    } else {
        $('.retry').css('display','inline');
        $('#username').val('');
        $('#password').val('');
        return false;
    }
}

function logout(){
    console.log('logout!');
    var url = baseURL + '/session/logout';
    $.ajax({
        type: 'POST',
        url: url,
        error: logError
    });
    currentUser = null;
    sessionStorage.removeItem('currentUser');
    return false;
}