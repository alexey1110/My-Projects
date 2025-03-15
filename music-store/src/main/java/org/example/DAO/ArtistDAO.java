package org.example.DAO;

import org.example.Entities.Artist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ArtistDAO implements GenericDAO<Artist> {
    private final SessionFactory sessionFactory;

    public ArtistDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Artist findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Artist artist = session.createQuery("from Artist where name = :name", Artist.class)
                .setParameter("name", name.trim())
                .uniqueResult();
        transaction.commit();
        session.close();
        return artist;
    }
    public List<Artist> findByNameLike(String name) {
        Session session = sessionFactory.openSession();
        List<Artist> artists = session.createQuery("FROM Artist WHERE name LIKE :name", Artist.class)
                    .setParameter("name", name + "%")
                    .list();
        return artists;
    }
    @Override
    public void save(Artist artist) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(artist);
        transaction.commit();
        session.close();
    }

    @Override
    public Artist findById(Long id) {
        Session session = sessionFactory.openSession();
        Artist artist = session.get(Artist.class, id);
        session.close();
        return artist;
    }

    @Override
    public List<Artist> findAll() {
        Session session = sessionFactory.openSession();
        List<Artist> artists = session.createQuery("from Artist", Artist.class).list();
        session.close();
        return artists;
    }

    @Override
    public void update(Artist artist) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(artist);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Artist artist) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(artist);
        transaction.commit();
        session.close();
    }
}

