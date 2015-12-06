

$(document).ready(function(){
	
	/*Guardo los datos anteriores por si hay error*/
	
	var nombre = $("#edit-nombre").val();
	var apellido = $("#edit-empresa").val();
	var telefono = $("#edit-telefono").val();
	var email=$("#edit-email").val();
	var intereses=$("#edit-intereses").val();
	var descripcion=$("#edit-descripcion").val();
	
	
    /*Ocultar mensajes*/
    
	$("#mens1").hide();
    $("#mens2").hide();
    $("#mens3").hide();
    $("#mens4").hide();
    $("#mens5").hide();
    $("#mens6").hide();
    
    /* Al pulsar el boton anadir 	*/
    
    $("input[type=submit]").click(function(){
    	actualizarUsuario();
 	  });



});	


function actualizarUsuario(){
	
	var comprobarnombre = $("#edit-nombre").val();
	var comprobarapellido = $("#edit-empresa").val();
	var comprobartelefono = $("#edit-telefono").val();
	var comprobaremail=$("#edit-email").val();
	var comprobarimg=$("#edit-imagen").val();
	var comprobarintereses=$("#edit-intereses").val();
	var comprobardescripcion=$("#edit-descripcion").val();

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

if (comprobarnombre == "" || comprobarnombre == null){   /*Comprobar nombre*/
     $("#mens1").show();
     validacion=false;

 }else{
     $("#mens1").hide();      /* Si si lo introduce, esconde el mensaje */
     }
	
if (comprobarapellido == "" || comprobarapellido == null){   /*Comprobar apellido*/
     $("#mens4").show();
     validacion=false;

 }else{
     $("#mens4").hide();      /* Si si lo introduce, esconde el mensaje */
     }

if (comprobardescripcion == null){   /*Comprobar descipcion*/
	comprobardescripcion="";
}

if (comprobarintereses == null){   /*Comprobar intereses*/
	comprobarintereses="";
}

if (archivoimg && extension != extensiones_permitida){   /*Comprobar imagen*/
    $("#mens6").show();
    validacion=false;

}else{
    $("#mens6").hide();      /* Si si lo introduce, esconde el mensaje */
    }


if (comprobaremail == "" || comprobaremail == null || comprobaremail == ''){   /*Comprobar correo*/
    $("#mens4").show();
    validacion=false;

}else{
    $("#mens4").hide();      /* Si si lo introduce, esconde el mensaje */
    }

if ( !(/^\d{9}$/.test(comprobartelefono)) || comprobartelefono == "" || comprobartelefono == null || comprobartelefono == ''){   /*Comprobar telefono*/
    $("#mens5").show();
    validacion=false;

}else{
    $("#mens5").hide();      /* Si si lo introduce, esconde el mensaje */
    }

	return validacion;
}
