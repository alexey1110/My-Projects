<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Content Overview</title>
    </head>
    <body>
        <h1>Tracks of Album: ${album.title}</h1>
        <c:if test="${empty tracks}">
            <p>No tracks found for this album.</p>
        </c:if>
            <c:if test="${not empty tracks}">
            <table border="1">
                <tr>
                    <th>Track Title</th>
                    <th>Track Duration</th>
                    <th>Album</th>
                </tr>
            <c:forEach var="track" items="${tracks}">
                <tr>
                    <td>${track.title}</td>
                    <td>${track.duration}</td>
                    <td>${track.album.title}</td>
                </tr>
            </c:forEach>
    </table>
</c:if>
</body>
</html>