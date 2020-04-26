
//getters and setters


document.addEventListener("DOMContentLoaded", function(event)
{
	var formFull = false;
	document.getElementById("submitButton").addEventListener("click", saveActionClick);
});

document.addEventListener("DOMContentLoaded", () => {
	const productListElements = document.getElementById("productsListing").children;

	for (let i = 0; i < productListElements.length; i++) {
		productListElements[i].addEventListener("click", productClick);
	}
});

function saveActionClick(event)
{
	if(!checkForm())
	{
		return;
	}
	else
	{
		displayError("This is probably not gonna work");
	}
}

function checkForm()
{
	
	const lookupCode = getLookupCode();
	console.log(lookupCode);
	if(lookupCode == null || (lookupCode.trim() == ""))
	{
		displayError("please provide valid lookup code");
		return false
	}

	return true;
}

function getLookupCode()
{
	return getProductLookupCodeElement().value;
}

function getLookupCodeElement()
{
	return document.getElementById("lookupInput");
}

function setLookupCodeElement(lookupCode)
{
	getLookupCodeElement().value = lookupCode;
}


