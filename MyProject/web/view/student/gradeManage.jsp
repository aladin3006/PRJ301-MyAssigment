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
            table {
                width: 90%;
                margin: 0 auto;
            }

            td {
                vertical-align: top;
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
        </style>
    </head>
    <body>
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
    </body>
</html>
