<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Invalid Cart</title>
</head>
<body>
<section>
<div class="jumbotron">
<div class="container">
<h1 class="alert alert-danger">Empty Cart</h1>
</div>
</div>
</section>

<section class="container">
<div class="container">
<p>
<a href="<spring:url value="/market/products" />" class="btn btn-primary">
<span class="glyphicon-hand-left glyphicon"> products</span>
</a>
</p>
</div>
</section>
</body>
</html>