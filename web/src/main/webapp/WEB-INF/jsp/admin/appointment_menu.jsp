<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <form:form action="showSchedule" modelAttribute="currentScheduleDto" method="post">

        <div class="row">
            <div class="col-md-12 school-options-dropdown text-center">
                <div class="dropdown btn-group">

                    <button class="btn btn-primary" type="button">Choose Date
                        <span class="caret"></span>
                    </button>

                    <form:select path="currentDate">
                        <form:option value="${currentDate}"/>
                        <form:options items="${scheduleDate}"/>
                    </form:select>
                    <input type="submit" value="Show Schedule">
                </div>
            </div>

        </div>
    </form:form>

<form:form action="showMasterSchedule" method="post" modelAttribute="currentScheduleDto">
    <div class="row">
        <div class="col-md-12 school-options-dropdown text-center">
            <div class="dropdown btn-group">

                <button class="btn btn-primary" type="button">Choose Master
                    <span class="caret"></span>
                </button>

                <form:select path="masterName">
                    <form:option value="${masterName}"/>
                    <form:options items="${allMasters}"/>
                </form:select>
                <input type="submit" value="Show Schedule">
            </div>
        </div>

    </div>

</form:form>


<%--<div>--%>
    <%--<table class="table text-center">--%>
        <%--<thead class="thead-dark">--%>
        <%--<tr>--%>
            <%--<th>Time</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>

        <%--<c:forEach var="timeSlot" items="${times}">--%>
        <%--<tr class="table-success">--%>
            <%--<td>${timeSlot}</td>--%>

        <%--</c:forEach>--%>

<%--</div>--%>


<a href="${pageContext.request.contextPath}/admin/startpage">Main menu</a>

