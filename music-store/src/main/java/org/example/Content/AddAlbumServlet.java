package org.example.Content;

import org.example.DAO.AlbumDAO;
import org.example.DAO.ArtistDAO;
import org.example.Entities.Album;
import org.example.Entities.Artist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAlbum")
public class AddAlbumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("albumTitle");
        String genre = req.getParameter("albumGenre");
        String artistId = req.getParameter("artistId");
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            AlbumDAO albumDAO = new AlbumDAO(factory);
            Album album = new Album();
            album.setTitle(title);
            album.setGenre(genre);
            ArtistDAO artistDAO = new ArtistDAO(factory);
            Artist artist = artistDAO.findById(Long.parseLong(artistId));
            album.setArtist(artist);
            albumDAO.save(album);
        }
        resp.sendRedirect("/Lab6-1.0-SNAPSHOT/start");
    }
}