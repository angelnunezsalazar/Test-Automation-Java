<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<ul id="products">
				<c:forEach var="product" items="${products}">
					<li class="product">
						<div class="productInfo">
							<h3>${product.name}</h3>
							<img class="product-image"
								src="resources/images/products/thumbnails/${product.imageName}"
								alt="${product.name}" />
							<p class="description">${product.description}</p>
						</div>
						<div>
							<p class="price">\$${product.price}</p>

							<a href="placeorder/${product.id}" class="order-button">Order
								Now</a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div id="footer">&copy;2013- Fourth Coffee</div>
	</div>
</body>
</html>