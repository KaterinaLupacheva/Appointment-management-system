<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div>
    <h2>Submit Appointment</h2>

    <form:form action="chooseMaster" modelAttribute="appointmentDto" method="post">

        <form:label path="clientName">Enter Name: </form:label>
        <form:input path="clientName"/><br>

        <form:label path="clientPhone">Enter Phone: </form:label>
        <form:input path="clientPhone"/><br>

        Choose Date:
        <form:select path="date">
            <form:option value="${appointmentDto.date}"/>
            <form:options items="${scheduleDate}"/>
        </form:select><br>

        Choose Service:
        <form:select path="service">
            <form:option value="${appointmentDto.service}"/>
            <form:options items="${allServices}"/>
        </form:select><br>

        <form:label path="comment">Add Comment </form:label>
        <form:textarea path="comment" rows="5" cols="30"/><br>
        <input type="submit" value="Choose master">
    </form:form>
</div>
