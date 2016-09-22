<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Zepo SignUp</title>

<link rel="stylesheet" type="text/css"	href="${contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" type="text/css"	href="${contextPath}/resources/css/component.css" />

<link rel="stylesheet" type="text/css"	href="${contextPath}/resources/css/sweetalert.css" />


<script src="${contextPath}/resources/js/modernizr.custom.js"></script>



</head>

<body>

	<div class="container">
		<section>
			<form:form method="POST" modelAttribute="user" class="simform"	autocomplete="off">
				<div class="simform-inner">
					<ol class="questions">
						<li><span><label for="name">Hey There! What's
									your Name?</label></span> <spring:bind path="name">
								<form:input type="text" path="name"  placeholder="The Rockstar" ></form:input>
							</spring:bind></li>
							
							<li><span><label for="company">Where do you work?</label></span> <spring:bind path="name">
								<form:input type="text" path="company"  placeholder="Your Company..Zepo?" ></form:input>
							</spring:bind></li>
							
						<li><span><label for="email">What's your email
									ID?</label></span> <spring:bind path="email">
								<form:input type="email" path="email"
									placeholder="This will be your Username.." ></form:input>
							</spring:bind></li>

						<li><span><label for="password">Password Please!</label></span>
							<spring:bind path="password">
								<form:input type="password" path="password"></form:input>
							</spring:bind></li>
							
						
			
					</ol>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
					<button class="submit" type="submit">Submit</button>
					<div class="controls">
						<button class="next"></button>
						<div class="progress"></div>
						<span class="number"> <span class="number-current"></span>
							<span class="number-total"></span>
						</span> <span class="error-message"></span>
					</div>					<!-- / controls -->
				</div>				<!-- /simform-inner -->
				<span class="final-message"></span>
				
			</form:form>
		</section>
		
		
		<footer>
			<a href="javascript:void(0)">Fun Project By Koustav...</a>
		</footer>

	</div>	<!-- /container -->
	
	<script src="//code.jquery.com/jquery-latest.js"></script>
	<script src="${contextPath}/resources/js/classie.js"></script>
	<script src="${contextPath}/resources/js/stepsForm.js"></script>
	<script src="${contextPath}/resources/js/sweetalert.min.js"></script>
	
	<script>
		var theForm = document.getElementById('user');
		var emailToCheck = document.getElementById('email').value;
		var globalValid=true;
		$('#email').blur(function(e){
			checkExists($('#email').val());
					}
				);
		
		 
		 new stepsForm(theForm, {
			onSubmit : function(form) {
							
				// hide form
				classie.addClass(theForm.querySelector('.simform-inner'),
						'hide');

				

				// let's just simulate something...
				var messageEl = theForm.querySelector('.final-message');
				messageEl.innerHTML = 'Thank you! <br> Validating &amp; Logging You In..';
				classie.addClass(messageEl, 'show');
				setTimeout(function(){/*Random Timer For Special Effects on UI*/},1000);
				if(globalValid)
					{
					form.submit();
					}
				else{
					swal({
						  title: "Retry?",
						  text: "Yo can only try again after Re-setting the form!",
						  type: "warning",
						  confirmButtonColor: "#DD6B55",
						  confirmButtonText: "Yes,Reset!",
						  closeOnConfirm: false
						},
						function(){
						  window.location.reload();
						});
					}
				
			} 
			 
		 });


		function checkExists(email)
		{
			
		var invalidated = false;

			var request = new XMLHttpRequest();
			request.open('GET', '/checkExists?email=' + email, true);

			request.onload = function(that) {
				if (request.status == 200) {

					if ('EXISTS' === request.responseText) {
						swal("User Exists", "Sorry", "error");
						globalValid = false;
					}
					else{//Required in case form input is changed
						globalValid = true;
						}
				} else {//For Server Errors
					
					swal("Error:" + request.status, "Server Error", "error");
				}
			};

			request.onerror = function() {//For Connect Exceptions
				swal("Server Unreachable", "Try Again Later", "error");
			};

			request.send();

		}
	</script>

</body>
</html>
