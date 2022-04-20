var socket = new SockJS("http://192.168.1.229:8081/ws");
var dataLine = [];
stompClient = Stomp.over(socket);

stompClient.connect({}, onConnected, onError);

function onConnected() {
  // Subscribe to the Public Topic
  stompClient.subscribe("/topic/publicChatRoom", onMessageReceived);

  // Tell your username to the server
  //   stompClient.send(
  //     "/app/hello",
  //     {},
  //     JSON.stringify({ sender: "ABC", type: "JOIN" })
  //   );
}

function onMessageReceived(payload) {
  let data = JSON.parse(payload.body);
  dataLine = data;
  console.log(dataLine);
  for (let i = 0; i < 12; i++) {
    if (i == 0) {
      target[i].value = dataLine[i].target;
      runtime[i].textContent = dataLine[i].runtime;
      downtime[i].textContent = dataLine[i].downtime;
      stt[i].textContent = dataLine[i].status == "1" ? "Running" : "Stop";
      fgs[i].textContent = dataLine[i].counterOut;
      const testBarChart = barChart[i];
      testBarChart.data.datasets[0].data[0] = dataLine[i].counterOut;
      testBarChart.data.datasets[0].data[1] = dataLine[i].target;
      testBarChart.update();
    }
  }
}

function onError(error) {
  console.log(
    "Could not connect to WebSocket server. Please refresh this page to try again!"
  );
}
