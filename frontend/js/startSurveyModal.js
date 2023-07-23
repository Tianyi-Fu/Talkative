$(document).ready(() => {
    // EventListeners
    $("#startBtn").on('click', () => {
        console.log('clicked start')
        $('.modal-content').empty();
        // document.getElementsByClassName("modal-body").innerHTML =  "<div class='spinner-border m-5' role='status'><span class='visually-hidden'>Loading...</span>";
        $('.modal-content').load('components/feedbackQuestionModal.html', () => {
            loadQuestions();
        });
    })

    // Functions
    function loadQuestions() {
        const questionArray = localStorage.getItem("questions");
        const questions = JSON.parse(questionArray)

        $('#question').append(`<span>${questions[0]}</span>`);

        localStorage.setItem('questionNumber', "1");
    }
})