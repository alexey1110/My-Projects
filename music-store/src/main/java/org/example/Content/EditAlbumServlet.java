package org.example.Content;

import org.example.DAO.AlbumDAO;
import org.example.DAO.ArtistDAO;
import org.example.Entities.Album;
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

@WebServlet("/editAlbum")
public class EditAlbumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String albumId = req.getParameter("albumId");
        String newTitle = req.getParameter("newTitle");
        String newGenre = req.getParameter("newGenre");
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            AlbumDAO albumDAO = new AlbumDAO(factory);
            Album album = albumDAO.findById(Long.parseLong(albumId));
            album.setTitle(newTitle);
            album.setGenre(newGenre);
            albumDAO.update(album);
        }

        resp.sendRedirect("/Lab6-1.0-SNAPSHOT/start");
    }
}
