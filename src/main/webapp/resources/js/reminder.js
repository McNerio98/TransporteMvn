$(function () {
  $('[data-toggle="tooltip"]').tooltip()
  $('#datepicker-group').datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true,
        autoclose: true,
        clearBtn: true
    });
})

