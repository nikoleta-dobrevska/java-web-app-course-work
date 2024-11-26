/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const urlParams = new URLSearchParams(window.location.search);

if (urlParams.get('register') === 'invalid') {
    document.getElementById('message-for-user').innerText = 'User with this email already exists!';
}

if (urlParams.get('error') === 'invalid') {
    document.getElementById('message-for-user').innerText = 'Wrong email and/or password!';
}

if (urlParams.get('register') === 'valid') {
    document.getElementById('message-for-user').innerText = 'You have registered successfully! Sign in to get started!';
}



