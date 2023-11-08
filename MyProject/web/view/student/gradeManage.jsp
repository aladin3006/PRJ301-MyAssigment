<%-- 
    Document   : gradeManage
    Created on : Nov 3, 2023, 5:08:18 AM
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

            button {
                position: absolute;
                bottom: 5px;
                right: 5px;
            }
            .header__navbar{
                padding: 8px 15px;
                margin-bottom: 20px;
                list-style: none;
                background-color: #f5f5f5;
                border-radius: 4px;
            }
            .header__navbar {
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 10px; /* Add padding as needed */
            }

            .header__list {
                list-style: none;
                padding: 0;
                margin: 0;
                display: flex;
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
            h1 {
                font-size: 30px;
                color: #133579;
                font-weight: bold;
                text-align: left;
                background: no-repeat top left;
                padding: 10px;
                margin-bottom: 2px;
            }
            .row {
                margin-right: -15px;
                margin-left: -15px;
            }
            .col-md-8,.col-md-4{
                position: relative;
                min-height: 1px;
                padding-right: 15px;
                padding-left: 15px;
            }
        </style>
    </head>
    <body>

        <div class="header__navbar">


            <ul class="header__list">
                <li class="header__item">
                    <a class="header__link panel" href="timetable">Timetable</a>&nbsp;|
                </li>
                <li class="header__item">
                    <a class="header__link panel" href="courses">Grading Management</a>    
                </li>
            </ul>
            <div>
                <a href="" class="label">${sessionScope.account.name}</a>&nbsp;|
                <a href="/MyProject/logout" class="label">Logout</a>&nbsp;|       
                <span class="label label-hover">CAMPUS: FPTU-Hòa Lạc</span>
            </div>

        </div>
        <form action="course" method="post">
            <table> 
                <tr class="th">
                    <td>INDEX</td>
                    <td>SUBJECT CODE</td>
                    <td>SUBJECT NAME</td>
                    <td>SEMESTER</td>
                    <td>STARTDATE</td>
                    <td>ENDDATE</td>
                    <td>AVERAGE MARK</td>
                    <td>STATUS</td>
                </tr>
                <c:forEach items="${requestScope.allCourses}" var="all" varStatus="index">
                    <tr>
                        <td>
                            ${index.index + 1}
                        </td>
                        <td>
                            ${all.subject.name}
                        </td>
                        <td>
                            ${all.subject.description}
                        </td>
                        <td>
                            ${all.subject.term.name}
                        </td>
                        <td></td>
                        <td></td>
                        <td> 
                            ${all.value}
                        </td>
                        <td> 
                            <c:if test="${all.status}"><span style="color: blue;">(Passed)</span></c:if>
                            <c:if test="${!all.status}"><span style="color: blue;">(Not Passed)</span></c:if>
                            </td>
                        </tr>   
                </c:forEach>
            </table>
        </form>
    </body>
</html>
