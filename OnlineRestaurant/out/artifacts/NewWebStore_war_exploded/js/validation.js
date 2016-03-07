function addDishFormValidation(){
    var name = document.Dish.name.value;
    var price = document.Dish.price.value;
    var quantity = document.Dish.quantity.value;
    var image = document.Dish.image.value;
    var ingredients = document.Dish.ingredients.value;
    var description = document.Dish.description.value;

    if(dishNameValidation(name)){
        if(dishPriceValidation(price)){
            if(dishQuantityValidation(quantity)){
                if(dishImageValidation(image)){
                    if(dishIngredientsValidation(ingredients)){
                        if(dishDescriptionValidation(description)){
                            return true;
                        }
                    }
                }
            }
        }
    }
    return false;
}

function dishNameValidation(name){
    document.Dish.name.focus();
    var letters = /^[0-9a-zA-Z]+$/;
    if(value!=="") {
        if(value.length>1&&value.length<40){
            if(value.match(letters)){
                document.getElementById('dishName-msg').innerHTML="";
                return true;
            }
        }
    }
    document.getElementById('dishName-msg').innerHTML="Please, enter valid name";
    return false;
}

function dishPriceValidation(){
    document.Dish.price.focus();
    var letters = /^[0-9]+\.[0-9]+$/;
    if(value!=="") {
        if(value.match(letters)){
            document.getElementById('dishPrice-msg').innerHTML="";
            return true;
        }
    }
    document.getElementById('dishPrice-msg').innerHTML="Please, enter valid price";
    return false;
}

function dishQuantityValidation(){
    document.Dish.quantity.focus();
    var letters = /^[0-9]+$/;
    if(value!=="") {
        if(value.match(letters)){
            document.getElementById('dishQuantity-msg').innerHTML="";
            return true;
        }
    }
    document.getElementById('dishQuantity-msg').innerHTML="Please, enter valid quantity";
    return false;
}

function dishImageValidation(){
    document.Dish.image.focus();
    var letters = /^[a-zA-Z]+\.[a-zA-Z]+$/;
    if(value!=="") {
        if(value.match(letters)){
            document.getElementById('dishImage-msg').innerHTML="";
            return true;
        }
    }
    document.getElementById('dishImage-msg').innerHTML="Please, enter valid omage url";
    return false;
}

function dishDescriptionValidation(value){
    document.Dish.description.focus();
    if(value!=="") {
        document.getElementById('dishDescription-msg').innerHTML="";
        return true;
    }
    document.getElementById('dishDescription-msg').innerHTML="Please, enter valid description";
    return false;
}

function dishIngredientsValidation(value){
    document.Dish.ingredients.focus();
    if(value!=="") {
        document.getElementById('dishIngredients-msg').innerHTML="";
        return true;
    }
    document.getElementById('dishIngredients-msg').innerHTML="Please, enter valid ingredients";
    return false;
}

function editCategoryFormValidation(){
    var name = document.EditCategory.name.value;
    var description = document.EditCategory.description.value;

    if(categoryNameOldValidation(name)){
        if(categoryDescriptionOldValidation(description)){
            return true;
        }
    }
    return false;
}

function categoryNameOldValidation(value){
    document.EditCategory.name.focus();
    var letters = /^[0-9a-zA-Z]+$/;
    if(value!=="") {
        if(value.length>1&&value.length<40){
            if(value.match(letters)){
                document.getElementById('categoryNameOld-msg').innerHTML="";
                return true;
            }
        }
    }
    document.getElementById('categoryNameOld-msg').innerHTML="Please, enter valid category name";
    return false;
}

function categoryDescriptionOldValidation(value){
    document.EditCategory.description.focus();
    if(value!=="") {
        document.getElementById('categoryDescriptionOld-msg').innerHTML="";
        return true;
    }
    document.getElementById('categoryDescriptionOld-msg').innerHTML="Please, enter valid category description";
    return false;
}

function addCategoryFormValidation(){
    var name = document.AddCategory.name.value;
    var description = document.AddCategory.description.value;

    if(categoryNameValidation(name)){
        if(categoryDescriptionValidation(description)){
            return true;
        }
    }
    return false;
}

function categoryNameValidation(value){
    document.AddCategory.name.focus();
    var letters = /^[0-9a-zA-Z]+$/;
    if(value!=="") {
        if(value.length>1&&value.length<40){
            if(value.match(letters)){
                document.getElementById('categoryName-msg').innerHTML="";
                return true;
            }
        }
    }
    document.getElementById('categoryName-msg').innerHTML="Please, enter valid category name";
    return false;
}

function categoryDescriptionValidation(value){
    document.AddCategory.description.focus();
    if(value!=="") {
        document.getElementById('categoryDescription-msg').innerHTML="";
        return true;
    }
    document.getElementById('categoryDescription-msg').innerHTML="Please, enter valid category description";
    return false;
}

function registrationFormValidation() {
    var name = document.Registration.name.value;
    var surname = document.Registration.surname.value;
    var card = document.Registration.pay_card_id.value;
    var email = document.Registration.email.value;
    var login = document.Registration.login.value;
    var password = document.Registration.password.value;


    if(userNameValidation(name)){
        if(userSurnameValidation(surname)){
            if(userEmailValidation(email)){
                if(userCardValidation(card)){
                    if(userLoginValidation(login)){
                        if(userPasswordValidation(password)){
                            return true;
                        }
                    }
                }
            }
        }
    }

    return false;
}

function userNameValidation(value){
    document.Registration.name.focus();
    var letters = /^[A-Za-z]+$/;
    if(value!=="") {
        if(value.length>1&&value.length<26){
            if(value.match(letters)){
                document.getElementById('userName-msg').innerHTML="";
                return true;
            }
        }
    }
    document.getElementById('userName-msg').innerHTML="Please enter name from 2 till 25 letters";
    return false;
}

function userSurnameValidation(value){
    document.Registration.surname.focus();
    var letters = /^[A-Za-z]+$/;
    if(value!=="") {
        if(value.length>1&&value.length<26){
            if(value.match(letters)){
                document.getElementById('userSurname-msg').innerHTML="";
                return true;
            }
        }
    }
    document.getElementById('userSurname-msg').innerHTML="Please enter surname from 2 till 25 letters";
    return false;
}

function userEmailValidation(value){
    document.Registration.email.focus();
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(value!=="") {
        if(value.match(mailformat)){
            document.getElementById('userEmail-msg').innerHTML="";
            return true;
        }
    }
    document.getElementById('userEmail-msg').innerHTML="Please, enter correct email";
    return false;
}

function userCardValidation(value){
    document.Registration.pay_card_id.focus();
    var numbers = /^[0-9]+$/;
    if(value!=="") {
        if(value.length===16){
            if(value.match(numbers)){
                document.getElementById('userPaycard-msg').innerHTML="";
                return true;
            }
        }
    }
    document.getElementById('userPaycard-msg').innerHTML="Please, enter correct pay card number";
    return false;
}

function userLoginValidation(value){
    document.Registration.login.focus();
    var letters = /^[0-9a-zA-Z]+$/;
    if(value!=="") {
        if(value.length>5&&value.length<31) {
            if (value.match(letters)) {
                document.getElementById('userLogin-msg').innerHTML="";
                return true;
            }
        }
    }
    document.getElementById('userLogin-msg').innerHTML="Please, enter login from 6 to 30 signs";
    return false;
}

function userPasswordValidation(value){
    document.Registration.password.focus();
    var letters = /^[0-9a-zA-Z]+$/;
    if(value!=="") {
        if(value.length>5&&value.length<31) {
            if (value.match(letters)) {
                document.getElementById('userPassword-msg').innerHTML="";
                return true;
            }
        }
    }
    document.getElementById('userPassword-msg').innerHTML="Please, enter password from 6 to 30 signs";
    return false;
}


