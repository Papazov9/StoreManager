let deleteBtn = document.getElementById("deleteBtn");
let clearBtn = document.getElementById("clearBtn");
let moveBtn = document.getElementById("moveBtn");
let csrfHeaderKey = document.head.querySelector('[name=_csrf_header]').content;
let csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

if (deleteBtn != null){
    deleteBtn.addEventListener('click', onDelete);
}

if (clearBtn != null){
    clearBtn.addEventListener("click", clearSearch);
}

function clearSearch() {
    window.location.replace("http://localhost:8080/products/progress");
}

async function onDelete(event) {
   let productId =  event.target.getAttribute("data-id");
   let text = "Are you sure you want to delete this product?";
   if (confirm(text)) {
       await fetch(`http://localhost:8080/products/delete/${productId}`,{
           method: 'DELETE',
           headers: {
               'Content-Type': 'application/json',
               'Accept': 'application/json',
               [csrfHeaderKey]: csrfHeaderValue
           }
       });
       window.location.replace("http://localhost:8080/products/successful-delete");
   }
}
function toggle(btnID, eIDs) {

    var theRows = document.querySelectorAll(eIDs);


    var theButton = document.getElementById(btnID);
    if (theButton.getAttribute("aria-expanded") === "false") {

        for (let i = 0; i < theRows.length; i++) {
            theRows[i].classList.remove("hidden");
        }

        theButton.setAttribute("aria-expanded", "true");
    } else {
        for (let i = 0; i < theRows.length; i++) {
            theRows[i].classList.add("hidden");
        }
        theButton.setAttribute("aria-expanded", "false");
    }
}
function toggleResult(buttonId, ids) {

    var theRows = document.querySelectorAll(ids);


    var theButton = document.getElementById(buttonId);
    if (theButton.getAttribute("aria-expanded") === "false") {

        for (let i = 0; i < theRows.length; i++) {
            theRows[i].classList.remove("hidden");
        }

        theButton.setAttribute("aria-expanded", "true");
    } else {
        for (let i = 0; i < theRows.length; i++) {
            theRows[i].classList.add("hidden");
        }
        theButton.setAttribute("aria-expanded", "false");
    }
}
