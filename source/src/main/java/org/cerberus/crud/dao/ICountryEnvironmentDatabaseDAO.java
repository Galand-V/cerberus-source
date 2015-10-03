/* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This file is part of Cerberus.
 *
 * Cerberus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cerberus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cerberus.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cerberus.crud.dao;

import java.util.List;
import org.cerberus.crud.entity.CountryEnvironmentDatabase;
import org.cerberus.exception.CerberusException;

/**
 * {Insert class description here}
 *
 * @author Tiago Bernardes
 * @version 1.0, 31/12/2012
 * @since 2.0.0
 */
public interface ICountryEnvironmentDatabaseDAO {

    CountryEnvironmentDatabase findCountryEnvironmentDatabaseByKey(String system, String country, String environment, String database) throws CerberusException;

    /**
     * Find all countryEnvironmentDatabase by System
     * @param system
     * @return
     * @throws CerberusException 
     */
    List<CountryEnvironmentDatabase> findAll(String system) throws CerberusException;
    
    /**
     * Update countryEnvironmentDatabase
     * @param ced
     * @throws CerberusException 
     */
    void update(CountryEnvironmentDatabase ced) throws CerberusException;
    
    /**
     * Delete countryEnvironmentDatabase
     * @param ced
     * @throws CerberusException 
     */
    void delete(CountryEnvironmentDatabase ced) throws CerberusException;
    
    /**
     * Create countryEnvironmentDatabase
     * @param ced
     * @throws CerberusException 
     */
    void create(CountryEnvironmentDatabase ced) throws CerberusException;

    public List<CountryEnvironmentDatabase> findListByCriteria(String system, String country, String environment) throws CerberusException;
}