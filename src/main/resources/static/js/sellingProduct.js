async function confirmSale(buttonElement) {

    const row = buttonElement.closest("tr");

    const asin = row.querySelector("th:nth-of-type(4)").textContent;

    console.log(asin);

    const isConfirmed = confirm("Are you sure you want to sell product with ASIN: " + asin + "?");

    if (isConfirmed) {
        const productId = row.querySelector("th:nth-of-type(2)").textContent;
        console.log(productId);
        const response = await fetch(`http://localhost:8080/products/sale/${productId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeaderKey]: csrfHeaderValue
            }
        });

        if (response.ok) {
            window.location.replace(`http://localhost:8080/products/sold`);
        } else {
            window.location.replace(`http://localhost:8080/returns/import/error`);
        }

    } else {
        console.log("Action canceled!");
    }
}
