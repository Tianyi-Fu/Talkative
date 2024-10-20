// let baseUrl = 'http://127.0.0.1:8081'
let baseUrl = 'https://13.42.40.174';

$(document).ready(() => {
    let agentName = '';

    // Ajax 
    /* Send Transcript to Backend */
    $("#submit").click(() => {
        let conversation = $("#chat").val();

        // Check for value in textarea 
        if (conversation.length < 1) return;

        let chatRecordId = JSON.parse(conversation).id;

        sendTranscript(chatRecordId, conversation);
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

    // Listen close button on click
    $(".btn-close").click(() => {
        localStorage.clear();
        window.location.reload();
    })
})

// Functions 
function formatTranscript(conversation, chatRecordId) {
    let newConversation = [];
    let msgArray = JSON.parse(conversation).messages;
    let sender;

    msgArray.map((item) => {
        newConversation.push({[item.sender_name]: item.message});
    })

    // Find agent name & ignore "Talkative" sender_name if human agent was involved 
    let reverseArr = msgArray.reverse();
    let lastAgent = reverseArr.find((item) => {
        return item.sender_type.includes("User");
    })

    msgArray.find((item) => {
        if (item.sender_type.includes("User")) {
            let agent = item.sender_name;
            // Check if the first response and last response are from the same agent 
            agent === lastAgent.sender_name ? sender = agent : sender = lastAgent.sender_name;
        }
    })

    let transcriptObj = {"agent_name": sender, "transcript": newConversation, "chatRecordId": chatRecordId}
    console.log(transcriptObj)
    return transcriptObj;
}

function loadModalContent() {
    let modalContainer = new bootstrap.Modal(document.getElementById('modalContainer'))
    $('.modal-content').load('/static/components/startSurveyModal.html', () => {
        modalContainer.show();
    });
}

function sendTranscript(chatRecordId, conversation, widget) {
    if (chatRecordId) {
        localStorage.setItem("chatRecordId", chatRecordId);
    } else {
        let now = Date.now();
        let id = now.toString().slice(6);
        localStorage.setItem("chatRecordId", id);
    }

    // Hide Talkative widget
    document.querySelector("#talkative-engage").style.display = "none";

    loadModalContent();

    let transcriptObj = !widget ? formatTranscript(conversation, chatRecordId) : conversation;

    agentName = transcriptObj.agent_name;
    localStorage.setItem("agentName", agentName);

    $.ajax({
        /* Write to chat_record table */
        url: baseUrl + '/read_transcript',
        method: 'post',
        contentType: "application/json", // Set the request header to JSON format
        data: JSON.stringify(transcriptObj),
        success: function (data) {
            if (data.code == 500) {
                console.log(data.message)
            } else {
                localStorage.setItem("feedbackRecordId", data.data)
                console.log('OK')

                /* Send transcript to Chat GPT */
                // HTTP requests across different ports on the same machine can lead to cross-domain issues.
                // These issues can be resolved by adding the @CrossOrigin annotation in the backend controller class.
                // Please refer to the backend code for specific details.
                $.ajax({
                    url: baseUrl + '/openai-chat',
                    method: 'get',
                    // data: JSON.stringify(transcriptObj),
                    data: {'content': JSON.stringify(transcriptObj.transcript)},
                    success: function (data) {
                        if (data.code == 500) {
                            alert(data.message)
                        } else {
                            localStorage.setItem("questions", JSON.stringify(data.data.questions))
                        }
                    }
                })
            }
        }
    })
}