let currentTab = 0;
const productId = document.getElementById("regForm").getAttribute("data");
let rackName = document.getElementById("rackName");
let rackNumber = document.getElementById("rackNumber");
let submitBtn = document.getElementById("submitBtn");
let csrfHeaderKey = document.head.querySelector('[name=_csrf_header]').content;
let csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;
let productSize;
let buttonsDiv = document.getElementById("buttonsDiv");
let scrapBtn = document.getElementById("scrapBtn");
let fixBtn = document.getElementById("fixBtn");

submitBtn.addEventListener("click", submitProduct);
showTab(currentTab);


async function showTab(n) {
    let allTabs = document.getElementsByClassName("tab");

    if (n === 0 || n === 3) {
        currentTab = 0;
        allTabs[0].style.display = "block";
        document.getElementById("prevBtn").style.display = "none";
        submitBtn.style.display = 'none';
        fixBtn.textContent = 'FIX';
        fixBtn.style.display = 'inline';
        scrapBtn.style.display = 'inline';
        fixBtn.addEventListener("click", fixSubmit);
        fixBtn.onclick = function (){return false;};
        document.getElementById("nextBtn").textContent = "SELL";
        document.getElementById("nextBtn").style.display = "inline";
        fixStepIndicator(n);
    }

    if (n === 1) {
        allTabs[n].style.display = "block";
        document.getElementById("prevBtn").style.display = "inline";
        scrapBtn.style.display = 'none';
        submitBtn.style.display = 'none';
        fixBtn.removeEventListener("click", fixSubmit);
        fixBtn.textContent = 'SMALL';
        fixBtn.style.display = 'inline';
        fixBtn.onclick = function (){nextPrev(1, event)};
        document.getElementById("nextBtn").textContent = 'BIG';
        document.getElementById("nextBtn").style.display = 'inline';
        fixStepIndicator(n);
    }

    if (n === 2) {
        allTabs[n].style.display = "block";
        fixBtn.style.display = 'none';
        document.getElementById("nextBtn").style.display = 'none';
        await findNextFreeRackSpace(productSize);
        submitBtn.style.display = 'inline';
        const generateBtn = document.getElementById("generateBtn");
        generateBtn.addEventListener("click", generateNewRackNumber);
        fixStepIndicator(n);
    }

    if (n === 4) {
        allTabs[1].style.display = "block";
        document.getElementById("prevBtn").style.display = "inline";
        document.getElementById("nextBtn").style.display = 'none';
        scrapBtn.style.display = 'none';
        fixBtn.style.display = 'none';
        submitBtn.style.display = 'none';
        document.getElementById("sizeOfProduct").textContent = 'Choose number of pallet:';

        for (let i = 1; i <= 4; i++) {
            let palletButton = document.createElement("button");
            palletButton.textContent = `Pallet_${i}`;
            palletButton.id = `Pallet_${i}`;
            palletButton.addEventListener("click", () => moveToScrapPallet(event));
            palletButton.className = 'btn btn-danger align-items-center px-5 mx-2 pallet';
            palletButton.style.display = 'inline';
            buttonsDiv.appendChild(palletButton);
        }
        fixStepIndicator(1);
    }


}

function hidePalletButtons(elementsByClassName) {

    for (let i =0; i < elementsByClassName.length; i++) {
        elementsByClassName[i].style.display = 'none';
    }
}

async function nextPrev(n, event) {


    let allTabs = document.getElementsByClassName("tab");

    if (currentTab === 0 || currentTab === 3) {
        allTabs[0].style.display = "none";
        currentTab = currentTab + n;
    }else {

            if (currentTab === 4) {
                allTabs[1].style.display = "none";
                hidePalletButtons(document.getElementsByClassName("pallet"));
                currentTab += n;
            }
        else {
            if (currentTab === 1) {
                allTabs[currentTab].style.display = "none";
                currentTab += n;

                if (n > 0) {
                    console.log(event.target.textContent);
                    productSize = event.target.textContent;
                }

            } else {

                allTabs[currentTab].style.display = "none";
                currentTab += n;
            }
        }
    }


    showTab(currentTab);
}

async function moveToScrapPallet(event) {
    event.preventDefault();
    const palletName = event.target.textContent;
    let text = `Are you sure you want to move product with id: ${productId} to scrap pallet: ${palletName}?`;

    if (confirm(text)) {
        await fetch(`http://localhost:8080/move/scrap/${productId}/${palletName}`, {
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

async function submitProduct() {
    let rackNameText = rackName.value;
    let rackNumberText = rackNumber.value;
    const text = `Are you sure you want to move product with id: ${productId} to Rack: ${rackNameText} - ${rackNumberText}?`
    if (confirm(text)) {
        await fetch(`http://localhost:8080/move/selling/${productId}/${rackNameText}/${rackNumberText}`, {
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

function fixStepIndicator(n) {
    let allSteps = document.getElementsByClassName("step");

    for (let i = 0; i < allSteps.length; i++) {
        allSteps[i].className = allSteps[i].className.replace(" active", "");
    }

    if (n === 3) {
        n = 0;
    }else if (n === 4) {
        n = 1;
    }

    allSteps[n].className += " active";
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
    } else {
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

async function fixSubmit() {
    let textConfirm = `Are you sure you want to send a product with id: ${productId} in fix section?`;
    if (!confirm(textConfirm)) {
        return;
    }
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
}