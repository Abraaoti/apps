$(document).ready(function () {
    moment.locale('pt-BR');
    var table = $('#table-funcionario').DataTable({
        searching: true,
        order: [[1, "asc"]],
        lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/funcionarios/datatables/server',
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
          //  {data: 'matricula'},
            {data: 'cpf'},
            {data: 'rg'},
            {data: 'passaporte'},
            {data: 'mae'},
            {data: 'pai'},
            {data: 'ec'},
            {data: 'genero'},
            {data: 'cargo.titulo'},
            {data: 'admissao', render:
                        function (admissao) {
                            return moment(admissao).format('L');
                        }
            },
            {data: 'salario', render: $.fn.dataTable.render.number('.', ',', 2, 'R$ ')},
            {data: 'naturalidade'},
            {data: 'empresa.nome'},
            
           
           
            {orderable: false,
                data: 'id',
                "render": function (id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/funcionarios/editar/' +
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
                    return '<a class="btn btn-danger btn-sm btn-block" href="/funcionarios/excluir/' +
                            id + '" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });    
    
    
});
