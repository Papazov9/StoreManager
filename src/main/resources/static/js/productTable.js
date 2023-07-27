let allData = undefined;
let tableBody = document.getElementById('productTableBody');
tableBody.innerHTML = '';

fetch(`http://localhost:8080/api/products`, {
    headers: {
        "Accept": "application/json"
    }
}).then(res => res.json())
    .then(products => {
        allData = products;
        if (products.length === 0) {
            tableBody.innerHTML += `
<div class="d-block justify-content-center text-center">
<h3 class="text-center justify-content-center text-danger">No products found!</h3>
</div>`
        } else {
            for (let product of products) {

                let row = document.createElement('tr');

                let buttonCol = document.createElement('th');
                let button = document.createElement('button');
                button.setAttribute("type", "button");
                button.setAttribute("id", "btnMSb");
                button.setAttribute("onClick", "toggle(this.id,'" + product.returnItemId + "');");
                button.setAttribute("aria-controls", product.returnItemId);
                button.setAttribute("aria-labelledby", "tnMSb lblMSb");
                buttonCol.appendChild(button);

                let idCol = document.createElement('th');
                idCol.textContent = product.id;

                let returnItemId = document.createElement('th');
                returnItemId.textContent = product.returnItemId;

                let asin = document.createElement('th');
                asin.textContent = product.asin;

                let category = document.createElement('th');
                category.textContent = product.category;
                category.style.maxWidth = "20px";

                let condition = document.createElement('th');
                condition.textContent = product.condition;

                let currencyCode = document.createElement('th');
                currencyCode.textContent = product.currencyCode;

                let department = document.createElement('th');
                department.textContent = product.department;

                let description = document.createElement('th');
                description.setAttribute("data-title", product.description);
                description.textContent = product.shortenDescription;
                description.style.maxWidth = "20px";

                let ean = document.createElement('th');
                ean.textContent = product.ean;

                let lpn = document.createElement('th');
                lpn.textContent = product.lpn;

                let palletId = document.createElement('th');
                palletId.textContent = product.palletId;

                let quantity = document.createElement('th');
                quantity.textContent = product.quantity;

                let subCategory = document.createElement('th');
                subCategory.textContent = product.subCategory;
                subCategory.style.maxWidth = "20px";

                let totalRetail = document.createElement('th');
                totalRetail.textContent = product.totalRetail;

                row.appendChild(buttonCol)
                row.appendChild(idCol);
                row.appendChild(returnItemId);
                row.appendChild(asin);
                row.appendChild(category);
                row.appendChild(condition);
                row.appendChild(currencyCode);
                row.appendChild(department);
                row.appendChild(description);
                row.appendChild(ean);
                row.appendChild(lpn);
                row.appendChild(palletId);
                row.appendChild(quantity);
                row.appendChild(subCategory);
                row.appendChild(totalRetail);

                tableBody.appendChild(row);


            }
        }


    })


function toggle(btnID, eIDs) {
    console.log("here")
    // Feed the list of ids as a selector
    var theRows = document.querySelectorAll(eIDs);
    // Get the button that triggered this
    var theButton = document.getElementById(btnID);
    // If the button is not expanded...
    if (theButton.getAttribute("aria-expanded") == "false") {
        // Loop through the rows and show them
        for (var i = 0; i < theRows.length; i++) {
            theRows[i].classList.add("shown");
            theRows[i].classList.remove("hidden");
        }
        // Now set the button to expanded
        theButton.setAttribute("aria-expanded", "true");
        // Otherwise button is not expanded...
    } else {
        // Loop through the rows and hide them
        for (var i = 0; i < theRows.length; i++) {
            theRows[i].classList.add("hidden");
            theRows[i].classList.remove("shown");
        }
        // Now set the button to collapsed
        theButton.setAttribute("aria-expanded", "false");
    }
}