$(document).ready(() => {
    let baseUrl = 'http://127.0.0.1:8081'

    // EventListeners
    /* Enable Next button when there is content in textarea */
    $("#answer").change((e) => {
        let val = e.target.value;

        if (val !== null) {
            $("#nextBtn").prop("disabled", false);
        } else {
            $("#nextBtn").prop("disabled", true);
        }
    })

    $("#nextBtn").click(() => {
        console.log("NEXT")
        saveAnsGetQuestion();
    })

    $("#submitFeedbackBtn").click(() => {
        saveAnsGetQuestion();

        $('.modal-content').empty();
        $('.modal-content').append('<div class="modal-body" style="margin-top: 50px"><h3>Thank you for your feedback!</h3></div>');

        setTimeout(() => {
            localStorage.clear();
            window.location.reload();
        }, 2000)
    })

    // Functions
    function saveAnsGetQuestion() {
        // Save customer answer to localStorage
        let answersArray = localStorage.getItem("answers") ? JSON.parse(localStorage.getItem("answers")) : [];
        answersArray.push($("#answer").val());
        localStorage.setItem("answers", JSON.stringify(answersArray));
        $("#answer").val("");

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
                localStorage.setItem("questionNumber", "2");
                break;
            case "2":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[2];
                localStorage.setItem("questionNumber", "3");
                break;
            case "3":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[3];
                localStorage.setItem("questionNumber", "4");
                break;
            case "4":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[4];
                localStorage.setItem("questionNumber", "5");
                break;
            case "5":
                document.querySelector(".modal-body #question span").innerHTML = questionArray[5];
                localStorage.setItem("questionNumber", "last");

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
        const questionArray = JSON.parse(localStorage.getItem("questions"));
        let answersArray = JSON.parse(localStorage.getItem("answers"));

        questionArray.map((q, i) => {
            feedback.push({
                "question": q,
                "answer": answersArray[i]
            })
        })
        console.log(feedback)

        $.ajax({
            url: baseUrl + '/create/' + localStorage.getItem("agentName"),
            method: 'post',
            contentType: "application/json", // Set the request header to JSON format.
            data: JSON.stringify(feedback),
            success: function (data) {
                if (data.code == 500) {
                    return alert(data.message);
                } else {
                    return "success";
                }
            }
        })
    }
})