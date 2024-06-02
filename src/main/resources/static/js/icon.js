function addLikeIcon(model) {
    const chatBox = document.getElementById("chatBox");
    const messageElement = document.createElement("div");
    messageElement.classList.add("like-option");
    messageElement.innerHTML = "<div class=\"iconDiv like\" id=\"like"+msgNumber+"\" onclick=\"increaseWeight(\'"+model+"\',"+msgNumber+")\">\n" +
        "                        <img src=\"/img/like.png\" class=\"icon\" alt=\"Like Icon\"/>\n" +
        "                    </div>\n" +
        "                    <div class=\"iconDiv dislike\" id=\"dislike"+msgNumber+"\" onclick=\"decreaseWeight(\'"+model+"\',"+msgNumber+")\">\n" +
        "                        <img src=\"/img/dislike.png\" class=\"icon\" alt=\"Dislike Icon\"/>\n" +
        "                    </div>";
    chatBox.appendChild(messageElement);
}

function increaseWeight(model, id) {
    document.getElementById("like"+id).classList.add('active');
    document.getElementById("dislike"+id).classList.remove('active');
    console.log("like" + id);
    console.log(model);
    modifyWeight(model, "INCREASE");
}

function decreaseWeight(model, id) {
    document.getElementById("like"+id).classList.remove('active');
    document.getElementById("dislike"+id).classList.add('active');
    console.log("dislike" + id);
    console.log(model);
    modifyWeight(model, "DECREASE");
}

async function modifyWeight(model, status) {
    let url = "http://localhost:" + port + "/api/user/modify";
    let headers = {
        'Content-Type': 'application/json'
    }
    let body = {
        "email": userEmail,
        "password": userPassword,
        "model": model,
        "status": status
    }

    try{
        const response = await fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(body)});
        const data = await response.text();
    }catch(err){
        console.log("Failed: " + err);
    }
}