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