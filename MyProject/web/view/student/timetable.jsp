<%-- 
    Document   : timetable
    Created on : Oct 27, 2023, 10:59:37 PM
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
        <title>TimeTable</title>
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

        <div class="row">
            <div class="col-md-8">
                <h1><span>FPT University Academic Portal</span>
                </h1>
            </div>
            <div class="col-md-4">
                <table>
                    <tbody><tr>
                            <td colspan="2" style="color: #FF3300"><strong>FAP mobile app (myFAP) is ready at</strong></td>
                        </tr>
                        <tr>
                            <td><a href="https://apps.apple.com/app/id1527723314">
                                    <img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px; height: 40px" alt="apple store"></a></td>
                            <td><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                    <img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px; height: 40px" alt="google store"></a></td>
                        </tr>
                    </tbody></table>
            </div>
        </div>

        <div class="header__navbar">


            <ul class="header__list">
                <li class="header__item">
                    <a class="header__link panel" href="timetable">Timetable</a>&nbsp;|
                </li>
                <li class="header__item">
                    <a class="header__link panel" href="#">Grading Management</a>
                </li>
            </ul>
            <div>
                <a href="" class="label">${sessionScope.account.name}</a>&nbsp;|
                <a href="../logout" class="label">Logout</a>&nbsp;|       
                <span class="label">CAMPUS: ${requestScope.timetables[1].campus.name}</span>
            </div>

        </div>


        <table border="">
            <tr class="th">
                <td>
                    <form action="timetable" method="GET">
                        From <input type="date" name="from" value="${requestScope.from}"/> <br/>
                        To <input type="date" name="to" value="${requestScope.to}"/>
                        <input type="hidden" value="${param.id}" name="id"/>
                        <input type="submit" value="View"/>
                    </form>  
                </td>

                <c:forEach items="${requestScope.dates}"  var="d">
                    <td>
                        <fmt:formatDate value="${d}" pattern="dd-MM-yyyy" var="formattedDate" />
                        <fmt:formatDate value="${d}" pattern="EEE" var="dayOfWeek" />
                        <c:set var="capitalizedDayOfWeek" value="${dayOfWeek.toUpperCase()}" />
                        <p>${capitalizedDayOfWeek}</p><p>${formattedDate}</p>  
                    </td>
                </c:forEach>
            </tr>              
            <c:forEach items="${requestScope.slots}" var="s">
                <tr>
                    <td>${s.name}</td>

                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.timetables}" var="tt">
                                <c:if test="${tt.time.id eq s.id and tt.schedule.date eq d}">
                                    ${tt.subject.name}-${tt.group.name}
                                    <br>at ${tt.room.rid}<br>                                   
                                    <c:if test="${tt.schedule.isAtt}">
                                        <c:if test="${tt.status}"><span style="color: green;">(Attended)</span></c:if>
                                        <c:if test="${!tt.status}"><span style="color: red;">(Absent)</span></c:if>
                                    </c:if>
                                    <c:if test="${!tt.schedule.isAtt}"><span style="color: red;">(Not yet)</span></c:if>
                                    <br>-<div class="label">${s.description}</div><br></c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>

                </tr>  
            </c:forEach>
        </table>
        <p>
            <b>More note / Chú thích thêm</b>:
        </p>
        <div id="ctl00_mainContent_divfoot">
            <ul><li>(<font color="green">attended</font>): ${sessionScope.account.name} had attended this activity / ${sessionScope.account.displayname} đã tham gia hoạt động này</li>
                <li>(<font color="red">absent</font>): ${sessionScope.account.name} had NOT attended this activity / ${sessionScope.account.displayname} đã vắng mặt buổi này</li>
                <li>( ): no data was given / chưa có dữ liệu</li> </ul></div>
        <p>
        </p>
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
