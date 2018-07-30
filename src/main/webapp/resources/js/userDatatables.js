var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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
    $.get(ajaxUrl, fillTable(data));
}

function checkEnabled(event) {
    var id = $(event.target).closest("tr").attr("id");
    var value = $(event.target).is(':checked');

    $.ajax({
        url: ajaxUrl + id + "?enabled=" + value,
        type: "PUT",
        success: function () {
            $(event.target).closest("tr").attr("data-userEnabled", value);
            successNoty(value ? "Set enable" : "Set disable");
        },
        error: function () {
            event.target.checked = !event.target.checked;
        }
    });
}