let newUserForm = document.forms[0];

let isValidEmail = (email) => {
    return email.match(/[\w]+@[a-zA-Z]+[.]{1}[a-zA-Z]{2,3}/) != null;
}

let isValidPassword = (password) => {
    return password.match(/(?=.*[!@#$%^&*])[\w!@#$%^&*]{8,}/) != null;
}

let areBothPasswordsEqual = (password, cnfPassword) => {
    return password === cnfPassword;
}

let isValidName = (name) => {
    return name.match(/[a-zA-Z]{3,}/) != null;
}

let addRedBoxShadow = (ele) => {
    ele.style.boxShadow = "0 0 0 0.25rem #f590908c";
}

let removeBoxShadow = (ele) => {
    ele.style.boxShadow = "";
}

let highlightInputAsInvalid = (input) => {
    input.style.border = "2px solid #f590908c";
    
    input.addEventListener("focus", () => {
        addRedBoxShadow(input);
    });

    input.addEventListener("blur", () => {
        removeBoxShadow(input);
    });
}

let isValidUserForm = () => {
	let firstName = newUserForm["firstName"];
    let lastName = newUserForm["lastName"];
	let email = newUserForm["email"];
    let password = newUserForm["password"];
    let confirmPassword = newUserForm["confirmPassword"];

    let isValid = true;

    if(!isValidName(firstName.value)){
        isValid = false;
        highlightInputAsInvalid(firstName);
        msg = "First name must be valid." + "<br>";
    }    

    if(!isValidEmail(email.value)){
        isValid = false;
        highlightInputAsInvalid(email);
        msg = "Email must be valid." + "<br>";
    }

    if(!isValidPassword(password.value)){
        isValid = false;
        highlightInputAsInvalid(password);
        msg = "Password must be valid." + "<br>";
    }

    if(!areBothPasswordsEqual(password.value, confirmPassword.value)){
        highlightInputAsInvalid(password);
        highlightInputAsInvalid(confirmPassword);
        isValid = false;
        msg = "Both Password must match." + "<br>";
    }

    return isValid;
}

newUserForm.addEventListener("submit", (evt) => {
    if(!isValidUserForm())
		evt.preventDefault();
});