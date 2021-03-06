<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Products</title>
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
<script src="/ShoppingCart/resources/js/controllers.js"></script>
</head>
<body>
<section>
<div class="jumbotron">
<div class="container">
<h1> Products</h1>
</div>
</div>
</section>

<section class="container" ng-app="cartApp">
<div class="row">
<div class="col-md-5">
<img src="<c:url value="/img/${product.productId }.jpg" > </c:url>" alt="image" style="width:100%"/>
</div>
<div class="col-md-5">

<h3>${product.name }</h3>
<p>${product.description }</p>
<p>
<strong>Item Code: </strong>${product.productId }
</p>
<p>
<strong>Manufacturer</strong>: ${product.manufacturer }
</p>
<p><strong>Category:</strong> ${product.category }
</p>
<p><strong>Available </strong>${product.unitsInStock } <strong>units in stock</strong></p>

<h4>$${product.unitPrice } USD</h4>
<a href="<spring:url value="/market/products" />" class="btn btn-default">
<span class="glyphicon-hand-left glyphicon"></span> back
</a>
<br><br>
<p ng-controller="cartCtrl">
<a href="#" class="btn btn-warning btn-large" 
ng-click="addToCart('${product.productId }')">
<span class="glyphicon-shopping-cart 
glyphicon"></span> Order Now
</a>
<br><br><br>
</p>

<a href="<spring:url value="/cart"/>" class="btn btn-default">
<span class="glyphicon-hand-right glyphicon"></span>View Cart
</a>
</div>
</div>
</section>
</body>
</html>