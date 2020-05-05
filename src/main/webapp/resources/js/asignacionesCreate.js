

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
    $("table").each(function (index) {
        if (index == 0) {
            $(this).attr("id", "lstUnidades");
            $(this).attr("class", "table row-border table-hover");
        } else if (index == 1) {
            $(this).attr("id", "lstMotorista");
            $(this).attr("class", "table row-border table-hover");
        } else {
            $(this).attr("id", "lstAux");
            $(this).attr("class", "table row-border table-hover");
        }
    });
    
    $('[data-toggle="tooltip"]').tooltip()
    var table = $('#lstUnidades').DataTable({
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false
    });
    $('#myInput1').on('keyup', function () {
        table.search(this.value).draw();
    });
    
    var table2 = $('#lstMotorista').DataTable({
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false
    });
    $('#myInput2').on('keyup', function () {
        table2.search(this.value).draw();
    });
    
    var table3 = $('#lstAux').DataTable({
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false
    });
    $('#myInput3').on('keyup', function () {
        table3.search(this.value).draw();
    });
});








