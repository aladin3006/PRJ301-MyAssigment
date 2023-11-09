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
            .wrapper{
                flex: 1.5
            }
            .main {
                flex: 1;
                /*background: #f1f1f1;*/
                min-height: 100vh;
                display: flex;
                justify-content: center;
            }
            img{
                width: 100%;
                background-position: center;
                background-repeat: no-repeat;
                background-size: contain;
            }
            body{
                display: flex;
            }
            form {
                width: 360px;
                min-height: 100px;
                padding: 32px 24px;
                text-align: center;
                background: #fff;
                border-radius: 2px;
                margin: 24px;
                align-self: center;
                /*box-shadow: 2px 2px 5px 0 rgba(51, 62, 73, 0.1);*/
                box-shadow:2px 2px 21px -6px rgba(0,0,0,0.3);
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <img src="image/fpt3.png" />
        </div>
        <div class="main">
            <div class="form-container">
                <form action="login" method="POST">
                    Username: <input type="text" name="username" /> <br/>
                    Password: <input type="password" name="password" /> <br/>
                    <input type="checkbox" name="remember" value="remember" /> Remember me. <br/>
                    <input class="login-button" type="submit" value="Login" />
                </form>
            </div>
        </div>
    </body>
</html>

