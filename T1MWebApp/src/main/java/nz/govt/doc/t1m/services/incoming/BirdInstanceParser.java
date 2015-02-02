package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.services.instance.birdCount.BCBirdService;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by McCaulC on 2/02/2015.
 */
@Component
public class BirdInstanceParser {

    @Autowired
    protected BCBirdService bcBirdService;
    @Autowired
    protected BDBirdService bdBirdService;

    private String[][] instanceField;
    private String[][] instanceData;

    private int count;

    public void initialize(String[][] instanceField, String[][] instanceData) {
        this.instanceField = instanceField;
        this.instanceData = instanceData;
        this.count = 0;
    }
}
