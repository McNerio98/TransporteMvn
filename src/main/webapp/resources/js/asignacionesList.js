$(function () {
    $('[data-toggle="tooltip"]').tooltip()
    $("table").each(function (index) {
        if (index == 0) {
            $(this).attr("id", "lstAsignaciones");
            $(this).attr("class", "table row-border table-hover ");
            $(this).attr("style", "width: 100%");
        } else if (index == 1) {
            $(this).attr("id", "lstMotorista");
            $(this).attr("class", "table row-border table-hover");
        } else {
            $(this).attr("id", "lstAux");
            $(this).attr("class", "table row-border table-hover");
        }
    });
    var t = document.getElementById("lstAsignaciones").getElementsByTagName("tr");
    for (i = 0; i < t.length; i++) {
        t[i].classList.remove("ui-state-highlight");
    }
    $('[data-toggle="tooltip"]').tooltip()
    var table = $('#lstAsignaciones').DataTable({
        responsive: true,
        "autoWidth": true,
        "bPaginate": true,
        "bLengthChange": true,
        "processing": true,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": true
    });
    $('#myInput1').on('keyup', function () {
        table.search(this.value).draw();
    });
    var table2 = $('#lstMotorista').DataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false
    });
    $('#myInput2').on('keyup', function () {
        table2.search(this.value).draw();
    });
    var table3 = $('#lstAux').DataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false
    });
    $('#myInput3').on('keyup', function () {
        table3.search(this.value).draw();
    });
});
function selectCurrentRow(index) {
    var table = PF('myTableWidgetVar');
    console.log(index, table.rows.length);
//    var row = table.rows.get(index);
//    console.log(row);
//    if (row.getAttribute("data-ri") === index) {
//        table.unselectAllRows();
//        table.selectRow(index, true);
//
//
//    }
    table.unselectAllRows();
    console.log(index);
    table.selectRow(index, true);
}

function selectByRowKey(rowKey) {
    var table = PF('myTableWidgetVar');
    for (var i = 0; i < table.rows.length; i++) {
        var row = table.rows.get(i);
        if (parseInt(row.getAttribute("data-rk")) === rowKey) {
            console.log(parseInt(row.getAttribute("data-rk")), rowKey);
            console.log(row);
            console.log(i);
            table.unselectAllRows();
            table.selectRow(i, false);
            break;
        }
    }
    var t = document.getElementById("lstAsignaciones").getElementsByTagName("tr");
    for (i = 0; i < t.length; i++) {
        t[i].classList.remove("ui-state-highlight");
    }

}

function reset() {
    setTimeout(function () {
        $("#lstMotorista").DataTable().destroy();
        $("#lstAux").DataTable().destroy();
        $("table").each(function (index) {
            if (index == 1) {
                $(this).attr("id", "lstMotorista");
                $(this).attr("class", "table row-border table-hover");
            }
            if (index == 2) {
                $(this).attr("id", "lstAux");
                $(this).attr("class", "table row-border table-hover");
            }
        });
        var table2 = $('#lstMotorista').DataTable({
            "bPaginate": false,
            "bLengthChange": false,
            "bFilter": true,
            "bInfo": false,
            "bAutoWidth": false
        });
        $('#myInput2').on('keyup', function () {
            table2.search(this.value).draw();
        });
        var table3 = $('#lstAux').DataTable({
            "bPaginate": false,
            "bLengthChange": false,
            "bFilter": true,
            "bInfo": false,
            "bAutoWidth": false
        });
        $('#myInput3').on('keyup', function () {
            table3.search(this.value).draw();
        });
    }, 1100);

}

$(function() {
    $('#carrouselChange').carousel({interval: false});
});
$('#carrouselChange').on('slide.bs.carousel', function (number) {
// do something...
    
    console.log(number.to)
    if (number.to == 2) {
        document.getElementById("btnN").style.display = "none";
        document.getElementById("save:btnC").style.display = "block";
    } else {
        document.getElementById("save:btnC").style.display = "none";
        document.getElementById("btnN").style.display = "block";
    }
})