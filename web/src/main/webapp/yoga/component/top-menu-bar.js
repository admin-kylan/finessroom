;!function($){
	Vue.component('top-menu-bar', {
		template: '<nav class="navbar navbar-default navbar-fixed-top">\
			<div class="brand">\
				<a href="gymSetting.html">瑜伽馆设置</a>\
			</div>\
			<div class="container-fluid">\
				<div class="navbar-btn">\
					<button type="button" class="btn-toggle-fullwidth"><i class="fa fa-arrow-circle-left"></i></button>\
				</div>\
			</div>\
		</nav>',
		data: function() {
			return {
				
			}
		},
	  	mounted: function() {
	  		this.bindEvent();
	  	},
	  	methods: {
	  		bindEvent:function(){
	  			$('.btn-toggle-fullwidth').on('click', function() {
					if(!$('body').hasClass('layout-fullwidth')) {
						$('body').addClass('layout-fullwidth');
			
					} else {
						$('body').removeClass('layout-fullwidth');
						$('body').removeClass('layout-default'); // also remove default behaviour if set
					}
					$(this).find('.fa').toggleClass('fa-arrow-circle-left fa-arrow-circle-right');
					if($(window).innerWidth() < 1025) {
						if(!$('body').hasClass('offcanvas-active')) {
							$('body').addClass('offcanvas-active');
						} else {
							$('body').removeClass('offcanvas-active');
						}
					}
				});
	  		}
	  	}
	})
}(jQuery);