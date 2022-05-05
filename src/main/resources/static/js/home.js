$(document).ready(() => {
    
    var table = $('#table-home').DataTable({
        dom: 'Pfrtip'
    });
	var container = $('<div/>').insertBefore(table.table().container());
        
         var chart = Highcharts.stockChart(container[0], {
        chart: {
            type: 'pie'
        },
        title: {
            text: 'Staff Count Per Position'
        },
        series: [
            {
                data: chartData(table)
            }
        ]
    });
    
     table.on('draw', function () {
        chart.series[0].setData(chartData(table));
    });
       
});

function chartData(table) {
    var counts = {};
 
    // Count the number of entries for each position
    table
        .column(1, { search: 'applied' })
        .data()
        .each(function (val) {
            if (counts[val]) {
                counts[val] += 1;
            } else {
                counts[val] = 1;
            }
        });
 
    // And map it to the format highcharts uses
    return $.map(counts, function (val, key) {
        return {
            name: key,
            y: val
        };
    });
}