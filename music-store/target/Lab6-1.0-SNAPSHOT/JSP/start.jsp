<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Content Overview</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#artistName').on('input', function () {
                const query = $(this).val();
                if (query.length >= 1) {
                    $.ajax({
                        url: '/Lab6-1.0-SNAPSHOT/searchArtists',
                        type: 'GET',
                        data: { query: query },
                        success: function (html) {
                            $('#artistSuggestions').html(html);
                        },
                        error: function () {
                            console.error('Error fetching artist suggestions');
                        }
                    });
                } else {
                    $('#artistSuggestions').empty();
                }
            });
        });
    </script>
</head>
<body>
    <h1>Search Albums by Artist Name</h1>
        <form action="searchArtistAlbums" method="POST" accept-charset="UTF-8">
            <label for="artistName">Enter Artist Name:</label>
            <input type="text" name="artistName" id="artistName" list="artistSuggestions" placeholder="Enter artist name" required>
            <datalist id="artistSuggestions">

            </datalist>
            <button type="submit">Show Albums</button>
        </form>
    <h1>Search Tracks by Album Title</h1>
        <form action="searchAlbumTracks" method="POST" accept-charset="UTF-8">
            <label for="albumTitle">Enter Album Title:</label>
            <input type="text" name="albumTitle" id="albumTitle" placeholder="Enter album title" required>
            <button type="submit">Show Tracks</button>
        </form>
    <h1>Artists</h1>
    <table border="1">
        <tr>
            <th>Artist Name</th>
            <th>Artist Id</th>
        </tr>
        <c:forEach var="artist" items="${Artists}">
            <tr>
                <td>${artist.name}</td>
                <td>${artist.id}</td>
                <td>
                    <form action="editArtist" method="POST">
                        <input type="hidden" name="artistId" value="${artist.id}" />
                        <input type="text" name="newName" placeholder="New Name" required />
                        <button type="submit">edit</button>
                    </form>
                    <form action="deleteArtist" method="POST">
                         <input type="hidden" name="artistId" value="${artist.id}" />
                         <button type="submit">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2>Add Artist<h2>
    <form action="addArtist" method="POST" accept-charset="UTF-8">
        <label for="artistName">Artist Name:</label>
        <input type="text" name="artistName" required>
        <button type="submit">add</button>
    </form>

    <h1>Albums</h1>
    <table border="1">
        <tr>
            <th>Album Title</th>
            <th>Album Genre</th>
            <th>Album Id</th>
            <th>Artist</th>
            <th>Artist Id</th>
        </tr>
        <c:forEach var="album" items="${Albums}">
            <tr>
                <td>${album.title}</td>
                <td>${album.genre}</td>
                <td>${album.id}</td>
                <td>${album.artist.name}</td>
                <td>${album.artist.id}</td>
                <td>
                    <form action="editAlbum" method="POST">
                        <input type="hidden" name="albumId" value="${album.id}" />
                        <input type="text" name="newTitle" placeholder="New Title" required />
                        <input type="text" name="newGenre" placeholder="New Genre" required />
                        <button type="submit">edit</button>
                    </form>
                    <form action="deleteAlbum" method="POST">
                        <input type="hidden" name="albumId" value="${album.id}" />
                        <button type="submit">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2>Add Album<h2>
        <form action="addAlbum" method="POST" accept-charset="UTF-8">
            <label for="albumTitle">Album Title, Genre, ArtistId:</label>
            <input type="text" name="albumTitle" required>
            <input type="text" name="albumGenre" required>
            <input type="text" name="artistId" required>
            <button type="submit">add</button>
        </form>

    <h1>Tracks</h1>
    <table border="1">
        <tr>
            <th>Track Title</th>
            <th>Track Duration</th>
            <th>Track Id</th>
            <th>Album</th>
            <th>Album Id</th>
        </tr>
        <c:forEach var="track" items="${Tracks}">
            <tr>
                <td>${track.title}</td>
                <td>${track.duration}</td>
                <td>${track.id}</td>
                <td>${track.album.title}</td>
                <td>${track.album.id}</td>
                <td>
                    <form action="editTrack" method="POST">
                        <input type="hidden" name="trackId" value="${track.id}" />
                        <input type="text" name="newTitle" placeholder="New Title" required />
                        <input type="text" name="newDuration" placeholder="New Duration" required />
                        <button type="submit">edit</button>
                    </form>
                    <form action="deleteTrack" method="POST">
                         <input type="hidden" name="trackId" value="${track.id}" />
                         <button type="submit">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2>Add Track<h2>
            <form action="addTrack" method="POST" accept-charset="UTF-8">
                <label for="trackTitle">Track Title, Duration, AlbumId:</label>
                <input type="text" name="trackTitle" required>
                <input type="text" name="trackDuration" required>
                <input type="text" name="albumId" required>
                <button type="submit">add</button>
            </form>
</body>
</html>