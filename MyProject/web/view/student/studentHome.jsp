<%-- 
    Document   : studentHome
    Created on : Oct 8, 2023, 3:20:03 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Welcome</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: white;
                text-align: center;
                margin: 0;
                padding: 0;
            }

            .welcome {
                display: flex;
                color: white;
                font-size: 26px;
                background-color: orange;
                margin-top: 50px;
                display: inline-block;
                border: 2px solid orange;
                border-radius: 5px;
                padding: 10px;
            }

            .options {
                margin-top: 30px;
            }

            .option-button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 18px;
                text-align: center;
                text-decoration: none;
                color: blue;
                background-color: white;
                border: 2px solid white;
                border-radius: 20px;
                margin: 10px;
            }
        </style>
    </head>
    <body>
        <div class="welcome">
            <c:if test="${sessionScope.account ne null}"> 
                <span>Hello ${sessionScope.account.displayname} </span>
            </c:if>
            <form action="logout" method="post" style="display: inline-block;">
                <input type="submit" value="Logout" />
            </form>
        </div>

        <div class="options">
            <c:forEach items="${requestScope.timetables}" var="sche">
                <a href="timetable?id=${sche.id}" class="option-button">Weekly Timetable</a>
            </c:forEach>
            <a href="#" class="option-button">Attendance Report</a>
            <a href="#" class="option-button">Mark Report</a>
        </div>

        <div id="ctl00_divSupport">
            <br/>
            <b>Mọi góp ý, thắc mắc xin liên hệ: </b><span style="color: rgb(34, 34, 34); font-family: arial, sans-serif; font-size: 13.333333969116211px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;">Phòng dịch vụ sinh viên</span>: Email: <a href="mailto: dichvusinhvien@fe.edu.vn">dichvusinhvien@fe.edu.vn</a>.
            Điện thoại: <span class="style1" style="color: rgb(34, 34, 34); font-family: arial, sans-serif; font-size: 13.333333969116211px; font-style: normal; font-variant: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;">(024)7308.13.13 </span>
            <br/>

            <p style="text-align: center">
                © Powered by <a href="http://fpt.edu.vn" target="_blank">FPT University</a>&nbsp;|&nbsp;
                <a href="http://cms.fpt.edu.vn/" target="_blank">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" target="_blank">books24x7</a>
                <span id="ctl00_lblHelpdesk"></span>
        </div>

    </body>
</html>
