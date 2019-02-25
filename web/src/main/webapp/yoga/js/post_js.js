;!function() {
	var version = "1.0";//版本控制
	postjs([
		"../js/jquery-3.3.1.min.js",
		"../js/vue.js",
		"js/jquery-slimscroll/jquery.slimscroll.min.js",
		"component/top-menu-bar.js",
		"component/sidebar-nav.js",
		"../js/bootstrap.min.js",
		"../js/axios.min.js"
	])
	function postjs(srcipts) {
		var arr = [];
		for(var i=0,len=srcipts.length; i<len; i++) {
			arr.push('<script src="'+srcipts[i]+'?v='+version+'"></script>');
		}
		document.write(arr.join(''))
	}
}();
