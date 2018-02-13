
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Customers</title>
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
</head>
<body>
<section>
<div class="pull-right" style="padding-right:50px">
<a href="<c:url value="/logout" />" >Logout</a>
</div>
</section>

<section>
<div class="jumbotron">
<div class="container">
<h1> Customers</h1>
<p> All our precious customers </p>
</div>
</div>
</section>

<section class="container">
<div class="row">
<c:forEach items="${customer }" var="customer">
<div class="col-sm-6 col-md-3">
<div class="thumbnail">
<div class="caption">
<h3>${customer.name }</h3>
<p>${customer.address }</p>
<p>${product.unitPrice }</p>
<p>${customer.name } has placed ${customer.noOfOrdersMade } orders till date.</p>
</div>
</div>

</div>
</c:forEach>
</div>
</section>
</body>
</html>