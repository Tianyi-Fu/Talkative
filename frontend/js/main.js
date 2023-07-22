// JavaScript Document

$(document).ready(function () {
	"use strict"

	//SELECT2
	$('#like').select2({
		placeholder: "Select liking",
		minimumResultsForSearch: -1
	});



	//FETCH DATA ON REVIEW SECTION - STEP-4
	$('#fname').change(function () {
		$('#summaryfname').text($(this).val());
	});

	$('#lname').change(function () {
		$('#summarylname').text($(this).val());
	});

	$('#pnumber').change(function () {
		$('#summarypnumber').text($(this).val());
	});

	$('#email').change(function () {
		$('#summaryemail').text($(this).val());
	});

	$('#message1').change(function () {
		$('#summarymessage1').text($(this).val());
	});

	$('input[name="service"]').change(function () {
		$('#summaryservice').text($(this).val());
	});

	$('#message2').change(function () {
		$('#summarymessage2').text($(this).val());
	});

	$('input[name="ref"]').change(function () {
		$('#summaryref').text($(this).val());
	});
	
	$('input[name="recomm"]').change(function () {
		$('#summaryrecomm').text($(this).val());
	});

	$('#like').change(function () {
		$('#summarylike').text($(this).val());
	});

});	
//DOCUMENT END//