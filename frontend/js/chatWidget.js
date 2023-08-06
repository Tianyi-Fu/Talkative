$(document).ready(() => {
    setTimeout(() => {
        let shadow = document.querySelector("#talkative-engage").shadowRoot

        let observer = new MutationObserver(function (m) {
            if (shadow.querySelector("button[title='End Interaction']")) {
                console.log('found End Interaction')

                // shadow.querySelector("button[title='End Interaction']").addEventListener("click", () => {
                //     console.log('End clicked')
                // }, false);

                // shadow.querySelector("button[title='Confirm']").addEventListener("click", () => {
                //     console.log('clicked')
                // })

                let chatBody = shadow.querySelector("div#app .bg-white.overflow-y-auto.flex.flex-grow")
                let observer = new MutationObserver((m) => {
                    if (chatBody.querySelector("form input[id='send-transcript']")) {
                        console.log('found Confirm');


                        // If customer confirm ends conversation, extract conversation transcript
                        let customer = shadow.querySelectorAll("#main-app .bg-primary .whitespace-pre-line");
                        customer.forEach((item) => console.log(item.innerText));
                    }
                });
                observer.observe(chatBody, {childList: true});
            }
        });

        observer.observe(shadow, {childList: true});

    }, 3000)

    /*
    // Agent ends conversation
    document.querySelector("#talkative-engage").shadowRoot.querySelector("input#send-transcript");


    let customer = document.querySelector("#talkative-engage").shadowRoot.querySelectorAll("#main-app .bg-primary .whitespace-pre-line");
    customer.forEach((item) => console.log(item.innerText)); => Get Customer Message

    let agent = document.querySelector("#talkative-engage").shadowRoot.querySelectorAll("#main-app .bg-white .whitespace-pre-line");
    agent.forEach((item) => console.log(item.innerText)); => Get Agent Message


    { id: xxxx,
    messages: [
        { message: "",
        sender_type: "Customer" / "User",
        sender_name: "Customer" / "agent's name" }
        ]
    }
     */
})