//getters and setters

document.addEventListener("DOMContentLoaded", function(event)
{
	var formFull = true;
	document.getElementById("submitButton").addEventListener("click", checkForm);
});

function checkForm()
{
	var lookupCode = document.forms["productLookupForm"]["lookupInput"].value;
	if(lookupCode == "")
	{
		document.getElementById("formError").innerHTML = "Lookup code field is empty";
		formFull = false;
		return false
	}

	document.getElementById("formError").innerHTML = "";
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
