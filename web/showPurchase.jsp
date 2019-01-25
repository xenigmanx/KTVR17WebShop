<%-- 
    Document   : library
    Created on : Oct 5, 2018, 9:39:52 AM
    Author     : Melnikov
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Общая информация</title>
    </head>
    <body>
        <h1>Общая Информация</h1>
        <form action="newPurchase" method="POST" name="form1">
            <h2>Список Продуктов</h2>
            <select name="selectedProduct">
                <c:forEach var="product" items="${listProducts}">
                    <option value="${product.id}">${product.name} ${product.price} евро</option>
                </c:forEach>
            </select>
            <br>
            <h2>Сколько купить товара</h2>
            <input type="text" name="quantity">
            <br>
            <br>
            <button type="submit">Выдать покупку</button>
        </form>
        <ul>
            <c:forEach var="bayPurchase" items="${listPurchase}">
            <li>${bayPurchase.product.name}:${bayPurchase.product.count}шт. цена: ${bayPurchase.product.price} евро 
            </c:forEach>
        </ul>
    </body>
</html>
