package com.lewdlistings.search;

import com.lewdlistings.entity.Location;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.spatial.geohash.GeoHashUtils;
import org.apache.lucene.spatial.tier.projections.CartesianTierPlotter;
import org.apache.lucene.spatial.tier.projections.IProjector;
import org.apache.lucene.spatial.tier.projections.SinusoidalProjector;

import static org.apache.lucene.document.Field.Index.ANALYZED;
import static org.apache.lucene.document.Field.Index.NOT_ANALYZED;
import static org.apache.lucene.document.Field.Store.YES;
import static org.apache.lucene.util.NumericUtils.doubleToPrefixCoded;

public class Spatial {

    public static final String GEOHASH_FIELD = "geohash";
    public static final String LAT_FIELD = "latitude";
    public static final String LON_FIELD = "longitude";
    public static final String ZIP_FIELD = "zipcode";

    private final IProjector projector = new SinusoidalProjector();
    private CartesianTierPlotter ctp = new CartesianTierPlotter(0, projector, CartesianTierPlotter.DEFALT_FIELD_PREFIX);
    private double maxMiles = 250, minMiles = 1;
    private int startTier = ctp.bestFit(maxMiles), endTier = ctp.bestFit(minMiles);

    public void addLocation(final IndexWriter writer, final Location location) throws Exception {
        final Document document = new Document();
        document.add(new Field(ZIP_FIELD, Integer.toString(location.getZipCode()), YES, ANALYZED));
        document.add(new Field(GEOHASH_FIELD, GeoHashUtils.encode(location.getLatitude(), location.getLongitude()), YES, NOT_ANALYZED));
        document.add(new Field(LAT_FIELD, doubleToPrefixCoded(location.getLatitude()), YES, NOT_ANALYZED));
        document.add(new Field(LON_FIELD, doubleToPrefixCoded(location.getLongitude()), YES, NOT_ANALYZED));
        for (int tier = startTier; tier <= endTier; tier++) {
            ctp = new CartesianTierPlotter(tier, projector, CartesianTierPlotter.DEFALT_FIELD_PREFIX);
            double boxId = ctp.getTierBoxId(location.getLatitude(), location.getLongitude());
            document.add(new Field(ctp.getTierFieldName(), doubleToPrefixCoded(boxId), YES, Field.Index.NOT_ANALYZED_NO_NORMS));
        }
        writer.addDocument(document);
    }

    public int getStartTier() {
        return startTier;
    }

    public int getEndTier() {
        return endTier;
    }
}
