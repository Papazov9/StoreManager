let currentTab = 0;
const productId = document.getElementById("regForm").getAttribute("data");
let csrfHeaderKey = document.head.querySelector('[name=_csrf_header]').content;
let csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;
showTab(currentTab);

function showTab(n) {
    let allTabs = document.getElementsByClassName("tab");
    allTabs[n].style.display = "block";
    let prevBtn = document.getElementById("prevBtn");
    let nextBtn = document.getElementById("nextBtn");
    if (n === 0){
        prevBtn.style.display = "none";
    }else {
        prevBtn.style.display = "inline";
    }

    if (n === (allTabs.length - 1)) {
        nextBtn.style.display = "none";
    }else {
        nextBtn.style.display = "inline";
    }

    fixStepIndicator(n);
}

async function nextPrev(n) {
    let allTabs = document.getElementsByClassName("tab");

    if (currentTab === 0) {
        let selectedProductType = document.getElementById("productType").value;
        if (selectedProductType === "sell") {
            allTabs[currentTab].style.display = "none";
            currentTab = currentTab + n;
        } else if (selectedProductType === "fix") {
            const url = `http://localhost:8080/move/fix/${productId}`;
            await fetch(url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    [csrfHeaderKey]: csrfHeaderValue
                }
            });
            window.location.replace("http://localhost:8080/products/fix");
            return;
        }else {
            const url = `http://localhost:8080/move/scrap/${productId}`;
            await fetch(url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    [csrfHeaderKey]: csrfHeaderValue
                }
            });
            window.location.replace("http://localhost:8080/products/scrap");
            return;
        }
    }
    else if (currentTab === 1) {
        let productSize = document.getElementById("productSize").value;
        await fetch(`http://localhost:8080/rack/getFirstFree/${productSize}`)
    }
    showTab(currentTab);
}

function fixStepIndicator(n) {
    let allSteps = document.getElementsByClassName("step");

    for (let i = 0; i < allSteps.length; i++) {
        allSteps[i].className = allSteps[i].className.replace(" active", "");
    }

    allSteps[n].className += " active";
}