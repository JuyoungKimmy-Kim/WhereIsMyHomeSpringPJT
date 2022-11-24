export default ({
    type :'line',
    data : {
        labels : ['Jan', 'Feb', 'Mar', 'Apr', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        datasets : [
            {
                label : '월별 거래 금액 / 단위: 만원',
                data : [],
                backgroundColor : 'rgba(54,73,93,.5)',
                borderColor : '#36495d',
                borderWidth : 1,
            },
        ]
    },

    options : {
        responsive : true,
        lineTension :1,
        scales : {
            yAxes : [{
                ticks : {
                    beginAtZero :true,
                    padding :25,
                }
            }]
        },
    }
})