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
        <table border="1px">
            <tr class="th">
                <th class="limit">CAMPUS</th>
                <th class="limit">TERM</th>
                <th>DEPARTMENT</th>
                <th>COURSE</th>
                <th>GROUP</th>
            </tr>
            <tr>
                <td>${requestScope.schedules[1].campus.name}</td>
                <td>
                    <c:forEach items="${requestScope.terms}" var="t">
                        <form action="studentlist" method="GET">
                            <input type="hidden" name="termid" value="${t.id}" />
                            <input type="submit" value="${t.name}" />
                        </form>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${requestScope.depts}" var="d">
                        <form action="studentlist" method="GET">
                            <input type="hidden" name="did" value="${d.id}" />
                            <input type="submit" value="${d.name}" />
                        </form>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${requestScope.subjects}" var="s">
                        <form action="studentlist" method="GET">
                            <input type="hidden" name="subid" value="${s.subject.id}" />
                            <input type="submit" value="${s.subject.name}" />
                        </form>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${requestScope.groups1}" var="g">
                        <form action="studentlist" method="GET">
                            <input type="hidden" name="gid" value="${g.id}" />
                            <input type="submit" value="${g.name}" />
                        </form>${g.name}
                    </c:forEach>
                    <c:forEach items="${requestScope.groups2}" var="g">
                        <form action="studentlist" method="GET">
                            <input type="hidden" name="gid" value="${g.id}" />
                            <input type="submit" value="${g.name}" />
                        </form>${g.name}
                    </c:forEach>
                </td>
            </tr>
        </table>
        <table border="1px"> 
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
                    <td class="td">${stu.student.name}${stu.student.code}
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
    </body>
</html>

