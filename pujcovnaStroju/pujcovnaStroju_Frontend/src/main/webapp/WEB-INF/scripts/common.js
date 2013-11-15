function showPart(id) {
	document.getElementById(id).className = document.getElementById(id).className
			.replace(/\boffscreen\b/, '');
}

function hidePart(id) {
	var d = document.getElementById(id);
	d.className = d.className + " .offscreen";
}

