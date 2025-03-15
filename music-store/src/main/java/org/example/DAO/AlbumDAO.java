package org.example.DAO;

import org.example.Entities.Album;
import org.example.Entities.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AlbumDAO implements GenericDAO<Album> {
    private final SessionFactory sessionFactory;

    public AlbumDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Album> findByArtistId(long artistId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Album> albums = session.createQuery("from Album where artist.id = :artistId", Album.class)
                .setParameter("artistId", artistId)
                .getResultList();
        transaction.commit();
        session.close();
        return albums;
    }
    public Album findByTitle(String title) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Album album = session.createQuery("from Album where title = :title", Album.class)
                .setParameter("title", title.trim())
                .uniqueResult();
        transaction.commit();
        session.close();
        return album;
    }
    @Override
    public void save(Album album) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(album);
        transaction.commit();
        session.close();
    }

    @Override
    public Album findById(Long id) {
        Session session = sessionFactory.openSession();
        Album album = session.get(Album.class, id);
        session.close();
        return album;
    }

    @Override
    public List<Album> findAll() {
        Session session = sessionFactory.openSession();
        List<Album> albums = session.createQuery("from Album", Album.class).list();
        session.close();
        return albums;
    }

    @Override
    public void update(Album album) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(album);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Album album) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(album);
        transaction.commit();
        session.close();
    }
}
