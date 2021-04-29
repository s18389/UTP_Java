import java.nio.file.Path;

@FunctionalInterface
public interface FilePredicate {
    boolean test(Path path, String searchParameter) ;
}
