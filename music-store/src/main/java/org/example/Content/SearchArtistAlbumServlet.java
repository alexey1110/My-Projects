package org.example.Content;

import org.example.DAO.AlbumDAO;
import org.example.DAO.ArtistDAO;
import org.example.DAO.TrackDAO;
import org.example.Entities.Album;
import org.example.Entities.Artist;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchArtistAlbums")
public class SearchArtistAlbumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("artistName");
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            ArtistDAO artistDAO = new ArtistDAO(factory);
            AlbumDAO albumDAO = new AlbumDAO(factory);

            Artist artist = artistDAO.findByName(name);
            if (artist != null) {
                List<Album> albums = albumDAO.findByArtistId(artist.getId());
                System.out.println("Found artist: " + artist.getName());
                if (albums.isEmpty()) {
                    req.setAttribute("message", "No albums found for this artist.");
                } else {
                    req.setAttribute("artist", artist);
                    req.setAttribute("albums", albums);
                }
            } else {
                req.setAttribute("message", "Artist not found.");
            }
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/JSP/artistAlbums.jsp").forward(req, resp);
    }
}