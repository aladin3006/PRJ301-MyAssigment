<%-- 
    Document   : lecturerHome
    Created on : Oct 9, 2023, 6:36:23 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: center;
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

        <form action="ScheduleController" method="GET" id="searchForm">
            <table>
                <thead>
                    <tr>
                        <th rowspan="2">
                            <label for="yearSelect">YEAR</label>
                            <span class="auto-style1"><strong>  </strong></span>
                            <select name="year" onchange="document.getElementById('searchForm').submit();">
                                <option>-2023-</option>
                                <c:forEach items="${requestScope.schedules}" var="s">
                                    <option value="${s.year}"
                                            <c:if test="${s.year eq param.year}">
                                                selected="selected"
                                            </c:if>
                                            >${s.year}</option>
                                </c:forEach>
                            <br/>
                            </select>  
                            <label for="weekSelect">WEEK</label>
                            <select name="week_number" onchange="document.getElementById('searchForm').submit();">
                                <c:forEach items="${requestScope.schedules}" var="s">
                                    <option value="${s.week_number}"
                                            <c:if test="${s.week_number eq param.week_number}">
                                                selected="selected"
                                            </c:if>
                                            >${s.week_description}</option>
                                </c:forEach>
                            </select>
                        </th>
                        <th>MON</th>
                        <th>TUE</th>
                        <th>WED</th>
                        <th>THU</th>
                        <th>FRI</th>
                        <th>SAT</th>
                        <th>SUN</th>
                    </tr>
                    <tr>
                        <th>1</th>
                        <th>2</th>
                        <th>3</th>
                        <th>4</th>
                        <th>5</th>
                        <th>6</th>
                        <th>7</th>
                    </tr>
                </thead>
                <tr>
                    <td>Slot 1</td>
                    <td id="slot1Mon"></td>
                    <td id="slot1Tue"></td>
                    <td id="slot1Wed"></td>
                    <td id="slot1Thu"></td>
                    <td id="slot1Fri"></td>
                    <td id="slot1Sat"></td>
                    <td id="slot1Sun"></td>
                </tr>
                <tr>
                    <td>Slot 2</td>
                    <td id="slot2Mon"></td>
                    <td id="slot2Tue"></td>
                    <td id="slot2Wed"></td>
                    <td id="slot2Thu"></td>
                    <td id="slot2Fri"></td>
                    <td id="slot2Sat"></td>
                    <td id="slot2Sun"></td>
                </tr>
                <tr>
                    <td>Slot 3</td>
                    <td id="slot3Mon"></td>
                    <td id="slot3Tue"></td>
                    <td id="slot3Wed"></td>
                    <td id="slot3Thu"></td>
                    <td id="slot3Fri"></td>
                    <td id="slot3Sat"></td>
                    <td id="slot3Sun"></td>
                </tr>
                <tr>
                    <td>Slot 4</td>
                    <td id="slot4Mon"></td>
                    <td id="slot4Tue"></td>
                    <td id="slot4Wed"></td>
                    <td id="slot4Thu"></td>
                    <td id="slot4Fri"></td>
                    <td id="slot4Sat"></td>
                    <td id="slot4Sun"></td>
                </tr>
                <tr>
                    <td>Slot 5</td>
                    <td id="slot5Mon"></td>
                    <td id="slot5Tue"></td>
                    <td id="slot5Wed"></td>
                    <td id="slot5Thu"></td>
                    <td id="slot5Fri"></td>
                    <td id="slot5Sat"></td>
                    <td id="slot5Sun"></td>
                </tr>
                <tr>
                    <td>Slot 6</td>
                    <td id="slot6Mon"></td>
                    <td id="slot6Tue"></td>
                    <td id="slot6Wed"></td>
                    <td id="slot6Thu"></td>
                    <td id="slot6Fri"></td>
                    <td id="slot6Sat"></td>
                    <td id="slot6Sun"></td>
                </tr>
                <tr>
                    <td>Slot 7</td>
                    <td id="slot7Mon"></td>
                    <td id="slot7Tue"></td>
                    <td id="slot7Wed"></td>
                    <td id="slot7Thu"></td>
                    <td id="slot7Fri"></td>
                    <td id="slot7Sat"></td>
                    <td id="slot7Sun"></td>
                </tr>
                <tr>
                    <td>Slot 8</td>
                    <td id="slot8Mon"></td>
                    <td id="slot8Tue"></td>
                    <td id="slot8Wed"></td>
                    <td id="slot8Thu"></td>
                    <td id="slot8Fri"></td>
                    <td id="slot8Sat"></td>
                    <td id="slot8Sun"></td>
                </tr>
            </table>
            <script>
                function populateSchedule() {
                    fetch('http://localhost:9999/MyProject/login') // Replace with the actual JSP script path
                            .then(response => response.json())
                            .then(scheduleData => {
                                // Loop through the scheduleData and populate the table cells
                                for (const slot of Object.keys(scheduleData)) {
                                    const row = document.createElement("tr");
                                    for (let i = 0; i < 7; i++) {
                                        const cellId = `slot${slot.charAt(6)}${i + 1}${slot.substring(6).toLowerCase()}`;
                                        const cell = document.createElement("td");
                                        cell.textContent = scheduleData[slot][i];

                                        // Add attendance button to each cell
                                        const button = document.createElement("button");
                                        button.textContent = "Attendance";
                                        button.onclick = () => takeAttendance(cellId);
                                        cell.appendChild(button);

                                        row.appendChild(cell);
                                    }
                                    document.getElementById("scheduleTable").appendChild(row);
                                }
                            })
                            .catch(error => {
                                console.error('Error fetching schedule data: ', error);
                            });
                }

                // Call the function to populate the schedule when the page loads
                window.onload = populateSchedule;

                // Function to handle attendance button click
                function takeAttendance(cellId) {
                    // Implement your attendance logic here
                    // You can use AJAX to send attendance data to the server
                    console.log('Taking attendance for cell:', cellId);
                }
            </script>

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
