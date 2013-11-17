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

function validateAndSubmitAddUserForm() {
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

function validateAndSubmitUpdateUserForm() {
    hidePart('userSuccessWindow');
    hidePart('userErrorWindow');
    var firstName = document.forms['updateUserForm'].firstName.value;
    var lastName = document.forms['updateUserForm'].lastName.value;
    var type = document.forms['updateUserForm'].type.value;
    if(firstName == "") {
        showPart('userFormFirstNameWarning');
    }
    if(lastName == "") {
        showPart('userFormLastNameWarning');
    } 
    if(type == "") {
        showPart('userFormTypeWarning');
    }
    if(id == "") {
        showPart('userFormTypeWarning');
    }
    if (firstName != "" && lastName != "" && type != "" && id != "") {
        document.getElementById('updateUserForm').submit();
    }
    
    function validateAndSubmitAddRevisionForm() {
    hidePart('revisionSuccessWindow');
    hidePart('revisionErrorWindow');
    var revDate = document.forms['addRevisionForm'].revDate.value;
    var comment = document.forms['addRevisionForm'].comment.value;
    
    if (revDate == "") {
        showPart('revisionFormRevDateWarning');
    }
    if(comment == "") {
        showPart('revisionFormCommentWarning');
    } 
    
    if (revDate != "" && comment != "") {
        document.getElementById('addRevisionForm').submit();
    }
}
function validateAndSubmitUpdateRevisionForm() {
    hidePart('revisionSuccessWindow');
    hidePart('revisionErrorWindow');
    var revDate = document.forms['updateRevisionForm'].revDate.value;
    var comment = document.forms['updateRevisionForm'].comment.value;
   
    if(revDate == "") {
        showPart('revisionFormRevDateWarning');
    }
    if(lastName == "") {
        showPart('revisionFormCommentWarning');
    } 
    
    if(id == "") {
        showPart('revisionFormTypeWarning');
    }
    if (revDate != "" && comment!= "" && id != "") {
        document.getElementById('updateRevisionForm').submit();
    } 
}
}