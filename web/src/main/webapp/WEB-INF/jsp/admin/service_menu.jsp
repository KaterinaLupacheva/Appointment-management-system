<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid text-center">
        <h1 class="display-1">Services</h1>
    </div>
    <div>
    <%--Button to add Service--%>
    <input class="btn btn-info" type="button" value="Add Service"
            onclick="window.location.href='showFormForAddService';"/>
    </div><br>

    <div>
        <table class="table text-center">
            <thead class="thead-dark">
            <tr>
                <th>Service</th>
                <th>Duration, min</th>
                <th>Edit</th>
            </tr>
            </thead>
            <c:forEach var="tempService" items="${services}">

                <c:url var="updateLink" value="/admin/showFormForUpdateService">
                    <c:param name="serviceId" value="${tempService.id}"/>
                </c:url>

                <c:url var="deleteLink" value="/admin/delete">
                    <c:param name="serviceId" value="${tempService.id}"/>
                </c:url>

                <tr class="table-success">
                    <td>${tempService.title}</td>
                    <td>${tempService.duration}</td>

                    <td>
                        <a href="${updateLink}">Update
                            |
                            <a href="${deleteLink}"
                               onclick="if (!(confirm('Are you sure you want to delete this service?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
<a href="${pageContext.request.contextPath}/admin/startpage" class="badge badge-warning">Main menu</a>

