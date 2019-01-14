<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <form:form action="showMasterSchedule" modelAttribute="currentScheduleDto" method="post">
        <h4 class="text-center">${currentScheduleDto.masterName}</h4>
        <h4 class="text-center">${master}</h4>
        <form:select path="masterName">
            <form:option value="${master}"/>
            <form:options items="${allMasters}"/>
        </form:select>
        <input type="submit" value="Change Master">
    </form:form>
</div>

<div class="container-fluid">
    <div class="row">
        <c:forEach var="tempEntity" items="${appointmentList}">
            <div class="col-sm">
                <table style="display: inline-block" class="table text-center">
                    <thead class="thead-dark">
                    <tr>
                        <td colspan="2"><b>${tempEntity.key.getDay()}</b></td>

                    <tr>
                        <th>Time</th>
                        <th>Available</th>
                    </tr>

                    </tr>
                    </thead>

                    <c:forEach var="tempAppointments" items="${tempEntity.value}">
                        <tr>
                            <td>${tempAppointments.slot}</td>
                            <td>
                                <c:url value="/admin/showAppointment" var="showAppointmentLink">
                                    <c:param name="appointmentId" value="${tempAppointments.appointmentId}"/>
                                    <c:param name="masterName" value="${master}"/>
                                </c:url>

                                <c:url value="/admin/addAppointment" var="addAppointmentLink">
                                    <c:param name="date" value="${tempEntity.key.getDay()}"/>
                                    <c:param name="slot" value="${tempAppointments.slot}"/>
                                    <c:param name="scheduleId" value="${tempEntity.key.getId()}"/>
                                    <c:param name="slotId" value="${tempAppointments.slotId}"/>
                                    <c:param name="check" value="1"/>
                                </c:url>
                                <c:choose>
                                    <c:when test="${tempAppointments.slotAppointment == 'Free'}">
                                        <a href="${addAppointmentLink}">Free</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${showAppointmentLink}" data-toggle="tooltip" data-placement="top" title="${tempAppointments.getClientName()}, ${tempAppointments.getClientPhone()}">${tempAppointments.slotAppointment}</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </c:forEach>
    </div>
</div>

<a href="${pageContext.request.contextPath}/admin/startpage">Main menu</a>
