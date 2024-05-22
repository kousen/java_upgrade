package streams;

public sealed interface Result<T>
    permits Success, Failure{
}

record Success<T>(T value) implements Result<T> { }

record Failure<T>(String message) implements Result<T> { }
