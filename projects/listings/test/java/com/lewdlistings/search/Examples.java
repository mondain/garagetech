package com.lewdlistings.search;

import com.lewdlistings.entity.Location;
import org.apache.lucene.index.IndexWriter;

public class Examples {

    public static final Location SUNNYVALE_94085 = new Location(94085, 37.3895, -122.018);
    public static final Location SUNNYVALE_94086 = new Location(94086, 37.3719, -122.021);
    public static final Location SUNNYVALE_94087 = new Location(94087, 37.3492, -122.033);
    public static final Location SUNNYVALE_94089 = new Location(94089, 37.4106, -122.004);
    public static final Location SUNNYVALE_94090 = new Location(94090, 37.3701, -122.03);

    public static void createSampleLocations(IndexWriter writer, Spatial spatial) throws Exception {
        spatial.addLocation(writer, SUNNYVALE_94085);
        spatial.addLocation(writer, SUNNYVALE_94086);
        spatial.addLocation(writer, SUNNYVALE_94087);
        spatial.addLocation(writer, SUNNYVALE_94089);
        spatial.addLocation(writer, SUNNYVALE_94090);
    }

    private Examples() {}
}
