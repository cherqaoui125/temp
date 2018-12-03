import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces("text/html")
public class SampleMessageBodyWriter implements MessageBodyWriter<User> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return type == User.class;
    }

    @Override
    public long getSize(User user, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
         return -1;
    }

    @Override
    public void writeTo(User user, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream out) throws IOException, WebApplicationException {

        Writer writer = new PrintWriter(out);
        writer.write("<html>");
        writer.write("<body>");
        writer.write("<h2>JAX-RS Message Body Writer Example</h2>");
        writer.write("<p>Id: " + user.getId() + "</p>");
        writer.write("<p>Name: " + user.getName() + "</p>");
        writer.write("</body>");
        writer.write("</html>");

        writer.flush();
        writer.close();
    }
}