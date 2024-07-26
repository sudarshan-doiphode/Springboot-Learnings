# Getting Started with GraphQL and Spring Boot

GraphQL is a powerful query language for APIs and a runtime for executing those queries by using a type system you define for your data. It enables clients to request exactly the data they need, making it efficient and flexible.

This guide will cover the basics of GraphQL, including its core concepts such as queries, types, and mutations. It will also introduce the annotations used in Spring Boot GraphQL applications to help you get started.

## What is GraphQL?

GraphQL is a query language for your API, which provides a more efficient, powerful, and flexible alternative to REST. It allows clients to:
- Request only the data they need.
- Combine multiple API calls into a single request.
- Retrieve data in a hierarchical and more understandable structure.

### Core Concepts

1. **Query**: The operation to read or fetch values.
2. **Mutation**: The operation to write or post values.
3. **Type**: The structure that defines the shape of the data.
4. **Schema**: A description of all the types and operations that can be executed by the GraphQL API.
5. **Resolver**: The functions responsible for populating the data for a single field in the schema.

## GraphQL Components

### Type
A type in GraphQL defines the shape of an object. It describes what fields are available in the object and what their types are.

```graphql
type Book {
    id: ID!
    title: String!
    author: String!
}
```

### Query
A query is used to fetch data from the server. It specifies what data is needed and in what format.

```graphql
type Query {
    allBooks: [Book]
    bookById(id: ID!): Book
}
```

### Mutation
A mutation is used to modify data on the server. It allows you to create, update, or delete data.

```graphql
type Mutation {
    saveBook(title: String!, author: String!): Book
    deleteBook(id: ID!): Boolean
}
```

## Annotations in Spring Boot GraphQL

Spring Boot provides several annotations to help define GraphQL queries and mutations in your application.

### @QueryMapping
This annotation is used to map GraphQL queries to methods in your Spring Boot application.

```java
@QueryMapping
public List<Book> getAllBooks() {
    return bookService.getAllBooks();
}
```

### @MutationMapping
This annotation is used to map GraphQL mutations to methods in your Spring Boot application.

```java
@MutationMapping
public Book saveBook(@Argument String title, @Argument String author) {
    return bookService.saveBook(new Book(title, author));
}
```

### @Argument
This annotation is used to bind method parameters to GraphQL arguments.

```java
@QueryMapping
public Book getBookById(@Argument Long id) {
    return bookService.getBookById(id);
}
```

### @SchemaMapping
This annotation is used to map GraphQL schema types to methods in your Spring Boot application.

```java
@SchemaMapping(typeName = "Book")
public String getTitle(Book book) {
    return book.getTitle();
}
```

### @GraphQLController
This annotation is used to designate a Spring component as a GraphQL controller.

```java
@GraphQLController
public class BookController {
    // Your methods here
}
```

## Example Application

Here's a step-by-step guide to building a simple GraphQL API with Spring Boot:

1. **Setup Spring Boot Project**: Use Spring Initializr to create a new project with dependencies: Spring Web, Spring Data JPA, GraphQL Spring Boot Starter, H2 Database.

2. **Define the Entity**:
    ```java
    @Entity
    public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String author;

        // Getters and setters
    }
    ```

3. **Create Repository**:
    ```java
    public interface BookRepository extends JpaRepository<Book, Long> {}
    ```

4. **Create Service**:
    ```java
    @Service
    public class BookService {
        @Autowired
        private BookRepository bookRepository;

        public List<Book> getAllBooks() {
            return bookRepository.findAll();
        }

        public Book getBookById(Long id) {
            return bookRepository.findById(id).orElse(null);
        }

        public Book saveBook(Book book) {
            return bookRepository.save(book);
        }

        public void deleteBook(Long id) {
            bookRepository.deleteById(id);
        }
    }
    ```

5. **Create Controller**:
    ```java
    @GraphQLController
    public class BookController {
        @Autowired
        private BookService bookService;

        @QueryMapping
        public List<Book> getAllBooks() {
            return bookService.getAllBooks();
        }

        @QueryMapping
        public Book getBookById(@Argument Long id) {
            return bookService.getBookById(id);
        }

        @MutationMapping
        public Book saveBook(@Argument String title, @Argument String author) {
            return bookService.saveBook(new Book(title, author));
        }

        @MutationMapping
        public Boolean deleteBook(@Argument Long id) {
            bookService.deleteBook(id);
            return true;
        }
    }
    ```

6. **GraphQL Schema**:
   Create a file `schema.graphqls` in `src/main/resources`:
    ```graphql
    type Book {
        id: ID!
        title: String!
        author: String!
    }

    type Query {
        allBooks: [Book]
        bookById(id: ID!): Book
    }

    type Mutation {
        saveBook(title: String!, author: String!): Book
        deleteBook(id: ID!): Boolean
    }
    ```

7. **Run the Application**: Use your IDE or run `mvn spring-boot:run` to start the application.

8. **Test Your API**: Access the GraphQL Playground at `http://localhost:8080/playground` and execute the following queries and mutations.

    - **Get All Books**:
    ```graphql
    {
        allBooks {
            id
            title
            author
        }
    }
    ```

    - **Save a Book**:
    ```graphql
    mutation {
        saveBook(title: "GraphQL for Beginners", author: "John Doe") {
            id
            title
            author
        }
    }
    ```

    - **Get Book by ID**:
    ```graphql
    {
        bookById(id: 1) {
            id
            title
            author
        }
    }
    ```

    - **Delete a Book**:
    ```graphql
    mutation {
        deleteBook(id: 1)
    }
    ```

## Conclusion

This guide introduced you to the basics of GraphQL and how to set up a simple GraphQL API using Spring Boot. By understanding core concepts like queries, mutations, and types, and by leveraging annotations provided by Spring Boot, you can build flexible and efficient APIs that serve only the data clients need. Explore more advanced features and customize your GraphQL API to suit your application's requirements.