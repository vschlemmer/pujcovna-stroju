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
	var description = document.forms['addMachineForm'].description.value;
	if(label == "") {
		showPart('machineFormLabelWarning');
	} 
	if(description == "") {
		showPart('machineFormDescriptionWarning');
	} 
	if (label != "" && description != "") {
		document.getElementById('addMachineForm').submit();
	}
}
function validateAndSubmitUpdateMachineForm() {
    hidePart('machineSuccessWindow');
    hidePart('machineErrorWindow');
    var label = document.forms['updateMachineForm'].label.value;
    var description = document.forms['updateMachineForm'].description.value;
    if(label == "") {
        showPart('machineFormLabelWarning');
    }
    if(description == "") {
        showPart('machineFormDescriptionWarning');
    } 
    if (label != "" && description != "") {
        document.getElementById('updateMachineForm').submit();
    }
}
    
    
function validateAndSubmitRegistrationForm() {
    hidePart('userSuccessWindow');
    hidePart('userErrorWindow');
    var firstName = document.forms['addUserForm'].firstName.value;
    var lastName = document.forms['addUserForm'].lastName.value;
    var type = document.forms['addUserForm'].type.value;
    var username = document.forms['addUserForm'].username.value;
    var password = document.forms['addUserForm'].password.value;
    var confPassword = document.forms['addUserForm'].confPassword.value;
    var proceed = true;
    if(firstName == "") {
        showPart('userFormFirstNameWarning');
        proceed = false;
    }
    if(lastName == "") {
        showPart('userFormLastNameWarning');
        proceed = false;
    } 
    if(type == "") {
        showPart('userFormTypeWarning');
        proceed = false;
    }
    if(username == "") {
        showPart('userFormUsernameWarning');
        proceed = false;
    }
    if (password != confPassword){
        showPart('userFormConfPasswordWarning');
        proceed = false;
    }
    if (proceed) {
        document.getElementById('addUserForm').submit();
    }
}

function validateAndSubmitAddUserForm() {
    hidePart('userSuccessWindow');
    hidePart('userErrorWindow');
    var firstName = document.forms['addUserForm'].firstName.value;
    var lastName = document.forms['addUserForm'].lastName.value;
    var type = document.forms['addUserForm'].type.value;
    var username = document.forms['addUserForm'].username.value;
    var password = document.forms['addUserForm'].password.value;
    var confPassword = document.forms['addUserForm'].confPassword.value;
    var proceed = true;
    if(firstName == "") {
        showPart('userFormFirstNameWarning');
        proceed = false;
    }
    if(lastName == "") {
        showPart('userFormLastNameWarning');
        proceed = false;
    } 
    if(type == "") {
        showPart('userFormTypeWarning');
        proceed = false;
    }
    if(username == "") {
        showPart('userFormUsernameWarning');
        proceed = false;
    }
    if(password == "") {
        showPart('userFormPasswordWarning');
        proceed = false;
    }
    if(confPassword == "") {
        showPart('userFormConfPasswordWarning');
        proceed = false;
    }
    if (password != confPassword){
        showPart('userFormConfPasswordWarning');
        proceed = false;
    }
    if (proceed) {
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
    if(comment == "") {
        showPart('revisionFormCommentWarning');
    } 
    if(revID == "") {
        showPart('revisionFormRevIDWarning');
    }
    if (revDate != "" && comment != "" && revID != "") {
        document.getElementById('updateRevisionForm').submit();
    } 

}

function validateAndSubmitAddLoanForm() {
    hidePart('loanSuccessWindow');
    hidePart('loanErrorWindow');
    var loanTime = document.forms['updateLoanForm'].loanTime.value;
    var returnTime = document.forms['updateLoanForm'].returnTime.value;
    var loanState = document.forms['updateLoanForm'].loanState.value;
    if(loanTime == "") {
        showPart('loanFormLoanTimeWarning');
    }
    if(returnTime == "") {
        showPart('loanFormReturnTimeWarning');
    }
    if(loanState == "") {
        showPart('loanFormLoanStateWarning');
    }
    if (loanTime != "" && returnTime != "" && loanState != "") {
        document.getElementById('updateLoanForm').submit();
    }
}

function validateAndSubmitUpdateLoanForm() {
    hidePart('loanSuccessWindow');
    hidePart('loanErrorWindow');
    var loanTime = document.forms['updateLoanForm'].loanTime.value;
    var returnTime = document.forms['updateLoanForm'].returnTime.value;
    var loanState = document.forms['updateLoanForm'].loanState.value;
    if(loanTime == "") {
        showPart('loanFormLoanTimeWarning');
    }
    if(returnTime == "") {
        showPart('loanFormReturnTimeWarning');
    }
    if(loanState == "") {
        showPart('loanFormLoanStateWarning');
    }
    if (loanTime != "" && returnTime != "" && loanState != "" && id != "") {
        document.getElementById('updateLoanForm').submit();
    }
   }