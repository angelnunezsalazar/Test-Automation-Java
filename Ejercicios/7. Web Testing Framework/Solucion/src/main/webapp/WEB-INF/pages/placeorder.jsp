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
			<h1>Place Your Order: ${product.name}</h1>

			<form action="<spring:url value='/placeorder'/>" method="POST">
				<fieldset class="no-legend">
					<legend>Place Your Order</legend>
					<img class="product-image order-image"
						src="<spring:url value='/resources/images/products/thumbnails/${product.imageName}'/>"
						alt="${product.name}" />
					<ol>
						<li>
							<label for="email">Your Email Address</label> 
							<input type="text" name="email" id="email" />
						</li>
						<li>
							<label for="address">Shipping Address</label> 
							<textarea name="address" id="email" rows="4" cols="20" ></textarea>
						</li>
						<li class="quantity">
							<label for="quantity">Quantity</label> 
							<input type="text" name="quantity" id="quantity" value="1"/> x 
							<span id="orderPrice">\$${product.price}</span>= 
							<span id="orderTotal"></span>
						</li>
					</ol>
					<input type="hidden" id="productId" name="productId" value="${product.id}" /> 
					<input type="hidden" id="productPrice" name="productPrice" value="${product.price}" />
					<p>
						<input type="submit" value="Place Order" />
					</p>
				</fieldset>
			</form>
		</div>
		<div id="footer">&copy;2013- Fourth Coffee</div>
	</div>
	<script type="text/javascript" src="<spring:url value='/resources/scripts/jquery-1.5.1.min.js'/>"></script>
	<script type="text/javascript">
		$(function() {
			$("#quantity").keyup(function() {
				var total = $(this).val() * $("#productPrice").val();
				$("#orderTotal").text("$" + total.toFixed(2));
			}).keyup();
		});
	</script>
</body>
</html>




