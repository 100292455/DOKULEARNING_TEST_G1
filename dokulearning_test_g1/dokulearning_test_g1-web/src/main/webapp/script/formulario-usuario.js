
$(document).ready(function(){
	//crear usuario
	var añadiroferta = true;
	
	$(':radio').mousedown(function(e){
		  var $self = $(this);
		  if( $self.is(':checked') ){
		    var uncheck = function(){
		      setTimeout(function(){$self.removeAttr('checked');},0);
		    };
		    var unbind = function(){
		      $self.unbind('mouseup',up);
		    };
		    var up = function(){
		      uncheck();
		      unbind();
		    };
		    $self.bind('mouseup',up);
		    $self.one('mouseout', unbind);
		  }
		});
	
	$(".mens").hide();
	
	



	/* Al pulsar el boton anadir 	*/
  
  	$("input[type=submit]").click(function(){
  			validar();
	  });

  

});		

	function validar(){
		
			var nombre = $("#nombre").val();
			var apellidos =  $("#apellidos").val();
			var correo = $("#correo").val();
			var telefono = $("#telefono").val();
			var password1 = $("#password1").val();
			var password2 = $("#password2").val();
			expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			
			var validacion = true; /*variable para crear la oferta*/
			
			if (nombre = "" || nombre == null || nombre == ''){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
				  $("#mens1").show();
		         validacion=false;
		     }else{
		         $("#mens1").hide();      /* Si si lo introduce, esconde el mensaje */
	         }
				
		
			if (apellidos == "" || apellidos == null || apellidos == ''){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
		         $("#mens2").show();
		         validacion=false;
		
		     }else{
		         $("#mens2").hide();      /* Si si lo introduce, esconde el mensaje */
		         }
				

			if (correo == "" || correo == null || correo == '' ||  !expr.test(correo)){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
		         $("#mens4").show();
		         validacion=false;
		
		     }else{
		         $("#mens4").hide();      /* Si si lo introduce, esconde el mensaje */
		         }
			
			if ( !(/^\d{9}$/.test(telefono)) || telefono == "" || telefono == null || telefono == ''){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
		         $("#mens5").show();
		         validacion=false;
		
		     }else{
		         $("#mens5").hide();      /* Si si lo introduce, esconde el mensaje */
		         }
			
			
		

			if (password2 == "" || password2 == null || password2 == ''){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
		         $("#mens10").show();
		         validacion=false;
		
		     }else{
		         $("#mens10").hide();      /* Si si lo introduce, esconde el mensaje */
		         }
		
			if (password1 == "" || password1 == null || password1 == ''){   /* Si no introduce el titulo enseña el mensaje de error y pone la variable validacion a false*/
		         $("#mens9").show();
		         validacion=false;
		
		     }else{
		         $("#mens9").hide();      /* Si si lo introduce, esconde el mensaje */
		         }
		
			if($("#acepto").is(':checked')) {  
		         $("#mens3").hide();      /* Si si lo introduce, esconde el mensaje */
	        } else {  
		         $("#mens3").show();
		         validacion = false;
	        } 
		if(password2 != password1){
			
			validacion=false;
		}
		$('body, html').animate({
			scrollTop: '200px'
		}, 300);
		
			return validacion;
	}
