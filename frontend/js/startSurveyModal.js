$(document).ready(() => {
    // EventListeners
    $("#startBtn").on('click', () => {
        $('.modal-content').empty();
        $('.modal-content').append("<div id='loading-container'><div class='spinner-border m-5' role='status'><span class='visually-hidden'>Loading...</span>");

        questionsLoadedCheck();
    })

    // Functions
    function loadQuestions() {
        const questionArray = localStorage.getItem("questions");
        const questions = JSON.parse(questionArray)

        $('#question').append(`<span>${questions[0]}</span>`);

        generateStepper(questions.length);
        let stepper = new Stepper($('.bs-stepper')[0]);

        localStorage.setItem('questionNumber', "1");
    }

    function questionsLoadedCheck() {
        let timer = setInterval(checkLocalStorage, 2000);

        function checkLocalStorage() {
            localStorage.getItem("questions") ? abortTimer() : console.log("not loaded");
        }

        function abortTimer() {
            clearInterval(timer);

            $('.modal-content').load('components/feedbackQuestionModal.html', () => {
                loadQuestions();
            });
        }
    }
})
