package org.example.Content;

import org.example.DAO.AlbumDAO;
import org.example.DAO.ArtistDAO;
import org.example.DAO.TrackDAO;
import org.example.Entities.Album;
import org.example.Entities.Artist;
import org.example.Entities.Track;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AccessType;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

@WebServlet("/editTrack")
public class EditTrackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String trackId = req.getParameter("trackId");
        String newTitle = req.getParameter("newTitle");
        String newDuration = req.getParameter("newDuration");
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            TrackDAO trackDAO = new TrackDAO(factory);
            Track track = trackDAO.findById(Long.parseLong(trackId));
            track.setTitle(newTitle);
            track.setDuration(LocalTime.parse(newDuration));
            trackDAO.update(track);
        }

        resp.sendRedirect("/Lab6-1.0-SNAPSHOT/start");
    }
}
