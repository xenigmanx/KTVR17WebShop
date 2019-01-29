<%-- 
    Document   : newCustomer
    Created on : Sep 26, 2018, 10:50:01 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый покупатель</title>
    </head>
    <body>
        <h1>Добавляем  нового покупателя</h1>
        ${info}
        <form action="addCustomer" method="POST" name="form1" id="_form1">
             Имя:<br>
            <input type="text" name="name"><br>
             Фамилия:<br>
            <input type="text" name="surname"><br>
             Телефон:<br>
            <input type="text" name="phone"><br>
             Город<br>
            <input type="text" name="city"><br>
            <br>
            Деньги:<br>
            <input type="text" name="money"><br>
            <br>
             Login<br>
            <input type="text" name="login"><br>
            <br>
            Password<br>
            <input type="text" name="password"><br>
            <br>
            <input type="submit" value="Добавить">
            
        </form><br>
 </body>
</html>            
        

