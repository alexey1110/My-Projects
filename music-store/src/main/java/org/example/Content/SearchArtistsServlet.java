package org.example.Content;

import org.example.DAO.ArtistDAO;
import org.example.Entities.Artist;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchArtists")
public class SearchArtistsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String query = req.getParameter("query");

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            ArtistDAO artistDAO = new ArtistDAO(factory);
            List<Artist> artists = artistDAO.findByNameLike(query);

            StringBuilder html = new StringBuilder();
            for (Artist artist : artists) {
                html.append("<option value=\"").append(artist.getName()).append("\"></option>");
            }

            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write(html.toString());
        }
    }
}
