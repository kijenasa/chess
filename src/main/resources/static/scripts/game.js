var board = null;
var game = new Chess();
var whiteSquareGrey = '#a9a9a9';
var blackSquareGrey = '#696969';

var gameUrl = window.location.pathname;
var apiUrl = "/api/game/";

var side;
var uuid;

function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=" + gameUrl;
}

function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function sendPost(url, data) {
  return new Promise ((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
          resolve(xhr.responseText);
        }
    };
    xhr.send(JSON.stringify(data));
  })
}

function sendGet(url) {
    return new Promise ((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", url);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve(xhr.responseText);
            }
        };
        xhr.send();
    })
}

function removeGreySquares() {
  $('#myBoard .square-55d63').css('background', '')
}

function greySquare(square) {
  var $square = $('#myBoard .square-' + square)

  var background = whiteSquareGrey
  if ($square.hasClass('black-3c85d')) {
    background = blackSquareGrey
  }

  $square.css('background', background)
}

function onDragStart(source, piece) {
  // do not pick up pieces if the game is over
  if(game.game_over()) return false

  // or if it's not the players turn
  if((side === 'w' && piece.search(/^b/) !== -1) || (side === 'b' && piece.search(/^w/) !== -1)) {
    return false
  }
}

function onDrop(source, target) {
    removeGreySquares()

    // see if the move is legal
    var move = game.move({
        from: source,
        to: target,
        promotion: 'q' // NOTE: always promote to a queen for example simplicity
    })

    if(move) {
        var result = {
            from: move.from.toUpperCase(),
            to: move.to.toUpperCase()
        };

        sendPost(apiUrl + gameUrl.split('/')[2] + "/move?playerUuid=" + uuid, result)
        .then(response => {

            if(response === false) {
                return 'snapback'
            }
        })
    }

    // illegal move
    if (move === null) return 'snapback'
}

function onMouseoverSquare(square, piece) {
  if((side === 'w' && piece.search(/^b/) !== -1) || (side === 'b' && piece.search(/^w/) !== -1)) {
    return false
  }

  // get list of possible moves for this square
  var moves = game.moves({
    square: square,
    verbose: true
  })

  // exit if there are no moves available for this square
  if(moves.length === 0) return

  // highlight the square they moused over
  greySquare(square)

  // highlight the possible squares for this piece
  for(var i = 0; i < moves.length; i++) {
    greySquare(moves[i].to)
  }
}

function onMouseoutSquare(square, piece) {
  removeGreySquares()
}

function onSnapEnd() {
  board.position(game.fen())
}

async function initializeGame() {
    if(getCookie("uuid") === "") {
        const response = await sendPost(apiUrl + gameUrl.split('/')[2] + "/join", null);
        if(response === null) {
            alert("Game is full");
        } else {
            responseJson = JSON.parse(response);
            uuid = responseJson.uuid;
            if (responseJson.side === "WHITE") {
                side = 'w';
            } else if (responseJson.side === "BLACK") {
                side = 'b';
            }
        }

        setCookie("uuid", uuid, 7);
        setCookie("side", side, 7);
    } else {
        uuid = getCookie("uuid");
        side = getCookie("side");
    }

    var config = {
        draggable: true,
        position: fen,
        onDragStart: onDragStart,
        onDrop: onDrop,
        onMouseoutSquare: onMouseoutSquare,
        onMouseoverSquare: onMouseoverSquare,
        onSnapEnd: onSnapEnd,
        pieceTheme: '/img/chesspieces/wikipedia/{piece}.png'
    };
    board = Chessboard('myBoard', config)

    const evtSource = new EventSource(apiUrl + gameUrl.split('/')[2] + "/events");
    evtSource.onmessage = (event) => {
        board.position(event.data);
        game.load(event.data);
    };
}

document.addEventListener("DOMContentLoaded", initializeGame);