/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let valid1 = false;
let valid2 = false;
let valid3 = false;

function validateNames() {
    const fname = document.getElementById('fname').value;
    const lname = document.getElementById('lname').value;
    const hasSpecialChar = /[!@#\$%\^\&*\)\(+=._-]/;
    const hasNumber = /^[0-9]+$/;
    
    if (fname.length <= 0 || hasSpecialChar.test(fname) ||
            lname.length <=0 || hasSpecialChar.test(lname) || hasNumber.test(fname) || hasNumber.test(lname)) {
        valid1 = false;
        document.getElementById('nameMessage').style.display = "block";
        document.getElementById('nameMessage').innerHTML = "Please fill in the name fields appropriately!";
    }
    else {
        valid1 = true;
        document.getElementById('nameMessage').style.display = "none";
    }
}

function validatePassword() {
    const password = document.getElementById('password').value;
    const hasUppercase = /[A-Z]/;
    const hasLowercase = /[a-z]/;
    const hasSpecialChar = /[!@#\$%\^\&*\)\(+=._-]/;

    if (password.length < 8 || !hasUppercase.test(password) ||
            !hasLowercase.test(password) ||
            !hasSpecialChar.test(password)) {
        valid2 = false;
        document.getElementById('validatePasswordMessage').style.display = "block";
        document.getElementById('validatePasswordMessage').innerHTML =
                "Password must contain at least one: uppercase letter, one lowercase letter, one special \n\
                                character, 8 characters in length!";
    } else {
        valid2 = true;
        document.getElementById('validatePasswordMessage').style.display = "none";
    }
}

function comparePasswords() {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (password !== confirmPassword) {
        valid3 = false;
        document.getElementById('comparePasswordsMessage').style.display = "block";
        document.getElementById('comparePasswordsMessage').innerHTML = "Passwords don't match!";
    } else {
        valid3 = true;
        document.getElementById('comparePasswordsMessage').style.display = "none";
    }
}

function validateForm() {
    validateNames();
    validatePassword();
    comparePasswords();

    if (valid1 && valid2 && valid3) {
        return true;
    } else {
        alert("Please fill in all the fields appropriately!");
        return false;
    }
}