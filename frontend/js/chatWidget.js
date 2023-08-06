$(document).ready(() => {
    setTimeout(() => {
        let shadow = document.querySelector("#talkative-engage").shadowRoot

        let observer = new MutationObserver(function (m) {
            // if (shadow.querySelector("button[title='End Interaction']")) {
            //     console.log('found End Interaction')
            // }

            let observer2 = new MutationObserver((m) => {
                console.log('new observer')
                if (shadow.querySelector("div.flex.h-full h1")?.innerHTML === "Feedback") {
                    // If customer confirm ends conversation, extract conversation transcript
                    let customer = shadow.querySelectorAll("#main-app .bg-primary .whitespace-pre-line");
                    customer.forEach((item) => console.log(item.innerText));
                } else console.log("not yet loaded");
            });
            observer2.observe(shadow.querySelector("div.flex.h-full"), {childList: true});
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