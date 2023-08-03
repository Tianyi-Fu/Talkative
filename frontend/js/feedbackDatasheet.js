<!-- AJAX script to fetch data from the server -->
$(document).ready(function () {

    // AJAX request to fetch data from the server
    var json = {"page": 1, "size": 100}
    $.ajax({
        url: "http://127.0.0.1:8081/rc/list",
        data: JSON.stringify(json),
        method: 'post',
        contentType: "application/json", // Set the request header to JSON format.
        success: function (data) {
            // Function to generate table rows dynamically
            function generateTableRow(feedbackUser) {
                return `
                        <tr>
                            <td>${feedbackUser.feedback_record_id}</td>
                            <td>${feedbackUser.chat_record_id}</td>
                            <td>${feedbackUser.first_name}</td>
                            <td>${feedbackUser.last_name}</td>
                            <td>${feedbackUser.email}</td>
                            <td>${feedbackUser.answer}</td>
                            <td>${feedbackUser.agent_name}</td>
                            <td>${feedbackUser.questions}</td>
                            <td>${feedbackUser.transcript}</td>
                            <td>${feedbackUser.created_at}</td>
                            <td style="width: 300px;">
                                <a type="button" class="btn btn-info btn-xs" href="/modify/${feedbackUser.feedback_record_id}" style="background-color:#27a844;color:white;">Modify</a>&nbsp;
                                <a type="button" class="btn btn-danger btn-xs" href="/delete/${feedbackUser.feedback_record_id}">Delete</a>
                            </td>
                        </tr>`;
            }


            // Iterate through the received data and generate table rows
            const tbody = $("#feedbackUserTable tbody");
            console.log(data)
            data.data.list.forEach(user => {
                if (1 == user.answer || 2 == user.answer) {
                    user.answer = "<div><input style=\"background-color: red;\"  placeholder=\"unsatisfied\" disabled/></div>"
                } else if (2 == user.answer || 4 == user.answer) {
                    user.answer = "<div><input style=\"background-color: yellow;\"  placeholder=\"neutral\" disabled/></div>"
                } else {
                    user.answer = "<div><input style=\"background-color: green;\"  placeholder=\"satisfied\" disabled/></div>"
                }

                user.questions = "<div><input type='button' value='view' placeholder='test' onclick='showPopup(" + user.feedback_record_id + ")'/></div>"
                user.transcript = "<div><input type='button' value='view' placeholder='test' onclick='showPopup(" + user.feedback_record_id + ")'/></div>"

                tbody.append(generateTableRow(user));
            });

        },
        error: function (error) {
            console.log("Error fetching data:", error);
        }

    });


});


