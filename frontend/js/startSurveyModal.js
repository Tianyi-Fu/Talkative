$(document).ready(() => {
    // EventListeners
    $("#startBtn").on('click', () => {
        console.log('clicked start')
        $('.modal-content').empty();
        $('.modal-content').append("<div class='spinner-border m-5' role='status'><span class='visually-hidden'>Loading...</span>");

        // NEEDS FIXING HERE => SHOW LOADING UNTIL QUESTIONS ARE LOADED IN LOCALSTORAGE
        setTimeout(() => {
            $('.modal-content').load('components/feedbackQuestionModal.html', () => {
                loadQuestions();
            });
        }, 8000)
    })

    // Functions
    function loadQuestions() {
        const questionArray = localStorage.getItem("questions");
        const questions = JSON.parse(questionArray)

        $('#question').append(`<span>${questions[0]}</span>`);

        localStorage.setItem('questionNumber', "1");
    }
})