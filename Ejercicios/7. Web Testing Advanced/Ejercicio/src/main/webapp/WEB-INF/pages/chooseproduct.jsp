<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul id="products">
	<c:forEach var="product" items="${products}">
		<li class="product">
			<div class="productInfo">
				<h3>${product.name}</h3>
				<img class="product-image" src="/resources/images/products/thumbnails/${product.imageName}" alt="${product.name}" />
				<p class="description">${product.description}</p>
			</div>
			<div>
				<p class="price">${product.price}</p>

				<a href="/placeorder/${product.id}" class="order-button">Order Now</a>
			</div>
		</li>
	</c:forEach>
</ul>