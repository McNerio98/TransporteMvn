
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

    
    function refreshEventsButtons(){
        $('#pnlButtons .ui-state-disabled').prop('disabled',true);
    }
    
    refreshEventsButtons();
    $('#pnlButtons').on('DOMSubtreeModified', refreshEventsButtons);
    
    

});
