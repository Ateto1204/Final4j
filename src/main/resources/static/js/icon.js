function addLikeIcon() {
    const chatBox = document.getElementById("chatBox");
    const messageElement = document.createElement("div");
    messageElement.classList.add("like-option");
    messageElement.innerHTML = "<div class=\"iconDiv like\" id=\"like"+msgNumber+"\" onclick=\"increaseWeight("+msgNumber+")\">\n" +
        "                        <img src=\"/img/like.png\" class=\"icon\" alt=\"Like Icon\"/>\n" +
        "                    </div>\n" +
        "                    <div class=\"iconDiv dislike\" id=\"dislike"+msgNumber+"\" onclick=\"decreaseWeight("+msgNumber+")\">\n" +
        "                        <img src=\"/img/dislike.png\" class=\"icon\" alt=\"Dislike Icon\"/>\n" +
        "                    </div>";
    chatBox.appendChild(messageElement);
}

function increaseWeight(id) {
    document.getElementById("like"+id).classList.add('active');
    document.getElementById("dislike"+id).classList.remove('active');
    console.log("like" + id);
}

function decreaseWeight(id) {
    document.getElementById("like"+id).classList.remove('active');
    document.getElementById("dislike"+id).classList.add('active');
    console.log("dislike" + id);
}