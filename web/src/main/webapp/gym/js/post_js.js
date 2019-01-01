;!function() {
	var version = "1.0";//版本控制
	postjs([
		"js/jquery-slimscroll/jquery.slimscroll.min.js",
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
