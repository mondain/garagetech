package com.lewdlistings.search.bridge;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.spatial.geometry.LatLng;
import org.apache.lucene.spatial.tier.projections.CartesianTierPlotter;
import org.apache.lucene.spatial.tier.projections.IProjector;
import org.apache.lucene.spatial.tier.projections.SinusoidalProjector;
import org.apache.lucene.util.NumericUtils;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import java.util.LinkedList;
import java.util.List;

/**
 * Cartesian Tier Plotter to work with hibernate search
 */
public class CartesianTierFieldBridgeImpl implements FieldBridge {

    // TODO: Update this as lucene updates it spatial search packages
    private static IProjector projector = new SinusoidalProjector();
    private static CartesianTierPlotter cartesianTierPlotter = new CartesianTierPlotter(0, projector, CartesianTierPlotter.DEFALT_FIELD_PREFIX);
    private static double maxMiles = 250, minMiles = 1;
    private static int startTier = cartesianTierPlotter.bestFit(maxMiles), endTier = cartesianTierPlotter.bestFit(minMiles);
    private static List<CartesianTierPlotter> plotters = new LinkedList<CartesianTierPlotter>();

    static {
        setUpPlotter(startTier, endTier);
    }

    private static void setUpPlotter(int base, int top) {
        for (; base <= top; base++) {
            plotters.add(new CartesianTierPlotter(base, projector, CartesianTierPlotter.DEFALT_FIELD_PREFIX));
        }
    }

    @Override
    public void set(String name, Object value, Document document, LuceneOptions options) {
        LatLng latLng = (LatLng) value;
        if (latLng == null) return;
        for (int i = 0; i < plotters.size(); i++) {
            CartesianTierPlotter ctp = plotters.get(i);
            document.add(new Field(ctp.getTierFieldName(),
                    NumericUtils.doubleToPrefixCoded(ctp.getTierBoxId(latLng.getLat(), latLng.getLng())),
                    Field.Store.YES,
                    Field.Index.NOT_ANALYZED_NO_NORMS));
        }
    }

}
