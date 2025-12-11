package filtres;


import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
 *
 * @Retention: permet de déterminer de quelle façon l'annotation pourra être exploitée.
 *
 * RetentionPolicy.RUNTIME: informations conservées dans le code source et le bytecode :
 * 							elles sont disponibles à l'exécution par introspection
 */

/*
 * @Target: permet de cibler sur quels éléments du langage nous pourrons utiliser des annotations.
 *
 * ElementType.TYPE: L'annotation pourra être utilisée sur une classe, une interface, une autre annotation, une énumération...$
 * 					 Tout ce qui permet au langage de définir un TYPE.
 *
 * ElementType.METHOD:  ceci indique que l'annotation n'est utilisable que sur des méthodes
 */

@NameBinding
@Retention(RetentionPolicy.RUNTIME)

//L'annotation pourra être utilisée sur une classe, une interface, etc ET aussi bien sur des méthodes
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Secured {
}
