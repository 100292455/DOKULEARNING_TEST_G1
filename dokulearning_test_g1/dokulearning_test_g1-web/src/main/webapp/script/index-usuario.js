

		$( "#provincia" ).selectmenu();
		$( "#top-visited-select" ).selectmenu();
		
		$(document).ready(function(){
			$('.your-class').slick({
				dots: true,
			  	slidesToShow: 5,
			 	slidesToScroll: 5
			});
			
				$("input[name=buscar]").click(function() {
					window.location.href = "../src/busqueda-usuario.html";
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
		
  
		
		
		
		
		
	
		
		
		
		