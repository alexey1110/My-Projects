package org.example.Content;

import org.example.DAO.AlbumDAO;
import org.example.DAO.ArtistDAO;
import org.example.DAO.TrackDAO;
import org.example.Entities.Album;
import org.example.Entities.Artist;
import org.example.Entities.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

@WebServlet("/addTrack")
public class AddTrackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("trackTitle");
        String duration = req.getParameter("trackDuration");
        String albumId = req.getParameter("albumId");
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            TrackDAO trackDAO = new TrackDAO(factory);
            Track track = new Track();
            track.setTitle(title);
            track.setDuration(LocalTime.parse(duration));
            AlbumDAO albumDAO = new AlbumDAO(factory);
            Album album = albumDAO.findById(Long.parseLong(albumId));
            track.setAlbum(album);
            trackDAO.save(track);
        }
        resp.sendRedirect("/Lab6-1.0-SNAPSHOT/start");
    }
}
