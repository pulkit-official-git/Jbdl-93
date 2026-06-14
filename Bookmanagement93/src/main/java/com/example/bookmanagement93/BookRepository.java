package com.example.bookmanagement93;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    DatabaseRepository databaseRepository;


    public BookRepository(DatabaseRepository databaseRepository)throws SQLException {
        this.databaseRepository = databaseRepository;
        createTable();
    }

    public void createTable() throws SQLException {
        String sql = "Create table if not Exists book(id int primary key auto_increment,name varchar(50) not null," +
                "author varchar(50),author_email varchar(50),genre varchar(50),price double , created_on Date, updated_on Date)";

        Connection connection = this.databaseRepository.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void createBookWithId(Book book) throws SQLException {

        String query = "insert into book(id,name,author,author_email,genre,price,created_on,updated_on)" +
                "values(?,?,?,?,?,?,?,?)";

//        String query = "insert into book(name,author,author_email,genre,price,created_on,updated_on)" +
//                "values(book.getName(),book.getAuthor(),?,?,?,?,?)";

        PreparedStatement preparedStatement = this.databaseRepository.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, book.getId());
        preparedStatement.setString(2, book.getName());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, book.getAuthorEmail());
        preparedStatement.setString(5,book.getGenre().name());
        preparedStatement.setDouble(6, book.getPrice());
        preparedStatement.setDate(7,new Date(book.getCreatedOn().getTime()));
        preparedStatement.setDate(8,new Date(book.getUpdatedOn().getTime()));

        preparedStatement.execute();

//        System.out.println(preparedStatement.execute());

    }

    public void createBook(Book book) throws SQLException {

        String query = "insert into book(name,author,author_email,genre,price,created_on,updated_on)" +
                "values(?,?,?,?,?,?,?)";

//        String query = "insert into book(name,author,author_email,genre,price,created_on,updated_on)" +
//                "values(book.getName(),book.getAuthor(),?,?,?,?,?)";

        PreparedStatement preparedStatement = this.databaseRepository.getConnection().prepareStatement(query);
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getAuthorEmail());
        preparedStatement.setString(4,book.getGenre().name());
        preparedStatement.setDouble(5, book.getPrice());
        preparedStatement.setDate(6,new Date(book.getCreatedOn().getTime()));
        preparedStatement.setDate(7,new Date(book.getUpdatedOn().getTime()));

        preparedStatement.execute();

//        System.out.println(preparedStatement.execute());

    }

    public List<Book> getAll() throws SQLException {

        String query = "select * from book";

        Statement statement = this.databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt(1));
            book.setName(resultSet.getString(2));
            book.setAuthor(resultSet.getString(3));
            book.setAuthorEmail(resultSet.getString("author_email"));
            book.setGenre(Genre.valueOf(resultSet.getString("genre")));
            book.setPrice(resultSet.getDouble("price"));
            book.setCreatedOn(resultSet.getDate("created_on"));
            book.setUpdatedOn(resultSet.getDate("updated_on"));
            books.add(book);
        }


        return books;
    }

    public Book getById(int id) throws SQLException {

        String query = "select * from book where id = ?";
        PreparedStatement preparedStatement = this.databaseRepository.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Book book = new Book();
        while (resultSet.next()) {
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString(3));
            book.setAuthorEmail(resultSet.getString("author_email"));
            book.setGenre(Genre.valueOf(resultSet.getString("genre")));
            book.setPrice(resultSet.getDouble("price"));
            book.setCreatedOn(resultSet.getDate("created_on"));
            book.setUpdatedOn(resultSet.getDate("updated_on"));
        }
        return book;
    }

    public void delete(int id) throws SQLException {

        String query = "delete from book where id = ?";
        PreparedStatement preparedStatement = this.databaseRepository.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);

        System.out.println(preparedStatement.execute());


    }

    public void update(Book existingBook,int id) throws SQLException {
        this.delete(id);
        this.createBookWithId(existingBook);
    }

}


