package org.example.Content;

import org.example.DAO.AlbumDAO;
import org.example.DAO.ArtistDAO;
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

@WebServlet("/deleteAlbum")
public class DeleteAlbumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long albumId = Long.parseLong(req.getParameter("albumId"));
        try(SessionFactory factory = new Configuration().configure().buildSessionFactory()){
            AlbumDAO albumDAO = new AlbumDAO(factory);
            Album album = albumDAO.findById(albumId);
            albumDAO.delete(album);
        }
        resp.sendRedirect("/Lab6-1.0-SNAPSHOT/start");
    }
}