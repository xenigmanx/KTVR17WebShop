<%-- 
    Document   : listProducts
    Created on : Oct 3, 2018, 10:46:46 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список товаров</title>
    </head>
    <body>
        <h1>Список товаров:</h1>
        <ul>
            <c:forEach var="product" items="${listProducts}">
                <li>${product.id}${product.name}:${product.count}шт. цена: ${product.price} евро</li> 
                
                    <a href=deleteProduct?deleteProductId=">Убрать товар</a>
                
                  </li>
            </c:forEach>
        </ul>
    </body>
</html>
