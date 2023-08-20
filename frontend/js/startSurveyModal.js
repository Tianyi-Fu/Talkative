$(document).ready(() => {
    // EventListeners
    $("#startBtn").on('click', () => {
        console.log("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111")
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
        nextStep(1);

        localStorage.setItem('questionNumber', "1");
    }

    function questionsLoadedCheck() {
        let timer = setInterval(checkLocalStorage, 2000);

        function checkLocalStorage() {
            localStorage.getItem("questions") ? abortTimer() : console.log("not loaded");
        }

        function abortTimer() {
            clearInterval(timer);

            $('.modal-content').load('/static/components/feedbackQuestionModal.html', () => {
                loadQuestions();
            });
        }
    }
})
