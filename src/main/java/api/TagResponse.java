package api;


import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.TagsRecord;


/**
 * This is an API Object.  Its purpose is to model the JSON API that we expose.
 * This class is NOT used for storing in the Database.
 *
 * This ReceiptResponse in particular is the model of a Receipt that we expose to users of our API
 *
 * Any properties that you want exposed when this class is translated to JSON must be
 * annotated with {@link JsonProperty}
 */
public class TagResponse {
    @JsonProperty
    Integer iid;

    @JsonProperty
    Integer id;

    @JsonProperty
    String tagName;

    public TagResponse(TagsRecord dbRecord) {
        this.tagName = dbRecord.getTag();
        this.id = dbRecord.getId();
        this.iid = dbRecord.getIid();
    }
}
