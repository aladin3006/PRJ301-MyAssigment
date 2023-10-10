<%-- 
    Document   : login
    Created on : Oct 8, 2023, 11:41:16 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Center the form on the screen */
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            /* Style the form container */
            .form-container {
                text-align: center;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #f5f5f5;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }

            /* Style the login button */
            .login-button {
                background-color: #007bff; /* Blue color */
                color: #fff; /* White text color */
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
            }

            /* Style the login button on hover */
            .login-button:hover {
                background-color: #0056b3; /* Darker blue color */
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <form action="login" method="POST">
                Username: <input type="text" name="username" /> <br/>
                Password: <input type="password" name="password" /> <br/>
                <input type="checkbox" name="remember" value="remember" /> Remember me. <br/>
                <input class="login-button" type="submit" value="Login" />
            </form>
        </div>
    </body>
</html>

