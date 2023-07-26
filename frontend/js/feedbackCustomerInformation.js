$(document).ready(function () {
    $("#startBtn").click(function () {
        var firstName = document.getElementById("firstName").value
        var lastName = document.getElementById("lastName").value
        var email = document.getElementById("inputEmail").value
        var regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
        if (firstName.trim().length <= 0) {
            alert("First Name Is Null")
        } else if (lastName.trim().length <= 0) {
            alert("Last Name Is Null")
        } else if (email.trim().length <= 0) {
            alert("email Is Null")
        }else if (!regEmail.test(email)){
            alert("email result is false")
        }else {
            localStorage.setItem("firstName", firstName)
            localStorage.setItem("lastName", lastName)
            localStorage.setItem("email", email);
            questionsLoadedCheck()
        }
    })


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

    // Functions
    function loadQuestions() {
        const questionArray = localStorage.getItem("questions");
        const questions = JSON.parse(questionArray)

        $('#question').append(`<span>${questions[0]}</span>`);

        localStorage.setItem('questionNumber', "1");
    }
})