						

		

$(document).ready(function(){

//metodo para ajustar la longitud de los seguidores

	var largo = $("#mi-empresa").height() -1300;
	var seguidores = $("#seguidores > ul").height();
	if (seguidores>600){
		$("#seguidores > ul").height(largo);
		
	}


/*eliminar oferta 

	$( ".eliminar-icon" ).click(function() {
		if (confirm("¿Estas seguro que quieres borrar esta oferta de trabajo") == true) {
		   	$(this).parent().parent().remove();
			largo = $("#mi-empresa").height()-100;
			seguidores = $("#seguidores > ul").height(); 
			if (seguidores>600){
				$("#seguidores > ul").height(largo);

			}
			
			if (largo < 800) {
				$("#añadir-oferta2").remove();
			}
		    }

	});

*/
/*
	//modificar oferta
	var oferta = document.createElement("li");
	var titulo = document.createElement("p");
	var empresa = document.createElement("p");
	var lugar = document.createElement("p");
	var resumen = document.createElement("p");
	var contrato = document.createElement("p");
	var jornada = document.createElement("p");
	var salario = document.createElement("p");

	//modifica los valores de la oferta y cierra la ventana

	$("#actualizar").click(function()
	{
		$(titulo).text($("#edit-nombre").val());
		$(empresa).text($("#edit-empresa").val());
		$(lugar).text($("#edit-ciudad").val());
		$(resumen).text($("#edit-descripcion").val());
		$(contrato).text($("#edit-contrato").val());
		$(jornada).text($("#edit-jornada").val());
		$(salario).text($("#edit-salario").val());

		$( "#dialog" ).dialog( "close" );


	});

*/
	//abre el editor de ofertas y guarda los valores de esa oferta

	$(function() {
	    $( "#dialog" ).dialog({
				width: 500,
		      autoOpen: false,
		      show: {
		        effect: "blind",
		        duration: 1000
		      },
		      hide: {
		        effect: "clip",
		        duration: 1000,
		      }

		    });

		    $( ".edicion-icon" ).click(function() {
		      $( "#dialog" ).dialog( "open" );
				oferta = $(this).parent().parent();
				titulo = $(oferta).find(".ofertas-titulo");
				empresa = $(oferta).find(".ofertas-empresa");
				lugar = $(oferta).find(".ofertas-lugar");
				resumen = $(oferta).find(".ofertas-resumen");
				contrato = $(oferta).find(".ofertas-tipo-contrato");
				jornada = $(oferta).find(".ofertas-jornada");
				salario = $(oferta).find(".ofertas-salario");

				$("#edit-nombre").val(titulo.text());
				$("#edit-empresa").val(empresa.text());
				$("#edit-ciudad").val(lugar.text());
				$("#edit-descripcion").val(resumen.text());
				$("#edit-contrato").val(contrato.text());
				$("#edit-jornada").val(jornada.text());
				$("#edit-salario").val(salario.text());



		    });
	  });
	
	//Añadir una oferta nueva 
	var añadiroferta = true;
	$("#añadir").hide();
	$(".añadir-oferta").click(function(){
	   if (añadiroferta){
		$("#añadir").show();
		añadiroferta=false;
		$(".añadir-oferta").val("Ocultar añadir")
		window.scrollTo(0, 1500);
		
		
	}
		else{
			añadiroferta=true;
			$("#añadir").hide();
			$(".añadir-oferta").val("Añadir cupon")
			
		}
	  });
	
	
	$("#mens1").hide();
    $("#mens2").hide();
    $("#mens3").hide();
    $("#mens4").hide();
    $("#mens5").hide();
    $("#mens6").hide();
    $("#mens7").hide();

	/* Al pulsar el boton anadir */
  /*
  	$("input[type=submit]").click(function(){
	   comprobarTexto();
	  });
*/
	function comprobarTexto(nombre){
  		var comprobartit = $("#añadir-titulo").val();
 		var comprobaremp = $("#añadir-empresa").val();
		var comprobarlug = $("#añadir-lugar").val();
		var comprobardes = $("#añadir-descripcion").val();
		var comprobarcon = $("#añadir-contrato").val();
		var comprobarjor = $("#añadir-jornada").val();
		var comprobarsal = $("#añadir-salario").val();
		var validacion = true; /*variable para crear la oferta*/
	    
		
		if (comprobartit == "" || titulo == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens1").show();
	         validacion=false;

	     }else{
	         $("#mens1").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
			

		if (comprobaremp == "" || titulo == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens2").show();
	         validacion=false;

	     }else{
	         $("#mens2").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
		
		if (comprobarlug == "" || titulo == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens3").show();
	         validacion=false;

	     }else{
	         $("#mens3").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
			

		if (comprobardes == "" || titulo == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens4").show();
	         validacion=false;

	     }else{
	         $("#mens4").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
		
		if (comprobarcon == "" || titulo == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens5").show();
	         validacion=false;

	     }else{
	         $("#mens5").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
		
		if (comprobarjor == "" || titulo == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens6").show();
	         validacion=false;

	     }else{
	         $("#mens6").hide();      /* Si si lo introduce, esconde el mensaje */
	         }

		if (comprobarsal == "" || titulo == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens7").show();
	         validacion=false;

	     }else{
	         $("#mens7").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
	
		if(validacion==true){

			crearOferta(comprobartit, comprobaremp, comprobarlug, comprobardes, comprobarcon, comprobarjor, comprobarsal);

     	}


	}
	
	function crearOferta(tit, emp, lug, des, con, jor, sal){
    	var oferta = $("#oferta-ejemplo").clone();
		titulo = $(oferta).find(".ofertas-titulo");
		empresa = $(oferta).find(".ofertas-empresa");
		lugar = $(oferta).find(".ofertas-lugar");
		resumen = $(oferta).find(".ofertas-resumen");
		contrato = $(oferta).find(".ofertas-tipo-contrato");
		jornada = $(oferta).find(".ofertas-jornada");
		salario = $(oferta).find(".ofertas-salario");
		
		$(titulo).text(tit);
		$(empresa).text(emp);
		$(lugar).text(lug);
		$(resumen).text(des);
		$(contrato).text(con);
		$(jornada).text(jor);
		$(salario).text(sal);
		
		$("#ofertas ul").append(oferta);
		
		//vuelvo a repetir los metodos para que la nueva oferta pueda ejecutarlos

			$( ".eliminar-icon" ).click(function() {
				if (confirm("¿Estas seguro que quieres borrar esta oferta de trabajo") == true) {
				   	$(this).parent().parent().remove();
					largo = $("#mi-empresa").height() -100; 
					$("#seguidores > ul").height(largo);
				    }

			});
			
			$(function() {
			    $( "#dialog" ).dialog({
						width: 500,
				      autoOpen: false,
				      show: {
				        effect: "blind",
				        duration: 1000
				      },
				      hide: {
				        effect: "clip",
				        duration: 1000,
				      }

				    });

				    $( ".edicion-icon" ).click(function() {
				      $( "#dialog" ).dialog( "open" );
						oferta = $(this).parent().parent();
						titulo = $(oferta).find(".ofertas-titulo");
						empresa = $(oferta).find(".ofertas-empresa");
						lugar = $(oferta).find(".ofertas-lugar");
						resumen = $(oferta).find(".ofertas-resumen");
						contrato = $(oferta).find(".ofertas-tipo-contrato");
						jornada = $(oferta).find(".ofertas-jornada");
						salario = $(oferta).find(".ofertas-salario");

						$("#edit-nombre").val(titulo.text());
						$("#edit-empresa").val(empresa.text());
						$("#edit-ciudad").val(lugar.text());
						$("#edit-descripcion").val(resumen.text());
						$("#edit-contrato").val(contrato.text());
						$("#edit-jornada").val(jornada.text());
						$("#edit-salario").val(salario.text());



				    });
			  });
		
	}

});
			