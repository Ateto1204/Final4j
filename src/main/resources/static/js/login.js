let myName;
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

async function findUserByEmail(email){
    let url = "http://localhost:8080/api/user/" + email;
    let isSuccess = false;

    try{
        const response = await fetch(url);
        const data = await response.json();
        console.log(data);
        myName = data.name;
        isSuccess = true;
    }catch(err){
        console.log("Failed: " + err);
        isSuccess = false;
    }

    return isSuccess;
}

async function saveUser(name, email) {
    let url = "http://localhost:8080/api/user";
    let headers = {
        'Content-Type': 'application/json'
    }
    let body = {
        "email": email,
        "name": name,
        "sentMsg": [],
        "responsedMsg": [],
        "models": {
            "gemini": 1.0,
            "llama": 1.0,
            "gpt4": 1.0
        }
    }

    let isSuccess = false;
    try{
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body)});
        const data = await response.json();
        myName = data.name;
        console.log(data);
        isSuccess = true;
    }catch(err){
        console.log(err);
        isSuccess = false;
    }

    return isSuccess;
}

async function userLogin(event) {
    event.preventDefault();
    let email = document.getElementById('login-email').value;
    let password = document.getElementById('login-password').value;

    if(await findUserByEmail(email) == true){
        alert("Login successfully");
        localStorage.setItem('name', myName);
        localStorage.setItem('email', email);
        localStorage.setItem('enter', "true");
        window.location.href = "http://localhost:8080/chatBot";
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

    if(await findUserByEmail(email) == true){
        alert("Email already exists");
        document.getElementById('register-username').value = "";
        document.getElementById('register-email').value = "";
        document.getElementById('register-password').value = "";
        return;
    }

    if(await saveUser(name, email) == true){
        alert("Register successfully");
        localStorage.setItem('name', myName);
        localStorage.setItem('email', email);
        localStorage.setItem('enter', "true");
        window.location.href = "http://localhost:8080/chatBot";
    }
}

window.addEventListener("load", start, false);