package org.openstack.atlas.api.resources;

import org.openstack.atlas.docs.loadbalancers.api.v1.LoadBalancer;
import org.openstack.atlas.docs.loadbalancers.api.v1.accesslist.AccessList1;
import org.openstack.atlas.docs.loadbalancers.api.v1.accesslist.NetworkItem1;
import org.w3c.dom.Element;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.List;
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

        AccessList1 accessList1 = (AccessList1) getAny(loadBalancer);
        if(accessList1 == null) {
            logger.log(Level.INFO, "No accesslist found");
        } else {
            for(NetworkItem1 networkItem1 : accessList1.getNetworkItem1s()) {
                logger.log(Level.INFO, "Network Item1: " + networkItem1.getAddress() + " : " + networkItem1.getType1());
            }
        }

        return Response.status(Response.Status.OK).entity(loadBalancer).build();
    }

    private Object getAny(LoadBalancer loadBalancer) {
        List<Object> anies = loadBalancer.getAnies();
        for (Object any : anies) {
            logger.log(Level.INFO, "Class: " + any.getClass());
            if (any instanceof Element) {
                Element element = (Element) any;
                try {
                    JAXBContext jc = JAXBContext.newInstance("org.openstack.atlas.docs.loadbalancers.api.v1.accesslist");
                    Unmarshaller unmarshaller = jc.createUnmarshaller();
                    Object o = unmarshaller.unmarshal(element);
                    if(o instanceof  AccessList1) {
                        AccessList1 list1 = (AccessList1) o;
                        List<NetworkItem1> ns = list1.getNetworkItem1s();
                        return list1;
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setRequestHeaders(HttpHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
    }
}
