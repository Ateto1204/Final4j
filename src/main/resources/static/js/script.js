let msgCount = 0;
let userName, userEmail;

function start(){
    msgCount = 1;
    userName = localStorage.getItem("name");
    userEmail = localStorage.getItem("email");
    console.log(userName);
    console.log(userEmail);

    if(userEmail == null)
        window.location.href = "http://localhost:8080/login";
    else
        document.getElementById("userName").innerHTML = userName;

    // document.getElementById("clickBtn").addEventListener("click", move_leftPage, false);
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
    window.location.href = "http://localhost:8080/login";
}

window.addEventListener("load", start, false);