$(document).ready(function () {
    let baseUrl = 'http://127.0.0.1:8081';

    // EventListeners
    $("#submitInfoBtn").click(function () {
        let firstName = document.getElementById("firstName").value
        let lastName = document.getElementById("lastName").value
        let email = document.getElementById("inputEmail").value
        let regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/

        if (firstName.trim().length <= 0) {
            alert("First Name Is Null")
        } else if (lastName.trim().length <= 0) {
            alert("Last Name Is Null")
        } else if (email.trim().length <= 0) {
            alert("email Is Null")
        } else if (!regEmail.test(email)) {
            alert("email result is false")
        } else {
            saveCustomerInfo(firstName, lastName, email);
        }

        $('.modal-content').empty();
        $('.modal-content').append('<div class="modal-body" style="margin-top: 50px"><h3>Thank you for your feedback!</h3></div>');

        setTimeout(() => {
            localStorage.clear();
            window.location.reload();
        }, 2000)
    })

    // Functions
    function saveCustomerInfo(firstName, lastName, email) {
        let chatRecordId = localStorage.getItem("chatRecordId")

        let json = {
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "chatRecordIdList": ["1"],
            "chatRecordId": chatRecordId
        };

        console.log(json)

        $.ajax({
            url: baseUrl + '/create-info/' + localStorage.getItem("agentName"),
            method: 'post',
            contentType: "application/json", // Set the request header to JSON format.
            data: JSON.stringify(json),
            success: function (data) {
                if (data.code == 500) {
                    return alert(data.message);
                } else {
                    return "success";
                }
            }
        })
    }
})