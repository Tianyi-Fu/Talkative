$(document).ready(() => {
    setTimeout(() => {
        let shadow = document.querySelector("#talkative-engage").shadowRoot

        // Track the changes in the chat widget
        let observer = new MutationObserver(function (m) {
            if (shadow.querySelector("button[title='End Interaction']")) {

                let observer = new MutationObserver((m) => {
                    if (shadow.querySelector("button[title='Finish']")) {
                        console.log('found Finish2')

                        let conversation = shadow.querySelector(".chat-thread-container").childNodes;
                        let conversationArr = Array.from(conversation);
                        let agentName = "flex pt-2 justify-start text-left";
                        let agentMsg = "flex flex-wrap space-y-2 justify-start pr-12";
                        let customerName = "flex pt-2 justify-end text-right";
                        let customerMsg = "flex flex-wrap space-y-2 justify-end pl-12";

                        let conversationObj = [];
                        let currentPair = {};
                        let sender_agent;

                        conversationArr.forEach((child) => {
                            let className = child.className;
                            let textContent = child.lastElementChild?.innerText || ""; // Get innerText safely

                            if (className === agentName) {
                                currentPair.agentName = textContent;
                                sender_agent = textContent;
                            } else if (className === agentMsg) {
                                if (!currentPair.agentName) {
                                    conversationObj.findLastIndex((obj) => currentPair.agentName = obj.agentName)
                                }
                                currentPair.agentMsg = textContent;
                                conversationObj.push(currentPair);
                                currentPair = {};
                            } else if (className === customerName) {
                                currentPair.customerName = textContent;
                            } else if (className === customerMsg) {
                                if (!currentPair.customerName) {
                                    conversationObj.findLastIndex((obj) => currentPair.customerName = obj.customerName)
                                }
                                currentPair.customerMsg = textContent;
                                conversationObj.push(currentPair);
                                currentPair = {};
                            }
                        });

                        let newConversation = [];
                        conversationObj.map((item) => {
                            newConversation.push({[item.agentName || item.customerName]: item.agentMsg || item.customerMsg});
                        })
                        // console.log(newConversation);

                        $(shadow.querySelector("button[title='Finish']")).click(() => {
                            createTranscriptObject({"agent_name": sender_agent, "transcript": newConversation});
                        })

                        // Detect when user closes browser
                        $(window).on("beforeunload", () => {
                            alert('test')
                            // Hide Talkative widget
                            document.querySelector("#talkative-engage").style.display = "none";
                            loadModalContent();
                            createTranscriptObject({"agent_name": sender_agent, "transcript": newConversation});
                        })
                    }
                });
                observer.observe(shadow, {attributes: true, childList: true, subtree: true});
            }
        });

        observer.observe(shadow, {childList: true});
    }, 3000)

    function createTranscriptObject(transcriptObj) {
        let id = Date.now();
        sendTranscript(id, {...transcriptObj, "chatRecordId": id}, true);
    }
})