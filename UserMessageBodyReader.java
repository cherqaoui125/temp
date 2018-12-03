package rest.book.entities.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import com.google.gson.Gson;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class UserMessageBodyReader implements MessageBodyReader<rest.book.model.User>
{
	
	private final static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(UserMessageBodyWriter.class);
	
	@Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) 
	{
        
		return type == rest.book.model.User.class;
        
    }

    @Override
    public rest.book.model.User readFrom(Class<rest.book.model.User> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException 
    {

    	// Variables
    	String jsonObject="";
    	java.io.BufferedReader buf=null;
    	Gson gson=null;
    	rest.book.model.User user=null;
    	
        try 
        {
        	
        	// Input stream : The JSON objet must be in one string. NOT CRLF
        	buf = new BufferedReader(new InputStreamReader(entityStream));
        	jsonObject=buf.readLine();
        	
        	logger.error(jsonObject);
        	
        	// Conversion JSON Object to Java Object
        	gson=new Gson();
        	user=gson.fromJson(jsonObject, rest.book.model.User.class); 
            
        } catch (Exception e) 
        {
           
        	// Journalisation
        	logger.error("+++ "+e.getMessage());
        	
        }finally
        {
        	
        	buf=null;
        	gson=null;
        	
        }
        
        return user;
    }
	
	

}
