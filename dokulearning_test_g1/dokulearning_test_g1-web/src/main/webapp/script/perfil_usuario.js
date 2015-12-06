						
//metodo para ajustar la longitud de los seguidores
				
	var largo = $("#mi-empresa").height();
	largo=largo-200;
	
	$("#seguidores > ul").height(largo);
	
	
	
	
//DATE PICKER

                          $('#edit-empresa').datepicker({
                              dateFormat: "dd-mm-yy",
                              changeMonth: true,
				 changeYear: true,
                                 yearRange: '1920:2014',
                              inline: true,
                              onSelect: function(dateText, inst) {
                                 date = $(this).datepicker('getDate'),
                                 day  = date.getDate(),
                                 month = date.getMonth() + 1,
                                 year =  date.getFullYear();
                                 }
                                 });
			
//metodo para ejecutar el editor de texto para la descripcion de empresa
			

	$('#informacion-empresa-descripcion').jqte();

	// settings of status
	var jqteStatus = true;
	$(".status").click(function()
	{
		jqteStatus = jqteStatus ? false : true;
		$('.jqte-test').jqte({"status" : jqteStatus})
	});
				
				
//metodo para cambiar la imagen

	document.getElementById('picField').onchange = function (evt) {
	    var tgt = evt.target || window.event.srcElement,
	        files = tgt.files;

	    // FileReader support
	    if (FileReader && files && files.length) {
	        var fr = new FileReader();
	        fr.onload = function () {
	            document.getElementById("imagen-empresa").src = fr.result;
	        }
	        fr.readAsDataURL(files[0]);
	    }

	    // Not supported
	    else {
	        // fallback -- perhaps submit the input to an iframe and temporarily store
	        // them on the server until the user's session ends.
	    }
	}
	
//vista previa

	$(function() {
	    $( "#dialog" ).dialog({
				width: 1000,
				height: 500,
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

		    $( "#opener" ).click(function() {
				$(".eleminar-vista-previa").remove();
				
				/*CORREGIMOS*/
			      var x = document.forms["personal"]["nombre"].value;
                              var y = document.forms["personal"]["empresa"].value;
                              var z = document.forms["personal"]["direccion"].value;
                              var t = document.forms["personal"]["ciudad"].value;
                              var u = document.forms["personal"]["representante"].value;
                              var v = document.forms["personal"]["telefono"].value;
			      var w = document.forms["personal"]["correo"].value;
			       /*si alguno falla no hay vista previa return false*/
			       if (x==null || x=="" || y==null || y=="" || z==null || z=="" || t==null || t==""|| u==null ||
                                  u==""|| v==null || v==""|| w==null || w=="") {
				       if (x==null || x==""){
				       $('#span1').text("*");
				       alert("Rellene el campo de nombre");
				       }
				       if (y==null || y=="") {
                                       $('#span2').text("*");
				         alert("Rellene la fecha de nacimiento");
                                       }
                                       if (z==null || z=="") {
                                       	$('#span3').text("*");
					 alert("Rellene el c—digo postal");
                                       }if (t==null || t=="") {
                                       	$('#span4').text("*");
					 alert("Rellene el campo de poblaci—n");
                                       }if (u==null || u=="") {
                                      	$('#span5').text("*");
					 alert("Rellene el campo de provincia");
                                       }if (v==null || v=="") {
                                       	$('#span6').text("*");
					alert("Rellene el campo de telefono");

                                       }if (w==null || w=="") {
                                        $('#span7').text("*");
					alert("Rellene el campo de email");

                                       }
				       return false;
				 }
				 $('span').text('');

			      
			      
			      
			      
			      
			      /*VISTA PREVIA*/

		      $( "#dialog" ).dialog( "open" );
			$(function() {
				var nombre = $("#edit-nombre").val();
				var empresa= $("#edit-empresa").val();
				var direccion = $("#edit-direccion").val();
				var ciudad = $("#edit-ciudad").val();
				var telefono = $("#edit-telefono").val();
				var email = $("#edit-email").val();
				var representante = $("#edit-representante").val();
				var descripcion = $(".jqte_editor").clone().addClass("eleminar-vista-previa");
				var img = $("#imagen-empresa").attr("src");

				$("#vista-previa-nombre").text(nombre);
				$("#vista-previa-empresa").text(empresa);
				$("#vista-previa-direccion").text(direccion);
				$("#vista-previa-ciudad").text(ciudad);
				$("#vista-previa-telefono").text(telefono);
				$("#vista-previa-email").text(email);
				$("#vista-previa-representante").text(representante);
				$("#dialog").append(descripcion);
				$("#imagen-vista-previa").attr("src", img);

			});
		    });
	  });
	

	

			