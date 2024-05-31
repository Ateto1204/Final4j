let count = 0;
let userName, userEmail;

function start(){
    userName = localStorage.getItem("name");
    userEmail = localStorage.getItem("email");
    console.log(userEmail);
    if(userEmail == null){
        window.location.href = "http://localhost:8080/login";
    }

    count = 0;
    const clickBtn = document.getElementById("clickBtn");
    clickBtn.addEventListener("click", move_leftPage, false);

    const inputBtn = document.getElementById("inputBtn");
    const text = document.getElementById("inputText");
    inputBtn.addEventListener("click", getInputMessage, false);
    text.addEventListener("keydown", function(event){
        if(event.key == "Enter"){
            if(event.shiftKey)
                text.value += "";
            else
                getInputMessage();
        }
    });
}

function move_leftPage(){
    const leftPage = document.getElementById("leftPage");
    const mainPage = document.getElementById("mainPage");
    count++;

    if(count % 2){
        for(let i=0, w = 0; i<25; i++){
            window.setTimeout(() => {
                w++;
                // console.log(w);
                leftPage.style.width = w + "%";
                mainPage.style.width = 100-w + "%";
            }, 8*i);
        }
    }
    else{
        for(let i=0, w=25; i<25; i++){
            window.setTimeout(() => {
                w--;
                // console.log(w);
                leftPage.style.width = w + "%";
                mainPage.style.width = 100-w + "%";
            }, 8*i);
        }
    }
}

function getInputMessage(){
    const text = document.getElementById("inputText");
    if(isMessage(text.value)){
        addUserMessage(text.value);
        setTimeout(() => {
            text.value = "";
        }, 1);
    }
}

function addUserMessage(message){
    if(isMessage(message)){
        addMessage(toMessage(message), "user");
    }
}

function addBotMessage(message){
    if(isMessage(message)){
        addMessage(toMessage(message), "bot");
    }
}

function isMessage(message){
    const it = message[Symbol.iterator]();
    let theChar = it.next();

    let check = false;
    while(!theChar.done){
        if(theChar.value != '\n' && theChar.value != ' ')
            check = true;
        theChar = it.next();
    }
    return check;
}

function toMessage(message) {
    let output = "";
    const it = message[Symbol.iterator]();
    let theChar = it.next();

    while(!theChar.done){
        if(theChar.value == '\n')
            output += "<br>";
        else
            output += theChar.value;
        theChar = it.next();
    }
    return output;
}

function addMessage(message, sender){
    const chatBox = document.getElementById("chatBox");
    const messageElement = document.createElement("div");
    messageElement.classList.add(sender);
    messageElement.innerHTML = message;
    chatBox.appendChild(messageElement);
}

window.addEventListener("load", start, false);