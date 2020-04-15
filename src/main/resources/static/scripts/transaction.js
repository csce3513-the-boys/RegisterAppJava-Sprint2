
//getters and setters

<<<<<<< HEAD
document.addEventListener("DOMContentLoaded", function(event)
{
	var formFull = true;
	document.getElementById("submitButton").addEventListener("click", checkForm());
});

function checkForm()
{
	
	var lookupCode = document.getElementById("lookupInput");
	console.log(lookupCode);
	if(lookupCode == "")
	{
		document.getElementById("formError").innerHTML = "Lookup code field is empty";
		formFull = false;
		return false
	}

	document.getElementById("formError").innerHTML = "hey it kinda works";
	formFull = true;
	return true;
}

function getLookupCode()
{
	return getProductLookupCodeElement().nodeValue;
}

function getLookupCodeElement()
{
	return document.getElementById("lookupInput");
}

function setLookupCodeElement(lookupCode)
{
	getLookupCodeElement().value = lookupCode;
}
=======
// Getters and setters
function getSubmitActionElement() {
	return document.getElementById("productSearch");
}

function getAddActionElement() {
	return document.getElementById("addButton");
}

function getSearchElement() {
	return document.getElementById("search");
}

//ADD price getters
// End getters and setters
>>>>>>> 52ade7f59b5ecad2fc0a6262ae3390525fc91e96
