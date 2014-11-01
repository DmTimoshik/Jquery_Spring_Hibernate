$(document).ready(function() {
	$('#addUserShow').hide();
	$('#editUserShow').hide();
	cliker();
});

function deleteUser(login) {
	if (confirm("Are you sure ?")) {
		$.ajax({
			type : "post",
			url : "delete",
			cache : false,
			clearForm : true,
			data : 'login=' + login,
			success : function(response) {
				$("#" + response).hide('slow', function() {
					$(this).remove();
				});
			},
			error : function() {
				alert('Error deleteUser');
			}
		});
	}
}

function addShow() {
	$('#adminShow').hide();
	$('#editUserShow').hide();
	addClear();
	$('#addUserShow').show();
}

function adminShow() {
	$('#addUserShow').hide();
	$("#editUserShow").hide();
	$('#adminShow').show();
}

function editShow(login) {
	$('#adminShow').hide();
	$('#addUserShow').hide();
	addClear();
	$('#editUserShow').show();

	$.ajax({
		type : "post",
		url : "editShow",
		cache : false,
		clearForm : true,
		data : "login=" + login,
		success : function(response) {
			$('#editformUser input[name*=login]').val(response.login);
			$('#editformUser input[name*=password]').val(response.password);
			$('#editformUser input[name*=passwordConfirm]').val(
					response.password);
			$('#editformUser input[name*=email]').val(response.email);
			$('#editformUser input[name*=firstName]').val(response.firstName);
			$('#editformUser input[name*=lastName]').val(response.lastName);
			$('#editformUser input[name*=birthday]').val(response.birthday);
			$('#editformUser option[value=' + response.role + ']').attr(
					'selected', true);
		}
	});
}

function editUser() {
	if (validatorEdit() == "error")
		return false;
	var data = packData("#editformUser");
	$.ajax({
		type : "post",
		url : "editserv",
		cache : false,
		data : data,
		dataType : "text",
		success : function(response) {

			if (response.toString() == "emailError") {
				$("#addformUser #email").css("border-color", "red");
				$('#addformUser #emailError').html("This email already exist")
						.css("color", "red");
			}

			if (response.toString() == "Success") {
				$("#" + data.login).replaceWith(
						"<tr id=\"" + data.login + "\"><td>" + data.login
								+ "</td>" + "<td>" + data.firstName + "</td>"
								+ "<td>" + data.lastName + "</td>" + "<td>"
								+ (113 - new Date(data.birthday).getYear())
								+ "</td>" + "<td>" + data.role + "</td>"
								+ "<td><a href=\"#\" onclick=\"editShow('"
								+ data.login + "')\">Edit   </a>&nbsp;&nbsp;"
								+ "<a href=\"#\"onclick=\"deleteUser('"
								+ data.login + "')\">Delete</a></td></tr>");
				adminShow();
			}
		},
		error : function() {
			alert('Error editUser');
		}
	});
}

function addClear() {

	$("#addformUser input[type=text]").val("");
	$("#addformUser input[type=password]").val("");
	$("input").css("border-color", "");
	$("#addformUser span").html("");

	$("#editformUser input[type=text]").val("");
	$("#editformUser input[type=password]").val("");
	$("input").css("border-color", "");
	$("#editformUser span").html("");

}

function addUser() {
	if (validatorAdd() == "error")
		return false;
	var data = packData("#addformUser");
	$.ajax({
		type : "post",
		url : "addserv",
		cache : false,
		data : data,
		dataType : "text",
		success : function(response) {

			if (response.toString() == "loginError") {
				$("#addformUser #login").css("border-color", "red");
				$('#addformUser #loginError').html("This user already exist")
						.css("color", "red");
			}

			if (response.toString() == "emailError") {
				$("#addformUser #email").css("border-color", "red");
				$('#addformUser #emailError').html("This email already exist")
						.css("color", "red");
			}

			if (response.toString() == "Success") {

				$("#userstable").append(
						"<tr id=\"" + data.login + "\"><td>" + data.login
								+ "</td>" + "<td>" + data.firstName + "</td>"
								+ "<td>" + data.lastName + "</td>" + "<td>"
								+ (113 - new Date(data.birthday).getYear())
								+ "</td>" + "<td>" + data.role + "</td>"
								+ "<td><a href=\"#\" onclick=\"editShow('"
								+ data.login + "')\">Edit   </a>&nbsp;&nbsp;"
								+ "<a href=\"#\"onclick=\"deleteUser('"
								+ data.login + "')\">Delete</a></td></tr>");
				adminShow();
			}
		},
		error : function() {
			alert('Error addUser');
		}
	});
}

function packData(form) {
	var editForm = $(form);
	var input = editForm.find("input");
	var select = editForm.find("select");
	input.push(select[0]);
	var data = {};

	for ( var i = 0; i < input.length; i++) {
		var item = $(input[i]);
		data[item.attr("name")] = item.val();
	}
	return data;
}

function validatorAdd() {

	var error = "";
	var login = $("#addformUser input[name=login]").val();
	if (login == null || login == "") {
		$("#addformUser #login").css("border-color", "red");
		$("#addformUser #loginError").html("Login is required").css("color",
				"red");
		error = "error";
	}

	var pass = $("#addformUser input[name=password]").val();
	var passConfirm = $("#addformUser input[name=passwordConfirm]").val();
	if (pass == null || pass == "") {
		$("#addformUser #password").css("border-color", "red");
		$("#addformUser #passwordError").html("Password is required").css(
				"color", "red");
		error = "error";
	}

	if (passConfirm == null || passConfirm == "") {
		$("#addformUser #passwordConfirm").css("border-color", "red");
		$("#addformUser #passwordConfError").html("Password is required").css(
				"color", "red");
		error = "error";
	}

	if (pass != passConfirm) {
		$("#addformUser #passwordConfirm").css("border-color", "red");
		$("#addformUser #passwordConfError").html(
				"Password again password not correct").css("color", "red");
		error = "error";
	}

	var email = $("#addformUser input[name=email]").val();
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	if (email == null || email == "") {
		$("#addformUser #email").css("border-color", "red");
		$("#addformUser #emailError").html("Email is required").css("color",
				"red");
		error = "error";
	} else {
		if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
			$("#email").css("border-color", "red");
			$('#emailError').html(
					"Not correct recording format, example Petrov@gmail.com")
					.css("color", "red");
			error = "error";
		}
	}

	var firstName = $("#addformUser input[name=firstName]").val();
	if (firstName == null || firstName == "") {
		$("#addformUser #firstName").css("border-color", "red");
		$("#addformUser #firstNameError").html("firstName is required").css(
				"color", "red");
		error = "error";
	}

	var lastName = $("#addformUser input[name=lastName]").val();
	if (lastName == null || lastName == "") {
		$("#addformUser #lastName").css("border-color", "red");
		$("#addformUser #lastNameError").html("lastName is required").css(
				"color", "red");
		error = "error";
	}

	var birthday = $("#addformUser input[name=birthday]").val();
	if (birthday == null || birthday == "") {
		$("#addformUser #birthday").css("border-color", "red");
		$("#addformUser #birthdayError").html("birthday is required").css(
				"color", "red");
		error = "error";
	} else {
		var dtRegex = new RegExp(
				/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/);
		if (!dtRegex.test(birthday)) {
			$("#addformUser #birthday").css("border-color", "red");
			$("#addformUser #birthdayError").html(
					"Birthday format not correct, example 2001-02-02").css(
					"color", "red");
			error = "error";
		}
	}

	return error;
}

function validatorEdit() {
	var error = "";
	var pass = $("#editformUser input[name=password]").val();
	var passConfirm = $("#editformUser input[name=passwordConfirm").val();
	if (pass == null || pass == "") {
		$("#editformUser #passwordEdit").css("border-color", "red");
		$("#editformUser #passwordErrorEdit").html("Password is required").css(
				"color", "red");
		error = "error";
	}

	if (passConfirm == null || passConfirm == "") {
		$("#editformUser #passwordConfirmEdit").css("border-color", "red");
		$("#editformUser #passwordConfErrorEdit").html("Password is required")
				.css("color", "red");
		error = "error";
	}

	if (pass != passConfirm) {
		$("#editformUser #passwordConfirmEdit").css("border-color", "red");
		$("#editformUser #passwordConfErrorEdit").html(
				"Password again password not correct").css("color", "red");
		error = "error";
	}

	var email = $("#editformUser input[name=email]").val();
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	if (email == null || email == "") {
		$("#editformUser #emailEdit").css("border-color", "red");
		$("#editformUser #emailErrorEdit").html("Email is required").css(
				"color", "red");
		error = "error";
	} else {
		if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
			$("#emailEdit").css("border-color", "red");
			$('#emailErrorEdit').html(
					"Not correct recording format, example Petrov@gmail.com")
					.css("color", "red");
			error = "error";
		}
	}

	var firstName = $("#editformUser input[name=firstName]").val();
	if (firstName == null || firstName == "") {
		$("#editformUser #firstNameEdit").css("border-color", "red");
		$("#editformUser #firstNameErrorEdit").html("firstName is required")
				.css("color", "red");
		error = "error";
	}

	var lastName = $("#editformUser input[name=lastName]").val();
	if (lastName == null || lastName == "") {
		$("#editformUser #lastNameEdit").css("border-color", "red");
		$("#editformUser #lastNameErrorEdit").html("lastName is required").css(
				"color", "red");
		error = "error";
	}

	var birthday = $("#editformUser input[name=birthday]").val();
	if (birthday == null || birthday == "") {
		$("#editformUser #birthdayEdit").css("border-color", "red");
		$("#editformUser #birthdayErrorEdit").html("birthday is required").css(
				"color", "red");
		error = "error";
	} else {
		var dtRegex = new RegExp(
				/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/);
		if (!dtRegex.test(birthday)) {
			$("#editformUser #birthdayEdit").css("border-color", "red");
			$("#editformUser #birthdayErrorEdit").html(
					"Birthday format not correct, example 2001-02-02").css(
					"color", "red");
			error = "error";
		}
	}

	return error;
}

function cliker() {
	$("#addformUser #login").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#loginError").html("");
	});

	$("#password").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#passwordError").html("");
	});

	$("#passwordConfirm").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#passwordConfError").html("");
	});

	$("#email").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#emailError").html("");
	});

	$("#firstName").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#firstNameError").html("");
	});

	$("#lastName").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#lastNameError").html("");
	});

	$("#birthday").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#birthdayError").html("");
	});

	$("#passwordEdit").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#passwordErrorEdit").html("");
	});

	$("#passwordConfirmEdit").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#passwordConfErrorEdit").html("");
	});

	$("#emailEdit").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#emailErrorEdit").html("");
	});

	$("#firstNameEdit").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#firstNameErrorEdit").html("");
	});

	$("#lastNameEdit").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#lastNameErrorEdit").html("");
	});

	$("#birthdayEdit").focus(function() {
		$(this).css("border-color", "");
		$(this).val("");
		$("#birthdayErrorEdit").html("");
	});
}
