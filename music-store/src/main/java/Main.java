import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.Entities.Album;
import org.example.Entities.Artist;
import org.example.Entities.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure("hibernate.cfg.xml")
//                .build();
//        Metadata metadata = new MetadataSources(registry)
//                .getMetadataBuilder()
//                .build();
//        SessionFactory factory = metadata.buildSessionFactory();
        //-------------------Добавление

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

//        Session session = factory.openSession();
//        session.beginTransaction();
//
//        Artist artist1 = new Artist();
//        artist1.setName("Артист - 1");
//
//        Album albumArt11 = new Album();
//        albumArt11.setTitle("Альбом - 1");
//        albumArt11.setGenre("ХИП-ХОП");
//        albumArt11.setArtist(artist1);
//
//        List<Track> tracksArt1Alb1 = new ArrayList<>();
//        Track trackArt1Alb11 = new Track();
//        trackArt1Alb11.setTitle("Трэк - 1");
//        trackArt1Alb11.setDuration(LocalTime.of(0, 3, 3));
//        trackArt1Alb11.setAlbum(albumArt11);
//
//        Track trackArt1Alb12 = new Track();
//        trackArt1Alb12.setTitle("Трэк - 2");
//        trackArt1Alb12.setDuration(LocalTime.of(0, 4, 37));
//        trackArt1Alb12.setAlbum(albumArt11);
//
//        Track trackArt1Alb13 = new Track();
//        trackArt1Alb13.setTitle("Трэк - 3");
//        trackArt1Alb13.setDuration(LocalTime.of(0, 6, 7));
//        trackArt1Alb13.setAlbum(albumArt11);
//
//        tracksArt1Alb1.add(trackArt1Alb11);
//        tracksArt1Alb1.add(trackArt1Alb12);
//        tracksArt1Alb1.add(trackArt1Alb13);
//        albumArt11.setTracks(tracksArt1Alb1);
//
//
//        Album albumArt12 = new Album();
//        albumArt12.setTitle("Альбом - 2");
//        albumArt12.setGenre("ДЖАЗЗ");
//        albumArt12.setArtist(artist1);
//
//        List<Track> tracksArt1Alb2 = new ArrayList<>();
//        Track trackArt1Alb21 = new Track();
//        trackArt1Alb21.setTitle("Трэк - 21");
//        trackArt1Alb21.setDuration(LocalTime.of(0, 7, 2));
//        trackArt1Alb21.setAlbum(albumArt12);
//
//        Track trackArt1Alb22 = new Track();
//        trackArt1Alb22.setTitle("Трэк - 22");
//        trackArt1Alb22.setDuration(LocalTime.of(0, 5, 55));
//        trackArt1Alb22.setAlbum(albumArt12);
//
//        tracksArt1Alb2.add(trackArt1Alb21);
//        tracksArt1Alb2.add(trackArt1Alb22);
//        albumArt12.setTracks(tracksArt1Alb2);
//
//        List<Album> artistAlbums = new ArrayList<>();
//        artistAlbums.add(albumArt11);
//        artistAlbums.add(albumArt12);
//        artist1.setAlbums(artistAlbums);
//
//        session.save(artist1);
//        session.getTransaction().commit();
//
//        System.out.println("Данные сохранены");
//        session.close();
//        factory.close();
        //-----------Удаление
//        Session session = factory.openSession();
//        session.beginTransaction();
//
//        Album album = session.get(Album.class, 4);
//        session.delete(album);
//        session.getTransaction().commit();
//
//        System.out.println("Данные удалены");
//
//        session.close();
//        factory.close();
        //--------------Редактирование
//        Session session = factory.openSession();
//        session.beginTransaction();
//        Track track = session.get(Track.class, 5);
//        track.setTitle("Ноыое название");
//        session.save(track);
//        session.getTransaction().commit();
//        session.close();
//        factory.close();

        //-------------------Вывод
        Session session = factory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Album> query = builder.createQuery(Album.class);
        query.from(Album.class);

        List<Album> result = session.createQuery(query).getResultList();
        System.out.println("Albums:");
        for (Album album : result) {
            System.out.println("ID - " + album.getId() + "; Title - " + album.getTitle() + "; Genre - " + album.getGenre());
        }

        String hql = "From " + Artist.class.getSimpleName();
        List<Artist> result1 = session.createQuery(hql).getResultList();
        System.out.println("Artists:");
        for (Artist artist : result1) {
            System.out.println(artist.getName());
        }

        session.getTransaction().commit();
        session.close();
        factory.close();
        }
    }













//    private static final String URL = "jdbc:postgresql://localhost:5432/MusicStore";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "postgres";
//
//    public static void main(String[] args) {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
//            System.out.println("Connected");
//
//            String query = "SELECT a.title AS Album_title, MIN(t.title) AS Track_title, MIN(t.duration) AS Duration" +
//                    " FROM album a JOIN track t on a.album_id = t.album_id " +
//                    "WHERE t.duration > '00:05:00' GROUP by a.title";

//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet result = statement.executeQuery();
//            System.out.println("Album_title | Track_title | Duration");
//            while (result.next()){
//                System.out.println(result.getString("album_title") + "    "
//                        + result.getString("track_title") + "   "
//                        + result.getTime("duration"));
//            }

//            String add = "INSERT INTO artist (name) VALUES ('Artist1')";
//            PreparedStatement statement1 = connection.prepareStatement(add);
//            int rows1 = statement1.executeUpdate();
//            if(rows1 > 0){
//                System.out.println("Запрос выполнен");
//            }
//
//            String update = "UPDATE artist SET name = 'Artist2' WHERE name = 'Artist1'";
//            PreparedStatement statement2 = connection.prepareStatement(update);
//            int rows2 = statement2.executeUpdate();
//            if(rows2 > 0){
//                System.out.println("Запрос выполнен");
//            }

//            String delete = "DELETE FROM artist WHERE name = 'Artist2'";
//            PreparedStatement statement3 = connection.prepareStatement(delete);
//            int rows3 = statement3.executeUpdate();
//            if(rows3 > 0){
//                System.out.println("Запрос выполнен");
//            }
//
//            String query = "SELECT * FROM artist";
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet result = statement.executeQuery();
//            System.out.println("Artist ID | Artist Name");
//            while (result.next()) {
//                System.out.println(result.getInt("artist_id") + "   " + result.getString("name"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Connection failed");
//        }
//    }