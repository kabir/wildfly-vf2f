/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstart.hibernate.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.jboss.as.quickstart.hibernate.model.Greeting;

/**
 * A simple REST service which is able to say hello to someone using HelloService Please take a look at the web.xml where JAX-RS
 * is enabled
 *
 * @author gbrey@redhat.com
 *
 */

@Path("/")
public class HelloWorld {
    @PersistenceContext(unitName = "primary")
    EntityManager em;

    @GET
    @Produces({ "application/json" })
    public List<Greeting> getAllGreetings() {
        // using Hibernate Session and Criteria Query via Hibernate Native API
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Greeting> query = cb.createQuery(Greeting.class);
        Root<Greeting> greetings = query.from(Greeting.class);
        query.where(cb.equal(greetings.get("manuallyApproved"), false));
        return em.createQuery(query).getResultList();
    }

    // TODO refactor to @DELETE
    @GET
    @Path("delete/{id}")
    @Transactional
    @Counted
    public Response delete(@PathParam("id") Long id) {
        Greeting greeting = em.find(Greeting.class, id);
        if (greeting != null) {
            em.remove(greeting);
        }
        // Wrong response code but we get the idea
        return Response.ok().build();
    }

    @GET
    @Path("approve/{id}")
    @Transactional
    @Counted
    public Response approve(@PathParam("id") Long id) {
        Greeting greeting = em.find(Greeting.class, id);
        if (greeting != null) {
            greeting.setManuallyApproved(true);
            em.persist(greeting);
        }
        // Wrong response code but we get the idea
        return Response.ok().build();
    }

}
