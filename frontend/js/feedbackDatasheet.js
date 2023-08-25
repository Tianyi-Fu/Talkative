$(document).ready(function () {
//    let baseUrl = 'http://localhost';

    $("#datepicker").datepicker({
        dateFormat: "dd/mm/yy", // Setting the date format
        changeMonth: true, // Allow month selection
        changeYear: true, // Allow year selection
        yearRange: "1900:2023" // Setting the year range
    });
    $("#datepicker2").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        yearRange: "1900:2023"
    });
    load(page, size, "", "", "", "", "")

});

var currentPage = 1;
var page = 1;
var size = 8;
var total;
var pages;
var all_total;
var participationRateValue;

function load(page, size, chatRecordID, satisfaction, agentName, datepicker, datepicker2) {
    // AJAX request to fetch data from the server
    var json = {
        "page": page,
        "size": size,
        "chatRecordID": chatRecordID,
        "satisfaction": satisfaction,
        "agentName": agentName,
        "beginTime": datepicker,
        "endTime": datepicker2
    }
    $.ajax({
        url: baseUrl + "/rc/list",
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
                                  <a type="button" class="btn btn-info btn-xs" 
                                  onclick="showPopup3(\'${feedbackUser.feedback_record_id}'\,\'${feedbackUser.first_name}'\,\'${feedbackUser.last_name}'\,\'${feedbackUser.aa_email}'\,\'${feedbackUser.agent_name}'\)" style="background-color:#27a844;color:white;">Modify</a>&nbsp;
                                <a type="button" class="btn btn-danger btn-xs" onclick="dele(${feedbackUser.feedback_record_id})">Delete</a>
                                <a type="button" class="btn btn-danger btn-xs" onclick="analysis(${feedbackUser.feedback_record_id})">Analysis</a>
                            </td>
                        </tr>`;
            }

            // Iterate through the received data and generate table rows
            const tbody = $("#feedbackUserTable tbody");
            data.data.list.forEach(user => {
                if (1 == user.answer || 2 == user.answer) {
                    user.answer = "<div><input style=\"background-color: red;\" class='answer'  placeholder=\"unsatisfied\" disabled/></div>"
                } else if (3 == user.answer || 4 == user.answer) {
                    user.answer = "<div><input style=\"background-color: yellow;\" class='answer'  placeholder=\"neutral\" disabled/></div>"
                } else {
                    user.answer = "<div><input style=\"background-color: green;\" class='answer' placeholder=\"satisfied\" disabled/></div>"
                }

                user.questions = "<div><input type='button' value='view' placeholder='test' onclick='showPopup(" + user.feedback_record_id + ")'/></div>"
                user.transcript = "<div><input type='button' value='view' placeholder='test' onclick='showPopup2(" + user.feedback_record_id + ")'/></div>"
                user.aa_email = user.email
                tbody.append(generateTableRow(user));

            });
            total = data.data.total
            pages = data.data.page
            all_total = data.data.all_total
            participationRateValue = (total / all_total) * 100;
            // Round the participation rate to two decimal places
            var formattedParticipationRate = participationRateValue.toFixed(2);

            document.getElementById("totalPages").value = data.data.page
            document.getElementById("totalCount").value = data.data.total
            document.getElementById("totalInteraction").value = data.data.all_total
            document.getElementById("participationRate").textContent = formattedParticipationRate + "%";

            const ctx = document.getElementById('peityChart');
            const dataValues = [all_total - total, total];

            const chartData = {
                labels: [
                    'No feedback',
                    'Feedback'
                ],
                datasets: [{
                    label: 'Amount',
                    data: dataValues,
                    backgroundColor: [
                        'rgb(186, 197, 204)',
                        'rgb(54, 162, 235)'
                    ],
                    hoverOffset: 4
                }]
            };

            const chartConfig = {
                type: 'pie',
                data: chartData,
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {}
                }
            };

            const peityChart = new Chart(ctx, chartConfig);


            // 统计满意度类别的数量
            const ctxs = document.getElementById('satisfactionChart').getContext('2d');

            const unsatisfiedCount = data.data.unsatisfiedCount;
            const neutralCount = data.data.neutralCount;
            const satisfiedCount = data.data.satisfiedCount;

            const chartSData = {
                labels: ['Unsatisfied', 'Neutral', 'Satisfied'],
                datasets: [{
                    label: 'Satisfaction Levels',
                    data: [unsatisfiedCount, neutralCount, satisfiedCount],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.7)',
                        'rgba(255, 206, 86, 0.7)',
                        'rgba(75, 192, 192, 0.7)'
                    ],
                    borderColor: [
                        'rgb(255, 99, 132)',
                        'rgb(255, 206, 86)',
                        'rgb(75, 192, 192)'
                    ],
                    borderWidth: 1
                }]
            };

            const chartSConfig = {
                type: 'bar',
                data: chartSData,
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            ticks: {
                                stepSize: 1
                            }
                        },
                        x: {
                            grid: {
                                display: false
                            }
                        }
                    },
                    plugins: {
                        legend: {
                            display: false
                        }
                    }
                }
            };

            const satisfactionChart = new Chart(ctxs, chartSConfig);


        },
        error: function (error) {
            console.log("Error fetching data:", error);
        }

    });

}

//next
function down() {
    var chatRecordID = document.getElementById("chatRecordID").value
    var satisfaction = document.getElementById("satisfaction").value
    var agentName = document.getElementById("agentName").value
    var datepicker = getData(document.getElementById("datepicker").value)
    var datepicker2 = getData(document.getElementById("datepicker2").value)
    if (currentPage < pages) {
        load(currentPage + 1, size, chatRecordID, satisfaction, agentName, datepicker, datepicker2)
        let myul = document.getElementById('feedbackUserTableTbody');
        while (myul.hasChildNodes()) {
            myul.removeChild(myul.firstChild);
        }
        currentPage++
    }
}

function up() {
    var chatRecordID = document.getElementById("chatRecordID").value
    var satisfaction = document.getElementById("satisfaction").value
    var agentName = document.getElementById("agentName").value
    var datepicker = getData(document.getElementById("datepicker").value)
    var datepicker2 = getData(document.getElementById("datepicker2").value)
    if (currentPage > 1) {
        load(currentPage - 1, size, chatRecordID, satisfaction, agentName, datepicker, datepicker2)
        let myul = document.getElementById('feedbackUserTableTbody');
        while (myul.hasChildNodes()) {
            myul.removeChild(myul.firstChild);
        }
        currentPage--
    }
}

function lastb() {
    var chatRecordID = document.getElementById("chatRecordID").value
    var satisfaction = document.getElementById("satisfaction").value
    var agentName = document.getElementById("agentName").value
    var datepicker = getData(document.getElementById("datepicker").value)
    var datepicker2 = getData(document.getElementById("datepicker2").value)
    load(pages, size, chatRecordID, satisfaction, agentName, datepicker, datepicker2)
    let myul = document.getElementById('feedbackUserTableTbody');
    while (myul.hasChildNodes()) {
        myul.removeChild(myul.firstChild);
    }
    currentPage = pages
}


function buttonSelect() {
    var chatRecordID = document.getElementById("chatRecordID").value
    var satisfaction = document.getElementById("satisfaction").value
    var agentName = document.getElementById("agentName").value
    var datepicker = getData(document.getElementById("datepicker").value)
    var datepicker2 = getData(document.getElementById("datepicker2").value)
    load(1, size, chatRecordID, satisfaction, agentName, datepicker, datepicker2)
    let myul = document.getElementById('feedbackUserTableTbody');
    while (myul.hasChildNodes()) {
        myul.removeChild(myul.firstChild);
    }
}

function reset() {
    document.getElementById("chatRecordID").value = ''
    document.getElementById("satisfaction").value = ''
    document.getElementById("agentName").value = ''
    document.getElementById("datepicker").value = ''
    document.getElementById("datepicker2").value = ''
    var chatRecordID = document.getElementById("chatRecordID").value
    var satisfaction = document.getElementById("satisfaction").value
    var agentName = document.getElementById("agentName").value
    var datepicker = getData(document.getElementById("datepicker").value)
    var datepicker2 = getData(document.getElementById("datepicker2").value)
    let myul = document.getElementById('feedbackUserTableTbody');
    while (myul.hasChildNodes()) {
        myul.removeChild(myul.firstChild);
    }
    load(currentPage, size, chatRecordID, satisfaction, agentName, datepicker, datepicker2)
}

function getData(inputDate) {
    if (inputDate == '') {
        return null
    }
// Date string entered

// Using the split () method to split the date string into arrays of months, days, and years
    const dateArray = inputDate.split('/');

// Obtain month, date, and year
    const month = dateArray[1];
    const day = dateArray[0];
    const year = dateArray[2];

// Build a new date string
    const convertedDate = `${year}-${month}-${day}`;

    return convertedDate

}

function dele(feedbackRecordId) {
    var json = {"feedbackRecordId": feedbackRecordId}
    $.ajax({
        url: baseUrl + "/all/del",
        data: JSON.stringify(json),
        method: 'post',
        contentType: "application/json", // Set the request header to JSON format.
        success: function (data) {
            buttonSelect()
        },
        error: function (error) {
            console.log("Error fetching data:", error);
        }

    });
}

function onsubmitEdit() {
    let firstName = document.getElementById("firstName").value
    let lastName = document.getElementById("lastName").value
    let email = document.getElementById("email").value
    var agentName = document.getElementById("agentNames").value
    var feedbackRecordId = document.getElementById("feedbackRecordId").value

    let regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/

    if (firstName.trim().length <= 0) {
        alert("First Name Is Null")
        return false
    } else if (lastName.trim().length <= 0) {
        alert("Last Name Is Null")
        return false
    } else if (email.trim().length <= 0) {
        alert("email Is Null")
        return false
    } else if (!regEmail.test(email)) {
        alert("email result is false")
        return false
    } else if (agentName.trim().length <= 0) {
        alert("agentName result is false")
        return false
    } else {
        var json = {
            "feedbackRecordId": feedbackRecordId,
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "agentName": agentName
        }
        $.ajax({
            url: baseUrl + "/all/upd",
            data: JSON.stringify(json),
            method: 'post',
            contentType: "application/json", // Set the request header to JSON format.
            success: function (data) {
                hidePopup3()
                buttonSelect()
            },
            error: function (error) {
                console.log("Error fetching data:", error);
            }

        });
    }
}