let deleteAllBtn = document.getElementById("deleteAllBtn");
let csrfHeaderKey = document.head.querySelector('[name=_csrf_header]').content;
let csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

if (deleteAllBtn != null){
    deleteAllBtn.addEventListener('click', onDeleteAll);
}

async function onDeleteAll(event) {
    let palletName =  event.target.getAttribute("data-id");
    let text = `Are you sure you want to delete the products in ${palletName}?`;
    if (confirm(text)) {
        await fetch(`http://localhost:8080/export/delete/all/${palletName}`,{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeaderKey]: csrfHeaderValue
            }
        });
        window.location.replace("http://localhost:8080/products/scrap");
    }
}
