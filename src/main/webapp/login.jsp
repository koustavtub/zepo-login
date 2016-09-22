<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Zepo Login</title>

<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<link rel="stylesheet" href="${contextPath}/resources/css/normalize.css">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">




<script src="${contextPath}/resources/js/prefixfree.min.js"></script>


</head>

<body>

	<div class="wrapper">
		
		
		<section>
			<form class="login hidden" name='loginForm' id='loginForm' method="post" action="${contextPath}/login">
				<p class="title">Log In</p>
				<input type="email" name="email" placeholder="Your email Id " autofocus required /> <i class="fa fa-user"></i> 
				<input name="password" type="password" placeholder="Password" required /> <i class="fa fa-key"></i> 
				<a href="${contextPath}/signup">Sign Up for a New Account!</a>
				<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> Commenting out as CSRF is disabled--%>
				<button>
					<i class="spinner" id="exclaim"></i>
 					<span class="state">Log in</span>
				</button>
			</form>
		</section>
		<footer>
			<a href="javascript:void(0)">Fun Project By Koustav...</a>
		</footer>
	</div>
	
	<script src="//code.jquery.com/jquery-latest.js"></script>
	<script src="${contextPath}/resources/js/index.js"></script>
	<script>
	var msg='${message}';
	var errorMsg='${error}';
	
		
	</script>



</body>
</html>

