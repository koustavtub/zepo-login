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

<title>Zepo Profile</title>

<link rel="stylesheet" type="text/css"	href="${contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" type="text/css"	href="${contextPath}/resources/css/component.css" />

<script src="${contextPath}/resources/js/modernizr.custom.js"></script>

<script>var userName='';</script>

</head>

<body>

	<div class="container">
		<section class="extra-pad">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" class="simform" autocomplete="off" method="post" action="${contextPath}/logout">
						<!-- /simform-inner -->

				<span class="final-message show">
				
					Welcome <b class="text-orange"> ${userName}</b> ! <br> <br>
					Your Company: <b class="text-blue"> ${userCompany}</b> <br>
					Your Registered email: <b class="text-blue"> ${userEmail}</b> <br><br>
					Have a Nice Day! <br> 
					<b style="color:#fcfcfc;font-size:100px">&#x263a;</b> <br><br>
					<button class="btn-logout" onclick="document.forms[\'logoutForm\'].submit()">Logout</button>

				</span>
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
				
			</form>
			
			    
    		</c:if>

     
		</section>
		<footer>
			<a href="javascript:void(0)">Fun Project By Koustav...</a>
		</footer>

	</div>	<!-- /container -->
	<script src="${contextPath}/resources/js/classie.js"></script>
	<script src="${contextPath}/resources/js/stepsForm.js"></script>
	

</body>
</html>
 