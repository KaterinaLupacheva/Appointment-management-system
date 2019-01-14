<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br><br><br>
<div>
    ${appointmentDto.date}<br>
        ${appointmentDto.service}<br>
            <form:form action="submitAppointment" modelAttribute="appointmentDto" method="post">

        <div class="container-fluid">
            <div class="row">

        <c:forEach var="masterScheduleDto" items="${masterAppointmentList}">
            <div class="col-sm">
            <table style="display: inline-block" class="table text-center">
                <thead class="thead-dark">
            <tr>
                <td><c:out value="${masterScheduleDto.masterName}"/></td>
            </tr>
                </thead>


            <c:forEach var="availableSlots" items="${masterScheduleDto.getAvailableSlots()}">
                <tr>
                    <td>${availableSlots.value}</td>
                    <td><form:radiobutton name="reservedSlot" path="reservedSlot" value="${availableSlots.key}"/> </td>
                </tr>
            </c:forEach>
            </table>
            </div>
        </c:forEach>
            </div>
            </div>

        <input type="submit" value="Submit Appointment">
    </form:form>
</div>
