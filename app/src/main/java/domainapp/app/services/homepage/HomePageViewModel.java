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
package domainapp.app.services.homepage;

import domainapp.dom.simple.SimpleObject;
import domainapp.dom.simple.SimpleObjectRepository;
import org.apache.isis.applib.annotation.ViewModel;
import org.apache.isis.applib.services.i18n.TranslatableString;

import java.util.Collection;

@ViewModel
public class HomePageViewModel {

    @javax.inject.Inject
    SimpleObjectRepository simpleObjectRepository;
    //endregion

    //region > title
    public TranslatableString title() {
        return TranslatableString.tr("{num} objects", "num", getObjects().size());
    }
    //endregion

    //region > injected services

    //region > object (collection)
    @org.apache.isis.applib.annotation.HomePage
    public Collection<SimpleObject> getObjects() {
        return simpleObjectRepository.listAll();
    }

    //endregion
}
