$(document).ready(() => {
    let baseUrl = 'http://127.0.0.1:8081';
    let answer;

    // EventListeners
    /* Get value from star rating */
    $('input[name="rating"]').click(() => {
        let selected = document.querySelector('input[name="rating"]:checked').value;
        console.log(selected)

        answer = selected;
        $("#nextBtn").prop("disabled", false);
    })

    /* Get value from radio buttons */
    $('input[name="flexRadioDefault"]').click((e) => {
        let val = e.target.value;
        answer = val;
        enableNextBtn(val);
    })

    /* Enable Next button when there is content in textarea */
    $("#open-end_answer").change((e) => {
        let val = e.target.value;
        answer = val;
        enableNextBtn(val);
    })

    $("#nextBtn").click(() => {
        // Uncheck radio button
        $('input[name="flexRadioDefault"]:checked').prop('checked', false);

        // clear textarea
        $("#open-end_answer").val("");

        saveAnsGetQuestion(answer);
    })

    $("#submitFeedbackBtn").click(() => {
        saveAnsGetQuestion();

        $('.modal-content').load('components/feedbackCustomerInformation.html');
    })

    // Functions
    function enableNextBtn(val) {
        let q = localStorage.getItem("questionNumber");
        let btn = q === "last" ? $("#submitFeedbackBtn") : $("#nextBtn");

        if (val !== null) {
            btn.prop("disabled", false);
        } else {
            btn.prop("disabled", true);
        }
    }

    function disableNextBtn() {
        let q = localStorage.getItem("questionNumber");
        q === "last" ? $("#submitFeedbackBtn").prop("disabled", true) : $("#nextBtn").prop("disabled", true);
    }

    function saveAnsGetQuestion(answer) {
        // Save customer answer to localStorage
        let answersArray = localStorage.getItem("answers") ? JSON.parse(localStorage.getItem("answers")) : [];
        answersArray.push(answer);
        localStorage.setItem("answers", JSON.stringify(answersArray));

        // Get questions from localStorage
        const questionArray = JSON.parse(localStorage.getItem("questions"));

        // Check which question to display
        const questionNum = localStorage.getItem("questionNumber");

        if (!questionNum) {
            document.querySelector(".modal-body #question span").innerHTML = questionArray[1];
            return localStorage.setItem("questionNumber", "1");
        }

        switch (questionNum) {
            case "1":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[1];
                $("#rating-container").prop("hidden", true);
                $("#radioBtns").prop("hidden", false);
                localStorage.setItem("questionNumber", "2");
                disableNextBtn();
                break;
            case "2":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[2];
                localStorage.setItem("questionNumber", "3");
                disableNextBtn();
                break;
            case "3":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[3];
                $("#radioBtns").prop("hidden", true);
                $("#open-end_answer").prop("hidden", false);
                localStorage.setItem("questionNumber", "4");
                disableNextBtn();
                break;
            case "4":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[4];
                localStorage.setItem("questionNumber", "5");
                disableNextBtn();
                break;
            case "5":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[5];
                localStorage.setItem("questionNumber", "last");
                disableNextBtn();
                $("#nextBtn").prop("hidden", true);
                $("#submitFeedbackBtn").prop("hidden", false);
                break;
            case "last":
                submitFeedbackForm();
                break;
            default:
                break;
        }
    }

    function submitFeedbackForm() {
        let feedback = [];
        let chatRecordId = localStorage.getItem("chatRecordId")
        let feedbackRecordId = localStorage.getItem("feedbackRecordId")
        const questionArray = JSON.parse(localStorage.getItem("questions"));
        let answersArray = JSON.parse(localStorage.getItem("answers"));

        questionArray.map((q, i) => {
            feedback.push({
                "question": q,
                "answer": answersArray[i]
            })
        })
        console.log(feedback)

        let data = {"chatRecordId": chatRecordId, "list": feedback, "feedbackRecordId": feedbackRecordId}

        $.ajax({
            url: baseUrl + '/create/' + localStorage.getItem("agentName"),
            method: 'post',
            contentType: "application/json", // Set the request header to JSON format.
            data: JSON.stringify(data),
            success: function (data) {
                if (data.code == 500) {
                    return alert(data.message);
                } else {
                    return "success"
                }
            }
        })
    }
})
