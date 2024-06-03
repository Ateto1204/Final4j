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

function showRegister(){
    document.getElementById('login-div').style.display = 'none';
    document.getElementById('register-div').style.display = 'block';
    document.getElementById('login-toggle').classList.remove('active');
    document.getElementById('register-toggle').classList.add('active');
}

async function isUserExisted(email) {
    let url = "http://localhost:" + port + "/api/user/find?mail=" + email;

    try {
        const response = await fetch(url);
        const data = await response.text();
        console.log(data);

        if(data == "true")
            return true;
        else
            return false;
    } catch (err) {
        console.log("Failed: " + err);
        return false;
    }

    return false;
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
        console.log(data);

        myName = data.name;
        myEmail = data.email;
        myPassword = data.pwd;

        if(myName == null || myEmail == null || myPassword == null)
            return false;
        else
            return true;
    }catch(err){
        console.log("Failed: " + err);
        return false;
    }

    return false;
}

async function saveUser(name, email, password) {
    let url = "http://localhost:" + port + "/api/user";
    let headers = {
        'Content-Type': 'application/json'
    }
    let body = {
        "email": email,
        "name": name,
        "pwd": password
    }

    try{
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body)});
        const data = await response.json();
        console.log(data);

        myName = data.name;
        myEmail = data.email;
        myPassword = data.pwd;

        if(myName == null || myEmail == null || myPassword == null)
            return false;
        else
            return true;
    }catch(err){
        console.log("Failed: " + err);
        return false;
    }

    return false;
}

async function userLogin(event) {
    event.preventDefault();
    let email = document.getElementById('login-email').value;
    let password = document.getElementById('login-password').value;

    if(await isUserExisted(email) && await findUserByEmail(email, password)){
        alert("Login successfully");
        localStorage.setItem('name', myName);
        localStorage.setItem('email', email);
        localStorage.setItem('password', password);
        localStorage.setItem('enter', 'true');
        window.location.href = "http://localhost:" + port + "/chatBot";
    }
    else{
        alert("Login Failed");
        document.getElementById('login-email').value = "";
        document.getElementById('login-password').value = "";
    }
}

async function userRegister(event) {
    event.preventDefault();
    let name = document.getElementById('register-username').value;
    let email = document.getElementById('register-email').value;
    let password = document.getElementById('register-password').value;

    if(await isUserExisted(email) == true){
        alert("Email already exists");
        document.getElementById('register-username').value = "";
        document.getElementById('register-email').value = "";
        document.getElementById('register-password').value = "";
        return;
    }

    if(await saveUser(name, email, password) == true){
        alert("Register successfully");
        localStorage.setItem('name', myName);
        localStorage.setItem('email', email);
        localStorage.setItem('password', password);
        localStorage.setItem('enter', 'true');
        window.location.href = "http://localhost:" + port + "/chatBot";
    }
}

window.addEventListener("load", start, false);