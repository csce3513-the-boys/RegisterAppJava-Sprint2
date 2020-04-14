document.addEventListener("DOMContentLoaded", () => {
	const productListElements = document.getElementById("productsListing").children;

	for (let i = 0; i < productListElements.length; i++) {
		productListElements[i].addEventListener("click", productClick);
	}
});

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
