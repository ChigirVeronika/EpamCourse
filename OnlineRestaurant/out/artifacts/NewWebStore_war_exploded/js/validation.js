function registrationFormValidation() {
    var name = document.registration.name.value;
    var surname = document.registration.surname;
    var card = document.registration.pay_card_id;
    var email = document.registration.email;
    var login = document.registration.login;
    var password = document.registration.password;

    if(name_validation(name)) {
            return true;
    }

    return false;
}

function name_validation(value){
    if(length_validation(value,2,25)) {
        if(allLetter(value)){
            return true;
        }
    }
    var msg = document.createTextNode("Field should not be empty / length be between "+mx+" to "+my);
    document.getElementById('userName-msg').appendChild(msg);
    return false;
}

function length_validation(uid,mx,my)
{
    var uid_len = uid.value.length;
    if (uid_len == 0 || uid_len >= my || uid_len < mx)
    {
        var msg = document.createTextNode("Field should not be empty / length be between "+mx+" to "+my);
        document.getElementById('name-msg').appendChild(msg);
        uid.focus();
        return false;
    }
    return true;
}
function allLetter(uname)
{
    var letters = /^[A-Za-z]+$/;
    if(uname.value.match(letters))
    {
        return true;
    }
    else
    {
        var msg = document.createTextNode("Field should contains only letters");
        document.getElementById('name-msg').appendChild(msg);
        uname.focus();
        return false;
    }
}
function alphanumeric(uadd)
{
    var letters = /^[0-9a-zA-Z]+$/;
    if(uadd.value.match(letters))
    {
        return true;
    }
    else
    {
        alert('User address must have alphanumeric characters only');
        uadd.focus();
        return false;
    }
}
function countryselect(ucountry)
{
    if(ucountry.value == "Default")
    {
        alert('Select your country from the list');
        ucountry.focus();
        return false;
    }
    else
    {
        return true;
    }
}
function allnumeric(uzip)
{
    var numbers = /^[0-9]+$/;
    if(uzip.value.match(numbers))
    {
        return true;
    }
    else
    {
        alert('ZIP code must have numeric characters only');
        uzip.focus();
        return false;
    }
}
function validateEmail(uemail)
{
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(uemail.value.match(mailformat))
    {
        return true;
    }
    else
    {
        alert("You have entered an invalid email address!");
        uemail.focus();
        return false;
    }
}
function validsex(umsex,ufsex)
{
    x=0;

    if(umsex.checked)
    {
        x++;
    } if(ufsex.checked)
{
    x++;
}
    if(x==0)
    {
        alert('Select Male/Female');
        umsex.focus();
        return false;
    }
    else
    {
        alert('Form Succesfully Submitted');
        window.location.reload()
        return true;
    }
}  