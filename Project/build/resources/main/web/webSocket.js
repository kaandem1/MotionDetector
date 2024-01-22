var webSocket;
var mp3_url = './alarm.mp3';
var audio = new Audio(mp3_url);
audio.loop = false;

//wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    //create WebSocket connection to server using the hostname and port
    webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/socket");
    console.log("WebSocket URL: ws://" + location.hostname + ":" + location.port + "/socket");

    //event handler for when the connection is opened
    webSocket.onopen = function (event) {
        console.log("WebSocket connection opened.");
    };

    //handler for incoming messages
    webSocket.onmessage = function (msg) {
        onMessage(msg);
        console.log("Something received")
    };
});

//to handle incoming WebSocket messages
function onMessage(msg) {
    //parse the received data
    var data = JSON.parse(msg.data);
    console.log(data);

    document.getElementById("frame").src = data.image;
    if (data.sound === true){
        console.log("Playing audio");
        audio.play();
    }
}
