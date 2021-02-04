package com.demo.springdoc.controller;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.demo.springdoc.exception.BookNotFoundException;
import com.demo.springdoc.model.Book;
import com.demo.springdoc.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@Tag(name = "book-api", description = "本関連の管理API")
public class BookController {

    @Autowired
    private BookRepository repository;

    // @formatter:off
    @Operation(
        summary = "idより本を取得",
        description = "***注意*** - 注意すべき内容を記載します。"
    )
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "本を見つけた", 
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "idは無効です", content = @Content), 
            @ApiResponse(responseCode = "404", description = "指定した本が見つかりません", content = @Content) }) // @formatter:on
    @GetMapping("/{id}")
    public Book findById(@Parameter(description = "検索したい本のid") @PathVariable long id) {
        return repository.findById(id)
            .orElseThrow(() -> new BookNotFoundException());
    }

    @Operation(summary = "本の一覧を取得")
    @GetMapping("/")
    public Collection<Book> findBooks() {
        return repository.getBooks();
    }

    @Operation(summary = "ページNoにより、本の一覧を取得")
    @GetMapping("/filter")
    public Page<Book> filterBooks(@ParameterObject Pageable pageable) {
        return repository.getBooks(pageable);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "本の情報を更新")
    public Book updateBook(@PathVariable("id") final String id, @RequestBody final Book book) {
        return book;
    }

    @Operation(summary = "本の情報を更新")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book patchBook(@PathVariable("id") final String id, @RequestBody final Book book) {
        return book;
    }

    @Operation(summary = "本を追加")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book postBook(@NotNull @Valid @RequestBody final Book book) {
        return book;
    }

    @Operation(summary = "本のタイトルのみを取得")
    @RequestMapping(method = RequestMethod.HEAD, value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Book headBook() {
        return new Book();
    }

    @Operation(summary = "idより本を削除")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public long deleteBook(@PathVariable final long id) {
        return id;
    }
}