<!-- AJAX script to fetch data from the server -->
$(document).ready(function () {
    // AJAX request to fetch data from the server
    $.ajax({
        url: "http://127.0.0.1:8081",
        type: "GET",
        dataType: "json",
        success: function (data) {
            // Function to generate table rows dynamically
            function generateTableRow(feedbackUser) {
                return `
                        <tr>
                            <td>${feedbackUser.id}</td>
                            <td>${feedbackUser.chat_record_id}</td>
                            <td>${feedbackUser.first_name}</td>
                            <td>${feedbackUser.last_name}</td>
                            <td>${feedbackUser.email}</td>
                            <td>${feedbackUser.satisfaction}</td>
                            <td>${feedbackUser.questions}</td>
                            <td>${feedbackUser.created_at}</td>
                            <td style="width: 300px;">
                                <a type="button" class="btn btn-info btn-xs" href="/modify/${feedbackUser.id}" style="background-color:#27a844;color:white;">Modify</a>&nbsp;
                                <a type="button" class="btn btn-danger btn-xs" href="/delete/${feedbackUser.id}">Delete</a>
                            </td>
                        </tr>`;
            }

            // Iterate through the received data and generate table rows
            const tbody = $("#feedbackUserTable tbody");
            data.forEach(user => {
                tbody.append(generateTableRow(user));
            });
        },
        error: function (error) {
            console.log("Error fetching data:", error);
        }
    });
});


