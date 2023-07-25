const togglePassword = document.querySelector(".togglePassword");
const password = document.querySelector("#password");

console.log("I AM HERE")

togglePassword.addEventListener("click", function () {
    const type = password.getAttribute("type") === "password" ? "text" : "password";
    password.setAttribute("type", type);
});

// const form = document.querySelector("form");
// form.addEventListener('submit', function (e) {
//     e.preventDefault();
// });