package org.example.Content;

import org.example.DAO.ArtistDAO;
import org.example.Entities.Artist;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AccessType;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editArtist")
public class EditArtistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String artistId = req.getParameter("artistId");
        String newName = req.getParameter("newName");
        System.out.println("New Name: " + newName);
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            ArtistDAO artistDAO = new ArtistDAO(factory);
            Artist artist = artistDAO.findById(Long.parseLong(artistId));
            artist.setName(newName);
            artistDAO.update(artist);
        }
        resp.sendRedirect("/Lab6-1.0-SNAPSHOT/start");
    }
}
