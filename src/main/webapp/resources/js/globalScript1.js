
$(document).ready(function () {
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
    });

    function showMessage() {
        let nodo = $('#pnlMessageJSF');
        let contenido = $(nodo).text();
        let type = 'success';
        if ($(nodo).find('MessageError').length != 0) {
            type = 'error';
        }
        Toast.fire({
            type: type,
            title: contenido
        });
    }

    function verifiedContentMessage() {
        let nodo = $('#pnlMessageJSF');
        if ($(nodo).text().length > 5) {
            showMessage();
        }
    }

    verifiedContentMessage();

    $('#pnlMessageJSFParent').on('DOMSubtreeModified', showMessage);


    function refreshEventsButtons() {
        $('#pnlButtons .ui-state-disabled').prop('disabled', true);
    }

    refreshEventsButtons();
    $('#pnlButtons').on('DOMSubtreeModified', refreshEventsButtons);
});


function ContratoSuccess(data) {
    var Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
    });
    if (data.status === "begin") {
        console.log("Sent request. Waiting for response...");
    } else if (data.status === "complete") {
        console.log("Response received");
    } else if (data.status === "success") {
        console.log("Rendered successfully");
        $("#modalTipoContrato").modal("hide");
        Toast.fire({
            type: 'success',
            title: 'Los cambios han sido guardados'
        })
    }


}

function ContratoError(data) {
    let Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
    });
    Toast.fire({
        type: 'error',
        title: 'Hubo problemas al guardar los cambios' + data.status
    });
}



function showEmptyForms() {

};

function ValidateFieldText(idparent) {
    var correcto = true;
    var countEmpty = 0;
    let nodeParentString = "#" + idparent + " input[type='text'],#" + idparent + " input[type='email']";
    $(nodeParentString).removeClass("is-invalid");
    $(nodeParentString).each(function (e) {

        if (!$(this).is(":disabled")) {
            if ($(this).val().length == 0) {
                $(this).addClass("is-invalid");
                countEmpty++;
            }
        }
        correcto = (countEmpty > 0) ? false : true;
    });
    return correcto;
}