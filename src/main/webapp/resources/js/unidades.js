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

$(function () {
    $('.ui-datatable-tablewrapper > table').attr("id", "lstUnidades");
    $('.ui-datatable-tablewrapper > table').attr("class", "table row-border table-hover");
    $('.ui-datatable-tablewrapper > table').attr("style", "width: 100%");
    //$('tr').attr("data-widget", "control-sidebar");
    //$('tr').attr("data-slide", "true");
    $('tr').attr("data-toggle", "modal");
    $('tr').attr("data-target", "#openModalOne");
    $('[data-toggle="tooltip"]').tooltip()
    var table = $('#lstUnidades').DataTable({
        responsive: true,
        "autoWidth": true,
        "bPaginate": true,
        "bLengthChange": true,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": true
    });
    $('#myInput1').on('keyup', function () {
        table.search(this.value).draw();
    });
})






var files = [];

function deleteFile(id) {
    files.splice(id, 1);
    printFiles();
}

function printFiles() {
    // files is a FileList of File objects. List some properties.
    var output = [];
    for (var i = 0, f; f = files[i]; i++) {
        var icon = "";
        switch (f.type) {
            case "application/pdf":
            {
                icon = `<i style="font-size:40px" class="far fa-file-pdf"></i>`;
                break;
            }
            case "image/png":
            {
                icon = `<i style="font-size:40px" class="far fa-file-image"></i>`;
                break;
            }
            default:
            {
                icon = `<i style="font-size:40px" class="fas fa-file"></i>`;
                break;
            }
        }
        var html = `
            <div class="col-6 float-left col-lg-3 col-md-4">
            <div class="card m-2 col-12" style="width: 8rem;"
                data-toggle="tooltip" data-placement="top" title="${escape(f.name)}">
            <span onclick="deleteFile(${i})" style="cursor:pointer" type="button" class="close" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </span>
            <div class="card-body">
             
              <center>
                ${icon}
                <div class="cortarTexto">
                      <small  class="card-text">${escape(f.name)}</small>
                </div>
              </center>
            </div>
          </div>
          </div>
       `;
        /*output.push('<li><strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ',
         (f.size / 1024).toFixed(2) , ' Kb </li>');*/
        output.push(html);
    }
    document.getElementById('list').innerHTML = output.join('');
}

function handleFileSelect(evt) {
    var fi = evt.target.files;
    for (var i = 0; i < fi.length; i++) {
        files.push(fi[i]);
    }
    printFiles();
}

if (document.getElementById('form:upload') != null) {
    document.getElementById('form:upload').addEventListener('change', handleFileSelect, false);
}

if (document.getElementById("btnCloseR") != null) {
    document.getElementById("btnCloseR").addEventListener("click", () => {
        $('#btnCloseR').attr("data-widget", "control-sidebar");
        $('#btnCloseR').attr("data-slide", "true");
    });
}



