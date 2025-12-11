package webservices;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/*
 *
 * Si vous avez besoin d’obtenir des informations sur le contexte d’exécution de la requête,
 * vous pouvez utilisez l’annotation @javax.ws.rs.core.Context pour obtenir une instance d’une classe particulière.
 * UriInfo : Cette interface donne accès à l’URI de la requête.
 *
 */


@Path("/authentification")
public class AuthenticationEndPoint {

    // ======================================
    // = Injection Points =
    // ======================================

    @Context
    private UriInfo uriInfo;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)

    // @Produces(MediaType.TEXT_PLAIN)
    // @Consumes(MediaType.APPLICATION_JSON)

    //// this is a first alternative (with formParam)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {
        //// this is a second alternative (with the Credentials class)
        // public Response authenticateUser(Credentials cred) {
        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);
            // authenticate(cred.getUsername(), cred.getPassword());

            // Issue a token for the user //2
            String token = issueToken(username);
            // String token = issueToken(cred.getUsername());

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(String username, String password) {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        System.out.println("Authenticating user...");

    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token

        String keyString = "simplekey";
        Key key = new SecretKeySpec(keyString.getBytes(), 0,
                keyString.getBytes().length, "DES");
        System.out.println("the key is : " + key.hashCode());

        System.out.println("uriInfo.getAbsolutePath().toString() : " + uriInfo.getAbsolutePath().toString());
        System.out.println("Expiration date: " + toDate(LocalDateTime.now().plusMinutes(15L)));


        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        System.out.println("the returned token is : " + jwtToken);

        return jwtToken;
    }

    // ======================================
    // = Private methods =
    // ======================================

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}

