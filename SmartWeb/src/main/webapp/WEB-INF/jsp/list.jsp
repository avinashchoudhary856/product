<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
</head>
<body>
<c:forEach var="user" items="${users}">
<tr>
    <td>
        <option value ="10"><c:out value="${user.getUserName()}"/></option>
    </td>
</tr>
<tr>
    <td>
        <option value ="10"><c:out value="${user.getFullName()}"/></option>
    </td>
</tr>
<tr>
    <td>
        <option value ="10"><c:out value="${user.getUserType()}"/></option>
    </td>
</tr>
<tr>
    <td>
        <option value ="10"><c:out value="${user.getPassword()}"/></option>
    </td>
</tr>
</c:forEach>
</body>
</html>