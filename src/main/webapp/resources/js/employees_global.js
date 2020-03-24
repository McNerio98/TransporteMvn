/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {
    //VARIABLES GLOBALES
    //DECLARACION DE FUNCIONES
    //LLAMADO Y CARGA DE FUNCIONES O METODOS

    $('#datepicker-group').datepicker({
        format: "dd/mm/yyyy",
        todayHighlight: true,
        autoclose: true,
        clearBtn: true
    });
    //ASIGNACION DE FUNCIONES A EVENTOS DE NODOS 
});

function closeModal(obj){
    $(obj).modal('hide');
};