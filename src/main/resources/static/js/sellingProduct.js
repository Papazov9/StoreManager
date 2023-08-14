function confirmSale(buttonElement) {

    const row = buttonElement.closest("tr");

    const asin = row.querySelector("th:nth-of-type(3)").textContent;

    console.log(asin);

    const isConfirmed = confirm("Are you sure you want to sell product with ASIN: " + asin + "?");

    if (isConfirmed) {
        const productId = row.querySelector("th:first-child").textContent;
        window.location.replace(`http://localhost:8080/products/sale/${productId}`);

    } else {
        console.log("Action canceled!");
    }
}