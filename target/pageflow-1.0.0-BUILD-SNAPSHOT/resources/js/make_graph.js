

function Make_graph(){

}

Make_graph.prototype = {
    init : function(type, canvas, data){

        if(type == 'graph') this.makeGraph(canvas, data);
        else this.makeChart(canvas, data);
        
    }
    ,
    makeGraph : function(canvas, data){
        var chartArea = document.getElementById(canvas).getContext('2d');
        var myChart = new Chart(chartArea, {
            // ①차트의 종류(String)
            type: 'bar',
            // ②차트의 데이터(Object)
            data: {
                // ③x축에 들어갈 이름들(Array)
                labels: ['2022/10', '2022/11', '2022/12', '2023/01', '2023/02', '2023/03', '2023/04', '2023/05', '2023/06', '2023/07', '2023/08', '2023/09'],
                // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
                datasets: [{
                    // ⑤dataset의 이름(String)
                    label: '업계 평균',
                    // ⑥dataset값(Array)
                    data: [12, 19, 3, 5, 2, 3, 12, 19, 3, 5, 2, 3],
                    // ⑦dataset의 배경색(rgba값을 String으로 표현)
                    backgroundColor: 'rgba(26, 112, 211, 0.2)',
                    // ⑧dataset의 선 색(rgba값을 String으로 표현)
                    borderColor: 'rgba(26, 112, 211, 1)',
                    // ⑨dataset의 선 두께(Number)
                    borderWidth: 1
                },
                {
                    label: '우리 회사',
                    data: [2, 4, 3, 1, 13, 3, 1, 3, 4, 5, 2, 3],
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
            type: 'pie',
            data: {
              labels: ['소설', '에세이', '경제', '영어', '공무원'],
              datasets: [{
                data: [10, 20, 30, 10, 40],
                backgroundColor:['rgba(26, 112, 211, .7)','rgba(21, 177, 55, .7)','rgba(221, 37, 37, .7)', 'rgba(138, 37, 221, .7)', 'rgba(233, 236, 41, .7)']
              }]
            },
            options:{
              responsive:true,
              maintainAspectRatio:true,
              plugins: {
                legend: {
                  position: 'top',
                }
              }
            },
         });

        window.addEventListener('resize', function() {
            myChart.resize();
        });
    }
    
}