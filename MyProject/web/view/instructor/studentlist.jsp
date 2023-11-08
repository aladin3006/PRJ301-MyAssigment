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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <title>Student List</title>
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

            .table {
                width: 100%;
                border-collapse: collapse;
            }
            table {
                width: 90%;
                margin: 0 auto;
            }

            .td {
                vertical-align: top;
                color: #337AB7;
                padding: 2px;
            }

            th, td {
                border: 1px solid black;
                padding: 8px;
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
            table {
                width: 90%;
                margin: 0 auto;
            }
            td {
                vertical-align: top;
            }
            .line{
                border-bottom: 1px solid #ccc;
            }
            .student-image{
                width: 100px;
                height: 100px;
                background-position: center;
                background-repeat: no-repeat;
                margin: 0 auto;

            }
            .student-image img{
                width: 100%;
                height: 100%;
                background-position: center;
                background-size: contain;
                background-repeat: no-repeat;
            }
            .limit{
                width: 80px;
            }
        </style>
    </head>
    <body>
        <div class="row">
            <div class="col-md-8">
                <h1><span>FPT University Academic Portal</span>
                </h1>
            </div>
            <div class="col-md-4">
                <table class="table">
                    <tbody><tr>
                            <td colspan="2" style="color: #FF3300"><strong>FAP mobile app (myFAP) is ready at</strong></td>
                        </tr>
                        <tr>
                            <td><a href="https://apps.apple.com/app/id1527723314">
                                    <img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px;
                                         height: 40px" alt="apple store"></a></td>
                            <td><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                    <img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px;
                                         height: 40px" alt="google store"></a></td>
                        </tr>
                    </tbody></table>
            </div>
        </div>

        <div class="header__navbar">
            <ul class="header__list">
                <li class="header__item">
                    <a class="header__link panel" href="schedule">Timetable</a>&nbsp;|
                </li>
                <li class="header__item">
                    <a class="header__link panel" href="studentlist">Student List</a>
                </li>
            </ul>
            <div>
                <a href="" class="label">${sessionScope.account.name}</a>&nbsp;|
                <a href="/MyProject/logout" class="label">Logout</a>&nbsp;|       
                <span class="label label-hover">CAMPUS: FPTU-Hòa Lạc</span>
            </div>
        </div>

        <table>
            <tr class="th">
                <td class="limit">CAMPUS</td>
                <td class="limit">TERM</td>
                <td>DEPARTMENT</td>
                <td>COURSE</td>
                <td>GROUP</td>
            </tr>
            <tr>
                <td>
                FPTU-Hòa Lạc
                </td>
                <td>
                    <c:forEach items="${requestScope.terms}" var="t">
                        <a href="studentlist?termid=${t.id}">${t.name}</a>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${requestScope.depts}" var="d">
                        <a href="studentlist?did=${d.id}">${d.name}</a><br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${requestScope.subjects}" var="s">
                        <a href="studentlist?subid=${s.subject.id}">${s.subject.description}(${s.subject.name})</a><br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${requestScope.groups1}" var="g">
                        <a href="studentlist?gid=${g.id}">${g.name}</a>
                    </c:forEach>
                    <c:forEach items="${requestScope.groups2}" var="g">
                        <a href="studentlist?gid=${g.id}">${g.name}</a>
                    </c:forEach>
                </td>
            </tr>
        </table>

        <c:set var="stu" value="${requestScope.students}"/>
        <c:if test="${not empty stu}">
            <p>...then see student list <span style="color: #6b90da;
                                              font-weight: bold;">(Export data)</span></p>
            <table> 
                <tr class="th">
                    <td>INDEX</td>
                    <td>IMAGE</td>
                    <td>MEMBER</td>
                    <td>CODE</td>
                    <td>FULL NAME</td>
                </tr>
                <c:forEach items="${requestScope.students}" var="stu" varStatus="index">
                    <tr>
                        <td>
                            ${index.index + 1}
                        </td>
                        <td>
                            <div class="student-image">
                                <img src="${stu.student.image}" alt="Image" />
                            </div>
                        </td>
                        <td class="td">${stu.student.name}${stu.student.code}<br>
                            <a href="/MyProject/student/courses?stuid=${stu.student.id}">View Grade</a>
                        </td>

                        <td>${stu.student.code}
                            <input type="hidden" name="stuid" value="${stu.student.id}"/>
                        </td>
                        <td>${stu.student.fullName}
                            <input type="hidden" name="stuid" value="${stu.student.id}"/>
                        </td>
                    </tr>   
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>

