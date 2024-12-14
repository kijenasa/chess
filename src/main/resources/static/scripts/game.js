var board = null;
var game = new Chess();
var whiteSquareGrey = '#a9a9a9';
var blackSquareGrey = '#696969';

var gameUrl = window.location.pathname;
var apiUrl = "/api/game/";

var side;
var uuid;

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

sendPost(apiUrl + gameUrl.split('/')[2] + "/join", null)
  .then(response => {
    if(response === null) {
      config.log("Game is full");
    } else {
      responseJson = JSON.parse(response)
      uuid = responseJson.uuid;
      if (responseJson.side === "WHITE") {
        side = 'w';
      } else if (responseJson.side === "BLACK") {
        side = 'b';
      }
      console.log(uuid);
      console.log(side);
    }
  })

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

var config = {
  draggable: true,
  position: 'start',
  onDragStart: onDragStart,
  onDrop: onDrop,
  onMouseoutSquare: onMouseoutSquare,
  onMouseoverSquare: onMouseoverSquare,
  onSnapEnd: onSnapEnd,
  pieceTheme: '/img/chesspieces/wikipedia/{piece}.png'
};

//http://localhost:8080/game/xxx
//http://localhost:8080/api/game/xxx/join

const evtSource = new EventSource(apiUrl + gameUrl.split('/')[2] + "/events");

evtSource.onmessage = (event) => {
  console.log(event.data);
};

document.addEventListener("DOMContentLoaded", function() {
    board = Chessboard('myBoard', config)
});