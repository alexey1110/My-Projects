package org.example.Content;

import org.example.DAO.ArtistDAO;
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
import java.util.List;

import org.example.DAO.*;

@WebServlet("/")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        try(SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession())
        {
            session.beginTransaction();

            List<Artist> artists = new ArtistDAO(factory).findAll();
            List<Album> albums = new AlbumDAO(factory).findAll();
            List<Track> tracks = new TrackDAO(factory).findAll();

            for (Album album : albums) {
                album.getArtist().getName();
                for (Track track : album.getTracks()) {
                    track.getAlbum().getTitle();
                }
            }
            req.setAttribute("Artists", artists);
            req.setAttribute("Albums", albums);
            req.setAttribute("Tracks", tracks);

            session.getTransaction().commit();
        }
        getServletContext().getRequestDispatcher("/JSP/start.jsp").forward(req,resp);
    }
}
