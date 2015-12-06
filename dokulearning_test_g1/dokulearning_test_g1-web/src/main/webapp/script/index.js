

		$( "#provincia" ).selectmenu();
		$( "#top-visited-select" ).selectmenu();
		
		$(document).ready(function(){
			
			$(function() {
			    $( "#dialog-confirm" ).dialog({
			      resizable: false,
			      height:140,
			      modal: true,
			      buttons: {
			        "Crear particular": function() {
			          $( this ).dialog( "close" );
			        },
			
					"Crear empresa": function() {
			          $( this ).dialog( "close" );
			        },
			        Cancel: function() {
			          $( this ).dialog( "close" );
			        }
			      }
			    });
			});
		  		$("input[name=buscar]").click(function() {
					window.location.href = "../src/busqueda.html";
				});
			
			
			$('.your-class').slick({
				dots: true,
			  	slidesToShow: 5,
			 	slidesToScroll: 5
			});
						
			 $(".slick-next").click(function() {
				var count = $(".slick-active").index();
				if (count ==5) {
					$("#tit-slick").text("General")
				}
				if (count ==10) {
					$("#tit-slick").text("Banca")
				}
				if (count ==15) {
					$("#tit-slick").text("Informática")
				}
				
			});
			
			 $(".slick-prev").click(function() {
				var count = $(".slick-active").index();
				if (count ==5) {
					$("#tit-slick").text("General")
				}
				if (count ==10) {
					$("#tit-slick").text("Banca")
				}
				if (count ==15) {
					$("#tit-slick").text("Informática")
				}
				
			});
			
			$(".slick-dots li").click(function() {
				var count = $(".slick-active").index();
				if (count ==5) {
					$("#tit-slick").text("General")
				}
				if (count ==10) {
					$("#tit-slick").text("Banca")
				}
				if (count ==15) {
					$("#tit-slick").text("Informática")
				}
				
			});
			
			
		
			
			
		
		});
		
		var vid = document.getElementById("descripcion-video");
		vid.muted = true;
		var play = false;

		$( vid ).click(function() {
			
			if (!play){
				vid.play();
			  	play=true;
			}
			else {
				vid.pause();
				play=false;
			}
			
		});
		
  
		
		
		
		
		
	
		
		
		
		