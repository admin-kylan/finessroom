;!function() {
	var version = "1.0";//版本控制
	postjs([
		"js/vue.js",
		"js/jquery/jquery.min.js",
		"js/jquery-slimscroll/jquery.slimscroll.min.js",
		"js/bootstrap/bootstrap.min.js",
		"component/top-menu-bar.js",
		"component/sidebar-nav.js",
	])
	function postjs(srcipts) {
		var arr = [];
		for(var i=0,len=srcipts.length; i<len; i++) {
			arr.push('<script src="'+srcipts[i]+'?v='+version+'"></script>');
		}
		document.write(arr.join(''))
	}
}();
