

function Make_graph(){

}

Make_graph.prototype = {
    init : function(type, canvas, data1, data2){

        if(type == 'graph') this.makeGraph(canvas, data1, data2);
        else this.makeChart(canvas, data1, data2);
        
        
    }
    ,
    makeGraph : function(canvas, data1, data2){
        var chartArea = document.getElementById(canvas).getContext('2d');
        var myChart = new Chart(chartArea, {
            // ①차트의 종류(String)
            type: 'bar',
            // ②차트의 데이터(Object)
            data: {
                // ③x축에 들어갈 이름들(Array)
                labels: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
                // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
                datasets: [
                    {
                    // ⑤dataset의 이름(String)
                    label: '2022년',
                    // ⑥dataset값(Array)
                    data: data1,
                    // ⑦dataset의 배경색(rgba값을 String으로 표현)
                    backgroundColor: 'rgba(26, 112, 211, 0.2)',
                    // ⑧dataset의 선 색(rgba값을 String으로 표현)
                    borderColor: 'rgba(26, 112, 211, 1)',
                    // ⑨dataset의 선 두께(Number)
                    borderWidth: 1
                }, 
                {
                    label: '2023년',
                    data: data2,
                    type:'line',
                    fill:false,
                    borderColor:'rgba(27, 190, 255)'
                }
            ]
            },
            // ⑩차트의 설정(Object)
            options: {
                // ⑪축에 관한 설정(Object)
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                },
                responsive:true,
                maintainAspectRatio:true
            }
        });

        window.addEventListener('resize', function() {
            console.log('??????????')
            myChart.resize();
        });
    }
    ,
    makeChart : function(canvas, data){
        

        var chartArea = document.getElementById(canvas).getContext('2d');
        var myChart = new Chart(chartArea, {
            type: 'doughnut',
            data: {
              labels: data.label,//['소설', '에세이', '경제', '영어', '공무원'],
              datasets: [{
                data: data.rank,//[10, 20, 30, 10, 40],
                backgroundColor: data.background,//['rgba(26, 112, 211, .7)','rgba(21, 177, 55, .7)','rgba(221, 37, 37, .7)', 'rgba(138, 37, 221, .7)', 'rgba(233, 236, 41, .7)']
              }]
            },
            options:{
              responsive:true,
              maintainAspectRatio:true,
              plugins: {
                legend: {
                  position: 'top',
                  display: false, // 범례를 표시하지 않도록 설정
                }
              }
            },
         });

        window.addEventListener('resize', function() {
            myChart.resize();
        });
    }
    
}