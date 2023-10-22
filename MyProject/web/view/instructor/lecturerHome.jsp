<%-- 
    Document   : lecturerHome
    Created on : Oct 9, 2023, 6:36:23 AM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>
        <style>
            .label {
                display: inline;
                padding: 0.2em 0.6em 0.3em;
                font-size: 75%;
                font-weight: 700;
                line-height: 1;
                color: #fff;
                text-align: center;
                white-space: nowrap;
                vertical-align: baseline;
                border-radius: 0.25em;
                background-color: #5cb85c;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                border: 1px solid black;
                padding: 8px;
            }

            td {
                position: relative;
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

            button {
                position: absolute;
                bottom: 5px;
                right: 5px;
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

        <table border="">
            <tr>
                <td>
                    <form action="schedule" method="GET">
                        From <input type="date" name="from" value="${requestScope.from}"/> <br/>
                        To <input type="date" name="to" value="${requestScope.to}"/>
                        <input type="hidden" value="${param.id}" name="id"/>
                        <input type="submit" value="View"/>
                    </form>  
                </td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>
                        <fmt:formatDate value="${d}" pattern="dd-MM-yyyy" var="formattedDate" />
                        <p>${formattedDate}</p>
                    </td>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="s">
                <tr>
                    <td>${s.name}</td>

                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.schedules}" var="sche">
                                <c:if test="${sche.time.id eq s.id and sche.date eq d}">
                                    ${sche.subject.name}-${sche.group.name}
                                    <br>at ${sche.room.rid}<br>                                   
                                    <br>-<div class="label">${s.description}</div><br>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>  
            </c:forEach>
        </table>
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
