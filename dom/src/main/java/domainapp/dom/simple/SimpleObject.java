/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.dom.simple;

import domainapp.dom.ColumnAllowsNull;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.eventbus.PropertyDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.util.ObjectContracts;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

@javax.jdo.annotations.PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "simple"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = "id")
@javax.jdo.annotations.Version(
        strategy = VersionStrategy.DATE_TIME,
        column = "version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByName",
                value = "SELECT FROM domainapp.dom.simple.SimpleObject WHERE name.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.Unique(name = "SimpleObject_name_UNQ", members = {"name"})
@DomainObject(
        publishing = Publishing.ENABLED,
        auditing = Auditing.ENABLED
)
public class SimpleObject implements Comparable<SimpleObject> {

    //region > name (read-only property)
    public static final int NAME_LENGTH = 40;
    //endregion
    //region > notes (editable property)
    public static final int NOTES_LENGTH = 4000;

    @javax.inject.Inject
    RepositoryService repositoryService;
    //endregion
    @javax.inject.Inject
    TitleService titleService;
    @javax.inject.Inject
    MessageService messageService;

    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.FALSE, length = NAME_LENGTH)
    private String name;
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.TRUE, length = NOTES_LENGTH)
    private String notes;
    @javax.jdo.annotations.Column(allowsNull = ColumnAllowsNull.TRUE)
    private Integer birthYear;
    //endregion

    //region > constructor
    public SimpleObject() {
        setName("");
    }

    public SimpleObject(final String name) {
        setName(name);
    }

    //region > title
    public TranslatableString title() {
        return TranslatableString.tr("Object: {name}", "name", getName());
    }

    @Property(
            editing = Editing.ENABLED,
            publishing = Publishing.ENABLED
    )
    public String getName() {
        return name;
    }

    //endregion

    public void setName(final String name) {
        this.name = name;
    }

    @Action(
            command = CommandReification.ENABLED,
            publishing = Publishing.ENABLED,
            semantics = SemanticsOf.IDEMPOTENT,
            domainEvent = UpdateNameDomainEvent.class
    )
    public SimpleObject updateName(@ParameterLayout(named = "Name") final String name) {
        setName(name);
        return this;
    }

    public String default0UpdateName() {
        return getName();
    }

    public TranslatableString validate0UpdateName(final String name) {
        return name != null && name.contains("!") ? TranslatableString.tr("Exclamation mark is not allowed") : null;
    }

    @Property(
            command = CommandReification.ENABLED,
            publishing = Publishing.ENABLED,
            domainEvent = NotesDomainEvent.class
    )
    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    @Property(
            command = CommandReification.ENABLED,
            publishing = Publishing.ENABLED,
            domainEvent = BirthYearDomainEvent.class
    )
    public Integer getBirthYear() {
        return birthYear;
    }
    //endregion

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    @Action(
            domainEvent = DeleteDomainEvent.class,
            semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE
    )
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.remove(this);
    }

    //endregion

    //region > toString, compareTo
    @Override
    public String toString() {
        return ObjectContracts.toString(this, "name");
    }

    @Override
    public int compareTo(final SimpleObject other) {
        return ObjectContracts.compare(this, other, "name");
    }

    //endregion

    //region > injected dependencies

    //region > updateName (action)
    public static class UpdateNameDomainEvent extends ActionDomainEvent<SimpleObject> {
    }

    public static class NotesDomainEvent extends PropertyDomainEvent<SimpleObject, String> {
    }

    public static class BirthYearDomainEvent extends PropertyDomainEvent<SimpleObject, Integer> {
    }

    //region > delete (action)
    public static class DeleteDomainEvent extends ActionDomainEvent<SimpleObject> {
    }

    //endregion

}
