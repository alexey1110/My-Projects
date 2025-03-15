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

@WebServlet("/deleteTrack")
public class DeleteTrackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long trackId = Long.parseLong(req.getParameter("trackId"));
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            TrackDAO trackDAO = new TrackDAO(factory);
            Track track = trackDAO.findById(trackId);
            trackDAO.delete(track);
        }
        resp.sendRedirect("/Lab6-1.0-SNAPSHOT/start");
    }
}