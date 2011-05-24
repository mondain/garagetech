package com.lewdlistings.search;

import com.lewdlistings.entity.Location;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.spatial.tier.DistanceQueryBuilder;
import org.apache.lucene.spatial.tier.projections.CartesianTierPlotter;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class HibernateSpatialSearchTest {

    private static final int BATCH_SIZE = 25;

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "test-app-config.xml" });
        sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        //populateIndex();
    }

    @Test
    @SuppressWarnings({"unchecked"})
    public void testQueryLocations() {
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(Location.class);
        List<Location> locations = crit.list();
        Assert.assertTrue("Query should have returned some results", !locations.isEmpty());
        Assert.assertEquals(35587, locations.size());
    }

    @Test
    @SuppressWarnings({"unchecked"})
    public void testHibernateSearchLatLong() {
        Spatial spatial = new Spatial();
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Location location = Examples.SUNNYVALE_94085;
        DistanceQueryBuilder builder = new DistanceQueryBuilder(
                location.getLatitude(),
                location.getLongitude(),
                25, 
                Spatial.LAT_FIELD,
                Spatial.LON_FIELD,
                CartesianTierPlotter.DEFALT_FIELD_PREFIX,
                true,
                spatial.getStartTier(),
                spatial.getEndTier());
        FullTextQuery query = fullTextSession.createFullTextQuery(builder.getQuery(new MatchAllDocsQuery()));
        List<Location> locations = query.list();
        Assert.assertTrue(locations.size() > 0);
        Assert.assertEquals(166, locations.size());
    }

    @Test
    public void testHibernateSearchGeohash() {
        Spatial spatial = new Spatial();
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Location location = Examples.SUNNYVALE_94085;
        DistanceQueryBuilder builder = new DistanceQueryBuilder(
                location.getLatitude(),
                location.getLongitude(),
                25,
                Spatial.GEOHASH_FIELD,
                CartesianTierPlotter.DEFALT_FIELD_PREFIX,
                true,
                spatial.getStartTier(),
                spatial.getEndTier());
        FullTextQuery query = fullTextSession.createFullTextQuery(builder.getQuery(new MatchAllDocsQuery()));
        List<Location> locations = query.list();
        Assert.assertTrue(locations.size() > 0);
        Assert.assertEquals(166, locations.size());
    }

    private static void populateIndex() {
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.setFlushMode(FlushMode.MANUAL);
        fullTextSession.setCacheMode(CacheMode.IGNORE);
        Transaction transaction = fullTextSession.beginTransaction();
        //Scrollable results will avoid loading too many objects in memory
        ScrollableResults results = fullTextSession.createCriteria(Location.class)
            .setFetchSize(BATCH_SIZE)
            .scroll(ScrollMode.FORWARD_ONLY);
        int index = 0;
        System.out.print("Indexing...");
        while( results.next() ) {
            index++;
            fullTextSession.index( results.get(0) ); //index each element
            if (index % BATCH_SIZE == 0) {
                fullTextSession.flushToIndexes(); //apply changes to indexes
                fullTextSession.clear(); //free memory since the queue is processed
                System.out.print(".");
            }
        }
        transaction.commit();
        System.out.println();
        System.out.println("Committed " + index + " locations to index");
    }
}

