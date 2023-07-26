$(document).ready(() => {
    // EventListeners
    $("#startBtn").on('click', () => {
        console.log('clicked start')
        $('.modal-content').empty();
        $('.modal-content').append("<div class='spinner-border m-5' role='status'><span class='visually-hidden'>Loading...</span>");

        abortTimerNot()
        // questionsLoadedCheck();
    })

    //load info-html
    function abortTimerNot() {
        $('.modal-content').load('components/feedbackCustomerInformation.html');
    }

})
