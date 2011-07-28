package org.openstack.atlas.api.resources;

import org.openstack.atlas.docs.loadbalancers.api.v1.LoadBalancer;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.*;

public class LoadBalancersResource {
    Logger logger = Logger.getLogger("LoadBalancersResource");

    private Integer accountId;
    private HttpHeaders requestHeaders;

    @POST
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public Response createLoadBalancer(LoadBalancer loadBalancer) {
        logger.log(Level.INFO, "loadbalancer: " + loadBalancer);

        loadBalancer.getAnies().clear();
        return Response.status(Response.Status.OK).entity(loadBalancer).build();
    }

    public void test() {

    }


    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setRequestHeaders(HttpHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
    }
}
