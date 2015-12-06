
$(document).ready(function(){
	var añadiroferta = true;
	var value = 0;
	var ventana = document.getElementById("selectedTabInput").value;
	if (ventana != 0) {
        $('#tabs').tabs({
            active : ventana
        });
    } else {
        $('#tabs').tabs();
    }
	      
    $("#spinner").val(value);
    $("#añadir-precio").val(value);
    

  //Añadir una oferta nueva 
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
			$(".añadir-oferta").val("Añadir curso")
			
		}
	  });
    
    
    /*Ocultar mensajes*/
    
	$("#mens1").hide();
    $("#mens2").hide();
    $("#mens3").hide();
    $("#mens4").hide();
    $("#mens5").hide();
    $("#mens6").hide();
    $("#mens7").hide();
    
    /* Al pulsar el boton anadir 	*/
    
    $("input[type=submit]").click(function(){
 	   validarcrearcurso();
 	  });



});	


function validarcrearcurso(){
	
	var comprobartit = $("#añadir-titulo").val();
	var comprobaremp = $("#dificultad-curso").val();
	var comprobartem = $("#tematica-curso").val();
	var comprobardes = $("#añadir-descripcion").val();
	var comprobarpre=$("#añadir-precio").val();
	var comprobarhor=$("#spinner").val();
	var comprobarimg=$("#imagen-curso").val();

	var value = 0;
	var validacion = true; /*variable para crear la oferta*/
	var archivoimg =false;
	var extensiones_permitida = ".jpg";
	var  extension;
	
	
	//metodo para especificar las horas de un curso

//compruebo si hay una imagen
	if(comprobarimg!="" && comprobarimg != null){
		archivoimg=true;
		extension = (comprobarimg.substring(comprobarimg.lastIndexOf("."))).toLowerCase(); 
	}

if (comprobartit == "" || comprobartit == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
     $("#mens1").show();
     validacion=false;

 }else{
     $("#mens1").hide();      /* Si si lo introduce, esconde el mensaje */
     }
	

if (comprobaremp == "-1"){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
     $("#mens2").show();
     validacion=false;

 }else{
     $("#mens2").hide();      /* Si si lo introduce, esconde el mensaje */
     }

if (comprobartem == "-1"){   /* Si no introduce el tema enseña el mensaje de error y pone la variable validacion a false*/
    $("#mens7").show();
    validacion=false;

}else{
    $("#mens7").hide();      /* Si si lo introduce, esconde el mensaje */
    }

if (isNaN(comprobarhor) || comprobarhor<1){   /* Si no introduce las horas como numero mayor que 0 enseña el mensaje de error y pone la variable validacion a false*/
    $("#mens3").show();
    $("#spinner").val(value);
    validacion=false;

}else{
    $("#mens3").hide();      /* Si si lo introduce, esconde el mensaje */
    }


if (comprobardes == "" || comprobardes == null){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
     $("#mens4").show();
     validacion=false;

 }else{
     $("#mens4").hide();      /* Si si lo introduce, esconde el mensaje */
     }

if (archivoimg && extension != extensiones_permitida){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
    $("#mens6").show();
    validacion=false;

}else{
    $("#mens6").hide();      /* Si si lo introduce, esconde el mensaje */
    }


if (isNaN(comprobarpre) || comprobarpre<1){   /* Si no introduce las horas como numero mayor que 0 enseña el mensaje de error y pone la variable validacion a false*/
    $("#mens5").show();
    $("#añadir-precio").val(value);
    validacion=false;

}else{
    $("#mens5").hide();      /* Si si lo introduce, esconde el mensaje */
    }


	$( "#tabs" ).tabs({ active: 1 });
	return validacion;
}

