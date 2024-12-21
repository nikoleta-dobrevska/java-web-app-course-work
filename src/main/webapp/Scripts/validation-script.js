/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function comparePasswords() {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (password !== confirmPassword) {
        document.getElementById('comparePasswordsMessage').style.display = "block";
        document.getElementById('comparePasswordsMessage').innerHTML = "Passwords don't match!";
        return false;
    } else {
        document.getElementById('comparePasswordsMessage').style.display = "none";
    }
    return true;
}