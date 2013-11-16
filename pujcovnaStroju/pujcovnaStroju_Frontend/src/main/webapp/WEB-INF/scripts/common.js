function showPart(id) {
	document.getElementById(id).className = document.getElementById(id).className
			.replace(/\boffscreen\b/, '');
}

function hidePart(id) {
	var part = document.getElementById(id);
	if (part != null) {
		part.className = part.className + " offscreen";
	}
}

function validateAndSubmitMachineForm() {
	hidePart('machineSuccessWindow');
	hidePart('machineErrorWindow');
	var label = document.forms['addMachineForm'].label.value;
	if(label == "") {
		showPart('machineFormIDWarning');
	} else {
		document.getElementById('addMachineForm').submit();
	}
}

function validateAndSubmitUserForm() {
	hidePart('userSuccessWindow');
	hidePart('userErrorWindow');
	var firstName = document.forms['addUserForm'].firstName.value;
        var lastName = document.forms['addUserForm'].lastName.value;
        var type = document.forms['addUserForm'].type.value;
	if(firstName == "") {
            showPart('userFormFirstNameWarning');
	}
        if(lastName == "") {
            showPart('userFormLastNameWarning');
	} 
        if(type == "") {
            showPart('userFormTypeWarning');
        }
        if (firstName != "" && lastName != "" && type != "") {
            document.getElementById('addUserForm').submit();
	}
}