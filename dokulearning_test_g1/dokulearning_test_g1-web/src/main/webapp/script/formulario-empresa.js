

$(document).ready(function(){
	//crear usuario
	var añadiroferta = true;
	var contraseñacheck = true;
	
	
	$(".mens").hide();
	

	function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "La longitud de " + n + " debe estar comprendida entre " +
          min + " y " + max + "." );
        return false;
      } else {
        return true;
      }
    }


	/* Al pulsar el boton anadir */
  
  	$("input[type=submit]").click(function(){
	   comprobarTexto();
	  });

	function comprobarTexto(){
  		var nombre = $("input[name=nombre]").val();
  		var apellidos = $("input[name=apellidos]").val();
		var fecha = $("input[name=fecha]").val();
		var correo = $("input[name=correo]").val();
		var telefono = $("input[name=telefono]").val();
		var poblacion = $("input[name=poblacion]").val();
		var provincia = $("input[name=provincia]").val();
		var contraseña1 = $("input[name=contraseña1]").val();
		var contraseña2 = $("input[name=contraseña2]").val();
		
		
		var validacion = true; /*variable para crear la oferta*/
		
		
	    
		if (nombre == "" ){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens1").show();
	         validacion=false;

	     }else{
	         $("#mens1").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
			

		if (apellidos == ""){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens2").show();
	         validacion=false;

	     }else{
	         $("#mens2").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
		
		if (fecha == ""){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens3").show();
	         validacion=false;

	     }else{
	         $("#mens3").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
			

		if (correo == "" ){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens4").show();
	         validacion=false;

	     }else{
	         $("#mens4").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
		
		if (telefono == "" ){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens5").show();
	         validacion=false;

	     }else{
	         $("#mens5").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
		
		if (poblacion == "" ){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens6").show();
	         validacion=false;

	     }else{
	         $("#mens6").hide();      /* Si si lo introduce, esconde el mensaje */
	         }

		if (provincia == "" ){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	         $("#mens7").show();
	         validacion=false;

	     }else{
	         $("#mens7").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
	
		if (contraseña1 == ""){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
	        $("#mens9").show();
	        validacion=false;
			contraseñacheck=false;

	     }
		else{
	         $("#mens9").hide();      /* Si si lo introduce, esconde el mensaje */
			contraseñacheck=true;
	     }
	
	
		if (contraseña2 == "" ){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
		  	$("#mens10").show();
			validacion=false;
			contraseñacheck=false;
		}
		else{
		    $("#mens10").hide();      /* Si si lo introduce, esconde el mensaje */
			contraseñacheck=true;
		}
			 
		if(document.getElementById("acepto").checked){}

		else{
				alert("Debe aceptar los términos y condiciones");
				validacion=false;
		}
		if(contraseñacheck)	 {
			if (contraseña1 == contraseña2){
				validacion==true;
			}
			else {
				alert ("Las contraseñas son distintas");
				validacion=false;
			}
		}

		if(validacion==true){
			window.location.href = "../src/index-empresa.html";

     	}


	}
	
	

});		
		
			
	
		
  
		
		
		
		
		
	
		
		
		
		