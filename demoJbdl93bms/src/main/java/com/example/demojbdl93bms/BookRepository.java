package com.example.demojbdl93bms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import java.util.Date;
@Repository
public class BookRepository {


    private DatabaseRepository databaseRepository;


    public BookRepository(DatabaseRepository databaseRepository) throws SQLException {
        this.databaseRepository = databaseRepository;
        createTable();
    }

//    public Connection getConnection() throws SQLException {
//
//        Connection connection = DriverManager.getConnection(this.url,this.username,this.password);
//        return connection;
//    }
//    private Integer id;
//    private String name;
//    private String author;
//    private String authorEmail;
//    private Double price;
//    private Genre genre;
//    private Date createdOn;
//    private Date updatedOn;

    public void createTable() throws SQLException {

        String query = "create table if not Exists book(id int primary key auto_increment, name varchar(50) not null" +
                ",author varchar(50) not null, authorEmail varchar(50),price double, genre varchar(50)" +
                ",createdOn Date, updatedOn Date)";

        Connection connection = this.databaseRepository.getConnection();

        Statement statement = connection.createStatement();
        statement.execute(query);

    }


    public void createBook(Book book) throws SQLException {

        String query = "insert into book(name,author,authorEmail,price,genre,createdOn,updatedOn)" +
                "values(?,?,?,?,?,?,?)";

//        not efficient
//        String query = "insert into book(name,author,authorEmail,price,genre,createdOn,updatedOn)" +
//                "values(book.getName(),book.getAuthor())";



        PreparedStatement preparedStatement = this.databaseRepository.getConnection().prepareStatement(query);
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getAuthorEmail());
        preparedStatement.setDouble(4, book.getPrice());
        preparedStatement.setString(5,book.getGenre().name());
        preparedStatement.setDate(6,new Date(book.getCreatedOn().getTime()));
        preparedStatement.setDate(7,new Date(book.getUpdatedOn().getTime()));

        preparedStatement.execute();

    }

    public List<Book> getAll() throws SQLException {

        String query = "select * from book";

        Statement statement = this.databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString(2));
            book.setAuthor(resultSet.getString(3));
            book.setAuthorEmail(resultSet.getString("authorEmail"));
            book.setPrice(resultSet.getDouble("price"));
            book.setGenre(Genre.valueOf(resultSet.getString("genre")));
            book.setCreatedOn(resultSet.getDate("createdOn"));
            book.setUpdatedOn(resultSet.getDate("updatedOn"));
            books.add(book);
        }
        return books;

    }

    public Book getBook(int id) throws SQLException {
        String query = "select * from book where id = ?";
        PreparedStatement preparedStatement = this.databaseRepository.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Book book = new Book();
        while (resultSet.next()) {
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString(2));
            book.setAuthor(resultSet.getString(3));
            book.setAuthorEmail(resultSet.getString("authorEmail"));
            book.setPrice(resultSet.getDouble("price"));
            book.setGenre(Genre.valueOf(resultSet.getString("genre")));
            book.setCreatedOn(resultSet.getDate("createdOn"));
            book.setUpdatedOn(resultSet.getDate("updatedOn"));
        }
        return book;
    }

    public void delete(int id) throws SQLException {
        String query = "delete from book where id = ?";
        PreparedStatement preparedStatement = this.databaseRepository.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    public void createBookWithId(Book book) throws SQLException {

        String query = "insert into book(id,name,author,authorEmail,price,genre,createdOn,updatedOn)" +
                "values(?,?,?,?,?,?,?,?)";

//        not efficient
//        String query = "insert into book(name,author,authorEmail,price,genre,createdOn,updatedOn)" +
//                "values(book.getName(),book.getAuthor())";



        PreparedStatement preparedStatement = this.databaseRepository.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,book.getId());
        preparedStatement.setString(2, book.getName());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, book.getAuthorEmail());
        preparedStatement.setDouble(5, book.getPrice());
        preparedStatement.setString(6,book.getGenre().name());
        preparedStatement.setDate(7,new Date(book.getCreatedOn().getTime()));
        preparedStatement.setDate(8,new Date(book.getUpdatedOn().getTime()));

        preparedStatement.execute();

    }

    public void update(Book existingBook,Integer id) throws SQLException {
        this.delete(id);
        this.createBookWithId(existingBook);
    }
}
//java util date
//mysql date

