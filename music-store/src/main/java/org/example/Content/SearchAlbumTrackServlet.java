package org.example.Content;

import org.example.DAO.AlbumDAO;
import org.example.DAO.ArtistDAO;
import org.example.DAO.TrackDAO;
import org.example.Entities.Album;
import org.example.Entities.Artist;
import org.example.Entities.Track;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchAlbumTracks")
public class SearchAlbumTrackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String title = req.getParameter("albumTitle");
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            AlbumDAO albumDAO = new AlbumDAO(factory);
            TrackDAO trackDAO = new TrackDAO(factory);

            Album album = albumDAO.findByTitle(title);
            if (album != null) {
                List<Track> tracks = trackDAO.findByAlbumId(album.getId());
                if (tracks.isEmpty()) {
                    req.setAttribute("message", "No tracks found for this album.");
                } else {
                    req.setAttribute("album", album);
                    req.setAttribute("tracks", tracks);
                }
            } else {
                req.setAttribute("message", "Album not found.");
            }
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/JSP/albumTracks.jsp").forward(req, resp);
    }
}

