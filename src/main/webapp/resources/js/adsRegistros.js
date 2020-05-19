function createJsonExtraPay() {}
;

function validar(parent) {
    let correcto = ValidateFieldText(parent);
    $("#formDetallesFinalizar\\:jsonExtraPay").val(createJsonExtraPay());
    if (!correcto) {
        Swal.fire({
            icon: 'error',
            title: 'No se permiten campos vacios',
            text: 'Debe llenar todos los campos!'
        });
    } else {
        $('.modal').modal('hide');
    }
    return correcto;
}
;


function newContainerExtraPay() {
    let structura;
    structura = "<div class='row pb-2 registrosExtraPay'>" +
            "<div class='input-group pnlinputTextCustom1 col-md-8'>" +
            "<div class='input-group-prepend'>" +
            "<span class='input-group-text'><i class='fas fa-angle-right'></i></span>" +
            "</div>" +
            "<input type='text' class='form-control inputTextCustom1 txtDescripcion' placeholder='Descripcion' maxlength='50'>" +
            "</div>" +
            "<div class='input-group pnlinputTextCustom1 col-md-4'>" +
            "<div class='input-group-prepend'>" +
            "<span class='input-group-text'><i class='fas fa-dollar-sign'></i></span>" +
            "</div>" +
            "<input type='text' class='form-control inputTextCustom1 txtMonto' placeholder='Cantidad' maxlength='10'>" +
            "</div>" +
            "</div>";
    return structura;
}


function addNewExtraPay() {
    let pnlParent = $('#pagosExtras #bodyExtraPay');
    $(newContainerExtraPay()).appendTo($(pnlParent));
}

function deleteRecord() {
    let pnlParent = $('#pagosExtras #bodyExtraPay');
    let childs = pnlParent.children();
    let childRemove = childs[childs.length - 1];
    $(childRemove).remove();
}



function createJsonExtraPay() {
    let pnlParent = $('#pagosExtras #bodyExtraPay');
    let count = pnlParent.children().length;
    let arrayRegistros = $(".registrosExtraPay");
    var jsonMtx = [];
    var desc, sum;
    for (let i = 0; i < count; i++) {
        desc = $(arrayRegistros[i]).find('.txtDescripcion').val();
        sum = $(arrayRegistros[i]).find('.txtMonto').val();
        if (desc.length > 0 && sum.length > 0) {
            var obj = {descripcion: desc, monto: sum};
            jsonMtx.push(obj);
        }
    }
    let cadena = JSON.stringify(jsonMtx);

    if (cadena == "[]") {
        return "";
    } else {
        return cadena;
    }
}


var table = $("#listFormEmployees\\:lstEmployees table").DataTable({
    responsive: true,
    "autoWidth": true,
    "bPaginate": true,
    "bLengthChange": true,
    "processing": true,
    "bFilter": true,
    "bInfo": false,
    "bAutoWidth": true
});

    $('#myInput2').on('keyup', function () {
        table.search(this.value).draw();
    });
    