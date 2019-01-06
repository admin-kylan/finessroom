;!function ($) {
    Vue.component('sidebar-nav', {
        template: '<div id="sidebar-nav" class="sidebar">\
			<div class="sidebar-scroll">\
				<nav>\
					<ul class="nav">\
						<li v-for="(item,index) in sidebarData" :class="activeIndex==index?\'active\':\'\'">\
							<a @click="changeIndex(index,item.frSetGyms)"><span>{{item.shopName}}</span></a>\
						</li>\
					</ul>\
				</nav>\
			</div>\
		</div>',
        props: {
//	    	选中第几个
            activeIndex: {
                type: Number, default: 0
            },
            type:'',
        },
        data: function () {
            return {
                sidebarData: []
            }
        },
        created: function () {
            var that = this;
            console.log(that.type)
            $.get("/frSetGym/getShop",
                {
                    type: that.type,
                },
                function (res) {
                    if (res.code == '200') {
                        that.sidebarData = res.data;
                        var temp={
                            shopName:'连锁店通用'
                        }
                        that.sidebarData.unshift(temp)
                    }
                })
        },
        mounted: function () {
            this.bindEvent();
        },
        methods: {
            changeIndex: function (val,type) {
                this.activeIndex = val ;
                this.$emit('update', val,type);
            },
            bindEvent: function () {
                $(window).on('load', function () {
                    // 调整右边栏顶部位置
                    $('.right-sidebar').css('top', $('.navbar').innerHeight());
                    // 如果页面有内容菜单，则设置主内容的顶部填充
                    if ($('.has-content-menu').length > 0) {
                        $('.navbar + .main-content').css('padding-top', $('.navbar').innerHeight());
                    }
                    // 主要内容较少时,设置最小高度
                    if ($('.main').height() < $('#sidebar-nav').height()) {
                        $('.main').css('min-height', $('#sidebar-nav').height() - $('.navbar-fixed-top').height());
                    }
                });
                $('.sidebar a[data-toggle="collapse"]').on('click', function () {
                    if ($(this).hasClass('collapsed')) {
                        $(this).addClass('active');
                    } else {
                        $(this).removeClass('active');
                    }
                });
                if ($('.sidebar-scroll').length > 0) {
                    $('.sidebar-scroll').slimScroll({
                        height: '100%',
                        wheelStep: 2,
                    });
                }
            }
        }
    })
    $.fn.clickToggle = function (f1, f2) {
        return this.each(function () {
            var clicked = false;
            $(this).bind('click', function () {
                if (clicked) {
                    clicked = false;
                    return f2.apply(this, arguments);
                }

                clicked = true;
                return f1.apply(this, arguments);
            });
        });

    }
}(jQuery);