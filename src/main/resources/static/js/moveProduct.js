let currentTab = 0;
const productId = document.getElementById("regForm").getAttribute("data");
let rackName = document.getElementById("rackName");
let rackNumber = document.getElementById("rackNumber");
let submitBtn = document.getElementById("submitBtn");
let csrfHeaderKey = document.head.querySelector('[name=_csrf_header]').content;
let csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;
let productSize;

submitBtn.addEventListener("click", submitProduct);
showTab(currentTab);

async function showTab(n) {
    let allTabs = document.getElementsByClassName("tab");
    allTabs[n].style.display = "block";
    if (n === 2) {
        await findNextFreeRackSpace(productSize);
        document.getElementById("submitBtn").style.display = "block";
        const generateBtn = document.getElementById("generateBtn");
        generateBtn.addEventListener("click", generateNewRackNumber);
    }
    else {
        document.getElementById("submitBtn").style.display = "none";
    }
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
        productSize = document.getElementById("productSize").value;

        allTabs[currentTab].style.display = "none";
        currentTab += n;
    }else {

        allTabs[currentTab].style.display = "none";
        currentTab += n;
    }
    showTab(currentTab);
}

async function generateNewRackNumber() {
    console.log(productSize);
    const response = await fetch(`http://localhost:8080/rack/findSuitable/${productSize}/${rackName.value}/${rackNumber.value}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [csrfHeaderKey]: csrfHeaderValue
        }
    });

    if (response.status === 404) {
        document.getElementById("outOfSpace").style.display = "block";
        return;
    }
    else {
        document.getElementById("outOfSpace").style.display = "none";
    }

    const rack = await response.json();
    console.log(rack);
    rackName.value = rack.rackName;
    rackNumber.value = rack.rackNumber;
}

async function findNextFreeRackSpace(productSize) {
    const response = await fetch(`http://localhost:8080/rack/getFirstFree/${productSize}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [csrfHeaderKey]: csrfHeaderValue
        }
    });

    if (!response.ok) {
        window.location.replace(`http://localhost:8080/returns/import/error`);
        return;
    }

    const rack = await response.json();
    rackName.value = rack.rackName;
    rackNumber.value = rack.rackNumber;
}

function fixStepIndicator(n) {
    let allSteps = document.getElementsByClassName("step");

    for (let i = 0; i < allSteps.length; i++) {
        allSteps[i].className = allSteps[i].className.replace(" active", "");
    }

    allSteps[n].className += " active";
}

async function submitProduct(){
    let rackNameText = rackName.value;
    let rackNumberText = rackNumber.value;
    const text = `Are you sure you want to move product with id: ${productId} to Rack: ${rackNameText} - ${rackNumberText}?`
    if (confirm(text)) {
        await fetch(`http://localhost:8080/move/selling/${productId}/${rackNameText}/${rackNumberText}`,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeaderKey]: csrfHeaderValue
            }
        });

        window.location.replace("http://localhost:8080/products/sale");
    }

}