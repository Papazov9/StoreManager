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

                //         tableBody += ` <tr>
                //     <td>"${product.id}"</td>
                //     <td>"${product.returnItemId}"</td>
                //     <td>asin</td>
                //     <td>category</td>
                //     <td>condition</td>
                //     <td>currencyCode</td>
                //     <td>department</td>
                //     <td>description</td>
                //     <td>ean</td>
                //     <td>lpn</td>
                //     <td>palletId</td>
                //     <td>quantity</td>
                //     <td>subCategory</td>
                //     <td>totalRetail</td>
                // </tr>`
            }
        }


    })