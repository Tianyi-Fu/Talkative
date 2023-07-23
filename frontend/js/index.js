$(document).ready(() => {
    let baseUrl = 'http://127.0.0.1:8081'
    let agentName = '';

    // Ajax
    /* Send Transcript to Backend */
    $("#submit").click((e) => {
        // e.preventDefault();
        let conversation = $("#chat").val();

        // Check for value in textarea
        if (conversation.length < 1) return;

        loadModalContent();

        let transcriptObj = formatTranscript(conversation);
        //
        $.ajax({
            /* Write to chat_record table */
            url: baseUrl + '/read_transcript',
            method: 'post',
            contentType: "application/json", // Set the request header to JSON format
            data: JSON.stringify(transcriptObj),
            success: function (data) {
                if (data.code == 500) {
                    alert(data.message)
                } else {
                    console.log('OK')

                    /* Send transcript to Chat GPT */
                    // HTTP requests across different ports on the same machine can lead to cross-domain issues.
                    // These issues can be resolved by adding the @CrossOrigin annotation in the backend controller class.
                    // Please refer to the backend code for specific details.
                    // agentName = transcriptObj.agent_name;
                    // $.ajax({
                    //     url: baseUrl + '/openai-chat',
                    //     method: 'get',
                    //     // data: JSON.stringify(transcriptObj),
                    //     data: {'content': JSON.stringify(transcriptObj.transcript)},
                    //     success: function (data) {
                    //         if (data.code == 500) {
                    //             alert(data.message)
                    //         } else {
                    //             localStorage.setItem("questions", JSON.stringify(data.data.questions))
                    //             localStorage.setItem("agentName", data.data.agentName)
                    //         }
                    //     }
                    // })
                }
            }
        })
    })

    // $('#submit').click(function () {
    //     // HTTP requests across different ports on the same machine can lead to cross-domain issues.
    //     // These issues can be resolved by adding the @CrossOrigin annotation in the backend controller class.
    //     // Please refer to the backend code for specific details.
    //     // ajax
    //     $('.questions').empty()
    //     agentName = '';
    //     $.ajax({
    //         url: baseUrl + '/openai-chat',
    //         method: 'get',
    //         data: {
    //             'content': $('#message').val()
    //         },
    //         success: function (data) {
    //             if (data.code == 500) {
    //                 alert(data.message)
    //             } else {
    //                 localStorage.setItem("questions", JSON.stringify(data.data.questions))
    //                 localStorage.setItem("agentName", data.data.agentName)
    //                 const htmlAnchorElement = document.querySelector("a");
    //                 htmlAnchorElement.click()
    //                 // const url = "http://localhost:63342/demo2/frontend/surveyForm.html?_ijt=vmhg70kv089cbm2l95i1dhafcs"
    //
    //                 // const questions = data.data.questions;
    //                 // agentName = data.data.agentName;
    //                 // questions.forEach(question => {
    //                 //     $('.questions').append('<div class="question"> <p class="qs">'+question+'</p> <input type="text" class="answer" /> </div>')
    //                 // });
    //                 // $('.questions').append('<input class="saveAnswer" type="button" value="Submit" />')
    //             }
    //         }
    //     })
    // })

    $('.questions').on('click', '.saveAnswer', function () {
        let feedback = []
        $('.question').each(function () {
            const question = $(this).children('.qs').text();
            const answer = $(this).children('.answer').val();
            feedback.push({
                "question": question,
                "answer": answer
            })
        })

        $.ajax({
            url: baseUrl + '/create/' + agentName,
            method: 'post',
            contentType: "application/json", // Set the request header to JSON format.
            data: JSON.stringify(feedback),
            success: function (data) {
                if (data.code == 500) {
                    alert(data.message)
                } else {
                    alert('Thank you for your answer!')
                }
            }
        })
    })

    // EventListeners
    /* Enable Submit(End Chat) button when there is content in textarea */
    $("#chat").change((e) => {
        let val = e.target.value;

        if (val !== null) {
            $("#submit").prop("disabled", false);
        } else {
            $("#submit").prop("disabled", true);
        }
    })

    // Functions
    function formatTranscript(conversation) {
        let newConversation = [];
        let msgArray = JSON.parse(conversation).messages;
        let sender;

        msgArray.find((item) => {
            if (item.sender_type.includes("User")) sender = item.sender_name;
        })

        msgArray.map((item) => {
            newConversation.push({[item.sender_name]: item.message});
        })

        let transcriptObj = {"agent_name": sender, "transcript": newConversation}
        console.log(transcriptObj)
        return transcriptObj;
    }

    function loadModalContent() {
        let modalContainer = new bootstrap.Modal(document.getElementById('modalContainer'))
        $('.modal-content').load('components/startSurveyModal.html', () => {
            modalContainer.show();
        });
    }
})