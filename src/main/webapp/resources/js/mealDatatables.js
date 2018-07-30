var ajaxUrl = "ajax/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "datetime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function updateTable() {
   var filter = "filter?" + $('#filter').serialize();
    $.get(ajaxUrl + filter, fillTable(data));
}

function filter() {
    updateTable();
}

function clearFilter() {
    $('#filter').find ('input, date, time').each(function() {
        $(this).val($(this).data('defvalue'));
    });

    updateTable();
}