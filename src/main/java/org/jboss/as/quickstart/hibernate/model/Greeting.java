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
package org.jboss.as.quickstart.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JPA Entity for the ModelHibernateDemo table
 * <p>
 * <p>
 * Also uses Hibernate Validators
 * </p>
 */

@Entity
@XmlRootElement
@Table(name = "Greeting", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Greeting implements Serializable {
    /**
     * Default value included to remove warning. Remove or modify at will.
     **/
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 512)
    private String greeting;

    private Type type;

    @NotNull
    @Column(name = "manually_aproved")
    private boolean manuallyApproved;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isManuallyApproved() {
        return manuallyApproved;
    }

    public void setManuallyApproved(boolean manuallyApproved) {
        this.manuallyApproved = manuallyApproved;
    }
}

