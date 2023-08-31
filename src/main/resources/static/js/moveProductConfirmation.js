async function confirmMoveBackToProgressProduct(event) {

    let productId =  event.target.getAttribute("data-id");
    let text = `Are you sure you want to move the product with id: ${productId} back to Products In Progress table?`;
    if (confirm(text)) {
        await fetch(`http://localhost:8080/products/undo/${productId}`,{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeaderKey]: csrfHeaderValue
            }
        });
        window.location.replace("http://localhost:8080/products/progress");
    }
}