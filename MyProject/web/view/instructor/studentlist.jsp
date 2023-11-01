<%-- 
    Document   : studentlist
    Created on : Nov 1, 2023, 8:42:57 AM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
                width: 90%;
                margin: 0 auto;
            }

            td {
                vertical-align: top;
            }
            .limit{
                width: 80px;
            }
            .th {
                border-right: 1px solid #fff;
                text-align: center;
                padding: 2px;
                text-transform: uppercase;
                height: 23px;
                background-color: #6b90da;
                font-weight: normal;
            }
            td {
                vertical-align: top;
                color: #337AB7;
                padding: 2px;
            }
            .line{
                border-bottom: 1px solid #ccc;
            }
        </style>
        <script>
            function showCourses(did) {
                $.ajax({
                    url: "StudentListController?did=" + did
                });
            }
        </script>
    </head>
    <body>
        <table border="1">
            <tr class="th">
                <th class="limit">CAMPUS</th>
                <th class="limit">TERM</th>
                <th>DEPARTMENT</th>
                <th>COURSE</th>
                <th>GROUP</th>
            </tr>

            <tbody>
                <tr>
                    <td>${requestScope.schedules[1].campus.name}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>

            <c:forEach items="${requestScope.terms}" var="term">
                <tr>
                    <td></td>
                    <td>${term.name}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>

            <c:forEach items="${requestScope.depts}" var="dept">
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <input type="text" value="${dept.name}" onclick="showCourses(${dept.did})">
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>

            <c:forEach items="${requestScope.groups}" var="g">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>${g.subject.name}</td>
                    <td>${g.name}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>