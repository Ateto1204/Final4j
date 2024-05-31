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
        sendMessageToLM(message);
    }
}

function addBotMessage(message){
    if(isMessage(message)){
        addMessage(toMessage(message), "bot");
        addLikeIcon();
        msgCount++;
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
    if(sender == "bot")
        messageElement.setAttribute("id", "msg"+msgCount);
    messageElement.innerHTML = message;
    chatBox.appendChild(messageElement);
}

async function sendMessageToLM(message) {
    let url = "http://localhost:8080/api/user/" + userEmail + "?msg=" + message;
    try{
        const response = await fetch(url, {method: "POST"});
        const responseMsg = await response.text();
        console.log(responseMsg);
        addBotMessage(responseMsg);
    }catch(err){
        console.log("Failed: " + err);
    }
}