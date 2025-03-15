package org.example.DAO;

import org.example.Entities.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TrackDAO implements GenericDAO<Track> {
    private final SessionFactory sessionFactory;

    public TrackDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Track> findByAlbumId(long albumId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Track> tracks = session.createQuery("from Track where album.id = :albumId", Track.class)
                .setParameter("albumId", albumId)
                .getResultList();
        transaction.commit();
        session.close();
        return tracks;
    }
    @Override
    public void save(Track track) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(track);
        transaction.commit();
        session.close();
    }

    @Override
    public Track findById(Long id) {
        Session session = sessionFactory.openSession();
        Track track = session.get(Track.class, id);
        session.close();
        return track;
    }

    @Override
    public List<Track> findAll() {
        Session session = sessionFactory.openSession();
        List<Track> tracks = session.createQuery("from Track", Track.class).list();
        session.close();
        return tracks;
    }

    @Override
    public void update(Track track) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(track);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Track track) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(track);
        transaction.commit();
        session.close();
    }
}
