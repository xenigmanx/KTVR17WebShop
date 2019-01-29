<%-- 
    Document   : listCustomers
    Created on : Oct 5, 2018, 9:11:16 AM
    Author     : pupil
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список Покупателей</title>
    </head>
    <body>
        <h1>Список Покупателей</h1>
        <ul>
            <c:forEach var="customer" items="${listCustomers}">
                <li>${customer.name} ${customer.surname}</li>
                
            </c:forEach>
        </ul>
    </body>
</html>
