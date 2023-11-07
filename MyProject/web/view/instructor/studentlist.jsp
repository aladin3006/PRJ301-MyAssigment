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
            .td {
                vertical-align: top;
                color: #337AB7;
                padding: 2px;
            }
            .line{
                border-bottom: 1px solid #ccc;
            }
        </style>
    </head>
    <body>
        <table>
            <tr class="th">
                <td class="limit">CAMPUS</td>
                <td class="limit">TERM</td>
                <td>DEPARTMENT</td>
                <td>COURSE</td>
                <td>GROUP</td>
            </tr>
            <tr>
                <td>${requestScope.schedules[1].campus.name}</td>
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
        <p>...then see student list <span style="color: #6b90da; font-weight: bold;">(Export data)</span></p>
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
                    <td></td>
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

