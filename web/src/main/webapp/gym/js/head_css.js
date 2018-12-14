;!function() {
	var version = "1.0";//版本控制
	postjs([
		"css/bootstrap/bootstrap.min.css",
		"css/font-awesome/css/font-awesome.min.css",
		"css/base.css",
		"css/common.css",
	])
	function postjs(srcipts) {
		var arr = [];
		for(var i=0,len=srcipts.length; i<len; i++) {
			arr.push('<link rel="stylesheet" href="'+srcipts[i]+'?v='+version+'" />');
		}
		document.write(arr.join(''))
	}
}();
