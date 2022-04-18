let chart = document.querySelectorAll("#myChart");
let barChart = [];
// Global Options
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 14;
Chart.defaults.global.defaultFontColor = "#777";

chart.forEach((myChart, index) => {
  barChart.push(
    new Chart(myChart, {
      type: "horizontalBar", // bar, horizontalBar, pie, line, doughnut, radar, polarArea
      data: {
        labels: ["FGs", "Target"],
        datasets: [
          {
            data: [4245, 6000],
            //backgroundColor:'green',
            backgroundColor: [
              "rgba(255, 99, 132, 0.6)",
              "rgba(54, 162, 235, 0.6)",
            ],
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
        scales: {
          xAxes: [
            {
              display: true,
              ticks: {
                beginAtZero: true,
                max: 10000,
                min: 0,
              },
            },
          ],
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
    })
  );
});
