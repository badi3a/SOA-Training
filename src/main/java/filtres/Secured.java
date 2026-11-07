package filtres;


import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)

//L'annotation pourra être utilisée sur une classe, une interface, etc ET aussi bien sur des méthodes
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Secured {

}
