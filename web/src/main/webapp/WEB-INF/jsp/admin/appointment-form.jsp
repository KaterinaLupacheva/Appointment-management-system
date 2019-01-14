<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>
    <form:form action="saveAppointment" modelAttribute="appointmentDto" method="post">

        <form:hidden path="id"/>
        <table>
            <tbody>
            <tr>
                <td>
                    <form:label path="date">Date: </form:label>
                    <form:select path="date">
                        <form:option value="${appointmentDto.date}"/>
                        <form:options items="${scheduleDate}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="startTime">Time: </form:label>

                </td>
            </tr>
            </tbody>
        </table>
    </form:form>

</div>