/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
	
	$(".logo-home").hide();
	$(".logo-home").fadeTo(3000, 0.90);

	// Accueil hashtag
	$('a[href^="#"]').on('click', function(event) {
		var target = $(this.getAttribute('href'));
		if (target.length) {
			event.preventDefault();
			$('html, body').stop().animate({
				scrollTop : target.offset().top
			}, 1500);
		}
	});
	
	$('#form_utilisateur .form_btn_editer').on('click',function(event) {
		$(this).hide();
		$('#form_utilisateur .form_btn_sauver').show();
		$('#form_utilisateur .form_btn_annuler').show();
		$('#form_utilisateur input').slideToggle();	
	});
	
	$('#form_utilisateur .form_btn_annuler').on('click',function(event) {
		$('#form_utilisateur .form_btn_editer').show();
		$('#form_utilisateur .form_btn_sauver').hide();
		$(this).hide();
		$('#form_utilisateur input').slideToggle();	
	});
	
	$("#article-selection-produit-btn").on('click',function(event){
		$("#table-article-produits-1").show();
		$("#table-article-produits-1 table").show();
		
	});
	
	$("#table-article-produits-1 .ligne .id").on('click',function(event){
		var ligne = $(this).text();
		$("#table-article-produits-1 table").hide();
		$("#table-article-produits-1").append('<input type="text" name="produit.id" readonly="readonly" class="form-control" value="'+ ligne+'"/>');
	});
	
	  $('.datetimepicker').datetimepicker({
    	  daysOfWeekDisabled: [0,1],
    	  stepping: 30,
    	 icons: {
             time: "fa fa-clock-o",
             date: "fa fa-calendar",
             up: "fa fa-arrow-up",
             down: "fa fa-arrow-down"
         },
      
         disabledDates: [
             new Date()
         ]
     });
		
	
});