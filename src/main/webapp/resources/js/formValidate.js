/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#btnMsg2').click(function () {
        console.log("Me esto ejecutando");
    });
});


function procesarJson(e) {
    e.preventDefault();
    //Aqui ira el proceso y validaciones 
    console.log("Informe");
    let formulario = document.getElementById("formTestNombre");
    //formulario.submit();
}


function handleMsg(msg) {
    alert(msg);
}

function validar(parent) {

    let correcto = ValidateFieldText(parent);

    if (!correcto) {
        Swal.fire({
            icon: 'error',
            title: 'No se permiten campos vacios',
            text: 'Debe llenar todos los campos!'
        })
    }
    /*let nom = document.getElementById("formTestNombre\:inputNombre");
     if(nom.value.length == 0){
     alert("Nombre necesario no se mando");
     correcto = false;
     $(nom).focus();
     }*/
    return correcto;
}




