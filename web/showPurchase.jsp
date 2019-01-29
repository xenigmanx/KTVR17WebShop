<%-- 
    Document   : showPurchase
    Created on : Oct 5, 2018, 9:39:52 AM
    Author     : pupil
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
                    <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
            <br>
            <h2>Сколько купить товара</h2>
            <input type="text" name="quantity">
            
            <br>
            <br>
            <button type="submit">Выдать покупку</button>
        </form>
        
        <h2>Список товаров в наличии:</h2>
        <ul>
            <c:forEach var="product" items="${listProducts}">
                <li>${product.name}:${product.count}шт. цена: ${product.price} евро</li> 
                
                    
                
                  </li>
            </c:forEach>
        </ul>
        <h2>Список выданных продуктов</h2>
        
		<ul>
                <li>${info}</li>
            <c:forEach var="bayPurchase" items="${listPurchase}">
                <li>${bayPurchase.customer.name} купил: ${bayPurchase.product.name} заплатил: ${bayPurchase.product.price} евро</li>
             
                <li>На счету : ${bayPurchase.customer.money} евро купил ${bayPurchase.product.count} шт.  по цене: ${bayPurchase.product.price} евро</li>
            </c:forEach>
               </ul>
    </ul>

    </body>
</html>
