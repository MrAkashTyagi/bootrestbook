package com.api.book.bootrestbook.controllers;



import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;


@RestController
public class BookController {

    @Autowired
    BookService bookService;
    
    //get all books handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){

    List<Book> list = this.bookService.getAllBooks();
    if (list.size()<=0) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        return ResponseEntity.of(Optional.of(list));
    // return this.bookService.getAllBooks();
    }

    //get single book handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){

    Book book = bookService.getBookById(id);
    if (book==null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        return ResponseEntity.of(Optional.of(book));
        // return bookService.getBookById(id);
    }
    
    //create book handler
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
    try{
        Book b = null;    
        b = this.bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
        
    }catch(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }    
    }

    //delete book handler
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id){
        try {
            this.bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {            
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    

    }

    //update book handler
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int bid){

        try {
            this.bookService.updateBook(book,bid);    
            return ResponseEntity.of(Optional.of(book));
        } catch (Exception e) {
            e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
