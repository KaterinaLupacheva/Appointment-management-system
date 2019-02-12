<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div>
    <h2>Submit Appointment</h2>

    <form:form action="chooseMaster" modelAttribute="appointmentDto" method="post">

        <form:label path="clientName">Enter Name: </form:label>
        <form:input path="clientName" required="required" placeholder="Enter Name"
                    oninvalid="this.setCustomValidity('Please, Enter Your Name Here')"
                    oninput="this.setCustomValidity('')" />
        <br>

        <form:label path="clientPhone">Enter Phone (with code): </form:label>
        <form:input path="clientPhone" required="required"
                    placeholder="+375(__)___-__-__"
                    pattern="^\\+375(\\s+)?\\(?(17|29|33|44)\\)?(\\s+)?[0-9]{3}-[0-9]{2}-[0-9]{2}$"
                    oninvalid="this.setCustomValidity('Please, Enter Your Phone Number Here')"
                    oninput="this.setCustomValidity('')"/>
        <br>

        Choose Date:
        <form:select path="date" required="required" placeholder="Choose date"
                     oninvalid="this.setCustomValidity('Please, Choose Date')"
                     oninput="this.setCustomValidity('')" >
            <form:option value="${appointmentDto.date}"/>
            <form:options items="${scheduleDate}"/>
        </form:select>
        <br>

        Choose Service:
        <form:select path="service" required="required" placeholder="Choose service"
                     oninvalid="this.setCustomValidity('Please, Choose Service')"
                     oninput="this.setCustomValidity('')">
            <form:option value="${appointmentDto.service}"/>
            <form:options items="${allServices}"/>
        </form:select>
        <br>

        <form:label path="comment">Add Comment </form:label>
        <form:textarea path="comment" rows="5" cols="30" placeholder="You can add comment here"/><br>
        <input type="submit" value="Choose master">
    </form:form>
</div>
