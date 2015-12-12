$(document).ready(function(){
	//crear usuario
	
	
	$(".mens").hide();

	/* Al pulsar el boton anadir 	*/
  
  	$("input[type=submit]").click(function(){
  		comprobarTarjeta();
	  });

  

});		

	function comprobarTarjeta(){
		
			var nombre = $("#nombre").val();
			var tajeta =  $("#numero").val();
			
			var validacion = true; /*variable para crear la oferta*/
			
			if (nombre = "" || nombre == null || nombre == ''){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
				  $("#mens1").show();
		         validacion=false;
		     }else{
		         $("#mens1").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
				
		
			if (tarjeta == "" || tarjeta == null || tarjeta == ''){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
		         $("#mens2").show();
		         validacion=false;
		
		     }else{
		         $("#mens2").hide();      /* Si si lo introduce, esconde el mensaje */
		         }
		
			return validacion;
	}