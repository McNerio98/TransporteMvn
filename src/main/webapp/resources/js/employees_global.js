/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {
    //VARIABLES GLOBALES

    //Inicializacion de widget
    bsCustomFileInput.init();
    
    $('#datepicker-group').datepicker({
        format: "yyyy/mm/dd",
        todayHighlight: true,
        autoclose: true,
        clearBtn: true
    });


    //DECLARACION DE FUNCIONES

    function addEventSelect() {
        $('table.clobi-style tr').removeClass('selected');
        $(this).addClass('selected');
    }
    ;
    function refreshEventRowSelect() {
        $('table.clobi-style tbody tr').off('click');
        $('table.clobi-style tbody tr').on('click', addEventSelect);
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
        console.log('Entro aqui..');
        $('#profilesAvatarPanel img').off('click');
        $('#profilesAvatarPanel img').click(function () {
            $('#profilesAvatarPanel img').removeClass('selected');
            $(this).addClass('selected');
            $('#frmEmployees\\:avatarValueHidden').val($(this).attr('avatarname'));
        });
    }
    //LLAMADO Y CARGA DE FUNCIONES O METODOS
    refreshEventRowSelect();
    verificarAvatar();
    EVENTS_FORM_EMPLOYEES();

    //ASIGNACION DE FUNCIONES A EVENTOS DE NODOS


    //NODOS RECARGADOS 
    $('#frmEmployees').on('DOMSubtreeModified', EVENTS_FORM_EMPLOYEES);
    $('#listFormEmployees').on('DOMSubtreeModified', refreshEventRowSelect);

    //NOTA: Los nodos recargables son componentes que se actualizan mediante ajax y es necesario
    //asignar nuevamente los eventos que estos tienen 
});