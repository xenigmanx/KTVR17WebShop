<%-- 
    Document   : page2
    Created on : Sep 26, 2018, 9:37:07 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Главная</title>
    </head>
    <body>
        <h1>Навигация по нашему магазину</h1>
        ${info}
        <br>
        <a href="showLogin">Войти</a>
        <br>
        <a href="logout">Выйти</a>
        <br>
        <hr>
        <p>Клиентская часть:</p>
        <br>
        <a href="showPurchase">Купить товар</a>
        <br>
        <a href="newCustomer">Новый покупатель</a>
        <br>
        
        <hr>
        <p>Для администратора:</p>
        <a href="newProduct">Новый продукт</a>
        <br>
        <a href="listCustomer">Все покупатели</a>
        <br>
        <a href="listProduct">Все продукты</a>
        <br>
        <a href="editUserRoles">Назначение ролей пользователям</a>
        <br><br>
        <hr>
       
        
    </body>
</html>
    </body>
</html>
