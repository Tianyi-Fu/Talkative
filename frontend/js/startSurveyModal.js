$(document).ready(() => {
    // EventListeners
    $("#startBtn").on('click', () => {
        $('.modal-content').empty();
        $('.modal-content').append("<div id='loading-container'><div class='spinner-border m-5' role='status'><span class='visually-hidden'>Loading...</span>");

        questionsLoadedCheck();
    })

    // Listen close button on click
    $(".btn-close").click(() => {
        localStorage.clear();
        window.location.reload();
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
        let counter = 0;

        function checkLocalStorage() {
            if (localStorage.getItem("questions")) {
                abortTimer();
            } else if (counter >= 30) {
                clearInterval(timer);

                $('.modal-content').empty();
                $('.modal-content').append("<div id='loading-container'><h3>Sorry, something went wrong :-(</h3></div>");

                setTimeout(() => {
                    localStorage.clear();
                    window.location.reload();
                }, 3000)
            } else {
                console.log("not loaded");
                counter ++;
            }
        }

        function abortTimer() {
            clearInterval(timer);

            $('.modal-content').load('/static/components/feedbackQuestionModal.html', () => {
                loadQuestions();
            });
        }
    }
})
