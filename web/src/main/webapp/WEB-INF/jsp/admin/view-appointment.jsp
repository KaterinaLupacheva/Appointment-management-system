<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    Date: <span>${appointmentDto.date}</span><br>
    Time: <span>${appointmentDto.startTime}</span><br>
    Service: <span>${appointmentDto.service}</span><br>
    Client Name: <span>${appointmentDto.clientName}</span><br>
    Client Phone: <span>${appointmentDto.clientPhone}</span><br>
    Comment: <span>${appointmentDto.comment}</span>
</div>
<c:url var="editLink" value="/admin/editAppointment">
    <c:param name="appointmentId" value="${appointmentDto.id}"/>
</c:url>

<c:url var="deleteLink" value="/admin/deleteAppointment">
    <c:param name="appointmentId" value="${appointmentDto.id}"/>
    <c:param name="masterName" value="${masterName}"/>
</c:url>

<%--<a href="${editLink}"><button type="button" class="btn btn-success">Edit</button></a>--%>

<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this appointment?'))) return false"><button type="button" class="btn btn-success">Delete</button></a>