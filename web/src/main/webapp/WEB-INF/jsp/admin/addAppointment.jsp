<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <form:form action="saveAppointmentByAdmin" method="post" modelAttribute="appointmentDto">

        <form:hidden path="id"/>
        <table>
            <tbody>
            <tr>
                <td>
                    Date: ${appointmentDto.date}
                    <form:hidden path="date" value="${appointmentDto.date}"/>
                    <%--<form:select path="date">--%>
                        <%--<form:option value="${date}"/>--%>
                        <%--<form:options items="${scheduleDate}"/>--%>
                    <%--</form:select>--%>
                </td>
            </tr>
            <tr>
                <td>
                    Time: ${appointmentDto.startTime}
                    <form:hidden path="startTime" value="${appointmentDto.startTime}"/>
                    <form:hidden path="reservedSlot" value="${appointmentDto.reservedSlot}"/>
                    <%--<form:select path="startTime">--%>
                        <%--<form:option value="${slot}"/>--%>
                        <%--<form:options items="${times}"/>--%>
                    <%--</form:select>--%>
                </td>
            <tr>
                <td>
                    <form:label path="service">Service: </form:label>
                    <form:select path="service">
                        <form:options items="${services}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                Master: <span>${master}</span>
                    <form:hidden path="masterId" value="${appointmentDto.masterId}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="clientName">Name: </form:label>
                    <form:input path="clientName"/>
                </td>
            </tr>
            <tr>
                <td>
                <form:label path="clientPhone">Phone: </form:label>
                <form:input path="clientPhone"/>
                    <form:hidden path="check" value="${check}"/>
                </td>
            </tr>
            <tr>
                <td>
                <form:label path="comment">Add Comment </form:label>
                <form:textarea path="comment" rows="5" cols="30"/><br>
                </td>
            </tr>
            <tr>
                <td>
                <input type="submit" value="Save Appointment"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>


</div>