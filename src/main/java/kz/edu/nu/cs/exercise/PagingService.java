package kz.edu.nu.cs.exercise;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/items")
public class PagingService {

    private List<String> list = new CopyOnWriteArrayList<String>();

    public PagingService() {
        for (int i = 0; i < 100; i++) {
            list.add("Entry " + String.valueOf(i));
        }
    }

    @GET
    public Response getMyList(@QueryParam("size") int size, @QueryParam("offset") int offset) {
        Gson gson = new Gson();
        String json;
        
        PagedHelper p = new PagedHelper();
        if(offset + size <= list.size()) {
            p.setNext("Next page ");
        }
        if(offset > 0) {
            p.setPrev("Prev page ");
        }
        if (size == 0) {
            p.setList(list);
        } else if (offset + size <= list.size() && offset >= 0){
            p.setList(list.subList(offset, offset + size));
        }
        
        
        
        json = gson.toJson(p, PagedHelper.class);
        
        return Response.ok(json).build();
    }
}
