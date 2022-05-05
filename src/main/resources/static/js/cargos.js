$(document).ready(function () {
	moment.locale('pt-BR');
    var table = $('#table-cargos').DataTable({
    	searching: true,
    	order: [[ 1, "asc" ]],
    	lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/cargos/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'idCargo'},
            {data: 'titulo'},
            {data: 'departamentos',
                render: function (departamentos) {
                    var aux = new Array();
                    $.each(departamentos, function (index, value) {
                        aux.push(value.depa);
                    });
                    return aux;
                },
                orderable: false
            },
            {orderable: false, 
             data: 'idCargo',
                "render": function(idCargo) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/cargos/editar/'+ 
                    	idCargo +'" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
             data: 'idCargo',
                "render": function(idCargo) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/cargos/excluir/'+ 
                    	idCargo +'" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }               
            }
        ]
    });
});    
