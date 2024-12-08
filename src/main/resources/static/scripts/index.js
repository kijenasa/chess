var boardConfig = {
    position: 'start',
    showNotation: false
};

document.addEventListener("DOMContentLoaded", function() {
    var board1 = Chessboard('board1', boardConfig);
});

function sendPost(url, data, callback) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 201) {
            callback(xhr);
        }
    };

    xhr.send(JSON.stringify(data));
}

function createGame() {
    sendPost("http://localhost:8080/api/game", null, function(xhr) {
        window.location.href = xhr.getResponseHeader("Location");
    });
}