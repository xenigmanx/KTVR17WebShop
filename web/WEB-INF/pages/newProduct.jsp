<%-- 
    Document   : newProduct
    Created on : Sep 26, 2018, 10:50:01 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый товар</title>
    </head>
    <body>
        <h1>Добавляем  новый товар</h1>
        ${info}
        <form action="addProduct" method="POST" name="form1" id="_form1">
             Название товара:<br>
            <input type="text" name="nameProduct"><br>
             колличество Товара:<br>
            <input type="text" name="count"><br>
             Цена:<br>
            <input type="text" name="price"><br>
            <br>
            <input type="submit" value="Добавить">
        </form><br>
 
  </body>
</html>       
   
