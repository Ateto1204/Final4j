function addLikeIcon(model) {
    const messageElement = document.createElement("div");
    messageElement.classList.add("like-option");
    messageElement.innerHTML = `<div class="iconDiv like" id="like${msgNumber}" onclick="increaseWeight('${model}',${msgNumber})">\n` +
        `                        <img src="/img/like.png\" class=\"icon\" alt=\"Like Icon\"/>\n` +
        `                    </div>\n` +
        `                    <div class="iconDiv dislike" id="dislike${msgNumber}" onclick="decreaseWeight('${model}',${msgNumber})">\n` +
        `                        <img src="/img/dislike.png" class="icon" alt="Dislike Icon"/>\n` +
        `                    </div>`;
    return messageElement;
}

function addModelLogo(model) {
    const messageElement = document.createElement("div");
    messageElement.classList.add("modelDiv");
    messageElement.innerHTML = `<img src="/img/${model}.png" class="model" alt="${model} logo"/>`;
    return messageElement;
}

function increaseWeight(model, id) {
    const like = document.getElementById("like"+id);
    const dislike = document.getElementById("dislike"+id);

    if(like.classList.contains("active"))
        return;
    if(dislike.classList.contains("active"))
        modifyWeight(model, "INCREASE");

    like.classList.add('active');
    dislike.classList.remove('active');
    console.log("like" + id);
    console.log(model);
    modifyWeight(model, "INCREASE");
}

function decreaseWeight(model, id) {
    const like = document.getElementById("like"+id);
    const dislike = document.getElementById("dislike"+id);

    if(dislike.classList.contains("active"))
        return;
    if(like.classList.contains("active"))
        modifyWeight(model, "DECREASE");

    like.classList.remove('active');
    dislike.classList.add('active');
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
        "pwd": userPassword,
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

module.exports = { addLikeIcon, addModelLogo };