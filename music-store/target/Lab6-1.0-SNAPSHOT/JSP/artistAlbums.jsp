<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html>
     <head>
        <meta charset="UTF-8">
        <title>Content Overview</title>
    </head>
    <body>
        <h1>Albums of Artist: ${artist.name}</h1>
        <c:if test="${empty albums}">
            <p>No albums found.</p>
        </c:if>
        <c:if test="${not empty albums}">
            <table border="1">
                <tr>
                    <th>Album Title</th>
                    <th>Album Genre</th>
                    <th>Artist</th>
                </tr>
                <c:forEach var="album" items="${albums}">
                    <tr>
                        <td>${album.title}</td>
                        <td>${album.genre}</td>
                        <td>${album.artist.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>