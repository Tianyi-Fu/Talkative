function generateStepper(questions) {
    let stepperContainer = document.querySelector(".modal-header .bs-stepper .bs-stepper-header");

    for (let i = 0; i < questions; i++) {
        let child = `<div class="step"><span class="bs-stepper-circle" id="${i + 1}">${i + 1}</span></button></div>`;

        let line = '<div class="line"></div>'
        stepperContainer.innerHTML += child;

        if (i < questions - 1) {
            stepperContainer.innerHTML += line;
        }
    }
}

function nextStep(question) {
    let steppers = document.querySelectorAll(".modal-header .bs-stepper .bs-stepper-header .step");

    Array.from(steppers).map((child) => {
        if (child.childNodes[0].id <= question) {
            child.childNodes[0].style.backgroundColor = "#3185fc";
        }
    })
}