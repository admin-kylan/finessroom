;!function($){
	Vue.component('sidebar-nav', {
		template: '<div id="sidebar-nav" class="sidebar">\
			<div class="sidebar-scroll">\
				<nav>\
					<ul class="nav">\
						<li v-for="item in sidebarData" :class="activeIndex==item.value?\'active\':\'\'">\
							<a v-if="!!!(item.children && item.children.length)" :href="item.href"><i class="fa" v-if="item.icon" :class="item.icon"></i> <span>{{item.text}}</span></a>\
							<a v-if="!!(item.children && item.children.length)" :href="\'#subPages\'+item.value" data-toggle="collapse" class="collapsed"><i class="fa" :class="item.icon"></i> <span>{{item.text}}</span> <i class="icon-submenu fa fa-angle-right"></i></a>\
							<div :id="\'subPages\'+item.value" class="collapse" v-if="!!(item.children && item.children.length)">\
								<ul class="nav">\
									<li v-for="it in item.children"><a :href="it.href" class=""><i class="fa" :class="item.icon"></i> <span>{{it.text}}</span></a></li>\
								</ul>\
							</div>\
						</li>\
					</ul>\
				</nav>\
			</div>\
		</div>',
		data: function() {
			return {
				sidebarData:[
					{
						value:0,//动态创建id用的，只有在有子元素时才用的到
						text:'健身馆设置',//导航名
						icon:'',//导航图标
						href:'gymSetting.html',//导航地址（无责为空即可）//子菜单无则省去
					},
					{
						value:1,
						text:'健身团教设置',
						icon:'',
						href:'groupTeaching.html',
					},
					{
						value:2,
						text:'健身私教设置',
						icon:'',
						href:'personalEducationSetting.html',
					}
				],
				activeIndex:0
			}
		},
	  	mounted: function() {
	  		this.bindEvent();
	  		var _pathname = decodeURIComponent(location.pathname);
	  		for(var i in this.sidebarData){
	  			if(_pathname.indexOf(this.sidebarData[i].href)!==-1){
	  				this.activeIndex=i;
	  				break;
	  			}
	  		}
	  	},
	  	methods: {
	  		bindEvent:function(){
				$(window).on('load', function() {
					// 调整右边栏顶部位置
					$('.right-sidebar').css('top', $('.navbar').innerHeight());
					// 如果页面有内容菜单，则设置主内容的顶部填充
					if($('.has-content-menu').length > 0) {
						$('.navbar + .main-content').css('padding-top', $('.navbar').innerHeight());
					}
					// 主要内容较少时,设置最小高度
					if($('.main').height() < $('#sidebar-nav').height()) {
						$('.main').css('min-height', $('#sidebar-nav').height()-$('.navbar-fixed-top').height());
					}
				});
				$('.sidebar a[data-toggle="collapse"]').on('click', function() {
					if($(this).hasClass('collapsed')) {
						$(this).addClass('active');
					} else {
						$(this).removeClass('active');
					}
				});
				if( $('.sidebar-scroll').length > 0 ) {
					$('.sidebar-scroll').slimScroll({
						height: '100%',
						wheelStep: 2,
					});
				}
	  		}
	  	}
	})
  	$.fn.clickToggle = function( f1, f2 ) {
		return this.each( function() {
			var clicked = false;
			$(this).bind('click', function() {
				if(clicked) {
					clicked = false;
					return f2.apply(this, arguments);
				}
	
				clicked = true;
				return f1.apply(this, arguments);
			});
		});
	
	}
}(jQuery);