/**
 * Created by Вероника on 06.03.2016.
 */
function sendSignin(type) {
    var elem = document.getElementById(type);
    var error = elem.querySelector('#error');
    var command = encodeURIComponent('LOGIN');
    var name = encodeURIComponent(elem.querySelector('#login').value);
    var password = encodeURIComponent(elem.querySelector('#password').value);
    var passRegEx = new RegExp('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$');
    var authRegEx = new RegExp('(([A-Za-z0-9]{6,18}))');

    if(!name.match(authRegEx) || name.match(authRegEx)[0] != name) {
        error.innerText = 'Invalid name. Name can consist of uppercase latin letters,\
            lowercase latin letters and numbers. And contain at least 6 sybols.';
        error.className = error.className.replace(' hide', '');
        error.className += ' show';
        return false;
    }

    if(!password.match(passRegEx) || password.match(passRegEx)[0] != password) {
        error.innerText = 'Invalid password. Password should contain at least one\
            uppercase latin letters, lowercase latin letters and number.';
        error.className = error.className.replace(' hide', '');
        error.className += ' show';
        return false;
    }

    var xhr = new XMLHttpRequest();

    var body = 'command=' + encodeURIComponent('LOGIN') +
        '&login=' + encodeURIComponent(elem.querySelector('#login').value) +
        '&password=' + encodeURIComponent(elem.querySelector('#password').value);

    xhr.open("POST", '/controller', true)
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')

    xhr.onloadend = function(response) {
        if(response.currentTarget.status === 401) {
            error.innerHTML = response.currentTarget.response;
            error.className = error.className.replace(' hide', '');
            error.className += " show";
        }
        else if (response.currentTarget.status === 200) {
            window.location.href = "/controller?command=SELECT_USER_PAGE";
        }
    }
    xhr.send(body);
    return false;
}