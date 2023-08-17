let scrapButtons = document.getElementsByClassName("pallet");
let csrfHeaderKey = document.head.querySelector('[name=_csrf_header]').content;
let csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

for (let i = 0; i < scrapButtons.length; i++) {
    let currentButton = scrapButtons[i];

    currentButton.addEventListener("click", () => exportExcel(event));
}

async function exportExcel(event) {
    event.preventDefault();
    let palletName = event.target.id;

    let text = `Are you sure you want to export excel with products from pallet: ${palletName}?`;

    if (confirm(text)) {
        await fetch(`http://localhost:8080/export/${palletName}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeaderKey]: csrfHeaderValue
            }
        });
        // window.location.replace("http://localhost:8080/products/successful-delete");
    }
}