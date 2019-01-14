<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid text-center">
    <h1 class="display-1">Masters</h1>
</div>
<div>
    <%--Button to add Master--%>
    <input class="btn btn-info" type="button" value="Add Master"
           onclick="window.location.href='showFormForAddMaster';"/>
</div><br>

<div>
    <table class="table text-center">
        <thead class="thead-dark">
        <tr>
            <th>First name</th>
            <th>Last Name</th>
            <th>Service(s)</th>
            <th>Schedule</th>
            <th>Edit</th>
        </tr>
        </thead>
        <c:forEach var="tempMaster" items="${masters}">

            <c:url var="updateLink" value="/admin/showFormForUpdateMaster">
                <c:param name="masterId" value="${tempMaster.id}"/>
            </c:url>

            <c:url var="deleteLink" value="/admin/deleteMaster">
                <c:param name="masterId" value="${tempMaster.id}"/>
            </c:url>

            <c:url var="scheduleLink" value="/admin/scheduleMaster">
                <c:param name="masterId" value="${tempMaster.id}"/>
            </c:url>

            <tr class="table-success">
                <td>${tempMaster.firstName}</td>
                <td>${tempMaster.lastName}</td>
                <td>${tempMaster.services}</td>

                <td>
                    <a href="${scheduleLink}"><button type="button" class="btn btn-success">Schedule</button></a>
                </td>

                <td>
                    <a href="${updateLink}">Update
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this Master?'))) return false">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
<a href="${pageContext.request.contextPath}/admin/startpage" class="badge badge-warning">Main menu</a>

