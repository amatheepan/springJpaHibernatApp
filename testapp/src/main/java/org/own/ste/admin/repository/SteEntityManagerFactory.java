/**
 * 
 */
package org.own.ste.admin.repository;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 * Proxy for Entity Manager Factory.
 * 
 * @author ramugadde.
 * 
 */
public class SteEntityManagerFactory implements EntityManagerFactory {

    private Map<String, EntityManagerFactory> emFactories = new HashMap<String, EntityManagerFactory>();
    
    private String defaultPersistenceUnit;

    /**
     * Overloaded Constructor.
     * 
     * @param emFactories
     *            List<String> - Names of the persistence units.
     * @param defaultPersistenceUnit String - The default persistence unit.
     */
    public SteEntityManagerFactory(Map<String, EntityManagerFactory> emFactories,String defaultPersistenceUnit) {
        this.emFactories = emFactories;
        this.defaultPersistenceUnit=defaultPersistenceUnit;
    }

    /**
     * 
     * @return EntityManagerFactory - The factory which should be used for this
     *         request.
     */
    private EntityManagerFactory getThreadLocalEntityManagerFactory() {
        String currPersistenceUnit = (String) TransactionSynchronizationManager
                .getResource("currentPersistence");
        if (currPersistenceUnit == null) {
            currPersistenceUnit = defaultPersistenceUnit;
        }
        return emFactories.get(currPersistenceUnit);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.persistence.EntityManagerFactory#addNamedEntityGraph(java.lang.
     * String, javax.persistence.EntityGraph)
     */
    @Override
    public <T> void addNamedEntityGraph(String arg0, EntityGraph<T> arg1) {
        this.getThreadLocalEntityManagerFactory().addNamedEntityGraph(arg0,
                arg1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.persistence.EntityManagerFactory#addNamedQuery(java.lang.String,
     * javax.persistence.Query)
     */
    @Override
    public void addNamedQuery(String arg0, Query arg1) {
        this.getThreadLocalEntityManagerFactory().addNamedQuery(arg0, arg1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#close()
     */
    @Override
    public void close() {
        this.getThreadLocalEntityManagerFactory().close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#createEntityManager()
     */
    @Override
    public EntityManager createEntityManager() {
        return this.getThreadLocalEntityManagerFactory().createEntityManager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.persistence.EntityManagerFactory#createEntityManager(java.util.Map)
     */
    @Override
    public EntityManager createEntityManager(Map arg0) {
        return this.getThreadLocalEntityManagerFactory().createEntityManager(
                arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.persistence.EntityManagerFactory#createEntityManager(javax.persistence
     * .SynchronizationType)
     */
    @Override
    public EntityManager createEntityManager(SynchronizationType arg0) {
        return this.getThreadLocalEntityManagerFactory().createEntityManager(
                arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.persistence.EntityManagerFactory#createEntityManager(javax.persistence
     * .SynchronizationType, java.util.Map)
     */
    @Override
    public EntityManager createEntityManager(SynchronizationType arg0, Map arg1) {
        return this.getThreadLocalEntityManagerFactory().createEntityManager(
                arg0, arg1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#getCache()
     */
    @Override
    public Cache getCache() {
        return this.getThreadLocalEntityManagerFactory().getCache();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#getCriteriaBuilder()
     */
    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return this.getThreadLocalEntityManagerFactory().getCriteriaBuilder();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#getMetamodel()
     */
    @Override
    public Metamodel getMetamodel() {
        return this.getThreadLocalEntityManagerFactory().getMetamodel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#getPersistenceUnitUtil()
     */
    @Override
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        return this.getThreadLocalEntityManagerFactory()
                .getPersistenceUnitUtil();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#getProperties()
     */
    @Override
    public Map<String, Object> getProperties() {
        return this.getThreadLocalEntityManagerFactory().getProperties();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#isOpen()
     */
    @Override
    public boolean isOpen() {
        return this.getThreadLocalEntityManagerFactory().isOpen();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.persistence.EntityManagerFactory#unwrap(java.lang.Class)
     */
    @Override
    public <T> T unwrap(Class<T> arg0) {
        return this.getThreadLocalEntityManagerFactory().unwrap(arg0);
    }

}
