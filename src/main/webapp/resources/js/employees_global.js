/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function EVENTS_FORM_EMPLOYEES(){};

    function refreshEventRowSelect() {
        EVENTS_FORM_EMPLOYEES();
        var table = $("#listFormEmployees table").DataTable({
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

    }
    ;
    function verificarAvatar() {
        let currentValue = $('#frmEmployees\\:avatarValueHidden').val();
        if (currentValue.length > 1) {
            $('#profilesAvatarPanel img').each(function (index) {
                if ($(this).attr('avatarname') == currentValue) {
                    $(this).addClass('selected');
                }
            });
        }

    }
    //FUNCIONES DE ASIGNACIONES DE EVENTOS A NODOS RECARGABLES  
    function EVENTS_FORM_EMPLOYEES() {
        bsCustomFileInput.init();
        verificarAvatar()
        $('#profilesAvatarPanel img').off('click');
        $('#profilesAvatarPanel img').click(function () {
            $('#profilesAvatarPanel img').removeClass('selected');
            $(this).addClass('selected');
            $('#frmEmployees\\:avatarValueHidden').val($(this).attr('avatarname'));
        });

        $('.input-group.date').datepicker({
            format: "yyyy/mm/dd",
            todayHighlight: true,
            autoclose: true,
            clearBtn: true
        });
    }
    
$(document).ready(function () {
    verificarAvatar();
    EVENTS_FORM_EMPLOYEES();
    refreshEventRowSelect();

});


function validarFrmEmployee(parent){
    let correcto = ValidateFieldText(parent);
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