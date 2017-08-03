package net.bingosoft.oss.ssoserver.console.controller;

import leap.core.doc.annotation.Doc;
import leap.web.annotation.Path;
import leap.web.annotation.http.DELETE;
import leap.web.annotation.http.GET;
import leap.web.annotation.http.PATCH;
import leap.web.annotation.http.POST;
import leap.web.api.mvc.ApiResponse;
import leap.web.api.mvc.ModelController;
import leap.web.api.mvc.params.Partial;
import leap.web.api.mvc.params.QueryOptions;
import net.bingosoft.oss.ssoserver.console.model.Client;

import java.util.List;

/**
 * @author kael.
 */
@Path("/console/client")
public class ClientController extends ModelController<Client> {
    @POST
    @Doc("创建应用")
    public ApiResponse<Client> createClient(Partial<Client> partial){
        return createAndReturn(partial);
    }
    @GET("/{id}")
    @Doc("获取应用")
    public ApiResponse<Client> getClient(String id){
        return get(id);
    }
    @PATCH("/{id}")
    @Doc("修改应用")
    public ApiResponse<Client> updateClient(Partial<Client> partial, String id){
        return updatePartial(id,partial);
    }
    @DELETE("/{id}")
    @Doc("删除应用")
    public ApiResponse deleteClient(String id){
        return delete(id);
    }
    @GET
    @Doc("查找应用")
    public ApiResponse<List<Client>> queryClients(QueryOptions options){
        return queryList(options);
    }
    
}
