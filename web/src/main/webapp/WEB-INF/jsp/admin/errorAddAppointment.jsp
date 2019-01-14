<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>

    <p>It's not possible to add ${appointmentDto.service} at ${startTime}.</p>
    <p>Duration of ${appointmentDto.service} is ${duration} minutes.</p>
    <p>Choose another start time of appointment.</p>


    <div>
        <c:url value="/admin/showSchedule" var="showScheduleLink">
            <c:param name="date" value="${appointmentDto.getDate()}"/>
        </c:url>
        <a href="${showScheduleLink}">Back</a>
    </div>


</div>