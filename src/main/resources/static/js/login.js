function start(){
    showLogin();
    document.getElementById("loginBtn").addEventListener("click", showLogin, false);
    document.getElementById("registerBtn").addEventListener("click", showRegister, false);
}

function showLogin() {
    document.getElementById('login-form').style.display = 'block';
    document.getElementById('register-form').style.display = 'none';
    document.getElementById('loginBtn').classList.add('active');
    document.getElementById('registerBtn').classList.remove('active');
}

function showRegister(){
    document.getElementById('login-form').style.display = 'none';
    document.getElementById('register-form').style.display = 'block';
    document.getElementById('loginBtn').classList.remove('active');
    document.getElementById('registerBtn').classList.add('active');
}

window.addEventListener("load", start, false);