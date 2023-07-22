// JavaScript Document

//Wizard Init
//FOR WIZARD AND VALIDATIONS

$(document).ready(function () {
	'use strict';

	//defining form
	var form = $("#example-form").show();


	form.validate({
		ignore: ".ignore",
		focusInvalid: true,

		errorPlacement: function errorPlacement(error, element) {
			return false;
		},

		//error message block
		showErrors: function (errorMap, errorList) {
			$("#wizard").find("input").each(function () {
				$(this).removeClass("error");
			});
			$(".errorblock").html("");
			if (errorList.length) {
				$(".errorblock").html(errorList[0]['message']);
				$(errorList[0]['element']).addClass("error");
			}
		},

		//rules for validation
		rules: {
			confirm: {
				equalTo: "#password"
			},
			recomm: {
				required: true,
			},
			ref: {
				required: true,
			},
			service: {
				required: true,
			},
			like: {
				required: true,
			},

		},

		//message for validation
		messages: {
			"fname": {
				required: "Firstname required"
			},
			"lname": {
				required: "Lastname required"
			},
			"pnumber": {
				required: "Phone No. required",
			},
			"email": {
				required: "Email is required",
				email: "Invalid email"
			},
			"like": {
				required: "Select likings",
			},
			"service": {
				required: "Please rate our service",
			},
			"recomm": {
				required: "Select recommendation"
			},
			"ref": {
				required: "Select reference"
			},
		},

	});

	//wizard steps
	form.children("div").steps({
		headerTag: "h3",
		bodyTag: "section",
		titleTemplate: '<span class="uneek-indicator"></span><h6 class="sr-only">#title#</h6>',
		transitionEffect: "fade",
        enableAllSteps: true,
        transitionEffectSpeed: 500,
		
		//labels
		labels: {
			finish: "Submit",
			next: "Next",
			previous: "Back",
		},
	
		//while changing step
		onStepChanging: function (event, currentIndex, newIndex) {

			// Allways allow previous action even if the current form is not valid!
			if (currentIndex > newIndex) {
				return true;
			}
	
			form.validate().settings.ignore = ":disabled,:hidden";
			return form.valid();
			
		},
		
		//while finishing
		onFinishing: function (event, currentIndex) {
			return form.valid();
		},
		
		//when finished
		onFinished: function (event, currentIndex) {
			
			$("#example-form").on("submit", function (e) {
				//send data through ajax
				e.preventDefault();
				return false;
			});

			//Ajax Example
			var field1 = $('#fname').val();		//taking values from firstname input
			var field2 = $('#lname').val();
			var field3 = $('#email').val();
			var field4 = $('#pnumber').val();
			var field5 = $('#message1').val();
			var field6 = $('input[name="service"]:checked').val();
			var field7 = $('#message2').val();
			var field8 = $('input[name="ref"]:checked').val();
			var field9 = $('input[name="recomm"]:checked').val();
			var field10 = $('#like').val();
			var field11 = $('input[name="offers"]:checked').val();

			$.ajax({
				//Google form url : check documentation
				url: "URL-HERE/formResponse?",
				data: {
					//for getting entry.number : check documentation
					"entry.1695251061": field1,		//take values from field1 and send it to entry.760639197
					"entry.1074770898": field2,
					"entry.17574498": field3,
					"entry.502197350": field4,
					"entry.2118709941": field5,
					"entry.677174314": field6,
					"entry.973519115": field7,
					"entry.87130071": field8,
					"entry.539199472": field9,
					"entry.1688454063": field10,
					"entry.521097916": field11,
				},
				type: "POST",
				dataType: "xml",
				success: function (data) {},

			});
			
			//On submit
			alert("Form Submitted!");
			$('#example-form')[0].reset();
			$("#wizard").steps('reset');
			
		}

	});


})