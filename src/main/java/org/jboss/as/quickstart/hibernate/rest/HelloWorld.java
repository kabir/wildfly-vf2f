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

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.as.quickstart.hibernate.model.Greeting;
import org.jboss.as.quickstart.hibernate.model.GreetingType;

/**
 * A simple REST service which is able to say hello to someone using HelloService Please take a look at the web.xml where JAX-RS
 * is enabled
 *
 * @author gbrey@redhat.com
 *
 */

@Path("/rest")
public class HelloWorld {
//    @Inject
//    EntityManager entityManager;

    @GET
    //@Produces({ "application/json" })
    public List<Greeting> getAllGreetings() {
        Greeting greeting = new Greeting();
        greeting.setId(1L);
        greeting.setGreeting("Hahahaha");
        greeting.setType(GreetingType.JOKE);
        greeting.setManuallyApproved(false);
        return Collections.singletonList(greeting);
    }

}
