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
      // j = 2 * i;
      // target[j].value = dataLine[i].counterOut;
      // target[j + 1].textContent = dataLine[i].counterOut;
      speed[i].value = dataLine[i].speed;
      runtime[i].textContent = dataLine[i].runtime;
      stt[i].textContent = dataLine[i].status == "1" ? "Running" : "Stop";
      fgs[i].textContent = dataLine[i].counterOut;
    }
  }
  const dataLine1 = data[0];
  const counterOutLine1 = dataLine1.counterOut;
  const testBarChart = barChart[0];
  testBarChart.data.datasets[0].data[0] = counterOutLine1;
  console.log(counterOutLine1);
  testBarChart.update();
}

function onError(error) {
  console.log(
    "Could not connect to WebSocket server. Please refresh this page to try again!"
  );
}
