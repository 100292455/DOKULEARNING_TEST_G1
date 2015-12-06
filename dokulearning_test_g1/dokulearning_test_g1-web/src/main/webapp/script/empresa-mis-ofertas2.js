
$(document).ready(function(){
	var añadiroferta = true;
	var anadircupon = true;
	var anadirseccion = true;
	//Fecha fin de cupon
	
	  $(function() {
		    $( "#datepicker" ).datepicker();
		  });

  //Añadir una oferta nueva 
	$("#anadir-leccion").hide();
	$(".anadir-leccion").click(function(){
	   if (añadiroferta){
		$("#anadir-leccion").show();
		añadiroferta=false;
		$(".anadir-leccion").val("Ocultar añadir");
		window.scrollTo(0, 1500);
		anadirseccion=true;
		$("#anadir-seccion").hide();
		$("#boton-anadir-seccion").val("Anadir seccion");
		anadircupon=true;
		$("#anadir-cupon").hide();
		$("#boton-anadir-cupon").val("Anadir cupon");
		
	}
		else{
			añadiroferta=true;
			$("#anadir-leccion").hide();
			$(".anadir-leccion").val("Añadir leccion");
			
		}
	  });
	
	  //Añadir una cupon nueva 
	$("#anadir-cupon").hide();
	$("#boton-anadir-cupon").click(function(){
	   if (anadircupon){
		$("#anadir-cupon").show();
		anadircupon=false;
		$("#boton-anadir-cupon").val("Ocultar anadir")
		window.scrollTo(0, 1500);
		añadiroferta=true;
		$("#anadir-leccion").hide();
		$(".anadir-leccion").val("Añadir leccion");
		anadirseccion=true;
		$("#anadir-seccion").hide();
		$("#boton-anadir-seccion").val("Anadir seccion");
		
	}
		else{
			anadircupon=true;
			$("#anadir-cupon").hide();
			$("#boton-anadir-cupon").val("Anadir cupon");
			
		}
	  });
	
	  //Añadir una seccion nueva 
	$("#anadir-seccion").hide();
	$("#boton-anadir-seccion").click(function(){
	   if (anadirseccion){
		$("#anadir-seccion").show();
		anadirseccion=false;
		$("#boton-anadir-seccion").val("Ocultar anadir")
		window.scrollTo(0, 1500);
		añadiroferta=true;
		$("#anadir-leccion").hide();
		$(".anadir-leccion").val("Añadir leccion");
		anadircupon=true;
		$("#anadir-cupon").hide();
		$("#boton-anadir-cupon").val("Anadir cupon");
		
	}
		else{
			anadirseccion=true;
			$("#anadir-seccion").hide();
			$("#boton-anadir-seccion").val("Anadir seccion");
			
		}
	  });
    
    
    /*Ocultar mensajes*/
    
	$("#mens1").hide();
    $("#mens2").hide();
    $("#mens4").hide();
    
    
	$("#mens7").hide();
    $("#mens8").hide();
    $("#mens9").hide();    
 
    $("#mens10").hide();    
    /* Al pulsar el boton anadir leccion*/
    
    $("#anadir-leccion-button").click(function(){
    	validarcrearleccion();
 	  });
    /* Al pulsar el boton anadir cupon*/
 
    $("#crear-cupon-boton").click(function(){
    	validarcrearcupon();
 	  });
    
    /* Al pulsar el boton anadir seccion*/
    
    $("#crear-seccion-boton").click(function(){
    	validarcrearseccion();
 	  });

});	

function validarcrearleccion(){
	var comprobartit = $("#titulo-leccion").val();
	var comprobarform = $("#formato-leccion").val();
	var comprobardes = $("#añadir-descripcion").val();
	var validacion = true; /*variable para crear la oferta*/
	if (comprobartit == "" || comprobartit == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	     $("#mens1").show();
	     validacion=false;

	 }else{
	     $("#mens1").hide();      /* Si si lo introduce, esconde el mensaje */
	     }
	if (comprobarform=="otro"){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	     $("#mens2").show();
	     validacion=false;

	 }else{
	     $("#mens2").hide();      /* Si si lo introduce, esconde el mensaje */
	     }	
	if (comprobardes == "" || comprobardes == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	     $("#mens4").show();
	     validacion=false;

	 }else{
	     $("#mens4").hide();      /* Si si lo introduce, esconde el mensaje */
	     }
	return validacion;

}

function validarcrearcupon(){
	var comprobarpre = $("#precio").val();
	var comprobarform = $("#tipo_cupon").val();
	var comprobardat = $("#datepicker").val();
	var value = 0;
	var validacion = true; /*variable para crear la oferta*/
	if (isNaN(comprobarpre) || comprobarpre<1){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	     $("#mens7").show();
	     $("#precio").val(value);
	     validacion=false;

	 }else{
	     $("#mens7").hide();      /* Si si lo introduce, esconde el mensaje */
	     }
	if (comprobarform=="otro"){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	     $("#mens8").show();
	     validacion=false;

	 }else{
	     $("#mens8").hide();      /* Si si lo introduce, esconde el mensaje */
	     }	
	if (comprobardat == "" || comprobardat == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	     $("#mens9").show();
	     validacion=false;

	 }else{
	     $("#mens9").hide();      /* Si si lo introduce, esconde el mensaje */
	 }
	
	return validacion;

}

function validarcrearseccion(){
	var comprobartit = $("#nombreSeccion").val();
	if (comprobartit == "" || comprobartit == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	     $("#mens10").show();
	     validacion=false;

	 }else{
	     $("#mens10").hide();      /* Si si lo introduce, esconde el mensaje */
	     alert(comprobardat);
	 }
	
	return validacion;

}
