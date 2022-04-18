let chartPie = document.querySelectorAll("#chart-pie");

chartPie.forEach((myChart, index) => {
  new Chart(myChart, {
    type: "pie", // bar, horizontalBar, pie, line, doughnut, radar, polarArea
    data: {
      labels: ["OEE", "OEE_1"],
      datasets: [
        {
          data: [29, 71],
          //backgroundColor:'green',
          backgroundColor: ["rgba(255, 99, 132, 0.6)", "rgba(54, 162, 235, 0.6)"],
          borderWidth: 1,
          borderColor: "#2C272E",
          hoverBorderWidth: 1,
          hoverBorderColor: "#090910",
        },
      ],
    },
    options: {
      legend: {
        display: false,
      },
      layout: {
        padding: {
          left: 0,
          right: 0,
          bottom: 0,
          top: 0,
        },
      },
      tooltips: {
        enabled: true,
      },
      responsive: true,
      maintainAspectRatio: false,
    },
  });
});
