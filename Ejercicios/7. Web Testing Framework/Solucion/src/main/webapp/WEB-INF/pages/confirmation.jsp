<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Fourth Coffee</title>
<link href="<spring:url value='/resources/site.css'/>" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="page">
		<div id="header">
			<p class="site-title">
				<a href="<spring:url value='/'/>">Fourth Coffee</a>
			</p>
			<ul id="menu">
				<li><a href="<spring:url value='/'/>">Home</a></li>
			</ul>
		</div>
		<div id="body">
			<div id="delivery">
				<div>
					<h1>Order Confirmation</h1>
					<div id="order-success" class="message">
						<h2>Thank you for your order!</h2>
						<p>We are processing your order and have sent a confirmation
							email. Thank you for your business and enjoy!</p>
					</div>
					<p>
						<a class="back" href="<spring:url value='/'/>">Continue Shopping</a>
					</p>
				</div>
			</div>
		</div>
		<div id="footer">&copy;2013- Fourth Coffee</div>
	</div>
</body>
</html>