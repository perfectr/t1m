package nz.govt.doc.t1m.domain.instance.litterLarge;

import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;
import nz.govt.doc.t1m.domain.instance.InstanceEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by McCaulC on 27/01/2015.
 */
@Entity
public class LLItemEntity extends InstanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer instanceId;
    @NotNull
    private Integer dataSheetId;

    public LLItemEntity(Integer dataSheetId) {
        this.dataSheetId = dataSheetId;
    }

    public Integer getInstanceId() {
        return instanceId;
    }

    public Integer getDataSheetId() {
        return dataSheetId;
    }
}
