$(document).ready(() => {
    setTimeout(() => {
        let shadow = document.querySelector("#talkative-engage").shadowRoot

        // Track the changes in the chat widget
        let observer = new MutationObserver(function (m) {
            if (shadow.querySelector("button[title='End Interaction']")) {
                // console.log('found End Interaction')

                // let chatBody = shadow.querySelector("div#app .bg-white.overflow-y-auto.flex.flex-grow")
                let observer = new MutationObserver((m) => {
                    if (shadow.querySelector("button[title='Finish']")) {
                        console.log('found Finish2')

                        // If customer confirm ends conversation, extract conversation transcript
                        let customer = shadow.querySelectorAll("#main-app .bg-primary .whitespace-pre-line");
                        customer.forEach((item) => console.log(item.innerText));

                        let agent = shadow.querySelectorAll("#main-app .bg-white .whitespace-pre-line");
                        agent.forEach((item) => console.log(item.innerText));

                        let conversation = shadow.querySelector(".chat-thread-container").childNodes;
                        let conversationArr = Array.from(conversation);
                        let agentName = "flex pt-2 justify-start text-left";
                        let agentMsg = "flex flex-wrap space-y-2 justify-start pr-12";
                        let customerName = "flex pt-2 justify-end text-right";
                        let customerMsg = "flex flex-wrap space-y-2 justify-end pl-12";

                        conversationArr.filter((child)=> {
                            if(child.className === agentName || child.className === agentMsg || child.className === customerName || child.className === customerMsg){
                                console.log(child.lastElementChild.innerText)
                            //    TODO: form object for each pair
                            }
                        })
                    }
                });
                observer.observe(shadow, {attributes: true, childList: true, subtree: true});
            }
        });

        observer.observe(shadow, {childList: true});
    }, 3000)

    function createTranscriptObject() {
        let id = Date.now();
        let messages = [];
    }

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