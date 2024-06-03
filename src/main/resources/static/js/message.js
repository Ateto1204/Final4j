function getInputMessage(){
    const text = document.getElementById("inputText");
    if(isMessage(text.value)){
        userSendMessage(text.value);
        setTimeout(() => {
            text.value = "";
        }, 1);
    }
}

function userSendMessage(message) {
    addUserMessage(message);
    sendMessageToLM(message);
}

function addUserMessage(message){
    const chatBox = document.getElementById("chatBox");
    const msg = addMessage(toMessage(message), "user");
    chatBox.appendChild(msg);
}

function addBotMessage(model, message){
    const chatBox = document.getElementById("chatBox");
    const modelLogo = addModelLogo(model);
    const msg = addMessage(toMessage(message), "bot");
    const icon = addLikeIcon(model);

    const messageElement = document.createElement("div");
    messageElement.classList.add("botDiv");
    messageElement.appendChild(modelLogo);
    messageElement.appendChild(msg);

    chatBox.appendChild(messageElement);
    chatBox.appendChild(icon);
    msgNumber++;
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
    const messageElement = document.createElement("div");
    messageElement.classList.add(sender);
    messageElement.innerHTML = message;
    return messageElement;
}

async function sendMessageToLM(message) {
    let url = "http://localhost:" + port + "/api/user/send";
    let headers = {
        'Content-Type': 'application/json'
    }
    let body = {
        "email": userEmail,
        "pwd": userPassword,
        "message": message
    }

    try{
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body)});
        const data = await response.json();
        addBotMessage(data.model, data.message);
    }catch(err){
        console.log("Failed: " + err);
    }
}

function getHistoryMessage(userMessage, botMessage, model) {
    let len = userMessage.length;

    for(let i = 0; i < len; i++){
        addUserMessage(userMessage[i]);
        addBotMessage(model, botMessage[i]);
    }
}