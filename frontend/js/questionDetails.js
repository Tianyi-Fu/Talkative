<!-- AJAX script to fetch data from the server -->
$(document).ready(function () {
    // AJAX request to fetch data from the server
    $.ajax({
        url: "http://127.0.0.1:8081",
        type: "GET",
        dataType: "json",
        success: function (data) {
            // Function to generate table rows dynamically
            function generateTableRows(questionDetail) {
                let rows = '';

                // Generate a row for ID
                rows += `<tr><th>ID</th><td>${questionDetail.id}</td></tr>`;

                // Generate rows for each question and its answer
                for (let i = 1; i <= 6; i++) {
                    const questionKey = `Question${i}`;
                    const answerKey = `Answer${i}`;
                    rows += `<tr><th>${questionDetail[questionKey]}</th><td>${questionDetail[answerKey]}</td></tr>`;
                }

                // Generate a row for Action
                rows += `<tr><th>Action</th><td style="...">
                            <a type="button" class="btn btn-info btn-xs" href="/modify/${questionDetail.id}" style="...">Modify</a>&nbsp;
                            <a type="button" class="btn btn-danger btn-xs" href="/delete/${questionDetail.id}">Delete</a>
                        </td></tr>`;

                return rows;
            }

            // Get the table body
            const tbody = $("#questionDetailTable tbody");

            // Iterate through the received data and generate table rows
            data.forEach(questionDetail => {
                const tableRows = generateTableRows(questionDetail);
                tbody.append(tableRows);
            });
        },
        error: function (error) {
            console.log("Error fetching data:", error);
        }
    });
});

