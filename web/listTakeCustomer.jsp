<%-- 
    Document   : listTakeCustomer.jsp
    Created on : Oct 5, 2018, 10:38:13 AM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список выданных продуктов</title>
    </head>
    <body>
        <h1>Список выданных продуктов</h1>
        ${info}
            <ul>
                <c:forEach var="purchase" items="${takeProduct}">
                    <li>Продукт: ${purchase.product.name} взят: ${purchase.customer.name} ${purchase.customer.surname}
                        <a href="returnProduct?purchaseId=${purchase.id}"> Вернуть продукт</a></li>
                </c:forEach> 
            </ul>
    </body>
</html>
