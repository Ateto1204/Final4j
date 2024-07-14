const port = window.location.port;
let myName, myEmail, myPassword;

function start(){
    localStorage.clear();
    showLogin();
    document.getElementById("login-toggle").addEventListener("click", showLogin, false);
    document.getElementById("register-toggle").addEventListener("click", showRegister, false);
    document.getElementById("login-form").addEventListener("submit", userLogin, false);
    document.getElementById("register-form").addEventListener("submit", userRegister, false);
}

function showLogin() {
    document.getElementById('login-div').style.display = 'block';
    document.getElementById('register-div').style.display = 'none';
    document.getElementById('login-toggle').classList.add('active');
    document.getElementById('register-toggle').classList.remove('active');
}

async function isUserExisted(email) {
    let url = "http://localhost:" + port + "/api/user/find?mail=" + email;

    try {
        const response = await fetch(url);
        const data = await response.text();
        return data === "true";
    } catch (err) {
        console.log("Failed: " + err);
        return false;
    }
}

async function findUserByEmail(email, password){
    let url = "http://localhost:" + port + "/api/user/check";
    let headers = {
        'Content-Type': 'application/json'
    }
    let body = {
        "email": email,
        "pwd": password
    }

    try{
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body)
        });
        const data = await response.json();
        return data;
    }catch(err){
        console.log("Failed: " + err);
        return null;
    }
}

module.exports = { isUserExisted, findUserByEmail, showLogin, start };