const targetHistory = document.querySelectorAll(".target-history");
const runtimeHistory = document.querySelectorAll(".runtime-history");
const downtimeHistory = document.querySelectorAll(".downtime-history");
const sttHistory = document.querySelectorAll(".status-history");
const fgsHistory = document.querySelectorAll(".fgs-history");
const selectDate = document.getElementById("slct-date");
const selectShift = document.getElementById("slct-shift");
const selectLine = document.getElementById("slct-line");
const filterBtn = document.getElementById("filter-btn");
const tableHistory = document.getElementById("table-history");

filterBtn.addEventListener("click", () => {
  let data = {
    date: selectDate.value,
    shift: selectShift.value,
    line: selectLine.value,
  };
  console.log(data);
  fetch("http://192.168.1.229:8081/filter", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data != null) {
        tableHistory.classList.remove("visibility-hidden");
        targetHistory[0].textContent = data[0].target;
        fgsHistory[0].textContent = data[0].counterOut;
        runtimeHistory[0].textContent = data[0].runtime;
        downtimeHistory[0].textContent = data[0].downtime;
        sttHistory[0].textContent = data[0].status == "1" ? "Running" : "Stop";
        const testBarChartHistory = barChart[1];
        const testAPQChartHistory = apqCharts[1];
        const testPieChartHistory = chartPies[1];
        testBarChartHistory.data.datasets[0].data[0] = dataLine[0].counterOut;
        testBarChartHistory.data.datasets[0].data[1] = dataLine[0].target;
        testAPQChartHistory.data.datasets[0].data[0] = dataLine[0].available;
        testAPQChartHistory.data.datasets[0].data[1] = dataLine[0].performance;
        testAPQChartHistory.data.datasets[0].data[2] = dataLine[0].quantity;
        testPieChartHistory.data.datasets[0].data[0] = dataLine[0].oee;
        testPieChartHistory.data.datasets[0].data[1] = dataLine[0].oee1;
        testBarChartHistory.update();
        testAPQChartHistory.update();
        testPieChartHistory.update();
      }
    });
});
