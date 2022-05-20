$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-fornecedor').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/fornecedores/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'nome'},
            {data: 'sobrenome'},
            {data: 'nascimento', render:
                        function (nascimento) {
                            return moment(nascimento).format('L');
                        }

            },
            {data: 'cnpj'},
            {data: 'ie'},
            {data: 'im'},
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/fornecedores/editar/' +
                            id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {data: 'id',
                render: function (id) {
                    return ''.concat('<a class="btn btn-success btn-sm btn-block"', ' ')
                            .concat('href="').concat('/enderecos/novo/').concat(id, '"', ' ')
                            .concat('role="button" title="ADD" data-toggle="tooltip" data-placement="right">', ' ')
                            .concat('<i class="fas fa-plus"></i></a>');
                },
                orderable: false
            },
            {data: 'id',
                render: function (id) {
                    return ''.concat('<a class="btn btn-success btn-sm btn-block"', ' ')
                            .concat('href="').concat('/telefones/novo/').concat(id, '"', ' ')
                            .concat('role="button" title="ADD" data-toggle="tooltip" data-placement="right">', ' ')
                            .concat('<i class="fas fa-plus"></i></a>');
                },
                orderable: false
            }
            ,
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/fornecedores/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
