package org.openstack.atlas.api.resources;

import org.openstack.atlas.docs.loadbalancers.api.v1.LoadBalancer;
import org.openstack.atlas.docs.loadbalancers.api.v1.NetworkItem;
import org.openstack.atlas.docs.loadbalancers.api.v1.accesslist.AccessList1;
import org.openstack.atlas.docs.loadbalancers.api.v1.accesslist.NetworkItem1;
import org.w3c.dom.Element;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.validation.SchemaFactory;
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

        List<Object> anies = loadBalancer.getAnies();
        for(Object any : anies) {
            logger.log(Level.INFO, "Class: " + any.getClass());
            if(any instanceof Element) {
                Element element = (Element)any;
                String name = element.getFirstChild().getNodeName();
                String value = element.getFirstChild().getNodeValue();
            }
        }
        return null;
    }

    public static String translateRealCst(Object o){
		String ret = "";
		if(o instanceof Element){
			Element e = (Element)o;
			ret = ret + e.getFirstChild().getNodeValue().trim();
		}
		return ret;
	}

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setRequestHeaders(HttpHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
    }
}
