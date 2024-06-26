const port = window.location.port;
let msgNumber = 0;
let userName, userEmail, userPassword, enter;
let sentMsg, responsedMsg;

function start(){
    msgNumber = 1;
    userName = localStorage.getItem("name");
    userEmail = localStorage.getItem("email");
    userPassword = localStorage.getItem("password");
    enter = localStorage.getItem("enter");
    console.log("name: " + userName);
    console.log("email: " + userEmail);
    console.log("password: " + userPassword);

    if(enter == null)
        window.location.href = "http://localhost:" +　port + "/login";

    document.getElementById("userName").innerHTML = userName;
    localStorage.removeItem("enter");

    // document.getElementById("clickBtn").addEventListener("click", move_leftPage, false);
    loadHistoryMessage();
    document.getElementById("logoutBtn").addEventListener("click", logout, false);
    document.getElementById("inputBtn").addEventListener("click", getInputMessage, false);
    document.getElementById("inputText").addEventListener("keydown", function(event){
        if(event.key == "Enter"){
            if(!event.shiftKey)
                getInputMessage();
        }
    });
}

// function move_leftPage(){
//     const leftPage = document.getElementById("leftPage");
//     const mainPage = document.getElementById("mainPage");
//     count++;
//
//     if(count % 2){
//         for(let i=0, w = 0; i<25; i++){
//             window.setTimeout(() => {
//                 w++;
//                 // console.log(w);
//                 leftPage.style.width = w + "%";
//                 mainPage.style.width = 100-w + "%";
//             }, 8*i);
//         }
//     }
//     else{
//         for(let i=0, w=25; i<25; i++){
//             window.setTimeout(() => {
//                 w--;
//                 // console.log(w);
//                 leftPage.style.width = w + "%";
//                 mainPage.style.width = 100-w + "%";
//             }, 8*i);
//         }
//     }
// }

function logout(){
    localStorage.clear();
    window.location.href = "http://localhost:" + port + "/login";
}

async function loadHistoryMessage() {
    let url = "http://localhost:" + port + "/api/user/check";
    let headers = {
        'Content-Type': 'application/json'
    }
    let body = {
        "email": userEmail,
        "pwd": userPassword
    }

    try{
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body)
        });
        const data = await response.json();

        sentMsg = data.sentMsg;
        responsedMsg = data.responsedMsg;
    }catch(err){
        console.log("Failed: " + err);
    }

    getHistoryMessage(sentMsg, responsedMsg);
}

window.addEventListener("load", start, false);