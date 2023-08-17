$(document).ready(() => {
    // Check Qs array.length to get length of stepper
    // nextBtn on click, update stepper
})

function generateStepper(questions) {
    let stepperContainer = document.querySelector(".modal-header .bs-stepper .bs-stepper-header");

    for (let i = 0; i < questions; i++) {
        let child = `<div class="step"><span class="bs-stepper-circle">${i + 1}</span></button></div>`;

        let line = '<div class="line"></div>'
        stepperContainer.innerHTML += child;

        if (i < questions - 1) {
            stepperContainer.innerHTML += line;
        }
    }
}