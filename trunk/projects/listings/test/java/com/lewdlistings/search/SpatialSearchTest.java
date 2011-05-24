package com.lewdlistings.search;

import com.lewdlistings.entity.Location;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.spatial.tier.DistanceQueryBuilder;
import org.apache.lucene.spatial.tier.projections.CartesianTierPlotter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpatialSearchTest {

    @Test
    public void testSpatialSearch() throws Exception {
        Spatial spatial = new Spatial();
        Directory directory = new RAMDirectory();
        IndexWriter writer = new IndexWriter(directory, new WhitespaceAnalyzer(), IndexWriter.MaxFieldLength.UNLIMITED);
        Examples.createSampleLocations(writer, spatial);
        writer.commit();
        writer.close(true);

        IndexSearcher searcher = new IndexSearcher(directory);

        List<String> latLongLocations = findByLatLong(searcher, Examples.SUNNYVALE_94085, spatial);
        Assert.assertTrue(latLongLocations.size() > 0);
        Assert.assertEquals(5, latLongLocations.size());
        Assert.assertTrue(latLongLocations.contains("94085"));
        Assert.assertTrue(latLongLocations.contains("94090"));

        List<String> geohashLocations = findByGeohash(searcher, Examples.SUNNYVALE_94085, spatial);
        Assert.assertTrue(geohashLocations.size() > 0);
        Assert.assertEquals(5, geohashLocations.size());
        Assert.assertTrue(geohashLocations.contains("94085"));
        Assert.assertTrue(geohashLocations.contains("94090"));
    }

    private List<String> findByLatLong(IndexSearcher searcher, Location start, Spatial spatial) throws Exception {
        List<String> results = new ArrayList<String>();
        DistanceQueryBuilder dq = new DistanceQueryBuilder(
                start.getLatitude(),
                start.getLongitude(),
                25,
                Spatial.LAT_FIELD,
                Spatial.LON_FIELD,
                CartesianTierPlotter.DEFALT_FIELD_PREFIX,
                true,
                spatial.getStartTier(),
                spatial.getEndTier());
        Query query = new MatchAllDocsQuery();
        TopDocs hits = searcher.search(dq.getQuery(query), 10);
        for (int i = 0; i < hits.totalHits; i++) {
            Document doc = searcher.doc(hits.scoreDocs[i].doc);
            results.add(doc.get("zipcode"));
        }
        return results;
    }

    private List<String> findByGeohash(IndexSearcher searcher, Location start, Spatial spatial) throws Exception {
        List<String> results = new ArrayList<String>();
        DistanceQueryBuilder dq = new DistanceQueryBuilder(
                start.getLatitude(),
                start.getLongitude(),
                25,
                Spatial.GEOHASH_FIELD,
                CartesianTierPlotter.DEFALT_FIELD_PREFIX,
                true,
                spatial.getStartTier(),
                spatial.getEndTier());
        Query query = new MatchAllDocsQuery();
        TopDocs hits = searcher.search(dq.getQuery(query), 10);
        for (int i = 0; i < hits.totalHits; i++) {
            Document doc = searcher.doc(hits.scoreDocs[i].doc);
            results.add(doc.get("zipcode"));
        }
        return results;
    }
}
