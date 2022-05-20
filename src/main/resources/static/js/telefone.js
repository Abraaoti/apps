//datatables - lista de médicos
$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-telefone').DataTable({
        searching: true,
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/telefones/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'telefoneId'},
            {data: 'pessoaTelefone.nome'},
            {data: 'telefone'},            
            {data: 'telefoneId',
                render: function (telefoneId) {
                    return ''.concat('<a class="btn btn-success btn-sm btn-block"', ' ')
                            .concat('href="').concat('/telefones/editar/').concat(telefoneId, '"', ' ')
                            .concat('role="button" title="Editar" data-toggle="tooltip" data-placement="right">', ' ')
                            .concat('<i class="fas fa-edit"></i></a>');
                },
                orderable: false
            },
            {orderable: false,
                data: 'telefoneId',
                "render": function (telefoneId) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/telefones/excluir/' +
                            telefoneId + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
    

});
