package org.example.Content;

import org.example.DAO.ArtistDAO;
import org.example.Entities.Artist;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteArtist")
public class DeleteArtistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long artistId = Long.parseLong(req.getParameter("artistId"));
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            ArtistDAO artistDAO = new ArtistDAO(factory);
            Artist artist = artistDAO.findById(artistId);
            artistDAO.delete(artist);
        }
        resp.sendRedirect("/Lab6-1.0-SNAPSHOT/start");
    }
}
